package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by EdgeDi on 10:19.
 */

public class PayTextViewTime extends TextView {

    private long Time;

    public PayTextViewTime(Context context) {
        super(context);
    }

    public PayTextViewTime(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PayTextViewTime(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTime(long time, long nowtime) {
        title = "抢购失败";
        Time = time + 300 - nowtime;
        handler.removeMessages(4001);
        handler.sendEmptyMessage(4001);
    }

    private String title;

    public void setTimeDiscount(long time, long nowtime) {
        title = "订单失效";
        Time = time - nowtime;
        handler.removeMessages(4001);
        handler.sendEmptyMessage(4001);
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    if (Time > 0) {
                        setText(count());
                        Time = Time - 1;
                        handler.sendEmptyMessageDelayed(4001, 1000);
                    } else {
                        setText("订单已失效");
                    }
                    break;
            }
        }
    };

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
                    count = "请在" + cover(l) + ":" + cover(l1) + ":" + cover(m) + ":" + cover(s) + "付款，否则" + title;
                } else {
                    count = "请在" + cover(h) + ":" + cover(m) + ":" + cover(s) + "付款，否则" + title;
                }
            } else {
                count = "请在00:" + cover(m) + ":" + cover(s) + "付款，否则" + title;
            }
        } else {//秒
            s = Time;
            count = "请在00:00:" + cover(s) + "付款，否则" + title;
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
}
