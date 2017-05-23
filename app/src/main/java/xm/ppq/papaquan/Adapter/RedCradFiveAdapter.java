package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.view.View;

import com.tencent.mapsdk.raster.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.CancelPwBean;
import xm.ppq.papaquan.Bean.RedCardFiveBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/4/17.
 */

public class RedCradFiveAdapter extends ConcreteAdapter<RedCardFiveBean.DataBean> {

    private final SharedPreferencesPotting my_login;
    private double lat;
    private double lng;
    private final LatLng latLng;
    private final DecimalFormat df;


    public RedCradFiveAdapter(Context context, List<RedCardFiveBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        my_login = new SharedPreferencesPotting(context, "my_login");
        String lats = my_login.getItemString("lat");
        if (!lats.equals("") && lats != null)
            lat = Double.valueOf(lats);
        String lngs = my_login.getItemString("lng");
        if (!lngs.equals("") && lngs != null)
            lng = Double.valueOf(lngs);
        latLng = new LatLng(lat, lng);
        df = new DecimalFormat("0.00");
    }

    @Override
    protected void convert(ViewHolder viewHolder, RedCardFiveBean.DataBean item, int position) {
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getLogo(), viewHolder.getView(R.id.icon_crf), R.mipmap.food);
        viewHolder.setText(item.getName(), R.id.title_rcf)
                .setText(item.getTitle(), R.id.content_rcf);

        if (item.getDesc() != null)
            viewHolder.setText(Html.fromHtml("今日<font color=#e60012>" + item.getDesc().replace("今天", "").replace("折", "") + "</font>折"), R.id.dis_rcf);
        viewHolder.setText(item.getUsenum() + "使用", R.id.number_rcf);
        String lat = item.getLat();
        String lng = item.getLng();
        try {
            if (item.getKm() != null) {
                viewHolder.setText(df.format(item.getKm())+"km", R.id.range_rcf);
            }else {
                LatLng latLngend = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                double v = Calculated_distance(latLng, latLngend);
                String format = df.format(v);
                viewHolder.setText(format + "km", R.id.range_rcf);
            }
        } catch (Exception e) {

        }

//                .setText(item.getk() + "km", R.id.range_rcf);
        if (item.getUphour().equals("0")) {
            viewHolder.setVisibility(View.VISIBLE, R.id.scare);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.scare);
        }
    }
}
