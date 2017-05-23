package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import com.tencent.mapsdk.raster.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.ClassificationBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ContentClassifyAdapter extends ConcreteAdapter<ClassificationBean.DataBean> {

    private LatLng start;
    private DecimalFormat df = new DecimalFormat("0.00");

    public ContentClassifyAdapter(Context context, List<ClassificationBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    @Override
    protected void convert(ViewHolder viewHolder, ClassificationBean.DataBean item, int position) {
        try {
            LatLng end = new LatLng(Double.valueOf(item.getLat()), Double.valueOf(item.getLng()));
            viewHolder.setText(df.format(Calculated_distance(start, end)) + "km", R.id.km);
        } catch (Exception e) {
            e.printStackTrace();
        }
        viewHolder.setText(item.getName(), R.id.name)
                .setText(item.getAddress(), R.id.address)
                .setText(item.getBrowse() + "人关注", R.id.follow)
                .setOnClickListener(v -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        LinkSkip.Phone((Activity) getContext(), item.getTel());
                    }
                }, R.id.phone);
    }
}
