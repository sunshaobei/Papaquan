package xm.ppq.papaquan.View.Life.invite_friend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.BaseRecyclerAdapter;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Bean.life.EvenRewardBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.Red_Share;

/**
 * Created by EdgeDi on 17:39.
 */

public class EventRewardFragment extends LoadingOneFragment {

    @BindView(R.id.event_recycler_view)
    RecyclerView event_recycler_view;

    private View view;

    private SharedPreferencesPotting potting;
    private BaseRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_event_reward, container, false);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            event_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }

    @Override
    public void loadData() {
        red_share = new Red_Share(getActivity());
        ArrayList<String> list = new ArrayList<>();
        if (adapter == null) {
            adapter = new BaseRecyclerAdapter<String>(getContext(), list, R.layout.zanwei_item) {
                @Override
                protected void Evaluate(REViewHolder viewHolder, String item, int position) {

                }
            };
            adapter.addHeaderView(CreateView(), View.VISIBLE);
            event_recycler_view.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    private TextView one_event;
    private TextView two_event;
    private TextView invitation_code;
    private ImageView share;

    private View CreateView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.event_reward_head, null);
        one_event = (TextView) view.findViewById(R.id.one_event);
        two_event = (TextView) view.findViewById(R.id.two_event);
        share = (ImageView) view.findViewById(R.id.share);
        invitation_code = (TextView) view.findViewById(R.id.invitation_code);
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "type", "citycode")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, 1, potting.getItemString("citycode"));
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.MINEINVITE, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    EvenRewardBean evenRewardBean = (EvenRewardBean) JsonUtil.fromJson(s, EvenRewardBean.class);
                    if (evenRewardBean.getCode() == 0) {
                        one_event.setText("一级返利：" + evenRewardBean.getData().getOfirst() + "-" + evenRewardBean.getData().getOnext() + "元/人");
                        two_event.setText("二级返利：" + evenRewardBean.getData().getTfirst() + "-" + evenRewardBean.getData().getTnext() + "元/人");
                        invitation_code.setText(evenRewardBean.getData().getMyinvitation());
                    }
                }
            }
        });
        return view;
    }

    private Red_Share red_share;

    @Override
    public void setListener() {
        share.setOnClickListener(v -> {
            if (red_share != null) {
                red_share.Show(v);
            }
        });
    }
}
