package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.ParcelUuid;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/15.
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

    public PopupWindow getPopupWindow() {
        return popupWindow;
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
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setOnDismissListener(() -> Hide());
        initUI();
        setListener();
    }

    public void setStyle(int style)
    {
        popupWindow.setAnimationStyle(style);
    }

    public void Trend_Show(View view) {
        popupWindow.showAsDropDown(view);
    }

    public void Show(View view) {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 0.6f;
        (activity).getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void ShowLow(View view) {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 0.6f;
        (activity).getWindow().setAttributes(lp);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - popupWindow.getWidth() * 2, location[1] + view.getHeight());
    }

    public void ShowLow(View view, int Gravity) {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 0.6f;
        (activity).getWindow().setAttributes(lp);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity, location[0] + view.getWidth(), location[1] + view.getHeight());
    }

    public void Hide() {
        WindowManager.LayoutParams lp = (activity).getWindow().getAttributes();
        lp.alpha = 1f;
        (activity).getWindow().setAttributes(lp);
        popupWindow.dismiss();
    }

    protected abstract int getLayout();

    protected abstract void initUI();

    protected abstract void setListener();

    private SparseArray<View> sparseArray = new SparseArray<>();

    protected <T extends View> T Bind(int rid) {
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
