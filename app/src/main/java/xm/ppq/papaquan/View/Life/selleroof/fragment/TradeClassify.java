package xm.ppq.papaquan.View.Life.selleroof.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.shownews.NoScrollGridView;
import xm.ppq.papaquan.View.Life.classification.ClassificationActivity;
import xm.ppq.papaquan.View.Life.selleroof.SellersortgridAdapter;

/**
 * Created by EdgeDi on 9:32.
 */

public class TradeClassify extends Fragment {

    @BindView(R.id.seller_title_custom)
    NoScrollGridView sellerTitleCustom;

    private View view;
    private ArrayList<TypeClassfiyBean.DataBean> dataBeen = new ArrayList<>();
    private ArrayList<TypeClassfiyBean.DataBean> bean = new ArrayList<>();
    private int position;
    private SellersortgridAdapter sellersortgridAdapter;

    public TradeClassify() {
    }

    public TradeClassify(int position, ArrayList<TypeClassfiyBean.DataBean> dataBeen) {
        this.position = position;
        this.dataBeen = dataBeen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_trade_classify, container, false);
            ButterKnife.bind(this, view);
            loadData();
            setListener();
        }
        return view;
    }

    public void loadData() {
        sellersortgridAdapter = new SellersortgridAdapter(getContext(), bean, R.layout.item_lifehomegd);
        sellerTitleCustom.setAdapter(sellersortgridAdapter);
        bean.clear();
        for (int i = 0; i < dataBeen.size(); i++) {
            int i1 = 10 * position;
            if (i >= i1 && i < i1 + 10)
                bean.add(dataBeen.get(i));
        }
        sellersortgridAdapter.notifyDataSetChanged();
    }

    public void setListener() {
        sellerTitleCustom.setOnItemClickListener((parent, view, position, id) -> {
            if (dataBeen != null) {
                Intent intent = new Intent(getContext(), ClassificationActivity.class);
                intent.putExtra("bean", dataBeen.get(position));
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (s.equals("114")) {
            bean.clear();
            for (int i = 0; i < dataBeen.size(); i++) {
                int i1 = 10 * position;
                if (i >= i1 && i < i1 + 10)
                    bean.add(dataBeen.get(i));
            }
            sellersortgridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}