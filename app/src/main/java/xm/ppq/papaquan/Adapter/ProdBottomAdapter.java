package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.ProductBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.place_order.Place_OrderActivity;

/**
 * Created by EdgeDi on 13:20.
 */

public class ProdBottomAdapter extends ConcreteAdapter<ProductBean.OtherBean> {

    private String shopname;

    public ProdBottomAdapter(Context context, List<ProductBean.OtherBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ProductBean.OtherBean item, int position) {
        viewHolder.setKtClicked(R.id.scare_bestow, false).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Place_OrderActivity.class);
            getContext().startActivity(intent);
        }, R.id.scare_bestow)
                .setText(shopname, R.id.title_scare)
                .setText(item.getTitle(), R.id.content_scare)
                .setText("￥" + Stringutil.ThreeString(item.getPrice(), "0"), R.id.red_money)
                .setText(Stringutil.ThreeString(item.getCostprice(), "0") + "元", R.id.prime_cost)
                .setVisibility(View.GONE, R.id.distance)
                .setKtTextView(R.id.scare_bestow, "马上抢", 10, 10)
                .setVisibility(View.VISIBLE, R.id.scare_bestow);
        TextView_Delete(viewHolder.getView(R.id.prime_cost));
        ImageView view = viewHolder.getView(R.id.scare_icon);
        if (item.getUphour() == 0) {
            viewHolder.setVisibility(View.VISIBLE, R.id.myy);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.myy);
        }
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getImg(), view, R.mipmap.food);
    }
}
