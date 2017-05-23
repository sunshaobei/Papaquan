package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.graphics.Color;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.ClassifiListBean;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ClassifiListAdapter extends ConcreteAdapter<ClassifiListBean> {

    private int on = Color.parseColor("#E60012");
    private int off = Color.parseColor("#000000");

    public ClassifiListAdapter(Context context, List<ClassifiListBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ClassifiListBean item, int position) {
        viewHolder.setText(item.getContent(), R.id.subclass);
        if (item.isSelect() == false) {
            viewHolder.setTextColor(on, R.id.subclass);
        } else {
            viewHolder.setTextColor(off, R.id.subclass);
        }
    }
}