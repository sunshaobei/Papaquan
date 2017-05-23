package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 13:36.
 */

public class RadioAdapter extends BaseAdapter {

    private ArrayList<LevelBean> list;
    private Context context;
    private int layout;
    private LayoutInflater inflater;
    private int type = 0;

    public void setType(int type) {
        this.type = type;
    }

    public RadioAdapter(ArrayList<LevelBean> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        inflater = LayoutInflater.from(context);
    }

    public ArrayList<LevelBean> getList() {
        return list;
    }

    public void setList(ArrayList<LevelBean> list) {
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
        Holder holder = null;
        if (holder == null) {
            holder = new Holder();
            convertView = inflater.inflate(layout, parent, false);
            holder.level_text = (TextView) convertView.findViewById(R.id.level_text);
            holder.level_image = (ImageView) convertView.findViewById(R.id.level_image);
            holder.level_lin = (LinearLayout) convertView.findViewById(R.id.level_lin);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.level_text.setText(list.get(position).getResult());
        if (list.get(position).isCheck() == true) {
            holder.level_lin.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.level_image.setVisibility(View.VISIBLE);
        } else {
            holder.level_lin.setBackgroundColor(Color.parseColor("#f2f2f2"));
            holder.level_image.setVisibility(View.GONE);
        }
        if (type == 10) {
            holder.level_lin.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        return convertView;
    }

    class Holder {
        LinearLayout level_lin;
        TextView level_text;
        ImageView level_image;
    }
}
