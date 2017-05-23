package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.tauth.Tencent;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 18:45.
 */

public class Red_Share extends PopupWindowPotting {

    private TextView code;
    private LinearLayout wx_share;
    private LinearLayout qf_share;
    private LinearLayout qq_share;
    private LinearLayout qzone_share;
    private String url;
    private Tencent tencent;

    private SharedPreferencesPotting potting;

    public Red_Share(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayout() {
        return R.layout.red_share;
    }

    @Override
    protected void initUI() {
        tencent = Tencent.createInstance(ShareAndPay.APP_ID, getActivity());
        potting = new SharedPreferencesPotting(getActivity(), "my_login");
        url = "index/user/myredcard?city=" + BaseUrl.citycode + "&uid=" + potting.getItemInt("uid");
        code = Bind(R.id.code);
        wx_share = Bind(R.id.wx_share);
        qf_share = Bind(R.id.qf_share);
        qq_share = Bind(R.id.qq_share);
        qzone_share = Bind(R.id.qzone_share);
        code.setText(potting.getItemInt("uid") + "");
    }

    @Override
    protected void setListener() {
        wx_share.setOnClickListener(v -> {
            ShareAndPay.WxShare(0, getActivity(), BaseUrl.URL + url, BaseUrl.red, BaseUrl.Image_url);
            potting.setItemString("result", "分享")
                    .build();
        });
        qf_share.setOnClickListener(v -> {
            ShareAndPay.WxShare(1, getActivity(), BaseUrl.URL + url, BaseUrl.red, BaseUrl.Image_url);
            potting.setItemString("result", "分享")
                    .build();
        });
        qq_share.setOnClickListener(v -> {
            ShareAndPay.TXShare(tencent, getActivity(), BaseUrl.URL + url, BaseUrl.red, BaseUrl.Image_url, 1);
        });
        qzone_share.setOnClickListener(v -> {
            ShareAndPay.TXShare(tencent, getActivity(), BaseUrl.URL + url, BaseUrl.red, BaseUrl.Image_url, 0);
        });
    }
}