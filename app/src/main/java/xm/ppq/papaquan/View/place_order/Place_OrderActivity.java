package xm.ppq.papaquan.View.place_order;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.ProductBean;
import xm.ppq.papaquan.Presenter.place_order.Mutual_Place_Order;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.order_pay.Order_PayActivity;
import xm.ppq.papaquan.life.Tool.MoreDialog;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Place_OrderActivity extends BaseActivity implements Round_Place_Order {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.minus_order)
    ImageView minus_order;
    @BindView(R.id.add_order)
    ImageView add_order;
    @BindView(R.id.edit_order)
    EditText edit_order;
    @BindView(R.id.btn_order)
    TextView btn_order;
    @BindView(R.id.place_shop_name)
    TextView place_shop_name;
    @BindView(R.id.place_shop)
    TextView place_shop;
    @BindView(R.id.place_spec)
    TextView place_spec;
    @BindView(R.id.place_money)
    TextView place_money;
    @BindView(R.id.place_all_money)
    TextView place_all_money;

    private MoreDialog moreDialog;
    private Mutual_Place_Order mutual_place_order;
    private SharedPreferencesPotting my_login;
    private ProductBean.DataBean.SpecarrBean specarrBean;
    private String shopname;
    private String shop;
    private double a_money = 0;
    private int cid;
    private String spid;

    @Override
    protected int getLayout() {
        return R.layout.activity_place_order;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        shop = getData("shop");
        shopname = getData("shopname");
        moreDialog = new MoreDialog(this);
        moreDialog.setOnDialogListener(view -> finish());
        cid = getIntent().getIntExtra("cid", 0);
        specarrBean = (ProductBean.DataBean.SpecarrBean) getIntent().getSerializableExtra("bean");
        edit_order.setText("1");
        finish_result.setText("");
        center_result.setText("提交订单");
        mutual_place_order = new Mutual_Place_Order(this);
        my_login = new SharedPreferencesPotting(this, "my_login");
        if (specarrBean != null) {
            spid = String.valueOf(specarrBean.getId());
            place_spec.setText(specarrBean.getTitle());
            place_money.setText(specarrBean.getPrice() + "元");
            a_money = Double.valueOf(specarrBean.getPrice());
        } else {
            spid = getData("spid");
            place_spec.setText("无");
            place_money.setText(getData("price") + "元");
            a_money = Double.valueOf(getData("price"));
        }
        place_all_money.setText(df.format(a_money * 1) + "元");
        place_shop_name.setText(Stringutil.ThreeString(shopname, ""));
        place_shop.setText(Stringutil.ThreeString(shop, ""));
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        add_order.setOnClickListener(v -> mutual_place_order.Count("+"));
        minus_order.setOnClickListener(v -> mutual_place_order.Count("-"));
    }

    private String number;

    @Override
    public void setEdit(String number, String money) {
        this.number = number;
        edit_order.setText(number);
        place_all_money.setText(money);
    }

    @Override
    public int getEdit() {
        return Integer.valueOf(edit_order.getText().toString());
    }

    @Override
    public double getA_Money() {
        return a_money;
    }

    /************************
     * onClick
     *****************************/
    public void pay(View v) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1)
                    .put("cid", cid)
                    .put("spid", spid)
                    .put("num", number)
                    .put("agent", "app");
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.COUPONORDER, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    String code = JsonUtil.getString(o, "code");
                    String info = JsonUtil.getString(o, "info");
                    if (code.equals("0")) {
                        String data = JsonUtil.getString(o, "data");
                        data = JsonUtil.getString(data, "oid");
                        Intent intent = new Intent(getActivity(), Order_PayActivity.class);
                        intent.putExtra("url", BaseUrl.ORDERDETAILS);
                        intent.putExtra("type", "精选");
                        intent.putExtra("oid", data);
                        startActivity(intent);
                        finish();
                    } else if (code.equals("-2")) {
                        String data = JsonUtil.getString(o, "data");
                        data = JsonUtil.getString(data, "oid");
                        moreDialog.setOid(data);
                        if (moreDialog != null) moreDialog.show();
                    } else {
                        ToastShow(info);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}