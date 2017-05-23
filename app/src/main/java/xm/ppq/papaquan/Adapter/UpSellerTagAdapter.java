package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.View;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;

/**
 * Created by Administrator on 2017/4/20.
 */

public abstract class UpSellerTagAdapter<T> extends TagAdapter<T> {

    private Context context;
    private int layout;
    private List<T> list;

    public UpSellerTagAdapter(List<T> datas, Context context, int layout) {
        super(datas);
        this.context = context;
        this.layout = layout;
        this.list = datas;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, T t) {
        ViewHolder viewHolder = new ViewHolder(context, layout, parent);
        Initialize(viewHolder, t, position);
        return viewHolder.getHoldView();
    }

    protected abstract void Initialize(ViewHolder viewHolder, T item, int position);

}