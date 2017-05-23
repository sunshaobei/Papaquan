package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.NewsReplyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ReplyAdapter extends ConcreteAdapter<NewsReplyBean.Data> implements PapanewsInterface {

    private EmotionPopupWindow emotionPopupWindow;

    public ReplyAdapter(Context context, List<NewsReplyBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
        emotionPopupWindow = new EmotionPopupWindow((Activity) getContext(), this, "回复评论");
    }

    @Override
    protected void convert(ViewHolder viewHolder, NewsReplyBean.Data item, int position) {
        viewHolder.setText(item.topicUserdata.nickname, R.id.reply_ta_name)
                .setText(item.topicdata.content, R.id.reply_content)
                .setText(item.replydata.nickname, R.id.reply_name)
                .setText(item.time, R.id.reply_time)
                .setText(item.content, R.id.reply_discuss)
                .setOnClickListener(v -> {
                    emotionPopupWindow.setCid(item.cid, item.replydata.uid, item.tid);
                    emotionPopupWindow.setRid(item.id);
                    emotionPopupWindow.Show(v);
                }, R.id.reply);
        if (item.tonickname != null) {
            viewHolder.setText(item.mynickname + "对" + item.tonickname + "说：" + item.saycontent, R.id.reply_reply);
        } else {
            viewHolder.setText(item.mynickname + "说：" + item.saycontent, R.id.reply_reply);
        }
        ImageLoading.common(getContext(), item.topicUserdata.headurl, viewHolder.getView(R.id.reply_ta_icon), R.drawable.default_icon_zheng);
        ImageLoading.Circular((Activity) getContext(), item.replydata.headurl, R.drawable.default_icon, viewHolder.getView(R.id.reply_icon));
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        ToastShow("回复成功");
    }

    @Override
    public void ShowShare(View view) {

    }
}
