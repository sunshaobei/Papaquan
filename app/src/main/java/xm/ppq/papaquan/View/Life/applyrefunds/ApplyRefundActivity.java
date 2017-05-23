package xm.ppq.papaquan.View.Life.applyrefunds;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_sunshaobei2017.app.SunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;

public class ApplyRefundActivity extends SunActivity {

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.apply_icon)
    ImageView apply_icon;
    @BindView(R.id.title_waiting)
    TextView title_waiting;
    @BindView(R.id.content_waiting)
    TextView content_waiting;
    @BindView(R.id.apply_red_money)
    TextView apply_red_money;
    @BindView(R.id.apply_money)
    TextView apply_money;
    @BindView(R.id.apply_buy_num)
    TextView apply_buy_num;
    @BindView(R.id.apply_ed_money)
    TextView apply_ed_money;
    @BindView(R.id.apply_can_money)
    TextView apply_can_money;
    @BindView(R.id.refund_money)
    TextView refund_money;
    @BindView(R.id.edit_order)
    EditText edit_order;

    private int oid;
    private SharedPreferencesPotting potting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_refund);
        ButterKnife.bind(this);
        initStatusBar(bar);
        potting = new SharedPreferencesPotting(this, "my_login");
        initUI();
        setListener();
    }

    private Order_PayBean.DataBean dataBean;

    private void initUI() {
        oid = getIntent().getIntExtra("oid", 0);
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "oid", "token", "tokentype")
                .put_value(potting.getItemInt("uid"), oid, potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.ORDERDETAILS, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (dataBean != null) {
                    ImageLoading.common(ApplyRefundActivity.this, BaseUrl.BITMAP + dataBean.getImg(), apply_icon, R.mipmap.food);
                    title_waiting.setText(dataBean.getName());
                    content_waiting.setText(dataBean.getTitle());
                    apply_red_money.setText("￥" + dataBean.getPrice());
                    apply_money.setText(dataBean.getCostprice() + "元");
                    apply_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    apply_buy_num.setText("x" + dataBean.getNum() + "份");
                    apply_ed_money.setText("￥" + dataBean.getMoney());
                    apply_can_money.setText(dataBean.getNum() + "份");
                    toplimit = dataBean.getNum();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    Order_PayBean order_payBean = (Order_PayBean) JsonUtil.fromJson(s, Order_PayBean.class);
                    if (order_payBean.getCode() == 0) {
                        dataBean = order_payBean.getData();
                    }
                }
            }
        });
    }

    private void setListener() {

    }

    /*************************************
     * 点击事件
     **************************/
    public void finish(View v) {
        finish();
    }

    public void pay(View view) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("oid", "uid", "token", "tokentype")
                .put_value(oid, potting.getItemInt("uid"), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.REFUND, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean.getCode().equals("0")) {
                    Toast.makeText(ApplyRefundActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ApplyRefundActivity.this, baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int toplimit = 0;

    public void backtohome(View v) {
        setResult(IntentCode.ResultCode.BACKTOLIFE);
        finish();
    }

    @OnClick({R.id.minus_order, R.id.add_order})
    public void Add_Minus(View view) {
        int before = Integer.parseInt(edit_order.getText().toString());
        switch (view.getId()) {
            case R.id.minus_order:
                if (before > 0) {
                    before--;
                    edit_order.setText(String.valueOf(before));
                }
                break;
            case R.id.add_order:
                if (before < toplimit) {
                    before++;
                    edit_order.setText(String.valueOf(before));
                }
                break;
        }
    }

}
