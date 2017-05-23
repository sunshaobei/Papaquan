package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.StaffBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.Life.staff_manage.MakeOver;
import xm.ppq.papaquan.life.Tool.Staff_A_DPopupwindow;

/**
 * Created by Administrator on 2017/4/15.
 */

public class StaffAdapter extends ConcreteAdapter<StaffBean.DataBean> {

    private Staff_A_DPopupwindow staff_a_dPopupwindow;
    private MakeOver makeOver;

    public StaffAdapter(Context context, List<StaffBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        makeOver = (MakeOver) context;
        staff_a_dPopupwindow = new Staff_A_DPopupwindow((Activity) context);
    }

    @Override
    protected void convert(ViewHolder viewHolder, StaffBean.DataBean item, int position) {
        viewHolder.setOnClickListener(v -> {
            staff_a_dPopupwindow.setOnMakeOverListener(() -> {
                makeOver.Make_Up(item);
            });
            staff_a_dPopupwindow.show(v, item.getId(), item.getSid());
        }, R.id.operate_text);
        viewHolder.setText(item.getName(), R.id.name);
        viewHolder.setText(item.getNickname(), R.id.nick_name);
        viewHolder.setText(item.getLatetime(), R.id.time);
    }
}