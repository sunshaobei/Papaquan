package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.RedCardVipBean;
import xm.ppq.papaquan.R;

/**
 * Created by 红卡特权adapter on 2017/4/10.
 */

public class RedCardAdapter extends ConcreteAdapter<RedCardVipBean> {

    private int type;

    public RedCardAdapter(Context context, List<RedCardVipBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder viewHolder, RedCardVipBean item, int position) {
        if (type == 0) {
            viewHolder.setResources(item.getOne(), R.id.red_one)
                    .setText(item.getTwo(), R.id.red_two)
                    .setText(item.getThree(), R.id.red_three);
        } else {

        }
    }
}