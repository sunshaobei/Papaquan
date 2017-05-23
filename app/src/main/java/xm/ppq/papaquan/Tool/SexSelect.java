package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/3/2.
 */

public class SexSelect implements View.OnClickListener {

    @BindView(R.id.sex_finish)
    TextView sex_finish;
    @BindView(R.id.man_select)
    TextView man_select;
    @BindView(R.id.woman_select)
    TextView woman_select;

    private View view;
    private PopupWindow window;
    private Context context;
    private OnSelectListener selectListener;

    public SexSelect(Context context, int layout) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(layout, null);
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ButterKnife.bind(this, view);
        initPopup();
        setListener();
    }

    public OnSelectListener getSelectListener() {
        return selectListener;
    }

    public void setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
    }

    private void initPopup() {
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    public void Show(View view) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.6f;
        ((Activity) context).getWindow().setAttributes(lp);
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void Hide() {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1f;
        ((Activity) context).getWindow().setAttributes(lp);
        window.dismiss();
    }

    private void setListener() {
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Hide();
            }
        });
        sex_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide();
            }
        });
        man_select.setOnClickListener(this);
        woman_select.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (selectListener != null) {
            selectListener.select(v);
        }
    }

    public interface OnSelectListener {
        void select(View v);
    }
}
