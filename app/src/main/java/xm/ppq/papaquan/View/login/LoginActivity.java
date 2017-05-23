package xm.ppq.papaquan.View.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.LoginBean;
import xm.ppq.papaquan.Bean.LoginEevenbus;
import xm.ppq.papaquan.Presenter.login.Mutual_Login;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ConnectionUtils;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.register.RegisterActivity;
import xm.ppq.papaquan.View.retrieve.RetrieveActivity;
import xm.ppq.papaquan.wxapi.WXEntryActivity;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, Round_Login {

    @BindView(R.id.register_text)
    TextView register_text;
    @BindView(R.id.forget_pass)
    TextView forget_pass;
    @BindView(R.id.login_cancel)
    ImageView login_cancel;
    @BindView(R.id.login_user_name)
    EditText loginUserName;
    @BindView(R.id.login_user_psw)
    EditText loginUserPsw;
    @BindView(R.id.login_double)
    Button login_double;
    @BindView(R.id.imageView)
    ImageView imageView;

    private Mutual_Login mutual_login;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        mutual_login = new Mutual_Login(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        usershare = new SharedPreferencesPotting(this, "my_user");
        pushService = PushServiceFactory.getCloudPushService();
        if (!usershare.getItemString("username").equals("")) {
            loginUserName.setText(usershare.getItemString("username"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void OnLogin(LoginEevenbus eevenbus) {
        if (eevenbus.getResult().equals("成功")) finish();
    }

    @Override
    protected void setListener() {
        register_text.setOnClickListener(this);
        forget_pass.setOnClickListener(this);
        login_cancel.setOnClickListener(this);
        login_double.setOnClickListener(this);
        imageView.setOnClickListener(v -> {
            IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.APP_ID, true);
            imageView.setEnabled(false);
            mApi.registerApp(WXEntryActivity.APP_ID);
            if (mApi != null && mApi.isWXAppInstalled()) {
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test_neng";
                mApi.sendReq(req);
            } else {
                ToastShow("用户未安装微信");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_text:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), IntentCode.RequestCode.TOREGISTER);
                break;
            case R.id.forget_pass:
                Skip(RetrieveActivity.class);
                break;
            case R.id.login_cancel:
                finish();
                break;
            case R.id.login_double:
                if (ConnectionUtils.isConnected(LoginActivity.this)) {
                    mutual_login.startLogin();
                } else {
                    ToastShow("网络错误");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLOGIN) {
            boolean defaultlogin = data.getBooleanExtra("defaultlogin", false);
            if (defaultlogin) {
                loginUserName.setText(data.getStringExtra("phone"));
                loginUserPsw.setText(data.getStringExtra("psw"));
                mutual_login.startLogin();
            }
        }
    }

    @Override
    public String getPhone() {
        return loginUserName.getText().toString();
    }

    @Override
    public String getPassWord() {
        return loginUserPsw.getText().toString();
    }

    @Override
    public String getType() {
        return "1";
    }

    private SharedPreferencesPotting usershare;

    @Override
    public void OnSuccess(String result) {
        ToastShow(result);
        if (result.equals("登录成功")) {
            usershare.setItemString("username", getPhone())
                    .build();
            IMUtils.setUserId("u_" + potting.getItemInt("uid"));
            IMUtils.login();
            if (getIntent().getIntExtra("action", 0) == 1) {
                setResult(IntentCode.ResultCode.BACKTOFOLLOW);
            } else setResult(1);
            finish();
        }
    }

    private CloudPushService pushService;
    private SharedPreferencesPotting potting;

    @Override
    public void setLoginBean(LoginBean.DataBean loginbean) {
        pushService.bindAccount(String.valueOf(loginbean.getUid()), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.e("TAG", "绑定成功----" + s);
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.e("TAG", s + ":" + s1);
            }
        });
        potting.setItemString("token", loginbean.getToken())
                .setItemString("tel", loginbean.getTel())
                .setItemInt("uid", loginbean.getUid())
                .setItemString("status", "已登录")
                .build();
    }
}
