package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mapsdk.raster.model.LatLng;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.DisComment;


/**
 * Created by Administrator on 2017/1/11.
 */

public abstract class ConcreteAdapter<T> extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<T> list;
    private int itemLayout;

    public ConcreteAdapter(Context context, List<T> list, int itemLayout) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        this.itemLayout = itemLayout;
    }

    public Context getContext() {
        return context;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(convertView, parent);
        convert(viewHolder, (T) getItem(position), position);
        return viewHolder.getHoldView();
    }

    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    private ViewHolder getViewHolder(View view, ViewGroup viewGroup) {
        return ViewHolder.get(context, view, viewGroup, itemLayout);
    }

    protected void ToastShow(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }

    protected void TextView_Delete(TextView... textView) {
        for (int i = 0; i < textView.length; i++) {
            textView[i].getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    public double Calculated_distance(LatLng start, LatLng end) {
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