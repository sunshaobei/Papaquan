package xm.ppq.papaquan.Adapter.life;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.RecordListBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 10:20.
 */

public class RecordAdapter extends ConcreteAdapter<RecordListBean.DataBean> {

    public RecordAdapter(Context context, List<RecordListBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RecordListBean.DataBean item, int position) {
        viewHolder.setText(item.isTitle(), R.id.title)
                .setText("核销金额：" + item.getMoney() + "元", R.id.money)
                .setText(item.getCreatetime(), R.id.time)
                .setText("用户：" + item.getNickname(), R.id.name)
                .setText("核销：" + item.getClerk_name(), R.id.shop_name);
    }
}
