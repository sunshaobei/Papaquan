package xm.ppq.papaquan.View.Life.lifehome.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.mapsdk.raster.model.LatLng;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.LifeDiscountAdapter;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Presenter.life.DiscountChoose.Mutual_DiscountChoose;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.Round_DiscountChoose;

/**
 * Created by EdgeDi on 11:57.
 */

public class LifeDiscountFragment extends LoadingOneFragment implements Round_DiscountChoose {

    @BindView(R.id.discount_list_view)
    RecyclerView discount_list_view;
    @BindView(R.id.pullup)
    View pullup;
    @BindView(R.id.backtotop)
    View backtotop;

    private View view;

    private LifeDiscountAdapter adapter;
    private Mutual_DiscountChoose mutual_discountChoose;
    private SharedPreferencesPotting potting;

    public LifeDiscountFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_discount_life, container, false);
            ButterKnife.bind(this, view);
            discount_list_view.setLayoutManager(new LinearLayoutManager(getContext()));
            discount_list_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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

    @Override
    public void loadData() {
        page = 0;
        if (mutual_discountChoose == null) {
            mutual_discountChoose = new Mutual_DiscountChoose(this, getActivity());
        }
        if (potting == null) {
            potting = new SharedPreferencesPotting(getActivity(), "my_login");
        }
        mutual_discountChoose.StartWares(BaseUrl.REBATELIST, page);
        adapter = new LifeDiscountAdapter(getActivity(), dataBeen, R.layout.discountchoose_item);
        discount_list_view.setAdapter(adapter);
    }

    private int page;

    @Override
    public void setListener() {
        backtotop.setOnClickListener(v -> {
            discount_list_view.scrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });
        discount_list_view.setOnScrollListener(onScrollListener);
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition >= 2) backtotop.setVisibility(View.VISIBLE);
            else backtotop.setVisibility(View.GONE);
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (lastVisibleItem == (totalItemCount - 1)) {
                    //加载更多功能的代码
                    if (dataBeen.size() != 0) {
                        if (!isloadmore) {
                            isloadmore = true;
                            pullup.setVisibility(View.VISIBLE);
                            page++;
                            mutual_discountChoose.StartWares(BaseUrl.REBATELIST, page);
                        }
                    }
                }
            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPost(int offset) {
        if (Math.abs(offset) < 30) {
            discount_list_view.scrollToPosition(0);
        }
    }

    private boolean isloadmore = false;

    @Subscribe
    public void onEvent(String s) {

    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setText(int position, String result, String id) {

    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return null;
    }

    @Override
    public LatLng getLatLng() {
        return ((Round_DiscountChoose) getActivity()).getLatLng();
    }

    @Override
    public void setListes(ArrayList<LevelBean> l1, ArrayList<LevelBean> l2, ArrayList<LevelBean> l3, ArrayList<LevelBean> l4) {

    }

    private ArrayList<DiscountBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    public void setDiscountBean(ArrayList<DiscountBean.DataBean> list, LatLng start) {
        adapter.setStart(start);
        if (page == 0) {
            dataBeen.clear();
        } else {
            isloadmore = false;
        }
        dataBeen.addAll(list);
        pullup.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start) {

    }
}