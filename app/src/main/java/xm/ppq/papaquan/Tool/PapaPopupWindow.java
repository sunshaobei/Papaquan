package xm.ppq.papaquan.Tool;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/2/22.
 */

public class PapaPopupWindow {

    private PopupWindow popupWindow;
    private Context context;
    private ImageView imageView;
    private TextView search_select;
    private TextView share_select;
    private ShareDialog shareDialog;
    private String url = "index/index/index?city=" + BaseUrl.citycode;
    private SharedPreferencesPotting potting;

    public PapaPopupWindow(Context context, ImageView imageView) {
        this.imageView = imageView;
        this.context = context;
        initUI();
        potting = new SharedPreferencesPotting(context, "my_login");
        shareDialog = new ShareDialog(context, R.layout.deteil_share);
        shareDialog.setStatus("主页", url, BaseUrl.Image_url);
    }

    private void initUI() {
        View view = LayoutInflater.from(context).inflate(R.layout.papa_select_right, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        share_select = (TextView) view.findViewById(R.id.share_select);
        share_select.setOnClickListener(v -> {
            Hide();
            shareDialog.Show(imageView);
        });
        search_select = (TextView) view.findViewById(R.id.search_select);
        search_select.setOnClickListener(v -> {
            Hide();
            EventBus.getDefault().post("dismissfalse");
            searchview.setVisibility(View.VISIBLE);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alpha = ObjectAnimator.ofFloat(searchview, "alpha", 0, 0.5f, 0.7f, 1f);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(searchview, "translationY", -3000, 0);
            animatorSet.setDuration(300);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.play(alpha).with(translationY);//两个动画同时开始
            animatorSet.start();
        });
    }

    private View searchview;

    public void Show(View view) {
        this.searchview = view;
        popupWindow.showAsDropDown(imageView, 0, 20);
    }

    public void Hide() {
        popupWindow.dismiss();
    }
}