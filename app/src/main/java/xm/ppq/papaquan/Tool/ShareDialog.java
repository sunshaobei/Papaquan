package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tencent.tauth.Tencent;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.TextView.ClickSpan;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.life.Tool.ShareAndPay;

/**
 * Created by 分享弹窗 on 2017/2/24.
 */

public class ShareDialog implements View.OnClickListener {

    @BindView(R.id.qq_share)
    LinearLayout qq_share;
    @BindView(R.id.wx_share)
    LinearLayout wx_share;
    @BindView(R.id.qf_share)
    LinearLayout qf_share;
    @BindView(R.id.qzone_share)
    LinearLayout qzone_share;
    @BindView(R.id.copy_url)
    LinearLayout copy_url;
    @BindView(R.id.cancel_attention)
    LinearLayout cancel_attention;
    @BindView(R.id.go_home)
    LinearLayout go_home;
    @BindView(R.id.report)
    LinearLayout report;
    @BindView(R.id.delect)
    LinearLayout delect;
    @BindView(R.id.cancel_topic)
    TextView cancel_topic;
    @BindView(R.id.two_lin)
    LinearLayout two_lin;

    private String url;
    private View view;
    private PopupWindow popupWindow;
    private Context context;
    private ShareListener shareListener;
    private Tencent tencent;
    private String title;
    private SharedPreferencesPotting potting;

    public ShareDialog(Context context, int layout) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(layout, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        ButterKnife.bind(this, view);
        potting = new SharedPreferencesPotting(context, "my_login");
        tencent = Tencent.createInstance(ShareAndPay.APP_ID, context);
        initPopup();
        setListener();
    }

    private String bit_url;
    private int uid;

    public void setUid(int uid) {
        this.uid = uid;
        if (potting.getItemInt("uid") != uid) {
            delect.setVisibility(View.GONE);
            report.setVisibility(View.VISIBLE);
        } else {
            delect.setVisibility(View.VISIBLE);
            report.setVisibility(View.GONE);
        }
        if (uid == potting.getItemInt("AdminId")) {
            delect.setVisibility(View.VISIBLE);
        }
    }

    public void setStatus(String status, String url, String bit_url) {
        this.url = url;
        this.bit_url = bit_url;
        switch (status) {
            case "主页":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.dynamic;
                break;
            case "生活":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.live;
                break;
            case "114":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.shop;
                break;
            case "精折":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.coupon;
                break;
            case "抢购":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.panic_buying;
                break;
            case "五列":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.discount;
                break;
            case "我五":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.red;
                break;
            case "积分":
                two_lin.setVisibility(View.GONE);
                title = BaseUrl.integral;
                break;
            case "抢详":
                two_lin.setVisibility(View.GONE);
                break;
        }
    }

    public void setStatus(String status, String url, String bit_url, String title) {
        this.url = url;
        this.bit_url = bit_url;
        switch (status) {
            case "主页":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.dynamic;
                break;
            case "生活":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.live;
                break;
            case "114":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.shop;
                break;
            case "精折":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.coupon;
                break;
            case "抢购":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.panic_buying;
                break;
            case "五列":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.discount;
                break;
            case "我五":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.red;
                break;
            case "积分":
                two_lin.setVisibility(View.GONE);
                this.title = BaseUrl.integral;
                break;
            case "抢详":
                two_lin.setVisibility(View.GONE);
                this.title = title;
                break;
            case "折详":
                two_lin.setVisibility(View.GONE);
                this.title = title;
                break;
            case "精商":
                two_lin.setVisibility(View.GONE);
                this.title = title;
                break;
            case "商主":
                two_lin.setVisibility(View.GONE);
                this.title = title;
                break;
            case "动详":
                cancel_attention.setVisibility(View.GONE);
                this.title = title;
                break;
            case "话详":
                two_lin.setVisibility(View.GONE);
                this.title = title;
                break;
            case "他详":
                report.setVisibility(View.GONE);
                cancel_attention.setVisibility(View.GONE);
                delect.setVisibility(View.GONE);
                this.title = title;
                break;
        }
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

    private void setListener() {
        qq_share.setOnClickListener(this);
        wx_share.setOnClickListener(this);
        qf_share.setOnClickListener(this);
        qzone_share.setOnClickListener(this);
        copy_url.setOnClickListener(this);
        cancel_attention.setOnClickListener(this);
        go_home.setOnClickListener(this);
        report.setOnClickListener(this);
        delect.setOnClickListener(this);
        popupWindow.setOnDismissListener(() -> Hide());
        cancel_topic.setOnClickListener(v -> Hide());
    }

    public void setShareListener(ShareListener shareListener) {
        this.shareListener = shareListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wx_share:
                ShareAndPay.WxShare(0, (Activity) context, BaseUrl.URL + url, title, bit_url);
                potting.setItemString("result", "分享")
                        .build();
                break;
            case R.id.qf_share:
                ShareAndPay.WxShare(1, (Activity) context, BaseUrl.URL + url, title, bit_url);
                potting.setItemString("result", "分享")
                        .build();
                break;
            case R.id.qq_share:
                ShareAndPay.TXShare(tencent, (Activity) context, BaseUrl.URL + url, title, bit_url, 1);
                break;
            case R.id.qzone_share:
                ShareAndPay.TXShare(tencent, (Activity) context, BaseUrl.URL + url, title, bit_url, 0);
                break;
            case R.id.go_home:
                context.startActivity(new Intent(context, MainActivity.class));
                break;
            case R.id.delect:
                if (shareListener != null) shareListener.share(v, "删除");
                break;
            case R.id.report:
                if (shareListener != null) shareListener.share(v, "举报");
                break;
            case R.id.copy_url:
                ClipboardManager manage = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
                ClipData myClip;
                myClip = ClipData.newPlainText("text", BaseUrl.URL + url);
                manage.setPrimaryClip(myClip);
                break;
            case R.id.cancel_attention:

                break;
        }
    }

    public interface ShareListener {
        void share(View v, String type);
    }

}