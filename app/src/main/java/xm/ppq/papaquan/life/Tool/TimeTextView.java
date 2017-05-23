package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Date;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/18.
 */

public class TimeTextView extends TextView {

    public TimeTextView(Context context) {
        super(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeTextView);
        sex = array.getInteger(R.styleable.TimeTextView_sex, 1);
        array.recycle();
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private long Time;
    private int sex = 1;

    public void setTime(long time) {
        Date date = new Date();
        Time = time - date.getTime() / 1000;
        if (Time > 0) {
            handler.removeMessages(4001);
            handler.sendEmptyMessage(4001);
        } else {
            setText("剩00:00:00结束");
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 4001:
                    if (Time > 0) {
                        TimeTextView.this.setText(count());
                        Time = Time - 1;
                        handler.sendEmptyMessageDelayed(4001, 1000);
                    }
                    break;
            }
        }
    };

    public static int START = 0;
    public static int STOP = 1;
    public static int EXPIRE = 2;
    private String end;

    public void Judge(int Type) {
        if (Type == START) {
            end = "开始";
        } else if (Type == STOP) {
            end = "结束";
        } else if (Type == EXPIRE) {
            end = "过期";
        }
    }

    private String count() {
        String count = null;
        long s = 0;
        if (Time >= 60) {//分
            long h = 0;
            long m = 0;
            m = Time / 60;
            s = Time % 60;
            if (m >= 60) {//时
                h = m / 60;
                m = m % 60;
                if (h > 24) {
                    long l = h / 24;
                    long l1 = h % 24;
                    if (sex == 1) {
                        count = "剩" + cover(l) + "天" + cover(l1) + "时" + cover(m) + "分" + cover(s) + "秒" + end;
                    } else {
                        count = "距" + end + cover(l) + "天" + cover(l1) + ":" + cover(m) + ":" + cover(s);
                    }
                } else {
                    if (sex == 1) {
                        count = "剩" + cover(h) + "时" + cover(m) + "分" + cover(s) + "秒" + end;
                    } else {
                        count = "距" + end + cover(h) + ":" + cover(m) + ":" + cover(s);
                    }
                }
            } else {
                if (sex == 1) {
                    count = "剩00时" + cover(m) + "分" + cover(s) + "秒" + end;
                } else {
                    count = "距" + end + "00:" + cover(m) + ":" + cover(s);
                }
            }
        } else {//秒
            s = Time;
            if (sex == 1) {
                count = "剩00时00分" + cover(s) + "秒" + end;
            } else {
                count = "距" + end + "00:00:" + cover(s);
            }
        }
        return count;
    }

    private String cover(long time) {
        if (time >= 10) {
            return String.valueOf(time);
        } else {
            return "0" + time;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeMessages(4001);
    }
}