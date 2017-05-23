package xm.ppq.papaquan.View.register;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Presenter.register.Mutual_Register;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.login.LoginActivity;
import xm.ppq.papaquan.webview_protocol.WebView_ProtocolActivity;

/**
 * Created by 注册页面 on 2017/2/20.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, Round_Register {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.register_number)
    EditText register_number;
    @BindView(R.id.register_password)
    EditText register_password;
    @BindView(R.id.register_set_code)
    EditText register_set_code;
    @BindView(R.id.get_code)
    Button get_code;
    @BindView(R.id.register_login)
    Button register_login;
    @BindView(R.id.register_protocol)
    CheckBox register_protocol;

    private Mutual_Register register;
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        center_text.setText("免费注册");
        register = new Mutual_Register(this);
        if (!register_protocol.isChecked()) {
            register_protocol.setChecked(true);
        }
    }

    @Override
    protected void setListener() {
        get_code.setOnClickListener(this);
        register_login.setOnClickListener(this);
        register_set_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString() != null && !s.toString().equals("")) {
                    register_login.setTextColor(getResources().getColor(R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_code:
                register.SendCode(get_code);
                break;
            case R.id.register_login:
                if (register_protocol.isChecked()) {
                    register.Register();
                } else ToastShow("请勾选协议!");
                break;
        }
    }

    @Override
    public String getPhone() {
        return register_number.getText().toString();
    }

    @Override
    public String getPassword() {
        return register_password.getText().toString();
    }

    @Override
    public String getCode() {
        return register_set_code.getText().toString();
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setSuccess(String result) {
        if (result.equals("验证码")) {
            get_code.setClickable(false);
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.obtainMessage().sendToTarget();
                }
            }, 0, 1000);
        } else {
            ToastShow("账号注册成功");
            Intent intent = getIntent();
            intent.putExtra("defaultlogin", true);
            intent.putExtra("phone", register_number.getText().toString());
            intent.putExtra("psw", register_password.getText().toString());
            setResult(IntentCode.ResultCode.BACKTOLOGIN, intent);
            finish();
        }
    }

    private int i = 180;
    private Timer timer;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            get_code.setTextColor(getResources().getColor(R.color.register_colors));
            get_code.setText("  " + i + "s  ");
            if (i == 0) {
                get_code.setTextColor(getResources().getColor(R.color.Red));
                get_code.setClickable(true);
                get_code.setText("获取验证码");
                timer.cancel();
            }
            i--;
        }
    };

    public void read(View view) {
        Intent intent = new Intent(this, WebView_ProtocolActivity.class);
        intent.putExtra("type", 2);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void Error(String cause) {

    }

    @Override
    public void ToastError(String cause) {
        ToastShow(cause);
    }
}
