package xm.ppq.papaquan.View.bindphone;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.LoginBean;
import xm.ppq.papaquan.Bean.LoginEevenbus;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;


/**
 * Created by Administrator on 2017/2/20.
 */

public class BindPhoneActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.code_get)
    Button code_get;
    @BindView(R.id.bind_phone)
    EditText bind_phone;
    @BindView(R.id.bind_code)
    EditText bind_code;
    @BindView(R.id.bind_now)
    Button bind_now;

    private SharedPreferencesPotting wxpotting;
    private SharedPreferencesPotting loginpotting;

    @Override
    protected int getLayout() {
        return R.layout.activity_bindphone;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        center_text.setText("绑定手机");
        code_get.setClickable(false);
        pushService = PushServiceFactory.getCloudPushService();
        wxpotting = new SharedPreferencesPotting(this, "my_wx");
        loginpotting = new SharedPreferencesPotting(this, "my_login");
    }

    @Override
    protected void setListener() {
        code_get.setOnClickListener(this);
        bind_now.setOnClickListener(this);
        bind_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 11) {
                    code_get.setTextColor(getResources().getColor(R.color.Red));
                    code_get.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void BindAccount(String tel) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tel", tel)
                    .put("code", bind_code.getText().toString())
                    .put("type", 2)
                    .put("headurl", wxpotting.getItemString("headurl"))
                    .put("openid", wxpotting.getItemString("openid"))
                    .put("unionid", wxpotting.getItemString("unionid"))
                    .put("sex", wxpotting.getItemString("sex"))
                    .put("wxname", wxpotting.getItemString("nickname"))
                    .put("citycode", loginpotting.getItemString("citycode"));
            OkPotting.getInstance(BaseUrl.PAPA_URL)
                    .AskOne("Login/bindphone", jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            if (s != null) {
                                switch (JsonUtil.getInt(s, "code")) {
                                    case 0:
                                        LoginBean loginBean = (LoginBean) JsonUtil.fromJson(s, LoginBean.class);
                                        if (loginBean != null) {
                                            pushService.bindAccount(String.valueOf(loginBean.getData().getUid()), new CommonCallback() {
                                                @Override
                                                public void onSuccess(String s) {
                                                    Log.e("TAG", "绑定成功----" + s);
                                                }

                                                @Override
                                                public void onFailed(String s, String s1) {
                                                    Log.e("TAG", s + ":" + s1);
                                                }
                                            });
                                            loginpotting.setItemString("token", loginBean.getData().getToken())
                                                    .setItemString("tel", loginBean.getData().getTel())
                                                    .setItemInt("uid", loginBean.getData().getUid())
                                                    .setItemString("status", "已登录")
                                                    .build();
                                            finish();
                                            EventBus.getDefault().post(new LoginEevenbus("成功"));
                                        }
                                        break;
                                }
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private CloudPushService pushService;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4001) {
                int i = (int) msg.obj;
                if (i >= 0) {
                    code_get.setText(i + "秒");
                    if (code_get.isClickable() == true) {
                        code_get.setClickable(false);
                    }
                } else {
                    code_get.setClickable(true);
                    code_get.setText("获得验证码");
                    timer.cancel();
                }
            }
        }
    };

    private int time = 180;
    private Timer timer;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_now:
                BindAccount(bind_phone.getText().toString());
                break;
            case R.id.code_get:
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "psw")
                            .put("tel", bind_phone.getText().toString());
                    OkPotting.getInstance(BaseUrl.PAPA_URL)
                            .AskOne(BaseUrl.SENDCODE, jsonObject.toString(), new Subscriber<String>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(String s) {
                                    if (s != null) {
                                        switch (JsonUtil.getInt(s, "code")) {
                                            case 0:
                                                if (timer == null) {
                                                    timer = new Timer();
                                                }
                                                timer.schedule(new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        handler.obtainMessage(4001, time).sendToTarget();
                                                        time--;
                                                    }
                                                }, 0, 1000);
                                                break;
                                            case 1:

                                                break;
                                            case 3:
                                                ToastShow("验证码发送过于频繁");
                                                break;
                                        }
                                    }
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}