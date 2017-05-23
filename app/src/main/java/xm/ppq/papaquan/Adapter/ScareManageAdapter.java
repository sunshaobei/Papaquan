package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.Scare_ManageBean;
import xm.ppq.papaquan.Bean.life.Scare_Manage_2Bean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;
import xm.ppq.papaquan.life.Tool.TextViewScare;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ScareManageAdapter<T> extends ConcreteAdapter<T> {

    private int type = 0;

    public ScareManageAdapter(Context context, List<T> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder viewHolder, T item, int position) {
        if (type == 0) {//抢购
            Scare_ManageBean.DataBean dataBean = (Scare_ManageBean.DataBean) item;
            int now = (int) (System.currentTimeMillis() / 1000);
            TextView_Delete((TextView) viewHolder.getView(R.id.delete));
            ImageLoading.common(getContext(), BaseUrl.BITMAP + dataBean.getImg(), viewHolder.getView(R.id.icon), R.mipmap.food);
            if (dataBean.getUphour().equals("0")) {
                viewHolder.setVisibility(View.VISIBLE, R.id.scare_uphour);
            } else {
                viewHolder.setVisibility(View.GONE, R.id.scare_uphour);
            }
            TextViewScare view = viewHolder.getView(R.id.state_uphour);
            if (now > dataBean.getEnd_time()) {
                view.setText("已结束");
                view.setType(1);
            } else {
                view.setText("已开始");
                view.setType(2);
            }
            if (now > dataBean.getConsumption_deadline()) {
                view.setText("已过期");
                view.setType(1);
            }
            viewHolder.setText(dataBean.getTitle(), R.id.content)
                    .setText("￥" + dataBean.getBuying_price(), R.id.money_text)
                    .setText(dataBean.getPrice() + "元", R.id.delete)
                    .setText("总数" + dataBean.getQuantity() + "/剩余" + (dataBean.getQuantity() - dataBean.getPaynum()), R.id.total_remain_num)
                    .setText(dataBean.getBrowse() + "关注", R.id.follow_u)
                    .setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), Panic_BuyingActivity.class);
                        intent.putExtra("pid", dataBean.getId());
                        getContext().startActivity(intent);
                    }, R.id.detail);
        } else {//精选
            Scare_Manage_2Bean.DataBean dataBean = (Scare_Manage_2Bean.DataBean) item;
            int now = (int) (System.currentTimeMillis() / 1000);
            TextView_Delete((TextView) viewHolder.getView(R.id.delete));
            ImageLoading.common(getContext(), BaseUrl.BITMAP + dataBean.getImg(), viewHolder.getView(R.id.icon), R.mipmap.food);
            if (dataBean.getUphour() == 0) {
                viewHolder.setVisibility(View.VISIBLE, R.id.scare_uphour);
            } else {
                viewHolder.setVisibility(View.GONE, R.id.scare_uphour);
            }
            TextViewScare view = viewHolder.getView(R.id.state_uphour);
            if (now > Integer.valueOf(dataBean.getEtime() != "" ? dataBean.getEtime() : "0")) {
                view.setText("已结束");
                view.setType(1);
            } else {
                view.setText("已开始");
                view.setType(2);
            }
            if (dataBean.getStatus() == 1) {
                view.setText("已下架");
                view.setType(1);
            }
            viewHolder.setText(dataBean.getTitle(), R.id.content)
                    .setText("￥" + dataBean.getPrice(), R.id.money_text)
                    .setText(dataBean.getCostprice() + "元", R.id.delete)
                    .setText("已售" + dataBean.getBuynum(), R.id.total_remain_num)
                    .setText(dataBean.getBrowse() + "关注", R.id.follow_u)
                    .setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                        intent.putExtra("cid", dataBean.getId());
                        getContext().startActivity(intent);
                    }, R.id.detail);
        }
    }

}