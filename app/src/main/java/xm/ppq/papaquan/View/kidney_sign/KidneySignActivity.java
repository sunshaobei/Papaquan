package xm.ppq.papaquan.View.kidney_sign;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 个性签名  on 2017/3/2.
 */

public class KidneySignActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    @BindView(R.id.signature_edit)
    EditText signature_edit;
    @BindView(R.id.remaining_word)
    TextView remaining_word;
    @BindView(R.id.sign_back)
    TextView sign_back;
    @BindView(R.id.sign_confirm)
    TextView sign_confirm;

    private String signature;
    private SharedPreferencesPotting potting;
    private String sign_result;

    @Override
    protected int getLayout() {
        return R.layout.activity_kidney_sign;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        signature = getIntent().getStringExtra("signname");
        potting = new SharedPreferencesPotting(this, "my_login");
        if (!signature.isEmpty()) {
            remaining_word.setText(signature.length() + "/50");
            signature_edit.setText(signature);
        }
        sign_result = signature_edit.getText().toString();
    }

    @Override
    protected void setListener() {
        sign_back.setOnClickListener(this);
        signature_edit.addTextChangedListener(this);
        sign_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_back:
                finish();
                break;
            case R.id.sign_confirm:
                if (sign_result.equals(signature_edit.getText().toString())) {
                    ToastShow("签名尚未修改");
                } else {
                    Sign_Make();
                }
                break;
        }
    }

    private void Sign_Make() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("info", signature_edit.getText().toString());
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("type", "profiles");
            jsonObject.put("token", potting.getItemString("token"));
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.MAKE_N_S_S, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean != null) {
                        switch (baseBean.getCode()) {
                            case "0":
                                ToastShow("修改成功");
                                setResult(20);
                                finish();
                                break;
                            case "1":
                                ToastShow("修改失败，请重试");
                                break;
                            case "2":
                                ToastShow("个性签名不能为空");
                                break;
                        }
                    }
                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        remaining_word.setText(s.length() + "/50");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
