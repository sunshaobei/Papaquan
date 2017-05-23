package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.JudgeOfBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.Reply_Judge_Dialog;

/**
 * Created by Administrator on 2017/4/15.
 */

public class JudgeOfAdapter extends ConcreteAdapter<JudgeOfBean.DataBean> {

    private JudgeGridAdapter adapter;
    private Reply_Judge_Dialog reply_judge_dialog;
    private String sid;

    public JudgeOfAdapter(Context context, List<JudgeOfBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        reply_judge_dialog = new Reply_Judge_Dialog(context, 1);
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    protected void convert(ViewHolder viewHolder, JudgeOfBean.DataBean item, int position) {
        reply_judge_dialog.setDataBean(item,this);
        adapter = new JudgeGridAdapter(getContext(), item.getPicture(), R.layout.judge_item_grid);
        ((GridView) viewHolder.getView(R.id.judge_item_grid)).setAdapter(adapter);
        if (item.getType() == 0) {
            viewHolder.setResources(R.mipmap.jing, R.id.type_icon);
        } else if (item.getType() == 2) {
            viewHolder.setResources(R.mipmap.qiang, R.id.type_icon);
        } else {
            viewHolder.setResources(R.mipmap.zhe, R.id.type_icon);
        }
        viewHolder.setText(item.getTitle(), R.id.title)
                .setText(item.getContent(), R.id.content)
                .setText(item.getNickname(), R.id.name)
                .setText(item.getCreatetime(), R.id.time);
        if (item.getBusiness_reply() != null) {
            viewHolder.setText("已回复", R.id.reply_btn)
                    .setVisibility(View.VISIBLE, R.id.reply)
                    .setText("商家回复:" + item.getBusiness_reply().toString(), R.id.reply);
        } else {
            viewHolder.setOnClickListener(v -> reply_judge_dialog.setIDAll(String.valueOf(item.getId()), sid).show(), R.id.reply_btn)
                    .setText("回复", R.id.reply_btn)
                    .setVisibility(View.GONE, R.id.reply);
        }
    }

    private class JudgeGridAdapter extends ConcreteAdapter<String> {

        JudgeGridAdapter(Context context, List<String> list, int itemLayout) {
            super(context, list, itemLayout);
        }

        @Override
        protected void convert(ViewHolder viewHolder, String item, int position) {
            ImageLoading.common(getContext(), BaseUrl.BITMAP + item, viewHolder.getView(R.id.item), R.mipmap.food);
        }
    }
}