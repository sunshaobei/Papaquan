package xm.ppq.papaquan.View.Life.scare_past.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ScarePastAdapter;
import xm.ppq.papaquan.Bean.Scare_PastBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by 折扣/往期列表 on 2017/4/17.
 */

public class Sca_PasFragment extends Fragment {

    @BindView(R.id.sca_pas_recycler)
    RecyclerView sca_pas_recycler;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private View view;
    private ScarePastAdapter adapter;
    private ArrayList<Scare_PastBean.Data> scare_pastBeen = new ArrayList<>();
    private String type;
    private SharedPreferencesPotting potting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_sca_pas, container, false);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
            sca_pas_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            sca_pas_recycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        loadData();
        setListener();
        return view;
    }

    public void loadData() {
        type = getArguments().getString("type");
        if (type == null) type = "now";
        isaddrefresh = false;
        get(0);
    }

    public void setListener() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            page = 0;
            get(0);
        });
        sca_pas_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    if (!swipeRefreshLayout.isRefreshing()) {
                        if (lastVisibleItem == (totalItemCount - 1)) {
                            if (scare_pastBeen.size() != 0) {
                                if (!isaddrefresh) {
                                    page++;
                                    get(1);
                                    isaddrefresh = true;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private int page = 0;
    private boolean isaddrefresh = false;

    private void get(int i) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", potting.getItemString("citycode"))
                    .put("type", type)
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1)
                    .put("page", page)
                    .put("length", 10);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PANICLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    getActivity().runOnUiThread(() -> {
                        if (scare_pastBeen != null) {
                            if (scare_pastBeen.size() > 0) {
                                if (adapter == null) {
                                    adapter = new ScarePastAdapter(getActivity(), scare_pastBeen, R.layout.scare_past_item);
                                    sca_pas_recycler.setAdapter(adapter);
                                } else {
                                    adapter.setList(scare_pastBeen);
                                    adapter.notifyDataSetChanged();
                                }
                                isaddrefresh = false;
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    });

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        Scare_PastBean scare_pastBean = (Scare_PastBean) JsonUtil.fromJson(s, Scare_PastBean.class);
                        if (scare_pastBean.getCode() == 0) {
                            if (i == 0) {
                                scare_pastBeen.clear();
                            }
                            scare_pastBeen.addAll(scare_pastBean.getData());
                            ArrayList<Scare_PastBean.Other> other = scare_pastBean.getOther();
                            EventBus.getDefault().post(other);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}