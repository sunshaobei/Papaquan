package xm.ppq.papaquan.View.tasheet.frame;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.mobileim.lib.model.message.IFileMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.BaseRecyclerAdapter;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.he_sheet.Mutual_HeSheet;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.ranksuggest.RankSuggestActivity;
import xm.ppq.papaquan.View.tasheet.Round_HeSheet;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by Administrator on 2017/3/1.
 */

public class DataFragment extends Fragment implements View.OnClickListener, Round_HeSheet {

    @BindView(R.id.trend_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipe_layout;

    private View view;
    private LayoutInflater inflater;
    private LinearLayout level_activity;

    private TextView nick_name_data, sex_data, time_data, level_data, vip_end;
    private SharedPreferencesPotting potting;
    private Mutual_HeSheet mutual_heSheet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_trend, container, false);
            ButterKnife.bind(this, view);
        }
        loadData();
        setListener();
        return view;
    }

    public void loadData() {
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        mutual_heSheet = new Mutual_HeSheet(this);
        BaseRecyclerAdapter baseRecyclerAdapter = new BaseRecyclerAdapter<Object>(getActivity(), new ArrayList<>(), 0) {
            @Override
            protected void Evaluate(REViewHolder viewHolder, Object item, int position) {
            }
        };
        View head = inflater.inflate(R.layout.fragment_data, null);
        vip_end = (TextView) head.findViewById(R.id.vip_end);
        nick_name_data = (TextView) head.findViewById(R.id.nick_name_data);
        sex_data = (TextView) head.findViewById(R.id.sex_data);
        time_data = (TextView) head.findViewById(R.id.time_data);
        address_data = (TextView) head.findViewById(R.id.address_data);
        level_activity = (LinearLayout) head.findViewById(R.id.level_activity);
        level_data = (TextView) head.findViewById(R.id.level_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        baseRecyclerAdapter.addHeaderView(head, View.VISIBLE);
        recyclerView.setAdapter(baseRecyclerAdapter);
        mutual_heSheet.start(Ta_SheetActivity.Uuid, 0);
    }

    private TextView address_data;

    public void setListener() {
        level_activity.setOnClickListener(this);
        swipe_layout.setColorSchemeResources(android.R.color.holo_blue_light);
        swipe_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mutual_heSheet.start(Ta_SheetActivity.Uuid, 0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.level_activity:
                intent = new Intent(getActivity(), RankSuggestActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void setNickName(String nickName) {
        nick_name_data.setText(nickName);
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
        address_data.setText(address);
    }

    @Override
    public void setVip_End(long end) {
        if (end == 0) {
            vip_end.setText("未开通");
        } else {
            vip_end.setText(DateUtil.getStringByFormat(end * 1000, DateUtil.dateFormatYMDHM));
        }
    }

    @Override
    public void setSexIcon(String sexIcon) {
        swipe_layout.setRefreshing(false);
        if (sexIcon.equals("2")) {
            sex_data.setText("女");
        } else if (sexIcon.equals("1")) {
            sex_data.setText("男");
        } else {
            sex_data.setText("无");
        }
    }

    @Override
    public void IsFollow(String follow) {

    }

    @Override
    public void setToast(String result) {

    }

    @Override
    public void setLevel(int level) {
        level_data.setText("Lv_" + level);
    }

    @Override
    public void setData(ArrayList<ShowNewsBigBean.Data> list, int type) {

    }

    @Override
    public void setCreateTime(String createTime) {
        time_data.setText(createTime);
    }
}
