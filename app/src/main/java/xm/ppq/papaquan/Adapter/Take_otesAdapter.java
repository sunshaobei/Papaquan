package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.TakeAllBean;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Take_otesAdapter extends ConcreteAdapter<TakeAllBean.DataBean> {

    public Take_otesAdapter(Context context, List<TakeAllBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, TakeAllBean.DataBean item, int position) {
        viewHolder.setText(item.getRebate() + "折", R.id.discount)
                .setText("消费" + item.getMoney() + "元", R.id.money)
                .setText("折后:" + item.getRebatemoney(), R.id.z_last)
                .setText(item.getCanceltime(), R.id.money_time)
                .setText(item.getNickname(), R.id.nickname)
                .setText("核销：" + item.getClerkname(), R.id.shop);
    }
}
