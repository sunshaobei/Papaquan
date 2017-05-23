package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mapsdk.raster.model.LatLng;

import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;

/**
 * Created by EdgeDi on 10:58.
 */

public class LifeDiscountAdapter extends RecyclerView.Adapter<LifeDiscountAdapter.Holder> {

    private Context context;
    private int layout;
    private List<DiscountBean.DataBean> list;

    public LifeDiscountAdapter(Context context, List<DiscountBean.DataBean> list, int itemLayout) {
        this.context = context;
        this.list = list;
        this.layout = itemLayout;
    }

    public void setList(List<DiscountBean.DataBean> list) {
        this.list = list;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    private DecimalFormat df = new DecimalFormat("0.00");
    private LatLng start;

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.discount_lin_all.setOnClickListener(v -> {
            Intent intent = new Intent(context, RestaurantActivity.class);
            intent.putExtra("sid", String.valueOf(list.get(position).getSid()));
            context.startActivity(intent);
        });
        holder.title_scare.setText(list.get(position).getName());
        holder.content_scare1.setText(list.get(position).getTitle());
        holder.user_number.setText(list.get(position).getUsenum() + "使用");
        if (list.get(position).getDiscount() == null) {
            holder.agio.setText("5");
        } else {
            holder.agio.setText(String.valueOf(list.get(position).getDiscount()));
        }
        if (list.get(position).getKm() != 0) {
            double km = list.get(position).getKm();
            holder.be_away_from.setText(df.format(km) + "km");
        } else {
            double lat = 0;
            double lng = 0;
            try {
                if (list.get(position).getLat() != null)
                    lat = Double.valueOf(list.get(position).getLat());
                if (list.get(position).getLng() != null)
                    lng = Double.valueOf(list.get(position).getLng());
            } catch (NumberFormatException e) {

            }
            LatLng end = new LatLng(lat, lng);
            holder.be_away_from.setText(df.format(Calculated_distance(start, end)) + "km");
        }
        ImageLoading.common(context, BaseUrl.BITMAP + list.get(position).getLogo(), holder.dc_icon, R.mipmap.food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView title_scare;
        TextView content_scare1;
        TextView user_number;
        TextView agio;
        TextView be_away_from;
        ImageView dc_icon;
        RelativeLayout discount_lin_all;

        public Holder(View itemView) {
            super(itemView);
            title_scare = (TextView) itemView.findViewById(R.id.title_scare);
            content_scare1 = (TextView) itemView.findViewById(R.id.content_scare1);
            user_number = (TextView) itemView.findViewById(R.id.user_number);
            agio = (TextView) itemView.findViewById(R.id.agio);
            be_away_from = (TextView) itemView.findViewById(R.id.be_away_from);
            dc_icon = (ImageView) itemView.findViewById(R.id.dc_icon);
            discount_lin_all = (RelativeLayout) itemView.findViewById(R.id.discount_lin_all);
        }
    }

    private double Calculated_distance(LatLng start, LatLng end) {
        if (start != null) {
            double lat1 = (Math.PI / 180) * start.getLatitude();
            double lat2 = (Math.PI / 180) * end.getLatitude();
            double lon1 = (Math.PI / 180) * start.getLongitude();
            double lon2 = (Math.PI / 180) * end.getLongitude();
            //地球半径
            double R = 6371;
            //两点间距离 km，如果想要米的话，结果*1000就可以了
            double b = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
            ;
            if (b > 500) {
                b = 500;
            }
            return b;
        } else {
            return 0;
        }
    }

}