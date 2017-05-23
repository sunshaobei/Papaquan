package xm.ppq.papaquan.Adapter;

import android.content.Context;

import com.tencent.mapsdk.raster.model.LatLng;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 10:58.
 */

public class DiscountAdapter extends ConcreteAdapter<DiscountBean.DataBean> {

    private DecimalFormat df = new DecimalFormat("0.00");
    private LatLng start;

    public DiscountAdapter(Context context, List<DiscountBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public LatLng getStart() {
        return start;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    @Override
    protected void convert(ViewHolder viewHolder, DiscountBean.DataBean item, int position) {
        viewHolder.setText(item.getName(), R.id.title_scare)
                .setText(item.getTitle(), R.id.content_scare1)
                .setText(item.getUsenum() + "使用", R.id.user_number);
        if (item.getDiscount() == null) {
            viewHolder.setText("5", R.id.agio);
        } else {
            viewHolder.setText(String.valueOf(item.getDiscount()), R.id.agio);
        }
        if (item.getKm() != 0) {
            double km = item.getKm();
            viewHolder.setText(df.format(km) + "km", R.id.be_away_from);
        } else {
            double lat = 0;
            double lng = 0;
            try {
                if (item.getLat() != null) lat = Double.valueOf(item.getLat());
                if (item.getLng() != null) lng = Double.valueOf(item.getLng());
            } catch (NumberFormatException e) {

            }
            LatLng end = new LatLng(lat, lng);
            viewHolder.setText(df.format(Calculated_distance(start, end)) + "km", R.id.be_away_from);
        }
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getLogo(), viewHolder.getView(R.id.dc_icon), R.mipmap.food);
    }



}