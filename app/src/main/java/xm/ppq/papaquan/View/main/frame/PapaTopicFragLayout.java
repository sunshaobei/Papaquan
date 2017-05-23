package xm.ppq.papaquan.View.main.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.BaseRecyclerAdapter;
import xm.ppq.papaquan.Adapter.PapaTopicAdapter;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.Presenter.papatopic.Mutual_Papatopic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.topic_deail.Topic_DetailActivity;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PapaTopicFragLayout extends Fragment implements BaseRecyclerAdapter.OnRecyclerItemListener<PapaTopicBean.DataBean>
        , Round_PapaTopic {

    @BindView(R.id.news_list)
    RecyclerView topic_double_list;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.pullup)
    View pullup;
    @BindView(R.id.backtotop)
    ImageView backtotop;

    private PapaTopicAdapter adapter;

    private View view;
    private Mutual_Papatopic mutual_papatopic;
    private SharedPreferencesPotting potting;
    private boolean isfresh = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_papa_news, null);
            ButterKnife.bind(this, view);
        }
        loadData();
        setListener();
        return view;
    }

    public void loadData() {
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        swipeRefreshLayout.setRefreshing(true);
        page = 0;
        topic_double_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mutual_papatopic = new Mutual_Papatopic(this);
//        mutual_papatopic.getData(1);
        isfresh = true;
    }

    public void setListener() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            page = 0;
//            mutual_papatopic.getData(1);
            isfresh = true;
        });
        topic_double_list.addOnScrollListener(onScrollListener);
        backtotop.setOnClickListener(v -> {
            topic_double_list.scrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
            if (lastVisibleItemPosition > 3) {
                backtotop.setVisibility(View.VISIBLE);
            } else {
                backtotop.setVisibility(View.GONE);
            }
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipeRefreshLayout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        if (list.size() != 0) {
                            if (!isfresh) {
                                pullup.setVisibility(View.VISIBLE);
//                                mutual_papatopic.getData(0);
                                isfresh = true;
                            }
                        }
                    }
                }
            }
        }
    };

    @Override
    public void RecyclerItem(View v, PapaTopicBean.DataBean item, int position) {
        Intent intent = new Intent(getActivity(), Topic_DetailActivity.class);
        intent.putExtra("hotid", list.get(position).getId());
        startActivity(intent);
    }

    private List<PapaTopicBean.DataBean> list = new ArrayList<>();

    private int page = 0;


    @Override
    public String getCityCode() {
            return potting.getItemString("citycode");
    }

    @Override
    public void setPapaTopicList(List<PapaTopicBean.DataBean> datalist) {
        page++;
        isfresh = false;
        pullup.setVisibility(View.GONE);
//        if (type == 1) {
            list.clear();
//        }
        list.addAll(datalist);
        if (adapter == null) {
            adapter = new PapaTopicAdapter(getActivity(), list, R.layout.topic_double_item);
            adapter.setRecycler(PapaTopicFragLayout.this);
            topic_double_list.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public int getUid() {
        return 0;
    }

    @Override
    public String getToken() {
        return null;
    }
}
