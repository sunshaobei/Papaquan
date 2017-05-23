package xm.ppq.papaquan.View.Life.invite_friend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.life.MyRewardAdapter;
import xm.ppq.papaquan.Adapter.life.RankingAdapter;
import xm.ppq.papaquan.Bean.life.MyRewardBean;
import xm.ppq.papaquan.Bean.life.RankingBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 17:44.
 */

public class RankingListFragment extends LoadingOneFragment {

    @BindView(R.id.rank_list)
    RecyclerView rank_list;

    private View view;

    private SharedPreferencesPotting potting;
    private RankingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_ranking_list, container, false);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            rank_list.setLayoutManager(new LinearLayoutManager(getContext()));
            rank_list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        return view;
    }

    @Override
    public void loadData() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "type", "citycode", "page", "length")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, 3, potting.getItemString("citycode"), 0, 10);
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
                    RankingBean bean = (RankingBean) JsonUtil.fromJson(s, RankingBean.class);
                    if (bean.getCode() == 0) {
                        if (adapter == null) {
                            adapter = new RankingAdapter(getContext(), bean.getData(), R.layout.my_rewar_item);
                            rank_list.setAdapter(adapter);
                        } else {
                            adapter.setList(bean.getData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void setListener() {

    }
}
