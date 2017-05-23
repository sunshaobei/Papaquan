package myview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.lib_sunshaobei2017.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.R.id.content;
import static android.R.id.list;

public class TabCustomsViewCircle extends View {

    private Paint paint5;
    private int width;

    @SuppressLint("Recycle")
    public TabCustomsViewCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabCircle);
        textSize = (int) typedArray.getDimension(R.styleable.TabCircle_textSizec, 26);
        pading = typedArray.getInteger(R.styleable.TabCircle_padingc, 20);
        width = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth()-60;
//        ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
//        this.setLayoutParams(layoutParams);
        initpaint();
    }

    private int textSize;
    private int defColor = Color.BLACK;
    private int SelColor = Color.parseColor("#ffffff");
    private int count = 31;
    private Paint paint;
    private Paint paint2;
    private int selectPosition;
    private Paint paint3;
    private int pading;

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        invalidate();
    }

    public void setTabCustomsView(int count, int textSize, int defColor, int SelColor, int pading) {
        this.textSize = textSize;
        this.defColor = defColor;
        this.SelColor = SelColor;
        this.pading = pading;
        this.count = count;
        initpaint();
        invalidate();
    }

    private void initpaint() {
        paint = new Paint();
        paint2 = new Paint();
        paint.setStyle(Style.FILL);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.CENTER);
        paint.setColor(defColor);

        paint2.setStyle(Style.FILL);
        paint2.setTextSize(textSize);
        paint2.setAntiAlias(true);
        paint2.setTextAlign(Align.CENTER);
        paint2.setColor(SelColor);

        paint3 = new Paint();
        paint3.setStyle(Style.FILL);
        paint3.setColor(Color.parseColor("#e60012"));

        paint4 = new Paint();
        paint4.setColor(Color.TRANSPARENT);
        paint4.setTextSize(textSize);


        paint5 = new Paint();
        paint5.setColor(Color.parseColor("#7a7a7a"));
        paint5.setStyle(Style.STROKE);
        paint5.setStrokeWidth(1);
        paint5.setAntiAlias(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                selectPosition = (int) (x / (width / 6));
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.onSelect(selectPosition);
                }
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }


    public interface OnSelectListener {
        void onSelect(int Selectposition);
    }

    private OnSelectListener listener;
    private Paint paint4;

    public void setOnSelectListener(OnSelectListener l) {
        this.listener = l;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = canvas.getHeight() / 2;
//        width = canvas.getWidth();
        //单行字体
        Rect rect = new Rect(0, 0, width, 2 * height);
        canvas.drawRect(rect, paint4);
        FontMetrics fontMetrics = paint4.getFontMetrics();
        int baseline = (int) ((rect.bottom + rect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        //两行字体first行
        Rect rect2 = new Rect(0, 0, width, height);
        canvas.drawRect(rect2, paint4);
        FontMetrics fontMetrics2 = paint4.getFontMetrics();
        int baseline2 = (int) ((rect2.bottom + rect2.top - fontMetrics2.bottom - fontMetrics2.top) / 2);
        //second行
        Rect rect3 = new Rect(0, height, width, 2 * height);
        canvas.drawRect(rect3, paint4);
        FontMetrics fontMetrics3 = paint4.getFontMetrics();
        int baseline3 = (int) ((rect3.bottom + rect3.top - fontMetrics3.bottom - fontMetrics3.top) / 2);

        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    canvas.drawText("全部", width / 6 / 2 + i * width / 6, baseline, paint);

                } else if (i == 1) {
                    canvas.drawText(getMonthDay(0), width / 6 / 2 + i * width / 6, baseline2 + 10, paint);
                    canvas.drawText("今天", width / 6 / 2 + i * width / 6, baseline3 - 10, paint);

                } else if (i == 2) {
                    canvas.drawText(getMonthDay(1), width / 6 / 2 + i * width / 6, baseline2 + 10, paint);
                    canvas.drawText("明天", width / 6 / 2 + i * width / 6, baseline3 - 10, paint);

                } else{
                    canvas.drawText(getMonthDay(i-1), width / 6 / 2 + i * width / 6, baseline2 + 10, paint);
                    canvas.drawText(getDayWeek(i-1), width / 6 / 2 + i * width / 6, baseline3 - 10, paint);
                }
                canvas.drawCircle(width / 6 / 2 + i * width / 6, height, height-3, paint5);
            }
//            canvas.drawCircle(1 * width / 6 + width / 6 / 2, height, height, paint3);
//            canvas.drawText(getMonthDay(0), 1 * width / 6 + width / 6 / 2, baseline2 + 10, paint2);
//            canvas.drawText("今天", 1 * width / 6 + width / 6 / 2, baseline3 - 10, paint2);
            if (selectPosition == 0) {
                canvas.drawCircle(0 * width / 6 + width / 6 / 2, height, height-3, paint3);
                canvas.drawText("全部", selectPosition * width / 6 + width / 6 / 2, baseline, paint2);

            } else if (selectPosition == 1) {
                canvas.drawCircle(1 * width / 6 + width / 6 / 2, height, height-3, paint3);
                canvas.drawText(getMonthDay(0), selectPosition * width / 6 + width / 6 / 2, baseline2 + 10, paint2);
                canvas.drawText("今天", selectPosition * width / 6 + width / 6 / 2, baseline3 - 10, paint2);

            } else if (selectPosition == 2) {
                canvas.drawCircle(2 * width / 6 + width / 6 / 2, height, height-3, paint3);
                canvas.drawText(getMonthDay(1), selectPosition * width / 6 + width / 6 / 2, baseline2 + 10, paint2);
                canvas.drawText("明天", selectPosition * width / 6 + width / 6 / 2, baseline3 - 10, paint2);

            } else{
                canvas.drawCircle(selectPosition * width / 6 + width / 6 / 2, height, height-3, paint3);
                canvas.drawText(getMonthDay(selectPosition-1), selectPosition * width / 6 + width / 6 / 2, baseline2 + 10, paint2);
                canvas.drawText(getDayWeek(selectPosition-1), selectPosition * width / 6 + width / 6 / 2, baseline3 - 10, paint2);
            }
        }
    }

    public String getMonthDay(long offset) {
        long l = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(l + offset * ondaytime);
        int month = instance.get(Calendar.MONTH);
        String months = intToString(month + 1);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        String days = intToString(day);
        return months + "-" + days;
    }

    public String getDayWeek(long offset) {
        long l = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(l + offset * ondaytime);
        int i = instance.get(Calendar.DAY_OF_WEEK);
        return getWeekNumber(i);
    }

    private String s;
    private int ondaytime = 1000 * 60 * 60 * 24;

    public String intToString(int i) {
        if (i <= 9) {
            s = "0" + i;
        } else {
            s = "" + i;
        }
        return s;
    }

    public static String getWeekNumber(int i) {
        String week = "星期";
        int intTemp = i - 1;
        switch (intTemp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }
}
