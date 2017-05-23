package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.ImageUtil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

import static xm.ppq.papaquan.Tool.ImageUtil.drawableToBitmap;

/**
 * Created by Administrator on 2017/2/23.
 */

public class PapaTopicAdapter extends BaseRecyclerAdapter<PapaTopicBean.DataBean> {

    private OnRecyclerItemListener recycler;

    public PapaTopicAdapter(Context context, List<PapaTopicBean.DataBean> list, int layout) {
        super(context, list, layout);
    }

    public void setRecycler(OnRecyclerItemListener recycler) {
        this.recycler = recycler;
    }

    @Override
    protected void Evaluate(REViewHolder viewHolder, PapaTopicBean.DataBean item, int position) {
        viewHolder.setText(item.getTitle(), R.id.title_topic)
                .setText("热度:" + item.getHeat() + "   参与:" + item.getJoinnum(), R.id.interfix_topic)
                .setOnClickener(v -> {
                    if (recycler != null) {
                        recycler.RecyclerItem(v, item, position);
                    }
                }, R.id.papa_topic_all);
        if (item.getTop().equals("1")) {
            viewHolder.setTextVisi(R.id.top_text, View.VISIBLE);
        } else {
            viewHolder.setTextVisi(R.id.top_text, View.GONE);
        }
        ImageLoading.Circular((Activity) getContext(), BaseUrl.BITMAP + item.getImg(), R.drawable.topic_item, viewHolder.getView(R.id.background_topic), 60);
    }

}