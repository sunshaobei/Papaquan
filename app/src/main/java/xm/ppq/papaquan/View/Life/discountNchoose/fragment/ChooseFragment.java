package xm.ppq.papaquan.View.Life.discountNchoose.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.tencent.mapsdk.raster.model.LatLng;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.Choose_twoAdapter;
import xm.ppq.papaquan.Bean.DisChBreak;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Presenter.life.DiscountChoose.Mutual_DiscountChoose;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.Round_DiscountChoose;

/**
 * Created by EdgeDi on 13:17.
 */

public class ChooseFragment extends LoadingOneFragment implements Round_DiscountChoose {

    @BindView(R.id.discount_list_view)
    ListView discount_list_view;

    private View view;

    private Choose_twoAdapter adapter;
    private Mutual_DiscountChoose mutual_discountChoose;
    private SharedPreferencesPotting potting;
    private ArrayList<ChooseBean.DataBean> dataBeen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_choose, container, false);
            ButterKnife.bind(this, view);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void loadData() {
        potting = new SharedPreferencesPotting(getActivity(), "my_login");
        mutual_discountChoose = new Mutual_DiscountChoose(this, getActivity());
        mutual_discountChoose.StartWares(BaseUrl.COUPONLIST, 0);
    }

    @Override
    public void setListener() {
        discount_list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (!isLoading) {
                            page++;
                            isLoading = true;
                            mutual_discountChoose.NotLifeGetInfo(BaseUrl.COUPONLIST, jsonObject, page);
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private boolean isLoading = false;
    private JSONObject jsonObject;

    @Subscribe
    public void onEvent(DisChBreak s) {
        if (isVisibleToUser) {
            jsonObject = s.getJsonObject();
            page = 0;
            mutual_discountChoose.NotLifeGetInfo(BaseUrl.COUPONLIST, jsonObject, page);
        }
    }

    private int page;

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setText(int position, String result, String id) {

    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return null;
    }

    @Override
    public LatLng getLatLng() {
        return ((Round_DiscountChoose) getActivity()).getLatLng();
    }

    @Override
    public void setListes(ArrayList<LevelBean> l1, ArrayList<LevelBean> l2, ArrayList<LevelBean> l3, ArrayList<LevelBean> l4) {

    }

    @Override
    public void setDiscountBean(ArrayList<DiscountBean.DataBean> dataBeen, LatLng start) {

    }

    @Override
    public void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start) {
        if (page == 0) {
            this.dataBeen = dataBeen;
        } else {
            this.dataBeen.addAll(dataBeen);
        }
        if (dataBeen.size() > 0) {
            isLoading = false;
        }
        if (adapter == null) {
            adapter = new Choose_twoAdapter(this.dataBeen, getActivity(), R.layout.discountchoose_item);
            adapter.setStart(start);
            discount_list_view.setAdapter(adapter);
        } else {
            adapter.setList(this.dataBeen);
            adapter.notifyDataSetChanged();
        }
    }
}