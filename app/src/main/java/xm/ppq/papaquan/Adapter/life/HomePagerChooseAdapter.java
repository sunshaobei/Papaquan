package xm.ppq.papaquan.Adapter.life;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.HomePagerOtherBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;

/**
 * Created by EdgeDi on 16:37.
 */

public class HomePagerChooseAdapter extends ConcreteAdapter<HomePagerOtherBean.DataBean.CouBean> {

    public HomePagerChooseAdapter(Context context, List<HomePagerOtherBean.DataBean.CouBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, HomePagerOtherBean.DataBean.CouBean item, int position) {
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getImg(), viewHolder.getView(R.id.icon_scare_past), R.mipmap.food);
        if (item.getUphour() == 0) {
            viewHolder.setVisibility(View.VISIBLE, R.id.left_top);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.left_top);
        }
        viewHolder.setText(item.getTitle(), R.id.title_scare)
                .setText(item.getPrice() + "元", R.id.money_yuan)
                .setText("￥" + item.getCostprice(), R.id.red_scare)
                .setText("已售" + item.getBuynum(), R.id.sold_num)
                .setText(item.getBrowse() + "人关注", R.id.follow_num)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                    intent.putExtra("cid", item.getId());
                    getContext().startActivity(intent);
                }, R.id.make_use_of_btn);
        TextView_Delete(viewHolder.getView(R.id.money_yuan));
    }
}
