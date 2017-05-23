package xm.ppq.papaquan.Adapter.base;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import xm.ppq.papaquan.life.Tool.TimeTextView;

import static xm.ppq.papaquan.Adapter.BaseRecyclerAdapter.TYPE_HEADER;

/**
 * Created by Administrator on 2017/3/9.
 */

public class REViewHolder extends RecyclerView.ViewHolder implements REHolder {

    private SparseArray<View> sparseArray;
    private View itemView;

    public REViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        sparseArray = new SparseArray<>();
    }

    public REViewHolder(View itemView, int type) {
        super(itemView);
        if (type == TYPE_HEADER) return;
    }

    @Override
    public <T extends View> T getView(int rid) {
        View view = sparseArray.get(rid);
        if (view == null) {
            view = itemView.findViewById(rid);
            sparseArray.append(rid, view);
        }
        return (T) view;
    }

    @Override
    public REHolder setText(String result, int rid) {
        TextView textView = getView(rid);
        textView.setText(result);
        return this;
    }

    @Override
    public REHolder setTextVisi(int rid, int visibility) {
        View textView = getView(rid);
        textView.setVisibility(visibility);
        return this;
    }

    @Override
    public REHolder setText(int colors, int rid) {
        TextView textView = getView(rid);
        textView.setTextColor(colors);
        return this;
    }

    @Override
    public REHolder setOnClickener(View.OnClickListener onClickener, int rid) {
        View view = getView(rid);
        view.setOnClickListener(onClickener);
        return this;
    }

    @Override
    public REHolder setOnClickListener(View.OnClickListener onClickListener, int rid) {
        View view = getView(rid);
        view.setOnClickListener(onClickListener);
        return this;
    }

    @Override
    public REHolder setTextBackGround(CharSequence result, Drawable drawable, int rid) {
        TextView textView = getView(rid);
        textView.setText(result);
        textView.setBackground(drawable);
        return this;
    }

    @Override
    public REHolder setTextTime(long time, int rid) {
        TimeTextView view = getView(rid);
        view.setTime(time);
        return this;
    }

    @Override
    public REHolder setTimeColor(int color, int rid, int type) {
        TimeTextView view = getView(rid);
        view.Judge(type);
        view.setTextColor(color);
        return this;
    }

}