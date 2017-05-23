package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by sunshaobei on 2017/3/6.
 */

public class Indicator extends View {

    private Paint paint;

    private float count = 3;
    private Paint paint2;
    private int position = 0;
    private float offset;
    private int width;
    private float offset2;
    private int offwidth = 15;
    private int backcolor;
    private int select = Color.RED;
    private int back = Color.WHITE;


    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public void setOffwidth(int width) {
        this.offwidth = px2dip(getContext(), width);
        invalidate();
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public void setBackcolor(int color) {
        this.backcolor = color;
        invalidate();
    }

    public void setSelectColors(int colors) {
        select = colors;
        invalidate();
    }

    public void setBackColors(int colors) {
        back = colors;
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(back);
        paint2 = new Paint();
        paint2.setColor(select);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);

    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = canvas.getHeight();
        width = canvas.getWidth();
        canvas.drawColor(Color.TRANSPARENT);
        //画指示器，带圆角
        for (int i = 0; i < count; i++) {
//            RectF rectf = new RectF(i * width / count + width / count / 3 + offwidth, 0, i * width / count + 2 * width / count / 3 - offwidth, height);
//            canvas.drawRoundRect(rectf, 6, 6, paint);
        }
        RectF rectf2 = new RectF(position * width / count + width / count / 3 + offwidth + offset, 0, position * width / count + 2 * width / count / 3 + offset - offwidth - offset2, height);
        canvas.drawRoundRect(rectf2, 6, 6, paint2);
    }

    public void onMove(float f, int position) {
        this.position = position;
        if (position == count - 1) {
            offset = 0;
        } else {
            offset = (width / count) * f;
            offset2 = (width / count / 3) * (0.5f - Math.abs(0.5f - f));
        }
        invalidate();
    }
}
