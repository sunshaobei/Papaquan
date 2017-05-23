package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/3/23.
 */
public class StringGridView extends BaseAdapter {

    private int i;
    private LayoutInflater inflater;
    private Activity context;

    public StringGridView(List<String> list, Activity context) {
        this.gridList.clear();
        this.gridList.addAll(list);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    private List<String> gridList = new ArrayList<>();

    public void setGridList(List<String> gridList) {
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
            ImageLoading.common(context, BaseUrl.BITMAP + gridList.get(position), imageView, R.drawable.default_icon_zheng);
            return view;
        } else if (i == 2) {
            View view = inflater.inflate(R.layout.grid_view_item, parent, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.picture_double);
            ImageLoading.common(context, gridList.get(position), imageView, R.drawable.default_icon_zheng);
            return view;
        }
        return null;
    }
}