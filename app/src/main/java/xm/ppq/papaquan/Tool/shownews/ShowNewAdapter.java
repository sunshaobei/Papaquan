package xm.ppq.papaquan.Tool.shownews;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.typewriting.SpanStringUtils;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ShowNewAdapter extends ConcreteAdapter<ShowNewsBigBean.Data.Comments> {

    public ShowNewAdapter(Context context, List<ShowNewsBigBean.Data.Comments> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ShowNewsBigBean.Data.Comments item, int position) {
//        if (position == 0) {
//            viewHolder.setResources(R.drawable.news_discuss, R.id.source_criticism);
//        } else {
//            viewHolder.setResources(R.drawable.zanwei, R.id.source_criticism);
//        }
        if (item.img == null||item.img.equals("")) {
            viewHolder.setText(item.nickname + "：", R.id.host_criticism)
                    .setSpanned(SpanStringUtils.getEmotionContent(getContext(), item.content), R.id.content_criticism);
        } else {
            viewHolder.setText(item.nickname + "：", R.id.host_criticism)
                    .setSpanned(SpanStringUtils.getEmotionContent(getContext(), item.content + "[图片]"), R.id.content_criticism);
        }
    }
}