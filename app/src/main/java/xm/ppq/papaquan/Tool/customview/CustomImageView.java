package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.R;


/**
 * Created by Pan_ on 2015/2/2.
 */
public class CustomImageView extends ImageView {

    private String url;
    private boolean isAttachedToWindow;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().setColorFilter(Color.GRAY,
                            PorterDuff.Mode.MULTIPLY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Drawable drawableUp = getDrawable();
                if (drawableUp != null) {
                    drawableUp.mutate().clearColorFilter();
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    public interface OnClickListener {
        void OnClick(List<String> s, int position);
    }

    public void setOnClickListener(List<String> s, int position, OnClickListener o) {
        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o.OnClick(s, position);
            }
        });
    }


    @Override
    public void onAttachedToWindow() {
        isAttachedToWindow = true;
        setImageUrl(url);
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
//        Picasso.with(getContext()).cancelRequest(this);
//        Picasso.with(getContext()).load(R.drawable.default_icon_zheng).into(this);
        isAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    public void setImageUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
            if (isAttachedToWindow) {
                Glide.with(getContext()).load(url).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(this);
            }
        }
    }
}
