package xm.ppq.papaquan.View.withdraw_cash;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 提现 on 2017/3/21.
 */

public class Withdraw_CashActivity extends BaseActivity {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.title_lin)
    LinearLayout title_lin;
    @BindView(R.id.money_edit)
    TextView money_edit;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.poundage)
    TextView poundage;

    private SharedPreferencesPotting potting;
    private double my_money;

    @Override
    protected int getLayout() {
        return R.layout.activity_withdraw_cash;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        title_lin.setBackgroundColor(getResources().getColor(R.color.title_color));
        left_image.setImageResource(R.drawable.white_finish);
        left_image.setPadding(25, 25, 25, 25);
        center_text.setText("余额提现");
        center_text.setTextColor(Color.WHITE);
        center_text.setTextColor(getResources().getColor(R.color.white));
        potting = new SharedPreferencesPotting(this, "my_login");
        my_money = getIntent().getDoubleExtra("my_money", 0);
        int money = (int) my_money;
        money_edit.setText(df.format(my_money - (money * 0.01)));
        balance.setText("当前余额￥" + my_money + "(每次提现金额大于10元整数)");
        textView2.setText(df.format(my_money - (money * 0.01)));
        poundage.setText("(手续服务费￥" + df.format(money * 0.01) + "元)");
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    @BindView(R.id.account_number)
    EditText account_number;

    @Override
    protected void setListener() {
        left_image.setOnClickListener(v -> finish());
    }

    public void pay(View view) {
        if (my_money >= 11) {
            if (account_number.getText().toString().equals("")) {
                ToastShow("手机号码不能为空");
            } else {
                JsonTool jsonTool = new JsonTool();
                jsonTool.put_key("uid", "token", "tokentype", "account")
                        .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, account_number.getText().toString());
                OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.CASHMONEY, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                                ToastShow("提现成功,请去查看");
                                money_edit.setText("0");
                                balance.setText("当前余额￥0(每次提现金额大于10元整数)");
                                textView2.setText("￥0");
                                poundage.setText("(手续服务费￥0元)");
                            } else {
                                ToastShow(baseBean.getInfo());
                            }
                        }
                    }
                });
            }
        } else {
            ToastShow("余额大于11元才可提现");
        }
    }
}
