package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/16.
 */

public class SelectTextView extends LinearLayout {

    private String result;
    private boolean isSelect;

    public SelectTextView(Context context) {
        super(context);
        setOrientation(HORIZONTAL);
        initContent();
    }

    public SelectTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SelectTextView);
        result = array.getString(R.styleable.SelectTextView_result);
        isSelect = array.getBoolean(R.styleable.SelectTextView_show, false);
        array.recycle();
        initContent();
        IsShow();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        textView.setText(result);
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
        IsShow();
    }

    private TextView textView;
    private ImageView imageView;

    private void initContent() {
        setPadding(20, 20, 20, 20);
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(Color.parseColor("#ffffff"));
        textView = new TextView(getContext());
        imageView = new ImageView(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1;
        if (result != null) textView.setText(result);
        textView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.tree_select);
        imageView.setPadding(0, 0, 20, 0);
        addView(textView);
        addView(imageView);
    }

    private void IsShow() {
        if (isSelect == false) {
            getChildAt(1).setVisibility(GONE);
        } else {
            getChildAt(1).setVisibility(VISIBLE);
        }
        invalidate();
    }

}