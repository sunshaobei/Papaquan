package xm.ppq.papaquan.View.my_topic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.TextView.MySpanTextView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.published_dynamic.Dynamic;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by sunshaobei on 2017/5/4.
 */

public class MydynamicListviewAdapter extends BaseAdapter {
    private MyTopicActivity context;
    private ArrayList<ShowNewsBigBean.Data> list;
    private final LayoutInflater inflater;
    private final int TYPEEMPTY = 0;
    private final int TYPEHAVEPIC = 1;
    private final int TYPETEXT = 2;


    public MydynamicListviewAdapter(MyTopicActivity context, ArrayList<ShowNewsBigBean.Data> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TYPEEMPTY;
        else {
            ShowNewsBigBean.Data data = list.get(position - 1);
            String video = data.video;
            if ((data.picture != null && data.picture.size() > 0) || (data.video != null && !data.video.equals("")))
                return TYPEHAVEPIC;
            else if ((data.picture == null || data.picture.size() == 0) && (data.video == null || data.video.equals("")))
                return TYPETEXT;
            return super.getItemViewType(position);
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        if (convertView == null) {
            switch (type) {
                case TYPEEMPTY:
                    convertView = inflater.inflate(R.layout.itemlayout_typeempty, null, false);
                    holder1 = new ViewHolder1();
                    holder1.time1 = (TextView) convertView.findViewById(R.id.time1);
                    holder1.image = (ImageView) convertView.findViewById(R.id.imageView);
                    convertView.setTag(holder1);
                    break;
                case TYPEHAVEPIC:
                    convertView = inflater.inflate(R.layout.itemlayout_typehavepic, null, false);
                    holder2 = new ViewHolder2();
                    holder2.piccount = (TextView) convertView.findViewById(R.id.piccount);
                    holder2.image = (ImageView) convertView.findViewById(R.id.image);
                    holder2.content = (MySpanTextView) convertView.findViewById(R.id.content);
                    holder2.play = (ImageView) convertView.findViewById(R.id.play);
                    holder2.time1 = (TextView) convertView.findViewById(R.id.time1);
                    holder2.time2 = (TextView) convertView.findViewById(R.id.time2);
                    holder2.click = (View) convertView.findViewById(R.id.click);
                    holder2.time1.setVisibility(View.GONE);
                    holder2.time2.setVisibility(View.GONE);
                    holder2.year = (TextView) convertView.findViewById(R.id.year);
                    convertView.setTag(holder2);
                    break;
                case TYPETEXT:
                    convertView = inflater.inflate(R.layout.itemlayout_typetext, null, false);
                    holder3 = new ViewHolder3();
                    holder3.time1 = (TextView) convertView.findViewById(R.id.time1);
                    holder3.time2 = (TextView) convertView.findViewById(R.id.time2);
                    holder3.time1.setVisibility(View.GONE);
                    holder3.time2.setVisibility(View.GONE);
                    holder3.content = (MySpanTextView) convertView.findViewById(R.id.content);
                    holder3.year = (TextView) convertView.findViewById(R.id.year);
                    convertView.setTag(holder3);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPEEMPTY:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPEHAVEPIC:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPETEXT:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
            }
        }
        switch (type) {
            case TYPEEMPTY:
                holder1.time1.setText("今天");
                holder1.time1.setTextSize(16);
                holder1.image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, Dynamic.class);
                    context.startActivityForResult(intent, IntentCode.RequestCode.TODYNAMIC);
                });
                break;
            case TYPEHAVEPIC:
                ShowNewsBigBean.Data data = list.get(position - 1);
                holder2.content.setText(data.content, data.id);
                holder2.click.setOnClickListener(v -> {
                    Intent intent = new Intent(context, TrendTopicDetailActivity.class);
                    intent.putExtra("tid", data.id);
                    context.startActivity(intent);
                });

                if (data.picture != null && data.picture.size() > 0) {
                    holder2.play.setVisibility(View.GONE);
                    try {
                        Glide.with(context).load(BaseUrl.BITMAP + data.picture.get(0)+"/200x200").into(holder2.image);
                    }catch (IllegalArgumentException e)
                    {

                    }
                    holder2.play.setVisibility(View.GONE);
                    if (data.picture.size() > 1) {
                        holder2.piccount.setVisibility(View.VISIBLE);
                        holder2.piccount.setText("共" + data.picture.size() + "张图片");
                    } else {
                        holder2.piccount.setVisibility(View.GONE);
                    }
                } else {
                    try {
                        Glide.with(context).load(BaseUrl.OUTURL + data.videopic).into(holder2.image);

                    }catch (IllegalArgumentException e)
                    {

                    }
                    holder2.play.setVisibility(View.VISIBLE);
                    holder2.piccount.setVisibility(View.GONE);
                    holder2.play.setOnClickListener(v -> {
                        JCVideoPlayerStandard.startFullscreen(context,JCVideoPlayerStandard.class,BaseUrl.OUTURL+data.video);
                    });
                }
                holder2.content.setText(data.content,data.id);
                int[] dateFromDate = DateUtil.getDateFromDate(System.currentTimeMillis());
                int systemyear = dateFromDate[0];
                int systemmonth = dateFromDate[1];
                int systemday = dateFromDate[2];
                long create_data = Long.valueOf((data.createtime != null ? data.createtime : System.currentTimeMillis() / 1000) + "000");
                int[] dateFromString = DateUtil.getDateFromDate(create_data);
                int year = dateFromString[0];
                int month = dateFromString[1];
                int day = dateFromString[2];
                if (position == 1) {
                    if (systemyear == year) {
                        holder2.year.setVisibility(View.GONE);
                        if (systemmonth == month && systemday == day) {
                            holder2.time1.setVisibility(View.GONE);
                            holder2.time2.setVisibility(View.GONE);
                        } else if (systemmonth == month && systemday == day + 1) {
                            holder2.time1.setVisibility(View.VISIBLE);
                            holder2.time1.setText("昨天");
                            holder2.time2.setVisibility(View.GONE);
                        } else {
                            holder2.time1.setVisibility(View.VISIBLE);
                            holder2.time2.setVisibility(View.VISIBLE);
                            String d = "";
                            if (day < 10)
                                d = "0" + day;
                            else d = day + "";
                            String m = "";
                            if (month + 1 < 10)
                                m = "0" + (month + 1) + "月";
                            else m = month + 1 + "月";
                            holder2.time1.setText(d);
                            holder2.time2.setText(m);
                        }
                    } else {
                        holder2.year.setVisibility(View.VISIBLE);
                        holder2.year.setText(year + "年");
                    }
                } else if (position > 1) {
                    ShowNewsBigBean.Data lastdata = list.get(position - 2);
                    int[] lastdateFromString = DateUtil.getDateFromDate(Long.valueOf((lastdata.createtime != null ? lastdata.createtime : System.currentTimeMillis() / 1000) + "000"));
                    int lastdatayear = lastdateFromString[0];
                    int lastdatamonth = lastdateFromString[1];
                    int lastdataday = lastdateFromString[2];
                    if (lastdatayear == year) {
                        holder2.year.setVisibility(View.GONE);
                        if (lastdatamonth == month && lastdataday == day) {
                            holder2.time1.setVisibility(View.GONE);
                            holder2.time2.setVisibility(View.GONE);
                        } else {
                            if (lastdatamonth == systemmonth && lastdataday == systemday && lastdataday == day + 1) {
                                holder2.time1.setVisibility(View.VISIBLE);
                                holder2.time1.setText("昨天");
                                holder2.time2.setVisibility(View.GONE);
                            } else {
                                holder2.time1.setVisibility(View.VISIBLE);
                                holder2.time2.setVisibility(View.VISIBLE);
                                String d = "";
                                if (day < 10)
                                    d = "0" + day;
                                else d = day + "";
                                String m = "";
                                if (month + 1 < 10)
                                    m = "0" + (month + 1) + "月";
                                else m = month + 1 + "月";
                                holder2.time1.setText(d);
                                holder2.time2.setText(m);
                            }
                        }
                    } else {
                        holder2.year.setVisibility(View.VISIBLE);
                        holder2.year.setText(year + "年");
                    }
                }
                break;
            case TYPETEXT:
                ShowNewsBigBean.Data data1 = list.get(position - 1);
                if (data1.content != null)
                    holder3.content.setText(data1.content, data1.id);
                holder3.content.setOnClickListener(v -> {
                    Intent intent = new Intent(context, TrendTopicDetailActivity.class);
                    intent.putExtra("tid", data1.id);
                    context.startActivity(intent);
                });
                int[] dateFromDate1 = DateUtil.getDateFromDate(System.currentTimeMillis());
                int systemyear1 = dateFromDate1[0];
                int systemmonth1 = dateFromDate1[1];
                int systemday1 = dateFromDate1[2];
                int[] dateFromString1 = DateUtil.getDateFromDate(Long.valueOf((data1.createtime != null ? data1.createtime : System.currentTimeMillis() / 1000) + "000"));

                int year1 = dateFromString1[0];
                int month1 = dateFromString1[1];
                int day1 = dateFromString1[2];
                if (position == 1) {
                    if (systemyear1 == year1) {
                        holder3.year.setVisibility(View.GONE);
                        if (systemmonth1 == month1 && systemday1 == day1) {
                            holder3.time1.setVisibility(View.GONE);
                            holder3.time2.setVisibility(View.GONE);
                        } else if (systemmonth1 == month1 && systemday1 == day1 + 1) {
                            holder3.time1.setVisibility(View.VISIBLE);
                            holder3.time1.setText("昨天");
                            holder3.time2.setVisibility(View.GONE);
                        } else {
                            holder3.time1.setVisibility(View.VISIBLE);
                            holder3.time2.setVisibility(View.VISIBLE);
                            String d = "";
                            if (day1 < 10)
                                d = "0" + day1;
                            else d = day1 + "";
                            String m = "";
                            if (month1 + 1 < 10)
                                m = "0" + (month1 + 1) + "月";
                            else m = month1 + 1 + "月";
                            holder3.time1.setText(d);
                            holder3.time2.setText(m);
                        }
                    } else {
                        holder3.year.setVisibility(View.VISIBLE);
                        holder3.year.setText(year1 + "年");
                    }
                } else if (position > 1) {
                    ShowNewsBigBean.Data lastdata = list.get(position - 2);

//                    , "yyyy-MM-dd HH:mm:ss"
                    int[] lastdateFromString = DateUtil.getDateFromDate(Long.valueOf((lastdata.createtime != null ? lastdata.createtime : System.currentTimeMillis() / 1000) + "000"));
                    int lastdatayear = lastdateFromString[0];
                    int lastdatamonth = lastdateFromString[1];
                    int lastdataday = lastdateFromString[2];
                    if (lastdatayear == year1) {
                        holder3.year.setVisibility(View.GONE);
                        if (lastdatamonth == month1 && lastdataday == day1) {
                            holder3.time1.setVisibility(View.GONE);
                            holder3.time2.setVisibility(View.GONE);
                        } else {
                            if (lastdatamonth == systemmonth1 && lastdataday == systemday1 && lastdataday == day1 + 1) {
                                holder3.time1.setVisibility(View.VISIBLE);
                                holder3.time1.setText("昨天");
                                holder3.time2.setVisibility(View.GONE);
                            } else {
                                holder3.time1.setVisibility(View.VISIBLE);
                                holder3.time2.setVisibility(View.VISIBLE);
                                String d = "";
                                if (day1 < 10)
                                    d = "0" + day1;
                                else d = day1 + "";
                                String m = "";
                                if (month1 + 1 < 10)
                                    m = "0" + (month1 + 1) + "月";
                                else m = month1 + 1 + "月";
                                holder3.time1.setText(d);
                                holder3.time2.setText(m);
                            }
                        }
                    } else {
                        holder3.year.setVisibility(View.VISIBLE);
                        holder3.year.setText(year1 + "年");
                    }
                }
                break;
        }

        return convertView;
    }

    //为每种布局定义自己的ViewHolder
    public class ViewHolder1 {
        TextView time1;
        ImageView image;
    }

    public class ViewHolder2 {
        TextView time1;
        TextView time2;
        ImageView image;
        ImageView play;
        MySpanTextView content;
        TextView piccount;
        View click;
        TextView year;
    }

    public class ViewHolder3 {
        TextView time1;
        TextView time2;
        TextView year;
        MySpanTextView content;
    }

}
