package xm.ppq.papaquan.Adapter;


import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.CityBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.Stringutil;

/**
 * Created by sunshaobei on 2017/3/18.
 */

public class CitySearchAdaptergrid extends ConcreteAdapter<String>{
    public CitySearchAdaptergrid(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);

    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(item,R.id.tv_cityitem);
    }
}
