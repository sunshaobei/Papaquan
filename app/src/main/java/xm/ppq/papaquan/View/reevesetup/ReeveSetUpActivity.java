package xm.ppq.papaquan.View.reevesetup;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Presenter.reevesetup.Mutual_Reeve;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 设置新密码 on 2017/2/20.
 */

public class ReeveSetUpActivity extends BaseActivity implements Round_Reeve, View.OnClickListener, TextWatcher {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.new_psw1)
    EditText newPsw1;
    @BindView(R.id.new_psw2)
    EditText newPsw2;
    @BindView(R.id.complete)
    Button complete;

    private String phone;
    private Mutual_Reeve mutual_reeve;
    private Intent intent;
    private String random;

    @Override
    protected int getLayout() {
        return R.layout.activity_reevesetup;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        center_text.setText("重设密码");
        intent = getIntent();
        phone = intent.getStringExtra("phone");
        random = getData("random");
        mutual_reeve = new Mutual_Reeve(this);
        initStatusBar(statusBar);
    }

    @Override
    protected void setListener() {
        complete.setOnClickListener(this);
        newPsw1.addTextChangedListener(this);
        newPsw2.addTextChangedListener(this);
    }

    @Override
    public String getPsw1() {
        return newPsw1.getText().toString();
    }

    @Override
    public String getPsw2() {
        return newPsw2.getText().toString();
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getRandom() {
        if (random != null) {
            return random;
        }
        return "";
    }

    @Override
    public void reSetSuccess(String result) {
        ToastShow(result);
        //TODO 修改成功后结束页面
        setResult(IntentCode.ResultCode.BACKTORETRI, intent);
        finish();
    }

    @Override
    public void reSetError(String error) {
        ToastShow(error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complete:
                mutual_reeve.reSetPsw();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString() != null && !s.toString().equals("")) {
            complete.setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}