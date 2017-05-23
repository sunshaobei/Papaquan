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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.my_topic.Mutual_MyTopic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.login.LoginActivity;
import xm.ppq.papaquan.View.my_topic.Round_MyTopic;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PapaMineFragLayout extends Fragment implements Round_papa_news, Round_MyTopic, UpMoreInterface {

    @BindView(R.id.my_topic_recycler)
    RecyclerView my_topic_recycler;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;
    @BindView(R.id.pullup)
    View pullup;
    @BindView(R.id.backtotop)
    View backtotop;
    @BindView(R.id.quick_login)
    View quick_login;
    @BindView(R.id.login)
    View login;
    @BindView(R.id.dataview)
    View dataview;

    private PapanewsFragmentAdapter adapter;
    private SharedPreferencesPotting potting;
    private Mutual_MyTopic mutual_myTopic;
    private View view;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_my_topic, null);
            ButterKnife.bind(this, view);
            loadData();
            setListener();
        }
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (potting != null) {
                if (potting.getItemInt("uid") != uid) {
                    uid = potting.getItemInt("uid");
                    swipe_layout.setRefreshing(true);
                    page = 0;
                    mutual_myTopic.start(page);
                }
            }
        }
    }

    public void loadData() {
        swipe_layout.setRefreshing(true);
        potting = new SharedPreferencesPotting(getActivity(), "my_login");
        uid = potting.getItemInt("uid");
        mutual_myTopic = new Mutual_MyTopic(this);
        adapter = new PapanewsFragmentAdapter(getActivity(), datas, this, this);
        my_topic_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        my_topic_recycler.setAdapter(adapter);
        mutual_myTopic.start(0);
    }

    private int page = 0;
    private boolean isfreshing = false;

    public void setListener() {
        swipe_layout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isfreshing = true;
                page = 0;
                mutual_myTopic.start(0);
            }
        });
        my_topic_recycler.addOnScrollListener(onScrollListener);
        backtotop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_topic_recycler.scrollToPosition(0);
                backtotop.setVisibility(View.GONE);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PapaMineFragLayout.this.getActivity(), LoginActivity.class);
                intent.putExtra("action", 1);
                PapaMineFragLayout.this.startActivityForResult(intent, IntentCode.RequestCode.TOLOGIN);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOFOLLOW) {
            swipe_layout.setRefreshing(true);
            mutual_myTopic.start(0);
        }
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (adapter.getView() != null)
                adapter.hide();
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition >= 3) {
                backtotop.setVisibility(View.VISIBLE);
            } else {
                backtotop.setVisibility(View.GONE);
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipe_layout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        if (!isfreshing) {
                            pullup.setVisibility(View.VISIBLE);
                            isfreshing = true;
                            page++;
                            mutual_myTopic.start(page);
                        }
                    }
                }
            }
        }
    };

    @Override
    public String getTid() {
        return null;
    }

    @Override
    public String getCityCode() {
        return null;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    @Override
    public void setList(ArrayList<ShowNewsBigBean.Data> list) {
        isfreshing = false;
        if (page == 0) {
            datas.clear();
        } else {
            pullup.setVisibility(View.GONE);
        }
        datas.addAll(list);
        adapter.notifyDataSetChanged();
        swipe_layout.setRefreshing(false);
    }

    @Override
    public int getUid() {
        if (potting.getItemInt("uid") == 0) {
            quick_login.setVisibility(View.VISIBLE);
            dataview.setVisibility(View.GONE);
        } else {
            quick_login.setVisibility(View.GONE);
            dataview.setVisibility(View.VISIBLE);
        }
        return potting.getItemInt("uid");
    }

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {

    }

    @Override
    public void showInfo(String s) {
        swipe_layout.setRefreshing(false);
        pullup.setVisibility(View.GONE);
    }

    @Override
    public void startMutual() {
        mutual_myTopic.start(1);
    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(pullup);
    }
}
