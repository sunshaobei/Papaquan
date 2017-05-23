package xm.ppq.papaquan.View.Life.selleroof.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tencent.mapsdk.raster.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.PaiHangAdapter;
import xm.ppq.papaquan.Bean.NewSellerOofBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.SeventhBean;
import xm.ppq.papaquan.Bean.life.TopSelleOofBean;
import xm.ppq.papaquan.Presenter.life.sller_oof.Mutual_Seller_Oof;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.View.Life.selleroof.Round_SellerOof;

/**
 * Created by 排行列表 on 2017/4/13.
 */

public class PaiHangFragment extends LoadingOneFragment implements Round_SellerOof {

    @BindView(R.id.paihang_list)
    RecyclerView paihang_list;

    private View view;
    private PaiHangAdapter adapter;
    private String title;
    private int page = 0;
    private Mutual_Seller_Oof mutual_seller_oof;
    private SharedPreferencesPotting potting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_paihang, container, false);
            ButterKnife.bind(this, view);
            paihang_list.setLayoutManager(new LinearLayoutManager(getContext()));
            paihang_list.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        }
        return view;
    }

    @Override
    public void loadData() {
        mutual_seller_oof = new Mutual_Seller_Oof(this);
        title = getArguments().getString("title");
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        String type = null;
        if (title != null) {
            switch (title) {
                case "周排行":
                    type = "1";
                    break;
                case "月排行":
                    type = "2";
                    break;
                case "年排行":
                    type = "3";
                    break;
            }
        }
        if (type != null) {
            mutual_seller_oof.getSeventh(type);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setTop(ArrayList<TopSelleOofBean.DataBean> dataBean) {

    }

    @Override
    public void setNesEnter(ArrayList<NewSellerOofBean.DataBean> dataBeen) {

    }

    @Override
    public void setClassify(ArrayList<TypeClassfiyBean.DataBean> dataBeen) {

    }

    private LatLng start;

    @Override
    public void setSeventh(ArrayList<SeventhBean.DataBean> dataBeen) {
        if (adapter == null) {
            try {
                start = new LatLng(Double.valueOf(potting.getItemString("lat")), Double.valueOf(potting.getItemString("lng")));
            } catch (Exception e) {
                start = new LatLng(0, 0);
            }
            adapter = new PaiHangAdapter(getContext(), dataBeen, R.layout.paihang_item);
            adapter.setStart(start);
            paihang_list.setAdapter(adapter);
        } else {
            adapter.setList(dataBeen);
            adapter.notifyDataSetChanged();
        }
    }
}
