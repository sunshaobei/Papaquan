package xm.ppq.papaquan.View.purchase_red;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.ShareAndPay;
import xm.ppq.papaquan.webview_protocol.WebView_ProtocolActivity;

/**
 * Created by 购买红卡 on 2017/4/10.
 */

public class PurchaseRedActivity extends BaseActivity {

    @BindView(R.id.red_money)
    TextView red_money;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.affirm_pay)
    TextView affirm_pay;
    @BindView(R.id.not_image)
    ImageView not_image;
    @BindView(R.id.card_type)
    TextView card_type;
    @BindView(R.id.end_time)
    TextView end_time;
    @BindView(R.id.invite_edit)
    EditText invite_edit;

    private String time;
    private float money;
    private int yu_e = 0;
    private SharedPreferencesPotting potting;
    private int type;

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase_red;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        finish_result.setText("");
        center_result.setText("购买红卡");
        time = getData("time");
        type = getIntent().getIntExtra("type", 0);
        potting = new SharedPreferencesPotting(this, "my_login");
        if (time.startsWith("￥")) {
            switch (type) {
                case 1:
                    card_type.setText("一年红卡");
                    break;
                case 2:
                    card_type.setText("半年红卡");
                    break;
                case 3:
                    card_type.setText("一月红卡");
                    break;
                case 4:
                    card_type.setText("一周红卡");
                    break;
            }
            money = Float.valueOf(time.substring(1));
            red_money.setText(money + "元");
            affirm_pay.setText("确认支付￥" + money);
            if (yu_e < money) {
                not_image.setVisibility(View.VISIBLE);
                pay_check.setVisibility(View.GONE);
            } else {
                not_image.setVisibility(View.GONE);
                pay_check.setVisibility(View.VISIBLE);
            }
        } else {
            red_money.setText("元");
            not_image.setVisibility(View.GONE);
            pay_check.setVisibility(View.VISIBLE);
        }
        getInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @BindView(R.id.pay_check)
    CheckBox pay_check;
    @BindView(R.id.zfb_pay)
    CheckBox zfb_pay;
    @BindView(R.id.wx_check)
    CheckBox wx_check;
    @BindView(R.id.red_pay)
    LinearLayout red_pay;

    private int pay_type = 0;

    @OnClick({R.id.red_wx, R.id.red_zfb, R.id.red_pay})
    public void pay(View view) {
        switch (view.getId()) {
            case R.id.red_wx:
                pay_type = 0;
                break;
            case R.id.red_zfb:
                pay_type = 1;
                break;
            case R.id.red_pay:
                pay_type = 2;
                break;
        }
        Checkboxed(pay_type, wx_check, zfb_pay, pay_check);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        affirm_pay.setOnClickListener(v -> {
            String s = invite_edit.getText().toString();
            switch (pay_type) {
                case 0:
                    JsonTool jsonTool = new JsonTool();
                    jsonTool.put_key("type", "uid", "token", "tokentype", "citycode", "trade_type", "invitecode")
                            .put_value(type, potting.getItemInt("uid"),
                                    potting.getItemString("token"),
                                    1, potting.getItemString("citycode"),
                                    "APP", s);
                    ShareAndPay.WxPay(this, "http://app.papaquan.com/index.php/index/Wxpay/paycard", jsonTool.getJson());
                    break;
                case 1:
                    JsonTool z = new JsonTool();
                    z.put_key("uid", "token", "tokentype", "type", "trade_type", "citycode", "invitecode")
                            .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, type, 2, potting.getItemString("citycode"), s);
                    ShareAndPay.ZhiPay(this, BaseUrl.LIFE_URL + BaseUrl.REDZFB, z.getJson(), handler);
                    break;
                case 2:
                    JsonTool jsonTool1 = new JsonTool();
                    jsonTool1.put_key("citycode", "uid", "token", "tokentype", "type", "invitecode")
                            .put_value(potting.getItemString("citycode"), potting.getItemInt("uid"), potting.getItemString("token"), 1, type, s);
                    OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.BALANCEPAY, jsonTool1.getJson().toString(), new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean != null) {
                                if (baseBean.getCode().equals("0")) {
                                    ToastShow("购买成功");
                                    finish();
                                } else {
                                    ToastShow(baseBean.getInfo());
                                }
                            }
                        }
                    });
                    break;
            }
        });
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                HashMap<Object, Object> hash = (HashMap<Object, Object>) msg.obj;
                String key = (String) hash.get("resultStatus");
                if (key.equals("9000")) {
                    finish();
                } else {
                    ToastShow("支付失败");
                }
            }
        }
    };

    @Subscribe
    public void Success(String result) {
        if (result.equals("Success")) {
            finish();
        }
    }

    @BindView(R.id.balance)
    TextView balance;

    private void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("type", "uid", "tokentype", "citycode", "token")
                .put_value(type, potting.getItemInt("uid"), 1, potting.getItemString("citycode"), potting.getItemString("token"));
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.GETCARDSET, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean != null) {
                            LinkedTreeMap<Object, String> map = (LinkedTreeMap<Object, String>) baseBean.getData();
                            end_time.setText(map.get("endtime"));
                            balance.setText("(余额：" + map.get("money") + "元)");
                            double red_money = Double.parseDouble(map.get("price"));
                            double money = Double.parseDouble(map.get("money"));
                            if (red_money <= money) {
                                pay_check.setVisibility(View.VISIBLE);
                                not_image.setVisibility(View.GONE);
                                red_pay.setClickable(true);
                            } else {
                                red_pay.setClickable(false);
                                pay_check.setVisibility(View.GONE);
                                not_image.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }
        );
    }

    public void read(View view) {
        Intent intent = new Intent(this, WebView_ProtocolActivity.class);
        intent.putExtra("type", 0);
        startActivity(intent);
    }
}