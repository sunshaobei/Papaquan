package xm.ppq.papaquan.View.Life.panic_pay_waiting;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.PaincPayBean;
import xm.ppq.papaquan.Presenter.life.order_pay.Mutual_Order_Pay;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.order_pay.Round_Order_Pay;
import xm.ppq.papaquan.life.Tool.PayDialog;
import xm.ppq.papaquan.life.Tool.PayTextViewTime;
import xm.ppq.papaquan.life.Tool.ShareAndPay;

/**
 * Created by EdgeDi on 20:32.
 */

public class Panic_Pay_WaitingActivity extends BaseActivity implements Round_Order_Pay {

    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;

    private Mutual_Order_Pay mutual_order_pay;
    private SharedPreferencesPotting potting;
    private String oid;

    @Override
    protected int getLayout() {
        return R.layout.activity_panic_pay_waiting;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        finish_result.setText("");
        center_result.setText("等待付款");
        oid = getData("oid");
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_order_pay = new Mutual_Order_Pay(this);
        mutual_order_pay.getInfo(BaseUrl.USERINFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    @Override
    public String getOid() {
        return oid;
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getType() {
        return "抢购";
    }

    @Override
    public void Toast(String result) {
        ToastShow(result);
    }

    @Override
    public void Skip() {

    }

    @Override
    public void setBean(Order_PayBean.DataBean dataBean) {

    }

    private String pay_type = "微信";

    @OnClick({R.id.wx_pay_lin, R.id.ali_pay_lin, R.id.wallet_pay_lin})
    public void OnClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.wx_pay_lin:
                pay_type = "微信";
                i = 0;
                break;
            case R.id.ali_pay_lin:
                pay_type = "支付宝";
                i = 1;
                break;
            case R.id.wallet_pay_lin:
                pay_type = "钱包";
                i = 2;
                break;
        }
        Checkboxed(i, wx_pay_check, ali_pay_check, wallet_pay_check);
    }

    @OnClick(R.id.affirm_pay)
    public void pay() {
        switch (pay_type) {
            case "微信":
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderid", oid)
                            .put("trade_type", "APP")
                            .put("uid", getUid())
                            .put("tokentype", 1)
                            .put("token", potting.getItemString("token"))
                            .put("citycode", potting.getItemString("citycode"));
                    ShareAndPay.WxPay(this, BaseUrl.LIFE_URL + BaseUrl.WXPAY_PAINC, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "支付宝":
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderid", oid)
                            .put("uid", getUid())
                            .put("tokentype", 1)
                            .put("token", potting.getItemString("token"))
                            .put("citycode", potting.getItemString("citycode"));
                    ShareAndPay.ZhiPay(this, BaseUrl.LIFE_URL + BaseUrl.ALIPANIC, jsonObject, handler);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "钱包":
                JsonTool jsonTool = new JsonTool();
                jsonTool.put_key("uid", "orderid", "token", "tokentype")
                        .put_value(potting.getItemInt("uid"), oid, potting.getItemString("token"), 1);
                OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.MONEYPAY, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                                Success("Success");
                            } else {
                                ToastShow(baseBean.getInfo());
                            }
                        }
                    }
                });
                break;
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                HashMap<Object, Object> hash = (HashMap<Object, Object>) msg.obj;
                String key = (String) hash.get("resultStatus");
                if (key.equals("9000")) {
                    Success("Success");
                } else {
                    Toast("支付失败");
                }
            }
        }
    };

    private PayDialog payDialog;

    @Subscribe
    public void Success(String result) {
        if (result.equals("Success")) {
            if (payDialog != null) {
                payDialog.setType("抢购", BaseUrl.WAITTINGFORUSEINFO);
                payDialog.show();
            }
        }
    }

    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.shopname)
    TextView shopname;
    @BindView(R.id.content_waiting)
    TextView content_waiting;
    @BindView(R.id.price_now)
    TextView price_now;
    @BindView(R.id.price_past)
    TextView price_past;
    @BindView(R.id.user_level)
    TextView user_level;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.need_money)
    TextView need_money;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.wx_pay_check)
    CheckBox wx_pay_check;
    @BindView(R.id.ali_pay_check)
    CheckBox ali_pay_check;
    @BindView(R.id.wallet_pay_check)
    CheckBox wallet_pay_check;
    @BindView(R.id.not_image)
    ImageView not_image;
    @BindView(R.id.wallet_pay_lin)
    LinearLayout wallet_pay_lin;
    @BindView(R.id.ppjnojjm)
    PayTextViewTime pay_text_time;

    @Override
    public void setPaincBean(PaincPayBean.DataBean dataBean) {
        if (dataBean.getPanic_info().getUphour().equals("0")) {
            state.setVisibility(View.VISIBLE);
        } else {
            state.setVisibility(View.GONE);
        }
        ImageLoading.common(this, BaseUrl.BITMAP + dataBean.getPanic_info().getImg(), icon, R.mipmap.food);
        shopname.setText(dataBean.getShop_name());
        content_waiting.setText(dataBean.getPanic_info().getTitle());
        price_now.setText("￥" + dataBean.getPanic_info().getBuying_price());
        price_past.setText(dataBean.getPanic_info().getPrice() + "元");
        if (dataBean.getIs_vip() == 1) {
            user_level.setText("红卡用户");
            double price = Double.parseDouble(dataBean.getPanic_info().getBuying_price());
            double vip_pice = Double.parseDouble(dataBean.getPanic_info().getVip_price());
            money.setText("在省￥" + (price - vip_pice));
        } else {
            user_level.setText("普通用户");
            money.setText("在省￥0.0");
        }
        double my_money = Double.parseDouble(dataBean.getMymoney());
        double pay_money = Double.parseDouble(dataBean.getPay_money());
        need_money.setText("￥" + pay_money);
        payDialog = new PayDialog(this, pay_money, Integer.valueOf(oid));
        payDialog.setOnDismissListener(dialog -> finish());
        payDialog.setOnDialogListener(view -> finish());
        balance.setText("(余额:" + my_money + "元)");
        if (my_money >= pay_money) {//余额充足
            wallet_pay_lin.setClickable(true);
            wallet_pay_check.setVisibility(View.VISIBLE);
            not_image.setVisibility(View.GONE);
        } else {//余额不足
            wallet_pay_lin.setClickable(false);
            wallet_pay_check.setVisibility(View.GONE);
            not_image.setVisibility(View.VISIBLE);
        }
        pay_text_time.setTime(dataBean.getCreatetime(), dataBean.getNowtime());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x8) {
            Success("Success");
        }
    }
}