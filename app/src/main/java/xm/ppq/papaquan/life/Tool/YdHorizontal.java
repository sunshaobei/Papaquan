package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * 横向二级列表
 * Created by EdgeDi on 2017/4/16.
 */

public class YdHorizontal extends LinearLayout {

    /**
     * 标题数组
     */
    private CharSequence[] title;
    /**
     * 选中颜色
     */
    private int text_color_on;
    /**
     * 未选中颜色
     */
    private int text_color_off;
    /**
     * 选中图标
     */
    private Drawable icon_on;
    /**
     * 未选择图标
     */
    private Drawable icon_off;

    private int on_item;

    private OnHorizontalListener onHorizontalListener;

    public YdHorizontal(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        initTitle();
    }

    public YdHorizontal(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.YdHorizontal);
        title = array.getTextArray(R.styleable.YdHorizontal_titles);
        text_color_on = array.getColor(R.styleable.YdHorizontal_text_color_on, Color.parseColor("#e60012"));
        text_color_off = array.getColor(R.styleable.YdHorizontal_text_color_off, Color.parseColor("#555555"));
        icon_on = array.getDrawable(R.styleable.YdHorizontal_icon_on);
        icon_off = array.getDrawable(R.styleable.YdHorizontal_icon_off);
        if (icon_on == null) icon_on = getResources().getDrawable(R.mipmap.icon_on);
        if (icon_off == null) icon_off = getResources().getDrawable(R.mipmap.icon_off);
        array.recycle();
        setOrientation(HORIZONTAL);
        initTitle();
    }

    public CharSequence[] getTitle() {
        return title;
    }

    public void setTitle(CharSequence[] title) {
        this.title = title;
    }

    public int getText_color_on() {
        return text_color_on;
    }

    public void setText_color_on(int text_color_on) {
        this.text_color_on = text_color_on;
    }

    public int getText_color_off() {
        return text_color_off;
    }

    public void setText_color_off(int text_color_off) {
        this.text_color_off = text_color_off;
    }

    public Drawable getIcon_on() {
        return icon_on;
    }

    public void setIcon_on(Drawable icon_on) {
        this.icon_on = icon_on;
    }

    public Drawable getIcon_off() {
        return icon_off;
    }

    public void setIcon_off(Drawable icon_off) {
        this.icon_off = icon_off;
    }

    public int getTitleItem() {
        return on_item;
    }

    public void setTitleItem(int on_item) {
        this.on_item = on_item;
    }

    public OnHorizontalListener getOnHorizontalListener() {
        return onHorizontalListener;
    }

    public void setOnHorizontalListener(OnHorizontalListener onHorizontalListener) {
        this.onHorizontalListener = onHorizontalListener;
    }

    private SparseArray<TextView> textviews = new SparseArray<>();
    private SparseArray<ImageView> imageviews = new SparseArray<>();

    /**
     * false未选中 true选中
     */
    private SparseArray<Boolean> booleans = new SparseArray<>();

    private void initTitle() {
        if (title == null) return;
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        for (int i = 0; i < title.length; i++) {
            final LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            final TextView textView = new TextView(getContext());
            textView.setText(title[i]);
            switch (title[i].length()) {
                case 1:
                    textView.setPadding(0, 25, 40, 25);
                    break;
                case 2:
                    textView.setPadding(0, 25, 30, 25);
                    break;
                case 3:
                    textView.setPadding(0, 25, 20, 25);
                    break;
                case 4:
                    textView.setPadding(0, 25, 10, 25);
                    break;
            }
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(icon_off);
            linearLayout.addView(textView);
            linearLayout.addView(imageView);
            if (booleans.get(i) == null) booleans.append(i, false);
            if (textviews.get(i) == null) textviews.append(i, textView);
            if (imageviews.get(i) == null) imageviews.append(i, imageView);
            final int finalI = i;
            linearLayout.setOnClickListener(v -> {
                if (onHorizontalListener != null)
                    onHorizontalListener.ItemHorizontal(linearLayout, finalI, title[finalI]);
                on_item = finalI;
                if (booleans.get(finalI) == true) {
                    booleans.append(finalI, false);
                } else {
                    booleans.append(finalI, true);
                }
                SelectStatus();
            });
            addView(linearLayout);
        }
    }

    private void SelectStatus() {
        for (int i = 0; i < booleans.size(); i++) {
            if (booleans.get(i) == false) {
                textviews.get(i).setTextColor(text_color_off);
                imageviews.get(i).setImageDrawable(icon_off);
            } else if (booleans.get(i) == true) {
                textviews.get(i).setTextColor(text_color_on);
                imageviews.get(i).setImageDrawable(icon_on);
            }
        }
        invalidate();
    }

    public interface OnHorizontalListener {
        void ItemHorizontal(View v, int position, CharSequence result);
    }
}