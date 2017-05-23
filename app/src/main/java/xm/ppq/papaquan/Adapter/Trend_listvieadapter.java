package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.Image;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by sunshaobei on 2017/3/31.
 */

public class Trend_listvieadapter extends ConcreteAdapter<String> {

    public Trend_listvieadapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        ImageLoading.loadIntoUseFitWidth(getContext(), BaseUrl.BITMAP + item, R.drawable.news_pic, viewHolder.getView(R.id.image_view));
    }
}
