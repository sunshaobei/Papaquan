package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MyToast {

    private Toast mToast;
    private TextView textView;

    private MyToast(Context context, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.follow_toast_layout, null);
        if (textView == null) {
            textView = (TextView) v.findViewById(R.id.content_toast);
        }
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    private MyToast(Context context, int duration, int gravity) {
        View v = LayoutInflater.from(context).inflate(R.layout.follow_toast_layout, null);
        if (textView == null) {
            textView = (TextView) v.findViewById(R.id.content_toast);
        }
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setGravity(gravity, 0, 0);
        mToast.setView(v);
    }

    public static MyToast makeText(Context context, int duration) {
        return new MyToast(context, duration);
    }

    public static MyToast makeText(Context context, int duration, int gravity) {
        return new MyToast(context, duration, gravity);
    }

    public MyToast setTextView(String result) {
        textView.setText(result);
        return this;
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }

}
