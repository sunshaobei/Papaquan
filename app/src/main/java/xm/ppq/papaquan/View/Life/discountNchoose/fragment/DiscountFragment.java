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
import xm.ppq.papaquan.Adapter.DiscountAdapter;
import xm.ppq.papaquan.Bean.DisChBreak;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Presenter.life.DiscountChoose.Mutual_DiscountChoose;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.Round_DiscountChoose;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;

/**
 * Created by EdgeDi on 11:57.
 */

public class DiscountFragment extends LoadingOneFragment implements Round_DiscountChoose {

    @BindView(R.id.discount_list_view)
    ListView discount_list_view;

    private View view;

    private DiscountAdapter adapter;
    private Mutual_DiscountChoose mutual_discountChoose;
    private SharedPreferencesPotting potting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_discount, container, false);
            ButterKnife.bind(this, view);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void loadData() {
        if (mutual_discountChoose == null) {
            mutual_discountChoose = new Mutual_DiscountChoose(this, getActivity());
        }
        if (potting == null) {
            potting = new SharedPreferencesPotting(getActivity(), "my_login");
        }
    }

    private JSONObject jsonObject;

    @Subscribe
    public void Break(DisChBreak result) {
        if (isVisibleToUser == true) {
            jsonObject = result.getJsonObject();
            mutual_discountChoose.NotLifeGetInfo(BaseUrl.REBATELIST, jsonObject, page);
        }
    }

    private int page = 0;
    private boolean isLoading = false;

    @Override
    public void setListener() {
        discount_list_view.setOnItemClickListener((adapterView, view1, i, l) -> {
            if (dataBeen != null) {
                Skip(RestaurantActivity.class, "sid", String.valueOf(dataBeen.get(i).getSid()));
            }
        });
        discount_list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (!isLoading) {
                    page++;
                    isLoading = true;
                    mutual_discountChoose.NotLifeGetInfo(BaseUrl.REBATELIST, jsonObject, page);
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

    private ArrayList<DiscountBean.DataBean> dataBeen;

    @Override
    public void setDiscountBean(ArrayList<DiscountBean.DataBean> dataBeen, LatLng start) {
        if (page == 0) {
            this.dataBeen = dataBeen;
        } else {
            this.dataBeen.addAll(dataBeen);
        }
        if (dataBeen.size() > 0) {
            isLoading = false;
        }
        if (adapter == null) {
            adapter = new DiscountAdapter(getActivity(), this.dataBeen, R.layout.discountchoose_item);
            adapter.setStart(start);
            discount_list_view.setAdapter(adapter);
        } else {
            adapter.setList(this.dataBeen);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start) {

    }
}