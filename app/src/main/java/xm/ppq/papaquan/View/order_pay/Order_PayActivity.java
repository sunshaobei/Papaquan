package xm.ppq.papaquan.View.order_pay;

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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.PaincPayBean;
import xm.ppq.papaquan.Presenter.life.order_pay.Mutual_Order_Pay;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.Finish_Order;
import xm.ppq.papaquan.life.Tool.PayDialog;
import xm.ppq.papaquan.life.Tool.PayTextViewTime;
import xm.ppq.papaquan.life.Tool.ShareAndPay;

/**
 * Created by 支付订单 on 2017/4/11.
 */

public class Order_PayActivity extends BaseActivity implements Round_Order_Pay {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private String oid;
    private Finish_Order finish_order;
    private SharedPreferencesPotting potting;
    private Mutual_Order_Pay mutual_order_pay;
    private PayDialog payDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        finish_result.setText("");
        oid = getData("oid");
        finish_order = new Finish_Order(this, Integer.valueOf(oid));
        int action = getIntent().getIntExtra("action", 0);
        if (action == 1) {
            center_result.setText("等待付款");
        } else {
            center_result.setText("确认订单");
        }
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_order_pay = new Mutual_Order_Pay(this);
        if (getData("url") != null) url = getData("url");
        if (getData("type") != null) type = getData("type");
        mutual_order_pay.getInfo(url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private String url = BaseUrl.ORDERDETAILS;
    private String type = "精选";

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    @BindView(R.id.wx_pay_check)
    CheckBox wx_pay_check;
    @BindView(R.id.ali_pay_check)
    CheckBox ali_pay_check;
    @BindView(R.id.wallet_pay_check)
    CheckBox wallet_pay_check;

    private String pay = "微信";

    @OnClick({R.id.wx_pay_lin, R.id.ali_pay_lin, R.id.wallet_pay_lin, R.id.wx_pay_check, R.id.ali_pay_check, R.id.wallet_pay_check})
    public void Pay(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.wx_pay_lin:
                i = 0;
                pay = "微信";
                break;
            case R.id.ali_pay_lin:
                i = 1;
                pay = "支付宝";
                break;
            case R.id.wallet_pay_lin:
                i = 2;
                pay = "钱包";
                break;
        }
        Checkboxed(i, wx_pay_check, ali_pay_check, wallet_pay_check);
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
        return type;
    }

    @Override
    public void Toast(String result) {
        ToastShow(result);
    }

    @Override
    public void Skip() {
        if (payDialog != null) {
            payDialog.setType(type, BaseUrl.ORDERDETAILS);
            payDialog.show();
        }
    }

    @Subscribe
    public void Success(String result) {
        if (result.equals("Success")) {
            Skip();
        }
    }

    @BindView(R.id.order_shop_name)
    TextView order_shop_name;
    @BindView(R.id.order_shop)
    TextView order_shop;
    @BindView(R.id.order_guige)
    TextView order_guige;
    @BindView(R.id.order_money)
    TextView order_money;
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.order_all_money)
    TextView order_all_money;
    @BindView(R.id.yu_money)
    TextView yu_money;
    @BindView(R.id.wallet_pay_lin)
    LinearLayout wallet_pay_lin;
    @BindView(R.id.not_image)
    ImageView not_image;
    @BindView(R.id.ppjnojjm)
    PayTextViewTime ppjnojjm;

    @Override
    public void setBean(Order_PayBean.DataBean dataBean) {
        payDialog = new PayDialog(this, Double.valueOf(dataBean.getMoney()), Integer.valueOf(oid));
        payDialog.setOnDismissListener(dialog -> finish());
        payDialog.setOnDialogListener(view -> finish());
        order_shop_name.setText(dataBean.getName());
        order_shop.setText(dataBean.getTitle());
        order_guige.setText(dataBean.getSpectitle());
        order_money.setText(dataBean.getPrice() + "元");
        textView19.setText(String.valueOf(dataBean.getNum()));
        order_all_money.setText(dataBean.getMoney() + "元");
        yu_money.setText("(余额：" + dataBean.getMymoney() + "元)");
        double money = Double.parseDouble(dataBean.getMoney());
        double mymoney = Double.parseDouble(dataBean.getMymoney());
        ppjnojjm.setTimeDiscount(dataBean.getOrdertime(), dataBean.getNowtime());
        if (mymoney >= money) {
            wallet_pay_lin.setClickable(true);
            not_image.setVisibility(View.GONE);
            wallet_pay_check.setVisibility(View.VISIBLE);
        } else {
            wallet_pay_lin.setClickable(false);
            not_image.setVisibility(View.VISIBLE);
            wallet_pay_check.setVisibility(View.GONE);
        }
        if (dataBean.getOrdertime() - dataBean.getNowtime() < 0) {
            Toast("订单已到期，请重新购买");
            finish();
        }
    }

    @Override
    public void setPaincBean(PaincPayBean.DataBean dataBean) {
        order_shop_name.setText(dataBean.getShop_name());
        order_shop.setText(dataBean.getPanic_info().getTitle());
        order_guige.setText("无");
        order_money.setText(dataBean.getPanic_info().getPrice() + "元");
        textView19.setText("1");
        order_all_money.setText(dataBean.getPay_money() + "元");
        yu_money.setText("(余额：" + dataBean.getMymoney() + "元)");
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                HashMap<Object, Object> hash = (HashMap<Object, Object>) msg.obj;
                String key = (String) hash.get("resultStatus");
                if (key.equals("9000")) {
                    Skip();
                } else {
                    Toast("支付失败");
                }
            }
        }
    };

    @OnClick(R.id.affirm_pay)
    public void onClick() {
        if (pay.equals("支付宝")) {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("oid", "uid", "token", "tokentype", "type")
                    .put_value(oid, potting.getItemInt("uid"), potting.getItemString("token"), 1, 2);
            ShareAndPay.ZhiPay(this, BaseUrl.LIFE_URL + BaseUrl.COUPONPAY_ZFB, jsonTool.getJson(), handler);
        } else if (pay.equals("微信")) {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "oid", "trade_type", "token", "tokentype")
                    .put_value(potting.getItemInt("uid"), oid, "APP", potting.getItemString("token"), 1);
            ShareAndPay.WxPay(this, BaseUrl.LIFE_URL + BaseUrl.PAYCOUPON, jsonTool.getJson());
        } else if (pay.equals("钱包")) {
            mutual_order_pay.Pay();
        }
    }

    public void finish_order(View view) {
        finish_order.Show(view);
    }

}