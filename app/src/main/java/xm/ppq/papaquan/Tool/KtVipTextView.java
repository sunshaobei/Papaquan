package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/10.
 */

public class KtVipTextView extends TextView {

    public static final int SCARE_BUYING = 0x0000001;
    public static final int RED_CARD = 0x0000002;
    private boolean clicked;
    private int background_type;
    private int padding_l_r;
    private int padding_t_b;

    public KtVipTextView(Context context) {
        super(context);
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
        Quality();
    }

    public int getPadding_l_r() {
        return padding_l_r;
    }

    public void setPadding(int padding_l_r, int padding_t_b) {
        this.padding_l_r = padding_l_r;
        this.padding_t_b = padding_t_b;
        setPadding(padding_l_r, padding_t_b, padding_l_r, padding_t_b);
    }

    public int getPadding_t_b() {
        return padding_t_b;
    }

    public KtVipTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KtVipTextView);
        clicked = array.getBoolean(R.styleable.KtVipTextView_clicked, false);
        background_type = array.getInteger(R.styleable.KtVipTextView_background_type, 0);
        padding_l_r = array.getInteger(R.styleable.KtVipTextView_padding_l_r, 0);
        padding_t_b = array.getInteger(R.styleable.KtVipTextView_padding_t_b, 0);
        array.recycle();
        setPadding(padding_l_r, padding_t_b, padding_l_r, padding_t_b);
        Quality();
    }

    protected void Quality() {
        switch (background_type) {
            case SCARE_BUYING://我的抢购和积分商城
                if (clicked == false) {
                    setBackground(getResources().getDrawable(R.drawable.pass_make));
                } else {
                    setBackground(getResources().getDrawable(R.drawable.pass_hs_make));
                }
                break;
            case RED_CARD://我的红卡
                if (clicked == false) {
                    setBackground(getResources().getDrawable(R.drawable.red_kt_card));
                } else {
                    setBackground(getResources().getDrawable(R.drawable.red_sw_card));
                }
                break;
        }
    }

    public KtVipTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}