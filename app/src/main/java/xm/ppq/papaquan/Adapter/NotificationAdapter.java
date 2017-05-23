package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.NotificationBean;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/21.
 */

public class NotificationAdapter extends ConcreteAdapter<NotificationBean> {

    public NotificationAdapter(Context context, List<NotificationBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, NotificationBean item,int position) {
        viewHolder.setText(item.getNick_name(), R.id.nick_name)
                .setText(item.getContent(), R.id.content)
                .setText(item.getTime(), R.id.time)
                .setResources(item.getResource(), R.id.head_portrait);
    }
}
