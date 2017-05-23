package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/8.
 */

public class Loading extends View {

    private Paint paint;
    private int[] colors = new int[2];
    private CharSequence sequence;
    private int TextColors;

    public Loading(Context context) {
        super(context);
    }

    public Loading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Loading);
        sequence = array.getText(R.styleable.Loading_content);
        colors[0] = array.getColor(R.styleable.Loading_hide_colors, Color.argb(255, 0, 0, 0));
        colors[1] = array.getColor(R.styleable.Loading_show_colors, Color.argb(255, 228, 228, 228));
        TextColors = array.getColor(R.styleable.Loading_content_colors, Color.argb(255, 0, 0, 0));
    }

    public Loading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(getLeft(), getBottom(), getRight(), getBottom());
        for (int i = 0; i < 360; i++) {
            canvas.drawArc(rectF, 0, 60, false, paint);
        }
    }
}
