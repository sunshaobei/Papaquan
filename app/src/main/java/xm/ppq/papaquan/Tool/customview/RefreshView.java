package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunshaobei on 2017/3/15.
 */

public class RefreshView extends View {

    private Paint paint;
    private int inradius = 10;
    private int outradius = 20;
    private int wide = 5;
    private int count = 8;
    float radius;

    public void setInradius(int inradius) {
        this.inradius = inradius;
        invalidate();
    }

    public void setOutradius(int outradius) {
        this.outradius = outradius;
    }

    public void setWide(int wide) {
        this.wide = wide;
        invalidate();
    }

    public void setCount(int count) {
        this.count = count;
        invalidate();
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                radius += 10;
                postInvalidate();
                handler.postDelayed(this, 30);
            }
        }, 30);
    }

    /**
     * px转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.rotate(radius);
        canvas.save();
        canvas.drawColor(Color.TRANSPARENT);
        for (int i = 0; i < count; i++) {
            canvas.rotate(360 / count);
            RectF rectf = new RectF(inradius, -wide / 2, outradius, wide / 2);
            if (i > 11)
                paint.setColor(colorList[11]);
            else
                paint.setColor(colorList[i]);
            canvas.drawRoundRect(rectf, 6, 6, paint);
        }
        canvas.restore();
    }

    int[] colorList = new int[]{Color.parseColor("#EAEAEA"), Color.parseColor("#DADADA"), Color.parseColor("#C5C5C5"), Color.parseColor("#ACACAC"), Color.parseColor("#A0A0A0"), Color.parseColor("#959595"), Color.parseColor("#7E7E7E"), Color.parseColor("#666666"), Color.parseColor("#4C4C4C"), Color.parseColor("#2F2F2F"), Color.parseColor("#0C0C0C"), Color.BLACK};

}
