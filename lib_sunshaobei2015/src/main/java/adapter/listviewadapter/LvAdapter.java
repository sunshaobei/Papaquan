package adapter.listviewadapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/26.
 */

public abstract class LvAdapter<T> extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<T> list;
    private int itemLayout;

    public LvAdapter(Context context, List<T> list, int itemLayout) {
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
}