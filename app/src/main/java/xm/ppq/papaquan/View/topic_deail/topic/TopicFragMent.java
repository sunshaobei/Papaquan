package xm.ppq.papaquan.View.topic_deail.topic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.topic_detail.Mutual_TopicDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;
import xm.ppq.papaquan.View.published_dynamic.Dynamic;
import xm.ppq.papaquan.View.topic_deail.Round_Topicdetail;

/**
 * Created by Administrator on 2017/3/23.
 */

public class TopicFragMent extends LoadingOneFragment implements Round_Topicdetail, Round_papa_news, UpMoreInterface {

    @BindView(R.id.topic_double_recycler)
    RecyclerView topic_double_recycler;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.partake_topic)
    TextView partake_topic;

    private View view;
    private Round_Topicdetail round_topicdetail;
    private Mutual_TopicDetail mutual_topicDetail;
    private PapanewsFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_topicdouble, container, false);
            ButterKnife.bind(this, view);
        }
        return view;
    }

    @Override
    public void loadData() {
        swipe_layout.setRefreshing(true);
        round_topicdetail = (Round_Topicdetail) getActivity();
        mutual_topicDetail = new Mutual_TopicDetail(getActivity(), this);
        startMutual();
    }

    private String Hottitle;

    @Override
    public void setListener() {
        partake_topic.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Dynamic.class);
            if (Hottitle != null) {
                intent.putExtra("hottitle", Hottitle);
            }
            startActivity(intent);
        });
        swipe_layout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipe_layout.setOnRefreshListener(() -> {
            isrefresh = true;
            datas.clear();
            page = 0;
            startMutual();
        });
        topic_double_recycler.addOnScrollListener(onScrollListener);
    }

    private int page = 0;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            // 当不滚动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的ItemPosition
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipe_layout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        //加载更多功能的代码

                        if (!isrefresh)
                        {
                            page++;
                            startMutual();
                            isrefresh = true;
                        }
                    }
                }
            }
        }
    };

    private boolean isrefresh = false;
    @Override
    public String getHotid() {
        return round_topicdetail.getHotid();
    }

    @Override
    public String getPager() {
        return String.valueOf(page);
    }

    @Override
    public String getCitycode() {
        return round_topicdetail.getCitycode();
    }

    @Override
    public String getTid() {
        return null;
    }

    @Override
    public String getCityCode() {
        return round_topicdetail.getCitycode();
    }

    @Override
    public String getToken() {
        return round_topicdetail.getToken();
    }

    @Override
    public void setBackground(String url) {

    }

    @Override
    public int getUid() {
        return round_topicdetail.getUid();
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void showInfo(String s) {

    }

    @Override
    public void startMutual() {
        mutual_topicDetail.setFragment(round_topicdetail.getHotid(), "2");
    }

    @Override
    public void setHotTitle(String Hottitle) {
        this.Hottitle = Hottitle;
    }

    @Override
    public void setHot_Num(String hot_num) {

    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setIcon(String icon) {

    }

    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    @Override
    public void setList(ArrayList<ShowNewsBigBean.Data> datas) {
        isrefresh = false;
        this.datas.addAll(datas);
        if (adapter == null) {
            adapter = new PapanewsFragmentAdapter(getActivity(), this.datas, this, this);
            topic_double_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            topic_double_recycler.setAdapter(adapter);
        } else {
            adapter.setDataList(this.datas);
            adapter.notifyDataSetChanged();
        }
        swipe_layout.setRefreshing(false);
    }

    @Override
    public void setShare(String title, String url) {

    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(partake_topic);
    }
}