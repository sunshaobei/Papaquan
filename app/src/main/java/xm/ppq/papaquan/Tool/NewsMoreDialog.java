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
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class NewsMoreDialog implements View.OnClickListener {

    @BindView(R.id.cancel_news)
    TextView cancel_news;

    private View view;
    private PopupWindow popupWindow;
    private Context context;
    private NewsMoreListener newsMoreListener;

    public NewsMoreDialog(Context context, int layout) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(layout, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        ButterKnife.bind(this, view);
        initPopup();
        setListener();
    }

    public void setNewsMoreListener(NewsMoreListener newsMoreListener) {
        this.newsMoreListener = newsMoreListener;
    }

    private void setListener() {
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Hide();
            }
        });
        cancel_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hide();
            }
        });
    }

    private void initPopup() {
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    public void Show(View view) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.6f;
        ((Activity) context).getWindow().setAttributes(lp);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void Hide() {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1f;
        ((Activity) context).getWindow().setAttributes(lp);
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (newsMoreListener != null) newsMoreListener.newmore(v);
    }

    public interface NewsMoreListener {
        void newmore(View v);
    }

}
