package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ItemTextView extends TextView {

    private boolean ColorText;

    public ItemTextView(Context context) {
        super(context);
    }

    public boolean isColorText() {
        return ColorText;
    }

    public void setColorText(boolean colorText) {
        ColorText = colorText;
        if (ColorText == false) {
            setTextColor(getResources().getColor(R.color.Red));
            setText("待审核");
        } else {
            setTextColor(Color.parseColor("#17dcab"));
            setText("已审核");
        }
    }

    public ItemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemTextView);
        ColorText = array.getBoolean(R.styleable.ItemTextView_text_colors, false);
        array.recycle();
        if (ColorText == false) {
            setTextColor(getResources().getColor(R.color.Red));
            setText("待审核");
        } else {
            setTextColor(Color.parseColor("#17dcab"));
            setText("已审核");
        }
    }

    public ItemTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
