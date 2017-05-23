package xm.ppq.papaquan.View.Life.selleroof;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.tencent.mapsdk.raster.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.SearchSellerData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by sunshaobei on 2017/5/15.
 */

public class SearchAdapter extends ConcreteAdapter<SearchSellerData.DataBean> {

    private final SharedPreferencesPotting my_login;

    public SearchAdapter(Context context, List<SearchSellerData.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        my_login = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    protected void convert(ViewHolder viewHolder, SearchSellerData.DataBean item, int position) {
        viewHolder.setOnClickListener(v -> {
                LinkSkip.Phone((Activity) getContext(), item.getTel());
        },R.id.p_phone);
        if (item.getMark() != null) {
            if (item.getMark().getCoupon() != 0) {
                if (item.getMark().getCoupon()!=0) {
                    viewHolder.setVisibility(View.VISIBLE,R.id.p_j);
                } else {
                    viewHolder.setVisibility(View.GONE,R.id.p_j);
                }
            }
            if (item.getMark().getPanic() != 0) {
                if (item.getMark().getPanic()!=0) {
                    viewHolder.setVisibility(View.VISIBLE,R.id.p_q);
                } else {
                    viewHolder.setVisibility(View.GONE,R.id.p_q);
                }
            }
            if (item.getMark().getRebate() != 0) {
                if (item.getMark().getRebate()!=0) {
                    viewHolder.setVisibility(View.VISIBLE,R.id.p_z);
                } else {
                    viewHolder.setVisibility(View.GONE,R.id.p_z);
                }
            }
        }

        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getLogo(),(ImageView) viewHolder.getView(R.id.p_icon), R.mipmap.food);
        viewHolder.setText(item.getName(),R.id.title_paiohang);
        viewHolder.setText(item.getAddress(),R.id.p_address);
        viewHolder.setVisibility(View.GONE,R.id.p_follow);
        try {
            start = new LatLng(Double.valueOf(my_login.getItemString("lat")), Double.valueOf(my_login.getItemString("lng")));
        } catch (Exception e) {
            start = new LatLng(0, 0);
        }
        try {
            LatLng end = new LatLng(Double.valueOf(item.getLat()), Double.valueOf(item.getLng()));
            viewHolder.setText(df.format(Calculated_distance(start, end)) + "km",R.id.p_range);
        } catch (Exception e) {
        }
        if (position < 10) {
            viewHolder.setText(String.valueOf(position + 1),R.id.ranking);
        } else {
            viewHolder.setVisibility(View.GONE,R.id.ranking);
        }
    }
    private DecimalFormat df = new DecimalFormat("0.00");
    private LatLng start;
}
