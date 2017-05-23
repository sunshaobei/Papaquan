package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;

/**
 * Created by sunshaobei on 2017/3/9.
 */

public class ListViewAdapter extends ConcreteAdapter<String> {

    private OnItemListener onItemListener;

    public ListViewAdapter(Context context, List list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(item, R.id.tv_cityitem)
                .setOnClickListener(v -> {
                    if (onItemListener != null) {
                        onItemListener.listener(item, position);
                    }
                }, R.id.tv_cityitem);
    }

    public interface OnItemListener {
        void listener(String result, int position);
    }
}
