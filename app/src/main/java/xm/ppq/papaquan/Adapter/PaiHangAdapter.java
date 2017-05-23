package xm.ppq.papaquan.Adapter;

import android.app.Activity;
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

import xm.ppq.papaquan.Bean.life.SeventhBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by Administrator on 2017/4/13.
 */

public class PaiHangAdapter extends RecyclerView.Adapter<PaiHangAdapter.Holder> {

    private Context context;
    private int itemLayout;
    private LatLng start;
    private List<SeventhBean.DataBean> list;

    public PaiHangAdapter(Context context, List<SeventhBean.DataBean> list, int itemLayout) {
        this.context = context;
        this.itemLayout = itemLayout;
        this.list = list;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    public void setList(List<SeventhBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(itemLayout, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setIsRecyclable(false);
        holder.pai_re.setOnClickListener(v -> {
            Intent intent = new Intent(context, Merchant_HomepageActivity.class);
            intent.putExtra("sid", String.valueOf(list.get(position).getId()));
            context.startActivity(intent);
        });
        holder.p_phone.setOnClickListener(v -> {
            LinkSkip.Phone((Activity) context, list.get(position).getTel());
        });
        if (list.get(position).getMark() != null) {
            if (list.get(position).getMark().getCoupon() != null) {
                if (!list.get(position).getMark().getCoupon().equals("0")) {
                    holder.p_j.setVisibility(View.VISIBLE);
                } else {
                    holder.p_j.setVisibility(View.GONE);
                }
            }
            if (list.get(position).getMark().getPanic() != null) {
                if (!list.get(position).getMark().getPanic().equals("0")) {
                    holder.p_q.setVisibility(View.VISIBLE);
                } else {
                    holder.p_q.setVisibility(View.GONE);
                }
            }
            if (list.get(position).getMark().getRebate() != null) {
                if (!list.get(position).getMark().getRebate().equals("0")) {
                    holder.p_z.setVisibility(View.VISIBLE);
                } else {
                    holder.p_z.setVisibility(View.GONE);
                }
            }
        }
        ImageLoading.common(context, BaseUrl.BITMAP + list.get(position).getLogo(), holder.p_icon, R.mipmap.food);
        holder.title_paiohang.setText(list.get(position).getName());
        holder.p_address.setText(list.get(position).getAddress());
        holder.p_follow.setText(list.get(position).getBrowse() + "人关注");
        try {
            LatLng end = new LatLng(Double.valueOf(list.get(position).getLat()), Double.valueOf(list.get(position).getLng()));
            holder.p_range.setText(df.format(Calculated_distance(start, end)) + "km");
        } catch (Exception e) {
        }
        if (position < 10) {
            holder.ranking.setText(String.valueOf(position + 1));
        } else {
            holder.ranking.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView title_paiohang;
        TextView ranking;
        TextView p_address;
        ImageView p_icon;
        TextView p_follow;
        TextView p_range;
        ImageView p_z;
        ImageView p_j;
        ImageView p_q;
        ImageView p_phone;
        RelativeLayout pai_re;

        public Holder(View itemView) {
            super(itemView);
            title_paiohang = (TextView) itemView.findViewById(R.id.title_paiohang);
            p_address = (TextView) itemView.findViewById(R.id.p_address);
            ranking = (TextView) itemView.findViewById(R.id.ranking);
            p_follow = (TextView) itemView.findViewById(R.id.p_follow);
            p_range = (TextView) itemView.findViewById(R.id.p_range);
            p_icon = (ImageView) itemView.findViewById(R.id.p_icon);
            p_z = (ImageView) itemView.findViewById(R.id.p_z);
            p_j = (ImageView) itemView.findViewById(R.id.p_j);
            p_q = (ImageView) itemView.findViewById(R.id.p_q);
            p_phone = (ImageView) itemView.findViewById(R.id.p_phone);
            pai_re = (RelativeLayout) itemView.findViewById(R.id.pai_re);
        }
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    private double Calculated_distance(LatLng start, LatLng end) {
        if (start != null) {
            double lat1 = (Math.PI / 180) * start.getLatitude();
            double lat2 = (Math.PI / 180) * end.getLatitude();

            double lon1 = (Math.PI / 180) * start.getLongitude();
            double lon2 = (Math.PI / 180) * end.getLongitude();

            //地球半径
            double R = 6371;

            //两点间距离 km，如果想要米的话，结果*1000就可以了
            double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
            if (d > 500) {
                d = 500;
            }
            return d;
        } else {
            return 0;
        }
    }
}