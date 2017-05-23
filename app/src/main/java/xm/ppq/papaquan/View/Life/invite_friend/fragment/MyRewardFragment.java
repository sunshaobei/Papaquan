package xm.ppq.papaquan.View.Life.invite_friend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.life.MyRewardAdapter;
import xm.ppq.papaquan.Bean.life.EvenRewardBean;
import xm.ppq.papaquan.Bean.life.MyRewardBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.scare_buying.fragment.MycouponAdapter;

/**
 * Created by EdgeDi on 17:42.
 */

public class MyRewardFragment extends LoadingOneFragment {

    @BindView(R.id.re_list_view)
    RecyclerView re_list_view;

    private View view;

    private SharedPreferencesPotting potting;
    private MyRewardAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_reward, container, false);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            re_list_view.setLayoutManager(new LinearLayoutManager(getContext()));
            re_list_view.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            adapter = new MyRewardAdapter(getContext(), dataBeen, R.layout.my_rewar_item);
            adapter.addHeaderView(CreateView(), View.VISIBLE);
            re_list_view.setAdapter(adapter);
        }
        return view;
    }

    private MyRewardBean.OtherBean otherBean;
    private ArrayList<MyRewardBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    public void loadData() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "type", "citycode", "page", "length")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, 2, potting.getItemString("citycode"), 0, 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.MINEINVITE, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (otherBean != null) {
                    one_friend.setText("一级好友：" + otherBean.getOne() + "人");
                    two_friend.setText("二级好友：" + otherBean.getTwo() + "人");
                    one_money.setText(otherBean.getOneMoney() + "元");
                    if (otherBean.getTwoMoney() < 10) {
                        two_money.setText(" " + otherBean.getTwoMoney() + "元");
                    } else {
                        two_money.setText(otherBean.getTwoMoney() + "元");
                    }
                    adapter.setList(dataBeen);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    MyRewardBean bean = (MyRewardBean) JsonUtil.fromJson(s, MyRewardBean.class);
                    if (bean.getCode() == 0) {
                        otherBean = bean.getOther();
                        dataBeen = (ArrayList<MyRewardBean.DataBean>) bean.getData();
                    }
                }
            }
        });
    }

    TextView one_friend;
    TextView two_friend;
    TextView one_money;
    TextView two_money;

    private View CreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_reward_head, null);
        one_friend = (TextView) view.findViewById(R.id.one_friend);
        two_friend = (TextView) view.findViewById(R.id.two_friend);
        one_money = (TextView) view.findViewById(R.id.one_money);
        two_money = (TextView) view.findViewById(R.id.two_money);
        return view;
    }

    @Override
    public void setListener() {

    }
}