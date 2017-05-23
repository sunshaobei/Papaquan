package xm.ppq.papaquan.Adapter.life;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.BalanceBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 13:37.
 */

public class BalanceAdapter extends ConcreteAdapter<BalanceBean.DataBean> {

    public BalanceAdapter(Context context, List<BalanceBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, BalanceBean.DataBean item, int position) {
        viewHolder.setText(item.getRemark(), R.id.title)
                .setText("余额：￥" + item.getNowmoney(), R.id.subtitle)
                .setText(item.getCreatetime(), R.id.time);
        TextView textView = viewHolder.getView(R.id.earn);
        textView.setText(item.getMoney());
        if (item.getMoney().startsWith("-")) {
            textView.setTextColor(Color.parseColor("#00CC99"));
        } else {
            textView.setTextColor(Color.parseColor("#e60012"));
        }
    }
}
