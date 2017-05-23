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
import android.widget.TextView;
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
import xm.ppq.papaquan.Tool.FollowDialog;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.login.LoginActivity;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PapaFollowFragLayout extends Fragment implements Round_papa_news, UpMoreInterface {

    @BindView(R.id.news_list)
    RecyclerView recyclerView;
    @BindView(R.id.swipfresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.backtotop)
    ImageView backtotop;
    @BindView(R.id.pullup)
    View pullup;

    private LayoutInflater inflater;
    private FollowDialog dialog;
    private View view;
    private TextView fast_follow;
    private SharedPreferencesPotting potting;
    private PapanewsFragmentAdapter adapter;
    private ArrayList<ShowNewsBigBean.Data> showNewsBigBeen = new ArrayList<>();
    private Mutual_PapaNews mutual_papaNews;
    private boolean isaddrefresh = false;
    private int uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_papa_news, null);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            adapter = new PapanewsFragmentAdapter(getActivity(), showNewsBigBeen, this, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
            mutual_papaNews = new Mutual_PapaNews(this);
            loadData();
            setListener();
        }
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            if (potting.getItemInt("uid")!=uid)
            {
                loadData();
            }
        }
    }

    public void loadData() {
        uid = potting.getItemInt("uid");
        if (!potting.getItemString("token").equals("")) {
            tid = "";
            swipeRefreshLayout.setRefreshing(true);
            mutual_papaNews.getData(1);
            isaddrefresh = true;
        } else {
            if (head == null) {
                head = inflater.inflate(R.layout.fragment_papa_follow, null);
                fast_follow = (TextView) head.findViewById(R.id.fast_follow);
            }
            adapter.addHeaderView(head, View.VISIBLE);
            showNewsBigBeen.clear();
            adapter.setDataList(showNewsBigBeen);
            adapter.notifyDataSetChanged();
            fast_follow.setText("快速登陆");
            fast_follow.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("action", 1);
                startActivityForResult(intent, IntentCode.RequestCode.TOLOGIN);
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOFOLLOW) {
            loadData();
        }
    }

    private String tid = "";

    public void setListener() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            tid = "";
            if (!potting.getItemString("token").equals("")) {
                mutual_papaNews.getData(1);
                isaddrefresh = true;
            } else swipeRefreshLayout.setRefreshing(false);
        });
        recyclerView.addOnScrollListener(onScrollListener);
        backtotop.setOnClickListener(v -> {
            recyclerView.scrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (adapter.getView() != null) adapter.hide();
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition >= 3) backtotop.setVisibility(View.VISIBLE);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipeRefreshLayout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        if (showNewsBigBeen.size() != 0) {
                            if (!isaddrefresh) {
                                tid = showNewsBigBeen.get(showNewsBigBeen.size() - 1).id;
                                mutual_papaNews.getData(2);
                                pullup.setVisibility(View.VISIBLE);
                                isaddrefresh = true;
                            }
                        }
                    }
                }
            }
        }
    };

    public void ToastShow(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getTid() {
        return tid;
    }

    @Override
    public String getCityCode() {
        if (potting.getItemString("citycode") != null) {
            return potting.getItemString("citycode");
        } else {
            return "";
        }
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    private View head;

    @Override
    public void setList(List<ShowNewsBigBean.Data> list) {
        isaddrefresh = false;
        showNewsBigBeen.clear();
        showNewsBigBeen.addAll(list);
        if (head == null) {
            head = inflater.inflate(R.layout.fragment_papa_follow, null);
            fast_follow = (TextView) head.findViewById(R.id.fast_follow);
        }
        if (showNewsBigBeen.size() > 0) {
            adapter.addHeaderView(head, View.GONE);
            adapter.setDataList(showNewsBigBeen);
            adapter.notifyDataSetChanged();
        } else {
            dialog = new FollowDialog(getContext(), R.style.dialog, getActivity());
            dialog.setData(String.valueOf(potting.getItemInt("uid")), potting.getItemString("token"));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter.addHeaderView(head, View.VISIBLE);
            recyclerView.setAdapter(adapter);
            fast_follow.setText("快速关注");
            fast_follow.setOnClickListener(v -> dialog.show());
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setRefreshList(List<ShowNewsBigBean.Data> list) {
        pullup.setVisibility(View.GONE);
        isaddrefresh = false;
        showNewsBigBeen.addAll(list);
        if (showNewsBigBeen.size() > 0) {
            adapter.setDataList(showNewsBigBeen);
            adapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showInfo(String s) {
        if (s.equals("token失效"))
        {
            if (head == null) {
                head = inflater.inflate(R.layout.fragment_papa_follow, null);
                fast_follow = (TextView) head.findViewById(R.id.fast_follow);
            }
            adapter.addHeaderView(head, View.VISIBLE);
            showNewsBigBeen.clear();
            adapter.setDataList(showNewsBigBeen);
            adapter.notifyDataSetChanged();
            fast_follow.setText("快速登陆");
            fast_follow.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("action", 1);
                startActivityForResult(intent, IntentCode.RequestCode.TOLOGIN);
            });
        }else {
            ToastShow(s);
        }
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void startMutual() {
        mutual_papaNews.getData(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onEvent(String s) {
        if (s.equals("关注")) {
            swipeRefreshLayout.setRefreshing(true);
            mutual_papaNews.getData(1);
        }
    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(backtotop);
    }
}
