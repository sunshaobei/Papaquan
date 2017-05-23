package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mapsdk.raster.model.LatLng;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;

/**
 * Created by EdgeDi on 10:58.
 */

public class LifeChooseAdapter extends RecyclerView.Adapter<LifeChooseAdapter.Holder> {

    private ArrayList<ChooseBean.DataBean> list;
    private Activity context;
    private int layout;
    private DecimalFormat df = new DecimalFormat("0.00");
    private LatLng start;

    public LifeChooseAdapter(ArrayList<ChooseBean.DataBean> list, Activity context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    public LatLng getStart() {
        return start;
    }

    public void setStart(LatLng start) {
        this.start = start;
    }

    public ArrayList<ChooseBean.DataBean> getList() {
        return list;
    }

    public void setList(ArrayList<ChooseBean.DataBean> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setIsRecyclable(false);
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
        holder.title_scare.setText(list.get(position).getName());
        holder.be_away_from.setText(df.format(Calculated_distance(start, end)) + "km");
        holder.jinri.setVisibility(View.GONE);
        ImageLoading.common(context, BaseUrl.BITMAP + list.get(position).getLogo(), holder.dc_icon, R.mipmap.food);
        if (list.get(position).getCoupon().size() > 0) {
            if (list.get(position).getCoupon().get(0).getUphour().equals("0")) {
                holder.is_bespoke.setVisibility(View.VISIBLE);
            } else {
                holder.is_bespoke.setVisibility(View.GONE);
            }
            holder.content_scare1.setText(list.get(position).getCoupon().get(0).getTitle());
            holder.agio.setText("￥" + list.get(position).getCoupon().get(0).getPrice());
            holder.zhekou.setText(list.get(position).getCoupon().get(0).getCostprice() + "元");
            TextView_Delete(holder.zhekou);
            if (holder.list.getChildCount() != list.get(position).getCoupon().size() - 1) {
                for (int i = 0; i < list.get(position).getCoupon().size(); i++) {
                    if (i > 0) {
                        String title;
                        if (list.get(position).getCoupon().get(i).getUphour().equals("0")) {
                            title = "【免预约】" + list.get(position).getCoupon().get(i).getTitle();
                        } else {
                            title = list.get(position).getCoupon().get(i).getTitle();
                        }
                        holder.list.addView(CreateText(title, list.get(position).getCoupon().get(i).getCid()));
                    }
                }
            }
        }
        holder.discount_lin_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getCoupon().size() > 0) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("cid", list.get(position).getCoupon().get(0).getCid());
                    context.startActivityForResult(intent, IntentCode.RequestCode.TOPRODUCTDETAIL);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView title_scare;
        TextView be_away_from;
        TextView jinri;
        TextView agio;
        TextView content_scare1;
        TextView zhekou;
        TextView is_bespoke;
        ImageView dc_icon;
        LinearLayout list;
        RelativeLayout discount_lin_all;

        public Holder(View convertView) {
            super(convertView);
            title_scare = (TextView) convertView.findViewById(R.id.title_scare);
            be_away_from = (TextView) convertView.findViewById(R.id.be_away_from);
            jinri = (TextView) convertView.findViewById(R.id.jinri);
            agio = (TextView) convertView.findViewById(R.id.agio);
            zhekou = (TextView) convertView.findViewById(R.id.zhekou);
            is_bespoke = (TextView) convertView.findViewById(R.id.is_bespoke);
            content_scare1 = (TextView) convertView.findViewById(R.id.content_scare1);
            dc_icon = (ImageView) convertView.findViewById(R.id.dc_icon);
            list = (LinearLayout) convertView.findViewById(R.id.list);
            discount_lin_all = (RelativeLayout) convertView.findViewById(R.id.discount_lin_all);
        }
    }

    public View CreateText(String result, int cid) {
        View view = LayoutInflater.from(context).inflate(R.layout.choose_bottom_item, null);
        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("cid", cid);
            context.startActivity(intent);
        });
        TextView bottom_content = (TextView) view.findViewById(R.id.bottom_content);
        bottom_content.setText(result);
        return view;
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
            double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
            if (d > 500) {
                d = 500;
            }
            return d;
        } else {
            return 0;
        }
    }

    protected void TextView_Delete(TextView... textView) {
        for (int i = 0; i < textView.length; i++) {
            textView[i].getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

}