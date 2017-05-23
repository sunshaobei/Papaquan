package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.REViewHolder;

/**
 * Created by Administrator on 2017/3/9.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<REViewHolder> {

    private List<T> list;
    private LayoutInflater inflater;
    private int layout;
    private Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;

    public BaseRecyclerAdapter(Context context, List<T> list, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
        inflater = LayoutInflater.from(context);
    }

    public void addHeaderView(View headerView, int View) {
        mHeaderView = headerView;
        headerView.setVisibility(View);
        notifyItemInserted(0);
    }

    public Context getContext() {
        return context;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public REViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new REViewHolder(mHeaderView, TYPE_HEADER);
        View view = inflater.inflate(layout, parent, false);
        REViewHolder viewHolder = new REViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(REViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        Evaluate(holder, getItem(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public T getItem(int position) {
        return mHeaderView == null ? list.get(position) : list.get(position - 1);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;
    }

    protected abstract void Evaluate(REViewHolder viewHolder, T item, int position);

    public interface OnRecyclerItemListener<T> {
        void RecyclerItem(View v, T item, int position);
    }

}