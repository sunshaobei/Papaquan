package xm.ppq.papaquan.View.tasheet.frame;

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
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.PapanewsFragmentAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.he_sheet.Mutual_HeSheet;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;
import xm.ppq.papaquan.View.tasheet.Round_HeSheet;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by Administrator on 2017/3/1.
 */

public class TrendFragment extends Fragment implements Round_HeSheet, Round_papa_news, UpMoreInterface {

    @BindView(R.id.backtotop)
    ImageView backtotop;
    @BindView(R.id.pullup)
    LinearLayout pullup;
    private View view;

    @BindView(R.id.trend_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;

    private SharedPreferencesPotting potting;
    private Mutual_HeSheet mutual_heSheet;
    private PapanewsFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_trend, container, false);
            ButterKnife.bind(this, view);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        setListener();
        return view;
    }

    public void loadData() {
        swipe_layout.setRefreshing(true);
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        mutual_heSheet = new Mutual_HeSheet(this);
        mutual_heSheet.start(Ta_SheetActivity.Uuid, 0);
    }

    public void setListener() {
        swipe_layout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipe_layout.setOnRefreshListener(() -> {
            page = 0;
            mutual_heSheet.start(Ta_SheetActivity.Uuid, 1);
        });
        recyclerView.addOnScrollListener(onScrollListener);
        backtotop.setOnClickListener(v -> {
            recyclerView.scrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });
    }

    private int page = 0;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (adapter!=null)
            {
                if (adapter.getView()!=null)
                {
                    adapter.hide();
                }
            }
            int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
            if (firstVisibleItemPosition>=1)
            {
                backtotop.setVisibility(View.VISIBLE);
            }else backtotop.setVisibility(View.GONE);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                int totalItemCount = manager.getItemCount();
                if (!swipe_layout.isRefreshing()) {
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        if (datas.size() > 0) {
                            page++;
                            mutual_heSheet.start(Ta_SheetActivity.Uuid, 0);
                            pullup.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
    };

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public int getPage() {
        return page;
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

    }

    @Override
    public String getTid() {
        return "";
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void setNickName(String nickName) {

    }

    @Override
    public void setHeadUrl(String headUrl) {

    }

    @Override
    public void setT_F_F(String topic, String Follow, String Follower) {

    }

    @Override
    public void setSignature(String signature) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setVip_End(long end) {

    }

    @Override
    public void setSexIcon(String sexIcon) {

    }

    @Override
    public void IsFollow(String follow) {

    }

    @Override
    public void setToast(String result) {

    }

    @Override
    public void setLevel(int level) {

    }

    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    @Override
    public void setData(ArrayList<ShowNewsBigBean.Data> list, int type) {
        if (type == 1) {
            datas.clear();
        }
        datas.addAll(list);
        if (adapter == null) {
            adapter = new PapanewsFragmentAdapter(getActivity(), datas, this, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setDataList(datas);
            adapter.notifyDataSetChanged();
        }
        swipe_layout.setRefreshing(false);
        pullup.setVisibility(View.GONE);
    }

    @Override
    public void setCreateTime(String createTime) {

    }

    @Override
    public void Show(ShareDialog dialog) {
        dialog.Show(recyclerView);
    }
}