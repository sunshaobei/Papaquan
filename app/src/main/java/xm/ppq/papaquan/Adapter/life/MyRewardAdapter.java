package xm.ppq.papaquan.Adapter.life;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.BaseRecyclerAdapter;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.MyRewardBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 18:54.
 */

public class MyRewardAdapter extends BaseRecyclerAdapter<MyRewardBean.DataBean> {

    public MyRewardAdapter(Context context, List<MyRewardBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void Evaluate(REViewHolder viewHolder, MyRewardBean.DataBean item, int position) {
        viewHolder.setText(item.getTime(), R.id.re_time)
                .setText(item.getNickname(), R.id.re_name)
                .setText("￥" + item.getMoney(), R.id.re_money);
    }

}