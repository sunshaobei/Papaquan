package xm.ppq.papaquan.View.retrieve;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Presenter.retrieve.Mutual_Retrieve;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.reevesetup.ReeveSetUpActivity;

/**
 * Created by 获取验证码 on 2017/2/20.
 */

public class RetrieveActivity extends BaseActivity implements View.OnClickListener, Round_Retrieve {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.next_step_back)
    Button next_step_back;
    @BindView(R.id.retrieve_phone)
    EditText retrievePhone;
    @BindView(R.id.retrieve_yzm)
    EditText retrieveYzm;
    @BindView(R.id.get_yzm)
    Button btn_getyzm;

    private Mutual_Retrieve mutual_retrieve;
    private boolean TimeRunning = false;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            btn_getyzm.setTextColor(getResources().getColor(R.color.register_colors));
            btn_getyzm.setText(i + "s");
            i--;
            if (i == 0) {
                btn_getyzm.setTextColor(getResources().getColor(R.color.Red));
                btn_getyzm.setText("获取验证码");
                btn_getyzm.setTextColor(getResources().getColor(R.color.register_colors));
                btn_getyzm.setClickable(true);
                btn_getyzm.removeCallbacks(this);
            }
            btn_getyzm.postDelayed(this, 1000);
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_getback_password;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        center_text.setText("找回密码");
        mutual_retrieve = new Mutual_Retrieve(this);
    }

    @Override
    protected void setListener() {
        next_step_back.setOnClickListener(this);
        btn_getyzm.setOnClickListener(this);
        retrievePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() == 11) {
                    btn_getyzm.setTextColor(Color.RED);
                } else if (!TimeRunning) {

                    btn_getyzm.setTextColor(getResources().getColor(R.color.register_colors));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        retrieveYzm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    next_step_back.setTextColor(getResources().getColor(R.color.white));
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
            case R.id.next_step_back:
                mutual_retrieve.setCode();
                break;
            case R.id.get_yzm:
                mutual_retrieve.getCode();
                break;

        }
    }

    private int i;

    @Override
    public String getPhone() {
        return retrievePhone.getText().toString();
    }

    @Override
    public String getCode() {
        return retrieveYzm.getText().toString();
    }

    @Override
    public void getCodeSuccess(String result) {
        ToastShow(result);
        //请求成功后开始倒计时
        i = 180;
        TimeRunning = true;
        btn_getyzm.setClickable(false);
        btn_getyzm.postDelayed(runnable, 0);
    }

    @Override
    public void Error(String error) {
        ToastShow(error);
    }

    @Override
    public void setCodeSuccess(String result, String random) {
        ToastShow(result);
        Intent intent = new Intent(this, ReeveSetUpActivity.class);
        intent.putExtra("phone", getPhone());
        intent.putExtra("random", random);
        startActivity(intent);
        retrieveYzm.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTORETRI) {
            finish();
        }
    }
}
