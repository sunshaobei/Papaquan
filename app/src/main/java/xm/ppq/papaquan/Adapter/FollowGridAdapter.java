package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.FollowGridBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;

/**
 * Created by Administrator on 2017/3/1.
 */

public class FollowGridAdapter extends ConcreteAdapter<FollowGridBean.Data> {

    public FollowGridAdapter(Context context, List<FollowGridBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, FollowGridBean.Data item, int position) {
        viewHolder.setText(item.nickname, R.id.follow_name);
        ImageLoading.Circular((Activity) getContext(), item.headurl, R.drawable.default_icon, viewHolder.getView(R.id.follow_icon));
        if (item.isSelect) {
            viewHolder.setVisibility(View.VISIBLE, R.id.cicle);
            viewHolder.setText(Color.BLACK, R.id.follow_name);
        } else {
            viewHolder.setVisibility(View.INVISIBLE, R.id.cicle);
            viewHolder.setText(Color.parseColor("#c2c2c2"), R.id.follow_name);
        }
    }
}
