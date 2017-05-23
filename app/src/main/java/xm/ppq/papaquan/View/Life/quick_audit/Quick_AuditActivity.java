package xm.ppq.papaquan.View.Life.quick_audit;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.QuickAuditBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.ShareAndPay;
import xm.ppq.papaquan.life.Tool.TypePopopWindow;

/**
 * Created by 快速审核 on 2017/4/15.
 */

public class Quick_AuditActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.wx_c)
    CheckBox wx_c;
    @BindView(R.id.zfb_c)
    CheckBox zfb_c;
    @BindView(R.id.year)
    TextView year;
    @BindView(R.id.qu_icon)
    ImageView qu_icon;
    @BindView(R.id.qu_shop_name)
    TextView qu_shop_name;
    @BindView(R.id.suatus)
    TextView suatus;
    @BindView(R.id.qu_money)
    TextView qu_money;

    private TypePopopWindow typePopopWindow;
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_quick_audit;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("快速审核店铺");
        typePopopWindow = new TypePopopWindow(this, year);
        potting = new SharedPreferencesPotting(this, "my_login");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", getData("sid"))
                    .put("citycode", getData("citycode"))
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETYEAR, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        QuickAuditBean quickAuditBean = (QuickAuditBean) JsonUtil.fromJson(s, QuickAuditBean.class);
                        if (quickAuditBean.getCode() == 0) {
                            qu_money.setText("￥" + quickAuditBean.getData().getOneyear());
                            typePopopWindow.setText("1年", "2年", quickAuditBean.getData().getOneyear(), quickAuditBean.getData().getTwoyear());
                            ImageLoading.common(getActivity(), BaseUrl.BITMAP + quickAuditBean.getData().getLogo(), qu_icon, R.mipmap.food);
                            qu_shop_name.setText(quickAuditBean.getData().getName());
                            if (quickAuditBean.getData().getCheck() == 1) {
                                suatus.setText("已审核");
                            } else {
                                suatus.setText("未审核");
                            }
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String type = "微信";

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        typePopopWindow.setOnItemListener(result -> {
            qu_money.setText("￥" + result);
        });
    }

    @OnClick({R.id.wx, R.id.zfb})
    public void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.wx:
                i = 0;
                type = "微信";
                break;
            case R.id.zfb:
                i = 1;
                type = "支付宝";
                break;
        }
        Checkboxed(i, wx_c, zfb_c);
    }

    public void pay(View view) {
        if (type.equals("微信")) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", potting.getItemInt("uid"))
                        .put("token", potting.getItemString("token"))
                        .put("tokentype", 1)
                        .put("sid", getData("sid"))
                        .put("trade_type", "APP");
                if (year.getText().toString().equals("1年")) {
                    jsonObject.put("type", 1);
                } else {
                    jsonObject.put("type", 2);
                }
                ShareAndPay.WxPay(this, BaseUrl.LIFE_URL + BaseUrl.PAYSHOP, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            ShareAndPay.ZhiPay(this, null, null, null);
        }
    }

    public void select_year(View view) {
        typePopopWindow.Show(view);
    }
}