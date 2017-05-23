package xm.ppq.papaquan.View.main.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.papanews.Mutual_PapaNews;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PapaNewsFragLayout extends Fragment implements Round_papa_news, UpMoreInterface {

    @BindView(R.id.news_list)
    RecyclerView news_list;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.backtotop)
    ImageView backtotop;
    @BindView(R.id.pullup)
    View pullup;

    private Mutual_PapaNews mutual_papaNews;
    private SharedPreferencesPotting potting;

    private View view;
    private LayoutInflater inflater;
    private PapanewsFragmentAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_papa_news, null);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            loadData();
            setListener();
            initRCView();
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEventBusEventSynEvent(String s) {
        if (s.equals("刷新")) {
            getActivity().runOnUiThread(() -> {
                news_list.scrollToPosition(0);
            });
            swipeRefreshLayout.setRefreshing(true);
            tid = "";
            mutual_papaNews.start(1);
        }else if (s.equals("backtotoprefresh"))
        {
            getActivity().runOnUiThread(() -> {
                news_list.scrollToPosition(0);
            });
            swipeRefreshLayout.setRefreshing(true);
            tid = "";
            mutual_papaNews.start(1);
        }
    }

    private void initRCView() {
        news_list.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new PapanewsFragmentAdapter(getActivity(), list, this, this);
        news_list.setAdapter(myAdapter);
    }

    private ArrayList<ShowNewsBigBean.Data> list = new ArrayList<>();

    public void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        mutual_papaNews = new Mutual_PapaNews(this);
        tid = "";
        mutual_papaNews.start(1);
    }

    public void setListener() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tid = "";
                mutual_papaNews.start(1);
            }
        });
        news_list.addOnScrollListener(onScrollListener);
        backtotop.setOnClickListener(v -> {
            news_list.scrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });
    }

    private OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (myAdapter.getView() != null) {
                myAdapter.hide();
            }
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition >= 2) backtotop.setVisibility(View.VISIBLE);
            else backtotop.setVisibility(View.GONE);
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipeRefreshLayout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码
                        if (list.size() != 0) {
                            tid = list.get(list.size() - 1).id;
                            if (!isaddfresh) {
                                isaddfresh = true;
                                pullup.setVisibility(View.VISIBLE);
                                mutual_papaNews.start(2);
                            }
                        }
                    }
                }
            }
        }
    };

    boolean isaddfresh = false;

    private String tid = "";

    @Override
    public String getTid() {
        return tid;
    }

    @Override
    public String getCityCode() {
        if (!potting.getItemString("citycode").equals("")) {
            return potting.getItemString("citycode");
        } else return "";
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            myAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {
        isaddfresh = false;
        pullup.setVisibility(View.GONE);
        if (list != null) {
            if (list.size() != 0) {
                this.list.addAll(list);
                myAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void showInfo(String s) {
        if (!s.equals("city不能为空") && !s.equals("success"))
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        isaddfresh = false;
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startMutual() {
        tid = "";
        mutual_papaNews.start(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(backtotop);
    }
}