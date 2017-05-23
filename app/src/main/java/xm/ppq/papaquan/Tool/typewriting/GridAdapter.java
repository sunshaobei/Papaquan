package xm.ppq.papaquan.Tool.typewriting;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/27.
 */

public class GridAdapter extends ConcreteAdapter<String> {

    public GridAdapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
        list.add("[删除]");
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item,int position) {
        viewHolder.setResources(EmotionUtils.EMOTION_ALL_MAP.get(item), R.id.icon);
    }
}