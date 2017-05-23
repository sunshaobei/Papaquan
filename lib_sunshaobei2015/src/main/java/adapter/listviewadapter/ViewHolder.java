package adapter.listviewadapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * Created by Administrator on 2017/1/11.
 */

public class ViewHolder implements Holder {

    private Context context;
    private SparseArray<View> sparseArray;
    private View view;

    public ViewHolder(Context context, int itemLayout, ViewGroup viewGroup) {
        this.context = context;
        sparseArray = new SparseArray<>();
        view = LayoutInflater.from(context).inflate(itemLayout, viewGroup, false);
        view.setTag(this);
    }

    public static ViewHolder get(Context context, View view, ViewGroup viewGroup, int itemLayout) {
        if (view == null) {
            return new ViewHolder(context, itemLayout, viewGroup);
        }
        return (ViewHolder) view.getTag();
    }

    @Override
    public <T extends View> T getView(int rid) {
        View view = sparseArray.get(rid);
        if (view == null) {
            view = this.view.findViewById(rid);
            sparseArray.append(rid, view);
        }
        return (T) view;
    }

    @Override
    public Holder setLinearLayout(int rid, int left_padding, int right_padding) {
        LinearLayout linearLayout = getView(rid);
        linearLayout.setPadding(left_padding, 0, right_padding, 0);
        return this;
    }


    @Override
    public Holder setFrameLayout(int rid, int width, int height) {
        FrameLayout frameLayout = getView(rid);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        return this;
    }

    @Override
    public Holder setText(CharSequence result, int rid) {
        TextView textView = getView(rid);
        textView.setText(result);
        return this;
    }

    @Override
    public Holder setTextColor(int cid, int rid) {
        TextView textView = getView(rid);
        textView.setTextColor(cid);
        return this;
    }

    @Override
    public Holder setTextColor(ColorStateList color, int rid) {
        TextView textView = getView(rid);
        textView.setTextColor(color);
        return this;
    }

    @Override
    public Holder setSpanned(Spanned spannld, int rid) {
        TextView textView = getView(rid);
        textView.setText(spannld);
        return this;
    }

    @Override
    public Holder setText(int colors, int rid) {
        TextView textView = getView(rid);
        textView.setTextColor(colors);
        return this;
    }

    @Override
    public Holder setText(String result, int rid, int width, int height) {
        TextView textView = getView(rid);
        textView.setText(result);
        textView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        return this;
    }

    @Override
    public Holder setText(String result, int rid, int visibility) {
        TextView textView = getView(rid);
        textView.setText(result);
        textView.setVisibility(visibility);
        return this;
    }

    @Override
    public Holder setResources(int did, int rid) {
        ImageView imageView = getView(rid);
        imageView.setImageResource(did);
        return this;
    }

    @Override
    public Holder setResources(int did, int rid, int visibility) {
        ImageView imageView = getView(rid);
        imageView.setImageResource(did);
        imageView.setVisibility(visibility);
        return this;
    }

    @Override
    public Holder setOnClickListener(View.OnClickListener onClickListener, int rid) {
        View view = getView(rid);
        view.setOnClickListener(onClickListener);
        return this;
    }

    @Override
    public Holder setBackGround(Drawable drawable, int rid) {
        View view = getView(rid);
        view.setBackground(drawable);
        return null;
    }

    @Override
    public Holder setVisibility(int visibility, int rid) {
        View view1 = getView(rid);
        view1.setVisibility(visibility);
        return this;
    }

    @Override
    public View getHoldView() {
        return view;
    }
}
