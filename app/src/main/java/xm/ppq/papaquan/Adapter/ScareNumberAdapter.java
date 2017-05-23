package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.ScareNumberBean;

/**
 * Created by Administrator on 2017/4/19.
 */

public class ScareNumberAdapter extends ConcreteAdapter<ScareNumberBean.DataBean> {

    public ScareNumberAdapter(Context context, List<ScareNumberBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ScareNumberBean.DataBean item, int position) {

    }
}
