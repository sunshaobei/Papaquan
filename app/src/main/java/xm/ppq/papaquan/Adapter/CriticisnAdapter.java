package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.GridView;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.RestaurantBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 19:15.
 */

public class CriticisnAdapter extends ConcreteAdapter<RestaurantBean.Other> {

    public CriticisnAdapter(Context context, List<RestaurantBean.Other> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RestaurantBean.Other item, int position) {
        ImageLoading.Circular((Activity) getContext(), item.getHeadurl(), R.drawable.default_icon, viewHolder.getView(R.id.headicon));
        viewHolder.setText(item.getNickname(), R.id.name)
                .setText(DateUtil.getStringByFormat(item.getCreatetime() * 1000, DateUtil.dateFormatYMDHMS), R.id.time)
                .setText(item.getContent(), R.id.content);
        GridView view = viewHolder.getView(R.id.gridview);
        if (item.getPicture() != null) {
            view.setAdapter(new ConcreteAdapter<String>(getContext(), item.getPicture(), R.layout.item_goodscommentgridview) {
                @Override
                protected void convert(ViewHolder viewHolder, String item, int position) {
                    ImageLoading.common(getContext(), BaseUrl.BITMAP + item, viewHolder.getView(R.id.imageView), R.drawable.default_icon_zheng);
                }
            });
        }
    }
}