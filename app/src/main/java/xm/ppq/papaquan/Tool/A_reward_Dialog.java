package xm.ppq.papaquan.Tool;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.Money_Dialog;
import xm.ppq.papaquan.life.Tool.ShareAndPay;

/**
 * Created by Administrator on 2017/3/15.
 */

public class A_reward_Dialog extends BaseActivity {

    @BindView(R.id.dialog_finish)
    ImageView dialog_finish;
    @BindView(R.id.dialog_head)
    ImageView dialog_head;
    @BindView(R.id.hb_name)
    TextView hb_name;
    @BindView(R.id.other_money)
    TextView other_money;
    @BindView(R.id.money_lin)
    LinearLayout money_lin;

    private String tid;
    private SharedPreferencesPotting potting;
    private Money_Dialog money_dialog;

    @Override
    protected int getLayout() {
        return R.layout.a_reward_dialog;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        tid = getData("tid");
        ImageLoading.Circular(this, getData("url"), R.drawable.default_icon, dialog_head);
        hb_name.setText(getData("name"));
        money_dialog = new Money_Dialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void Success(String result) {
        if (result.equals("Success")) {
            money_lin.setVisibility(View.GONE);
            finish();
        }
    }

    @Override
    protected void setListener() {
        money_dialog.setOnCheckListener((position, money) -> {
            if (position == 0) {
                Wx_Pay(money);
            } else {
                Ali_Pay(money);
            }
        });
        money_lin.setOnClickListener(v -> money_lin.setVisibility(View.GONE));
        other_money.setOnClickListener(v -> {
            money_lin.setVisibility(View.VISIBLE);
        });
        dialog_finish.setOnClickListener(v -> finish());
    }

    @BindView(R.id.money_edit)
    EditText money_edit;

    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four, R.id.five, R.id.six, R.id.hundred, R.id.eight_bucket, R.id.six_six_six, R.id.wancheng})
    public void OnClick(View view) {
        double money = 0;
        switch (view.getId()) {
            case R.id.one:
                money = 9.99;
                break;
            case R.id.two:
                money = 8.88;
                break;
            case R.id.three:
                money = 6.66;
                break;
            case R.id.four:
                money = 5;
                break;
            case R.id.five:
                money = 3;
                break;
            case R.id.six:
                money = 1;
                break;
            case R.id.hundred:
                money = 100;
                break;
            case R.id.eight_bucket:
                money = 88;
                break;
            case R.id.six_six_six:
                money = 66;
                break;
            case R.id.wancheng:
                if (!money_edit.getText().toString().equals("")){
                    money = Double.parseDouble(money_edit.getText().toString());
                    money_lin.setVisibility(View.GONE);
                }
                break;
        }
        money_dialog.setMoney(money);
        money_dialog.show();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x1) {
                HashMap<Object, Object> hash = (HashMap<Object, Object>) msg.obj;
                String key = (String) hash.get("resultStatus");
                if (key.equals("9000")) {
                    Success("Success");
                } else {
                    ToastShow("支付失败");
                }
            }
        }
    };

    private void Ali_Pay(double money) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "tid", "money", "type")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, tid, money, 2);
        ShareAndPay.ZhiPay(this, BaseUrl.MONEY_ZFB, jsonTool.getJson(), handler);
    }

    private void Wx_Pay(double money) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "tid", "money", "trade_type")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, tid, money, "APP");
        ShareAndPay.WxPay(this, BaseUrl.LIFE_URL + BaseUrl.PAYREWARD, jsonTool.getJson());
    }

    @Override
    public void onBackPressed() {
        if (money_lin.getVisibility() == View.VISIBLE) {
            money_lin.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();
    }
}