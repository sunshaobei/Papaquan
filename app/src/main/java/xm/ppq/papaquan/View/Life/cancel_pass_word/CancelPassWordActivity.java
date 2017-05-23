package xm.ppq.papaquan.View.Life.cancel_pass_word;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.CancelPwBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 核销密码 on 2017/4/14.
 */

public class CancelPassWordActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.now_password)
    TextView now_password;
    @BindView(R.id.make_pass_word_edit)
    EditText make;

    private String sid;
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_cancel_pass_word;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("核销密码");
        sid = getData("sid");
        potting = new SharedPreferencesPotting(this, "my_login");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid)
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PSD, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        CancelPwBean cancelPwBean = (CancelPwBean) JsonUtil.fromJson(s, CancelPwBean.class);
                        if (cancelPwBean.getCode() == 0) {
                            now_password.setText("当前核销密码：" + cancelPwBean.getData().getPwd());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    public void MakePassWord(View view) {
        String pad = make.getText().toString();
        if (!pad.equals("")) {
            if (pad.length() >= 6) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("sid", sid)
                            .put("uid", potting.getItemInt("uid"))
                            .put("token", potting.getItemString("token"))
                            .put("tokentype", 1)
                            .put("pwd", String.valueOf(pad));
                    OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.MAKEPSD, jsonObject.toString(), new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }


                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean.getCode().equals("0")) {
                                ToastShow("修改成功");
                                now_password.setText("当前核销密码：" + pad);
                            } else {
                                ToastShow(baseBean.getInfo());
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                ToastShow("核销密码要在6到12位直接");
            }
        } else {
            ToastShow("核销密码不能为空");
        }
    }
}
