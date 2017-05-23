package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.ViewManageBean;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/13.
 */

public class VipManageAdapter extends ConcreteAdapter<ViewManageBean.DataBean> {

    public VipManageAdapter(Context context, List<ViewManageBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ViewManageBean.DataBean item, int position) {
        viewHolder.setText(item.getNickname(), R.id.name)
                .setText(item.getTel(), R.id.phone)
                .setText(item.getMoney()+"", R.id.money);
        if (item.getSex().equals("1")) {
            viewHolder.setText("男", R.id.sex);
        } else {
            viewHolder.setText("女", R.id.sex);
        }
    }
}
