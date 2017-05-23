package xm.ppq.papaquan.Tool.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.TimerTask;

/**
 * Created by sunshaobei on 2017/5/18.
 */

public class LoadingView extends View {

    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    private Handler handler;
    boolean up = true;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            if (radius < 100&&up) {
                radius += 6;
                radius1 += 4;
                radius2 += 5;
            } else {
                up = false;
                radius -= 6;
                radius1 -= 4;
                radius2 -= 5;
                if (radius<20) up = true;
            }
            postInvalidate();
            handler.postDelayed(this, 20);
        }
    };

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        handler = new Handler();
        initPaint();
        post();
    }

    private void post() {
        handler.postDelayed(r,20);
    }


    int color = Color.parseColor("#007AFF");
    private void initPaint() {
        paint = new Paint();
        paint1 = new Paint();
        paint2 = new Paint();


        paint.setAntiAlias(true);
        paint1.setAntiAlias(true);
        paint2.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint1.setStyle(Paint.Style.STROKE);
        paint2.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        paint1.setStrokeWidth(20);
        paint.setColor(color);
        paint1.setColor(color);
        paint2.setColor(color);

    }


    int radius = 20;
    int radius1 = 50;
    int radius2 = 80;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth()/2;
        int height = canvas.getHeight()/2;

        canvas.translate(width,height);

        canvas.drawCircle(0,0,radius,paint2);
        canvas.drawCircle(0,0,radius1,paint1);
        canvas.drawCircle(0,0,radius2,paint);

    }

    public void removeCallback()
    {
        handler.removeCallbacks(r);
    }
}
