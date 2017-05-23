package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.ReplyListBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.shownews.DiscussUtil;
import xm.ppq.papaquan.View.review_details.Review_detailsActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class ReviewAdapter extends ConcreteAdapter<ReplyListBean.Data> implements PapanewsInterface {

    private EmotionPopupWindow emotionPopupWindow;
    private String tid;
    private String cid;
    private Review_detailsActivity review_detailsActivity;

    public ReviewAdapter(Context context, List<ReplyListBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
        review_detailsActivity = (Review_detailsActivity) context;
        emotionPopupWindow = new EmotionPopupWindow((Activity) getContext(), this, "回复评论");
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ReplyListBean.Data item, int position) {
        viewHolder.setText(item.time, R.id.reply_time)
                .setText(item.replyNickname, R.id.reply_nick)
                .setOnClickListener(v -> {
                    emotionPopupWindow.setRid(item.id);
                    emotionPopupWindow.setCid(cid, item.reply_uid, tid);
                    emotionPopupWindow.Show(v);
                }, R.id.rede_all_lin);
        ImageLoading.Circular((Activity) getContext(), item.replyHeadurl, R.drawable.default_icon, viewHolder.getView(R.id.reply_head));
        if (item.img != null) {
            ImageLoading.common((Activity) getContext(), item.img, viewHolder.getView(R.id.reply_img), R.drawable.news_pic);
        }
        if (item.toNickname != null) {
            viewHolder.setSpanned(DiscussUtil.getAllResult("#f86e1a", "回复" + item.toNickname + "：", item.content), R.id.reply_content);
        } else {
            viewHolder.setText(item.content, R.id.reply_content);
        }
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        review_detailsActivity.ReplyList(cid);
    }

    @Override
    public void ShowShare(View view) {

    }
}
