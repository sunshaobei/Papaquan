package xm.ppq.papaquan.View.owncoin;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ChangeBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.balance_detail.ExchangeHistoryActivity;


public class Exchange extends BaseActivity {

    @BindView(R.id.finish)
    View finish;
    @BindView(R.id.myexchange)
    View myexchange;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.code)
    TextView code;

    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_exchange;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        potting = new SharedPreferencesPotting(this, "my_login");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("id", "uid", "token", "tokentype")
                .put_value(getData("id"), potting.getItemInt("uid"), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.EXSUCCESS, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    ChangeBean changeBean = (ChangeBean) JsonUtil.fromJson(s, ChangeBean.class);
                    if (changeBean.getCode() == 0) {
                        code.setText("兑换码：" + changeBean.getData().getCode());
                    }
                }
            }
        });
    }

    @Override
    protected void setListener() {
        finish.setOnClickListener(v -> {
            setResult(IntentCode.ResultCode.BACKTOEXCHANGEDETAIL);
            finish();
        });
        myexchange.setOnClickListener(v -> {
            //TODO 跳到我的兑换记录页面
            startActivity(new Intent(Exchange.this, ExchangeHistoryActivity.class));
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.KEYCODE_BACK) {
            setResult(IntentCode.ResultCode.BACKTOEXCHANGEDETAIL);
        }
        return super.onKeyDown(keyCode, event);
    }
}
