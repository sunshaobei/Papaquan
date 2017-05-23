package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TextViewScare extends TextView {

    private int type;

    public TextViewScare(Context context) {
        super(context);
    }

    public void setType(int type) {
        switch (type) {
            case 1:
                setBackgroundColor(Color.parseColor("#80000000"));
                break;
            case 2:
                setBackgroundColor(Color.parseColor("#8017dcab"));
                break;
            case 3:
                setBackgroundColor(Color.parseColor("#80ec4706"));
                break;
        }
    }

    public TextViewScare(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewScare);
        type = array.getInteger(R.styleable.TextViewScare_type, 1);
        array.recycle();
        switch (type) {
            case 1:
                setBackgroundColor(Color.parseColor("#80000000"));
                break;
            case 2:
                setBackgroundColor(Color.parseColor("#8017dcab"));
                break;
            case 3:
                setBackgroundColor(Color.parseColor("#80ec4706"));
                break;
        }
    }

    public TextViewScare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}