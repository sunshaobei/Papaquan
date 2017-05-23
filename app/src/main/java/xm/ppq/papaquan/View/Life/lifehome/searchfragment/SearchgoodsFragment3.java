package xm.ppq.papaquan.View.Life.lifehome.searchfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ScarePastAdapter;
import xm.ppq.papaquan.Bean.Scare_PastBean;
import xm.ppq.papaquan.Bean.life.SearchGoodsData1;
import xm.ppq.papaquan.Bean.life.SearchGoodsData3;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.scare_past.Scare_PastActivity;
import xm.ppq.papaquan.View.scare_buying.fragment.ScareFragMent;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchgoodsFragment3 extends Fragment {


    private View inflate;
    private View view;
    private SearchgoodsAdapter searchgoodsAdapter;
    private SharedPreferencesPotting my_login;

    public SearchgoodsFragment3() {
        // Required empty public constructor
    }

    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.backtotop)
    View backtotop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_searchgoods, container, false);
            ButterKnife.bind(this, view);
            initData();
            initListener();
            my_login = new SharedPreferencesPotting(getActivity(), "my_login");
        }
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initListener() {
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(getActivity(), 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(getActivity()));
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                searchSellerData(s, page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                searchSellerData(s, page);
            }
        });
        backtotop.setOnClickListener(v -> {
            listView.smoothScrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });

        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem>=3)
                {
                    backtotop.setVisibility(View.VISIBLE);
                }
                else {
                    backtotop.setVisibility(View.GONE);
                }

            }
        });
    }

    int page;
    String s;

    List list = new ArrayList();

    private void initData() {
        searchgoodsAdapter = new SearchgoodsAdapter<>(getActivity(), list, R.layout.scare_past_item);
        searchgoodsAdapter.setType(3);
        listView.setAdapter(searchgoodsAdapter);
        searchSellerData("", page);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (s.startsWith("2"))
        {
            s = s.replaceFirst("2","");
            searchSellerData(s, page);
            this.s = s;
        }
    }


    ArrayList<SearchGoodsData3.DataBean> list1 = new ArrayList<>();

    public void searchSellerData(String s, int page) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", 350211)
                    .put("page", page)
                    .put("length", 10)
                    .put("search", s)
                    .put("type", 3);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("life/search", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    xRefreshView.stopLoadMore();
                    xRefreshView.stopRefresh();
                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        SearchGoodsData3 dataBean = (SearchGoodsData3) JsonUtil.fromJson(o, SearchGoodsData3.class);
                        if (dataBean != null) {
                            List<SearchGoodsData3.DataBean> list = dataBean.getData();
                            if (page == 0) {
                                list1.clear();
                            }
                            list1.addAll(list);
                            searchgoodsAdapter.setList(list1);
                            searchgoodsAdapter.notifyDataSetChanged();
                        }
                        xRefreshView.stopLoadMore();
                        xRefreshView.stopRefresh();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
