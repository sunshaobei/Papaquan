package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.GetComBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.shownews.DiscussUtil;
import xm.ppq.papaquan.View.trendtopicdetail.Round_Trend;

/**
 * Created by Administrator on 2017/3/3.
 */

public class DoubleDiscussAdapter extends ConcreteAdapter<GetComBean.Data.Reply> implements PapanewsInterface {

    public EmotionPopupWindow emotionPopupWindow;
    public String tid;
    public String cid;
    private Round_Trend round_trend;

    public DoubleDiscussAdapter(Context context, List<GetComBean.Data.Reply> list, int itemLayout) {
        super(context, list, itemLayout);
        round_trend = (Round_Trend) context;
        emotionPopupWindow = new EmotionPopupWindow((Activity) context, this, "回复评论");
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    protected void convert(ViewHolder viewHolder, GetComBean.Data.Reply item, int position) {
        if (item.accept_nickname.equals("")) {
            viewHolder.setSpanned(DiscussUtil.getAllResult("#5b6a91", item.reply_nickname + "：", item.content), R.id.double_discuss_content);
        } else {
            viewHolder.setSpanned(DiscussUtil.getAllResult("#5b6a91", item.accept_nickname, item.content, item.reply_nickname), R.id.double_discuss_content);
        }
        viewHolder.setOnClickListener(v -> {
            emotionPopupWindow.setRid(item.id);
            emotionPopupWindow.setCid(cid, item.reply_uid, tid);
            emotionPopupWindow.Show(v);
        }, R.id.double_all_lin);
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        round_trend.setCri();
    }

    @Override
    public void ShowShare(View view) {

    }
}
