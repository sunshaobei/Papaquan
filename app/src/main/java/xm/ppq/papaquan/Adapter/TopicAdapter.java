package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.TopicBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TopicAdapter extends ConcreteAdapter<TopicBean.Data> {

    private int type;

    public TopicAdapter(Context context, List<TopicBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder viewHolder, TopicBean.Data item, int position) {
        if (type == 1) {//关注
            ImageLoading.Circular((Activity) getContext(), item.headurl + "/200x200", R.drawable.default_icon, viewHolder.getView(R.id.topic_tx));
            viewHolder.setText(item.nickname, R.id.topic_title);

        } else if (type == 2) {//话题

        }
    }
}
