package xm.ppq.papaquan.View.make_address;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import data.CityData;
import myview.CityPickView;
import xm.ppq.papaquan.Presenter.makeaddress.Mutual_MakeAddress;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 修改个人地址 on 2017/2/23.
 */

public class Make_AddressActivity extends BaseActivity implements View.OnClickListener, Round_MakeAddress {

    @BindView(R.id.right_title)
    TextView right_title;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.minute_address)
    LinearLayout minute_address;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.postcode)
    EditText postcode;
    @BindView(R.id.street)
    EditText street;

    private String p;
    private String c;
    private String a;
    private String x;
    private int action;
    private int type;

    private Mutual_MakeAddress mutual_makeAddress;
    private CityPickView cityPickView;

    @Override
    protected int getLayout() {
        return R.layout.activity_makeaddress;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        right_title.setText("确定");
        right_title.setTextColor(Color.WHITE);
        Intent intent = getIntent();
        action = intent.getIntExtra("action", -1);
        if (action == -1) {
            type = 1;
            center_result.setText("增加地址");
        } else {
            type = 2;
            center_result.setText("修改地址");
            p = intent.getStringExtra("province");
            c = intent.getStringExtra("city");
            a = intent.getStringExtra("area");
            x = intent.getStringExtra("street");
            address.setText(p + " " + c + " " + a);
            street.setText(x);
            name.setText(intent.getStringExtra("username"));
            phone.setText(intent.getStringExtra("deliverytel"));
            postcode.setText(intent.getStringExtra("zipcode"));
        }
        cityPickView = new CityPickView(this);
        mutual_makeAddress = new Mutual_MakeAddress(this);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(this);
        minute_address.setOnClickListener(this);
        right_title.setOnClickListener(this);

        cityPickView.setOnSureListener(new CityPickView.OnSureListener() {
            @Override
            public void onSure(CityData.DataBean province, CityData.DataBean city) {
                address.setText(province.getFullname() + " " + city.getFullname());
                p = province.getFullname();
                c = city.getFullname();
            }

            @Override
            public void onSure(CityData.DataBean province, CityData.DataBean city, CityData.DataBean distric) {
                address.setText(province.getFullname() + " " + city.getFullname() + " " + distric.getFullname());
                p = province.getFullname();
                c = city.getFullname();
                a = distric.getFullname();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_result:
                finish();
                break;
            case R.id.minute_address:
                //TODO
                cityPickView.Show();
                break;
            case R.id.right_title:
                mutual_makeAddress.addNewaddress(type, action);
                break;
        }
    }

    @Override
    public String getName() {
        if (name.getText() != null) return name.getText().toString();
        return null;
    }

    @Override
    public String getPhone() {
        if (phone.getText() != null) return phone.getText().toString();
        return null;
    }

    @Override
    public String getProvince() {
        return p;
    }

    @Override
    public String getCity() {
        return c;
    }

    @Override
    public String getArea() {
        return a;
    }

    @Override
    public String getStreet() {
        return street.getText().toString();
    }

    @Override
    public int getPosition() {
        return action;
    }

    @Override
    public String getPostcode() {
        if (postcode.getText() != null) return postcode.getText().toString();
        return null;
    }

    @Override
    public void setSuccess(String s) {
        ToastShow(s);
        finish();
    }

    @Override
    public void setError(String s) {
        ToastShow(s);
    }

}
