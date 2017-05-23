package xm.ppq.papaquan.View.Life.panic_buying;

import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.Panic_buyUserData;
import xm.ppq.papaquan.R;

/**
 * Created by sunshaobei on 2017/4/27.
 */

public class BuyUserListAdapter extends ConcreteAdapter<Panic_buyUserData.DataBean> {

    public BuyUserListAdapter(Context context, List<Panic_buyUserData.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, Panic_buyUserData.DataBean item, int position) {
        viewHolder.setText(item.getNickname(), R.id.name)
                .setText(item.getRemark(), R.id.content)
                .setText(item.getPaytime(), R.id.time);
        int is_vip = item.getIs_vip();
        if (is_vip == 1)
            viewHolder.setVisibility(View.VISIBLE, R.id.redcard);
        else viewHolder.setVisibility(View.INVISIBLE, R.id.redcard);
    }
}
