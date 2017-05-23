package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ChatAdapter extends ConcreteAdapter<String> {

    public ChatAdapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(item, R.id.time_chat);
    }
}
