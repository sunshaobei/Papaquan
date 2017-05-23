package xm.ppq.papaquan.Tool.customview.CalendarView.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.customview.CalendarView.bean.DateItem;
import xm.ppq.papaquan.Tool.customview.CalendarView.customview.CustomCalendarView;
import xm.ppq.papaquan.Tool.customview.CalendarView.util.ConfigUtils;

/**
 * Created by zhangshiyu on 2016/8/26.
 */
public class CalendarGridViewAdapter extends BaseAdapter {

    private List<DateItem> list;

    private Context context;
    private CustomCalendarView customCalendarView;
    private int itembackgourd;
    public CalendarGridViewAdapter(CustomCalendarView customCalendarView, Context context, List<DateItem> list, int itembackgourd){
        this.context=context;
        this.list=list;
        this.itembackgourd=itembackgourd;
        this.customCalendarView=customCalendarView;

    }
    public void setDate(List<DateItem> list){
        this.list=list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView textView=null;
        ImageView imageView = null;
        if (convertView==null){
            View view = LayoutInflater.from(context).inflate(R.layout.item_calendarview, null);
            convertView=view;
        }
        textView = (TextView) convertView.findViewById(R.id.textView);
        imageView = (ImageView) convertView.findViewById(R.id.imageView);
        if (list.get(position).isselect()){
            if (itembackgourd==-1) {
//                textView.setBackgroundColor(context.getResources().getColor(R.color.tet_blue));
                imageView.setVisibility(View.VISIBLE);
            }else {
//                textView.setBackgroundResource(itembackgourd);
                imageView.setVisibility(View.VISIBLE);
            }
        }else {
//            textView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));
            imageView.setVisibility(View.GONE);
        }
        textView.setTextSize(22);



        if (list.get(position).getDateOfMonth()>0){
            final TextView finalTextView = textView;
            final ImageView finalImageView = imageView;
            textView.setText(list.get(position).getDateOfMonth() + "");
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.get(position).setIsselect(!list.get(position).isselect());
//                    if (list.get(position).isselect()){
//                        if (itembackgourd==-1) {
////                            finalTextView.setBackgroundColor(context.getResources().getColor(R.color.tet_blue));
//                            finalImageView.setVisibility(View.VISIBLE);
//                        }else {
////                            finalTextView.setBackgroundResource(itembackgourd);
//                            finalImageView.setVisibility(View.GONE);
//                        }
//
//                        Calendar calendar=Calendar.getInstance();
//                        calendar.set(list.get(position).getYear(),list.get(position).getMonth(),list.get(position).getDateOfMonth());
//                        customCalendarView.addSelectDate(ConfigUtils.simpleDate(calendar.getTime()));
//                    }else {
////                        finalTextView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));
//                        finalImageView.setVisibility(View.GONE);
//
//
//                        Calendar calendar=Calendar.getInstance();
//                        calendar.set(list.get(position).getYear(),list.get(position).getMonth(),list.get(position).getDateOfMonth());
//                        customCalendarView.removeSelect(ConfigUtils.simpleDate(calendar.getTime()));
//
//                    }
//                }
//            });
        }else {
//            textView.setBackgroundColor(context.getResources().getColor(R.color.base_bg));
            textView.setText("");
        }
        return convertView;
    }
}
