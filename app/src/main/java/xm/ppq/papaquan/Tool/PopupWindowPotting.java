package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/3/21.
 */

public abstract class PopupWindowPotting {

    private View view;
    private Activity activity;
    private PopupWindow popupWindow;

    public PopupWindowPotting(Activity activity) {
        this.activity = activity;
        onCreate(0, 0);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    protected void onCreate(int width, int height) {
        if (width == 0) width = WindowManager.LayoutParams.MATCH_PARENT;
        if (height == 0) height = WindowManager.LayoutParams.WRAP_CONTENT;
        if (view == null) view = LayoutInflater.from(activity).inflate(getLayout(), null);
        popupWindow = new PopupWindow(view, width, height);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(() -> {
            if (onDismissListener!=null)
            onDismissListener.onDismiss();
            PopupWindowPotting.this.Hide();
        });
        initUI();
        setListener();
    }

    public interface OnDismissListener
    {
        void onDismiss();
    }
    private OnDismissListener onDismissListener;
    public void setOnDismissListener(OnDismissListener o)
    {
        this.onDismissListener = o;
    }



    public void Show(View view) {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 0.6f;
        (activity).getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
    }

    public void Hide() {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 1f;
        (activity).getWindow().setAttributes(lp);
        popupWindow.dismiss();
        EventBus.getDefault().unregister(this);
    }

    protected abstract int getLayout();

    protected abstract void initUI();

    protected abstract void setListener();

    private SparseArray<View> sparseArray = new SparseArray<>();

    protected <T extends View> T $(int rid) {
        if (sparseArray.get(rid) == null) {
            View viewgrounp = view.findViewById(rid);
            sparseArray.append(rid, viewgrounp);
            return (T) viewgrounp;
        } else {
            return (T) sparseArray.get(rid);
        }
    }

    private Intent intent;
    private Toast toast;

    protected void Skip(Class cla) {
        intent = new Intent(activity, cla);
        activity.startActivity(intent);
    }

    protected void ToastShow(String result) {
        if (toast == null) {
            toast = Toast.makeText(activity, result, Toast.LENGTH_SHORT);
        } else {
            toast.setText(result);
        }
        toast.show();
    }
}