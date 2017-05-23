package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.TreeBean;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TreeListAdapte extends ConcreteAdapter<TreeBean> {

    public TreeListAdapte(Context context, List<TreeBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, TreeBean item, int position) {
        viewHolder.setText(item.getResult(), R.id.content);
        if (item.isStuats() == false) {
            viewHolder.setVisibility(View.GONE, R.id.select);
        } else {
            viewHolder.setVisibility(View.VISIBLE, R.id.select);
        }
    }
}
