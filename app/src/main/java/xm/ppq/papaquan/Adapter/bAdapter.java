package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.Bean.LikeBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by Administrator on 2017/3/22.
 */
public class bAdapter extends BaseAdapter {

    private int i;
    private LayoutInflater inflater;
    private Activity context;
    private SharedPreferencesPotting potting;

    public bAdapter(List<LikeBean> list, Activity context) {
        this.gridList.clear();
        this.gridList.addAll(list);
        inflater = LayoutInflater.from(context);
        this.context = context;
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private List<LikeBean> gridList = new ArrayList<>();

    public void setGridList(List<LikeBean> gridList) {
        this.gridList = gridList;
    }

    @Override
    public int getCount() {
        return gridList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (i == 1) {
            View view = inflater.inflate(R.layout.grid_view_item_double, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.picture);
            ImageLoading.common(context, BaseUrl.BITMAP + gridList.get(position).headurl + "/200x200", imageView, R.drawable.default_icon_zheng);
            return view;
        } else if (i == 2) {
            View view = inflater.inflate(R.layout.grid_view_item, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.picture_double);
            ImageLoading.common(context, gridList.get(position).headurl + "/200x200", imageView, R.drawable.default_icon_zheng);
            LinearLayout item_lin = (LinearLayout) view.findViewById(R.id.item_lin);
            item_lin.setOnClickListener(v -> {
                if (!gridList.get(position).uid.equals(String.valueOf(potting.getItemInt("uid")))) {
                    Intent intent = new Intent(context, Ta_SheetActivity.class);
                    intent.putExtra("Uuid", gridList.get(position).uid);
                    context.startActivity(intent);
                }
            });
            return view;
        }
        return null;
    }
}