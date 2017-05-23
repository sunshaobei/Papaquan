package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.DisComment;
import xm.ppq.papaquan.Bean.DisRepBean;
import xm.ppq.papaquan.Presenter.discuss.ReplyDisListenre;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.discuss_reply.Discuss_ReplyActivity;

/**
 * Created by Administrator on 2017/3/2.
 */

public class DiscussReplyAdapter extends ConcreteAdapter<DisComment.DataBean> implements PapanewsInterface {

    private EmotionPopupWindow popupWindow;

    public DiscussReplyAdapter(Context context, List list, int itemLayout) {
        super(context, list, itemLayout);
        popupWindow = new EmotionPopupWindow((Activity) context, this, "回复评论");
    }

    @Override
    protected void convert(ViewHolder viewHolder, DisComment.DataBean item, int position) {
        viewHolder.setText(item.getCommentuser().getNickname(), R.id.discuss_name)
                .setText(item.getTime(), R.id.discuss_time)
                .setText(item.getContent(), R.id.discuss_discuss)
                .setText(item.getSenduser().getNickname(), R.id.discuss_ta_name)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.setCid(String.valueOf(item.getId()), item.getCommentuser().getUid(), String.valueOf(item.getTid()));
                        popupWindow.setRid(item.getSenduser().getUid());
                        popupWindow.Show(v);
                    }
                }, R.id.reply)
                .setText(item.getTopiclist().getContent(), R.id.discuss_content);
        ImageLoading.Circular((Activity) getContext(), item.getCommentuser().getHeadurl(), R.drawable.default_icon, viewHolder.getView(R.id.discuss_icon));
        ImageLoading.common(getContext(), item.getSenduser().getHeadurl(), viewHolder.getView(R.id.discuss_ta_icon), R.drawable.default_icon_zheng);
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {

    }

    @Override
    public void ShowShare(View view) {

    }
}