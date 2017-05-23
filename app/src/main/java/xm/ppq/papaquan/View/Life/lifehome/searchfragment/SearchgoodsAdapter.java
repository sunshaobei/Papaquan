package xm.ppq.papaquan.View.Life.lifehome.searchfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.mapsdk.raster.model.LatLng;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.ScarePastAdapter;
import xm.ppq.papaquan.Adapter.base.REHolder;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.SearchGoodsData1;
import xm.ppq.papaquan.Bean.life.SearchGoodsData2;
import xm.ppq.papaquan.Bean.life.SearchGoodsData3;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.life.Tool.TimeTextView;

/**
 * Created by sunshaobei on 2017/5/10.
 */

public class SearchgoodsAdapter<T> extends ConcreteAdapter<T> {
    private int type;
    private final SharedPreferencesPotting my_login;

    public SearchgoodsAdapter(Context context, List<T> list, int itemLayout) {
        super(context, list, itemLayout);
        my_login = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    protected void convert(ViewHolder viewHolder, T data, int position) {
        switch (type) {
            case 1:
                SearchGoodsData1.DataBean item1 = (SearchGoodsData1.DataBean) data;
                viewHolder.setText(item1.getName(), R.id.title_scare)
                        .setText(item1.getTitle(), R.id.content_scare1);
                ImageView imageView = viewHolder.getView(R.id.dc_icon);
                Glide.with(getContext()).load(BaseUrl.BITMAP + item1.getLogo()).into(imageView);
                int uphour = item1.getUphour();
                if (uphour == 0) {
                    viewHolder.setVisibility(View.VISIBLE, R.id.is_bespoke);
                } else viewHolder.setVisibility(View.GONE, R.id.is_bespoke);
                viewHolder.setText(item1.getDiscount() + "", R.id.agio);
                if (item1.getUsenum() == 0)
                    viewHolder.setVisibility(View.GONE, R.id.user_number);
                else {
                    viewHolder.setVisibility(View.VISIBLE, R.id.user_number);
                    viewHolder.setText(item1.getUsenum() + "人使用", R.id.user_number);
                }
                String lat = item1.getLat();
                String lng = item1.getLng();
                String lat1 = my_login.getItemString("lat");
                String lng1 = my_login.getItemString("lng");
                LatLng latLng = null;
                if (!lat1.equals("") && lat1 != null) {
                    latLng = new LatLng(Double.valueOf(lat1), Double.valueOf(lng1));
                } else {
                    latLng = new LatLng(0, 0);
                }

                LatLng latLng1 = null;
                try {
                    latLng1 = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                } catch (Exception e) {
                    latLng1 = new LatLng(0, 0);
                }
                double v = Calculated_distance(latLng1, latLng);
                DecimalFormat df = new DecimalFormat("0.00");
                String format = df.format(v);
                viewHolder.setText(format + "km", R.id.be_away_from);
                break;
            case 2:
                viewHolder.setVisibility(View.GONE, R.id.jinri);
                SearchGoodsData2.DataBean item2 = (SearchGoodsData2.DataBean) data;
                viewHolder.setText(item2.getName(), R.id.title_scare)
                        .setText(item2.getTitle(), R.id.content_scare1);
                ImageView imageView2 = viewHolder.getView(R.id.dc_icon);
                Glide.with(getContext()).load(BaseUrl.BITMAP + item2.getImg()).into(imageView2);
                int uphour1 = item2.getUphour();
                if (uphour1 == 0) {
                    viewHolder.setVisibility(View.VISIBLE, R.id.is_bespoke);
                } else viewHolder.setVisibility(View.GONE, R.id.is_bespoke);
                viewHolder.setText("￥" + item2.getPrice() + "", R.id.agio);
                TextView tv1 = viewHolder.getView(R.id.agio);
                tv1.setPadding(0, 0, 10, 0);
                viewHolder.setText(item2.getCostprice() + "", R.id.zhekou);
                TextView tv = viewHolder.getView(R.id.zhekou);
                tv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if (item2.getBuynum() == 0)
                    viewHolder.setVisibility(View.GONE, R.id.user_number);
                else {
                    viewHolder.setVisibility(View.VISIBLE, R.id.user_number);
                    viewHolder.setText(item2.getBuynum() + "人购买", R.id.user_number);
                }
                String lat2 = item2.getLat();
                String lng2 = item2.getLng();
                String lat3 = my_login.getItemString("lat");
                String lng3 = my_login.getItemString("lng");
                LatLng latLng2 = null;
                if (!lat3.equals("") && lat3 != null) {
                    latLng2 = new LatLng(Double.valueOf(lat3), Double.valueOf(lng3));
                } else {
                    latLng2 = new LatLng(0, 0);
                }

                LatLng latLng3 = null;
                try {
                    latLng3 = new LatLng(Double.valueOf(lat2), Double.valueOf(lng2));
                } catch (Exception e) {
                    latLng3 = new LatLng(0, 0);
                }
                double v2 = Calculated_distance(latLng3, latLng2);
                DecimalFormat df2 = new DecimalFormat("0.00");
                String format2 = df2.format(v2);
                viewHolder.setText(format2 + "km", R.id.be_away_from);
                break;
            case 3:
                SearchGoodsData3.DataBean item3 = (SearchGoodsData3.DataBean) data;
                viewHolder.setText(item3.getName(), R.id.title_scare)
                        .setText(item3.getTitle(), R.id.content_scare1);
                ImageView imageView3 = viewHolder.getView(R.id.icon_scare_past);
                Glide.with(getContext()).load(BaseUrl.BITMAP + item3.getImg()).into(imageView3);
                String uphour2 = item3.getUphour();
                if (uphour2.equals("0")) {
                    viewHolder.setVisibility(View.VISIBLE, R.id.left_top);
                } else viewHolder.setVisibility(View.GONE, R.id.left_top);
                viewHolder.setText("￥" + item3.getBuying_price() + "", R.id.red_scare);
                TextView tv2 = viewHolder.getView(R.id.money_yuan);
                tv2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                tv2.setText(item3.getPrice() + "");

                if (item3.getBrowse() == 0)
                    viewHolder.setVisibility(View.GONE, R.id.follow_scare);
                else {
                    viewHolder.setVisibility(View.VISIBLE, R.id.follow_scare);
                    viewHolder.setText(item3.getBrowse() + "人关注", R.id.follow_scare);
                }

                switch (item3.getStatus()) {
                    case 1:
                        setTextBackGround("提醒我", getContext().getResources().getDrawable(R.drawable.pass_make_green), viewHolder.getView(R.id.make_use_of_btn));
                        viewHolder.setText(Color.parseColor("#33cc99"), R.id.follow_scare);
                        setTimeColor(Color.parseColor("#33cc99"), viewHolder.getView(R.id.count_down), TimeTextView.START);
                        setTextTime(item3.getStart_time(), viewHolder.getView(R.id.count_down));
                        break;
                    case 2:
                        setTextBackGround("抢购中", getContext().getResources().getDrawable(R.drawable.pass_make), viewHolder.getView(R.id.make_use_of_btn));
                        viewHolder.setText(Color.parseColor("#e60012"), R.id.follow_scare);
                        setTimeColor(Color.parseColor("#e60012"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
                        setTextTime(item3.getEnd_time(), viewHolder.getView(R.id.count_down));
                        break;
                    case 3:
                        setTextBackGround("抢光了", getContext().getResources().getDrawable(R.drawable.pass_make_gray), viewHolder.getView(R.id.make_use_of_btn));
                        viewHolder.setText(Color.parseColor("#B6B6B6"), R.id.follow_scare);
                        setTimeColor(Color.parseColor("#B6B6B6"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
                        setTextTime(item3.getEnd_time(), viewHolder.getView(R.id.count_down));
                        break;
                    case 4:
                        setTextBackGround("已结束", getContext().getResources().getDrawable(R.drawable.pass_make_gray), viewHolder.getView(R.id.make_use_of_btn));
                        viewHolder.setText(Color.parseColor("#B6B6B6"), R.id.follow_scare);
                        setTimeColor(Color.parseColor("#B6B6B6"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
                        setTextTime(item3.getStart_time(), viewHolder.getView(R.id.count_down));
                        break;
                    case 5:
                        setTextBackGround("过期了", getContext().getResources().getDrawable(R.drawable.pass_make_gray), viewHolder.getView(R.id.make_use_of_btn));
                        viewHolder.setText(Color.parseColor("#B6B6B6"), R.id.follow_scare);
                        setTimeColor(Color.parseColor("#B6B6B6"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
                        setTextTime(item3.getStart_time(), viewHolder.getView(R.id.count_down));
                        break;
                }
                viewHolder.setOnClickListener(v1 -> {
                    if (my_login.isLogin()) {
                        Intent intent = new Intent(getContext(), Panic_BuyingActivity.class);
                        intent.putExtra("pid", item3.getPid());
                        ((Activity) (getContext())).startActivityForResult(intent, IntentCode.RequestCode.TOPANICBUY);
                    } else {
                        Toast.makeText(getContext(), "您尚未登陆，暂时无法查看", Toast.LENGTH_SHORT).show();
                    }
                }, R.id.scare_all);

                break;
        }
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTextBackGround(CharSequence result, Drawable drawable, TextView textView) {
        textView.setText(result);
        textView.setBackground(drawable);
    }

    public void setTextTime(long time, TimeTextView view) {
        view.setTime(time);
    }

    public void setTimeColor(int color, TimeTextView view, int type) {
        view.Judge(type);
        view.setTextColor(color);
    }
}
