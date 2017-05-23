package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ZanWeiAdapter extends ConcreteAdapter<String> {

    public ZanWeiAdapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {

    }
}
