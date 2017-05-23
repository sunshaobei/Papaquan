package xm.ppq.papaquan.View.revise_password;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.LoginBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 修改密码 on 2017/4/9.
 */

public class RevisePassWordActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.old_edit)
    EditText old_edit;
    @BindView(R.id.new_edit)
    EditText new_edit;
    @BindView(R.id.check_edit)
    EditText check_edit;
    @BindView(R.id.make_btn)
    TextView make_btn;

    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_revise_password;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("修改密码");
        potting = new SharedPreferencesPotting(this, "my_login");
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    public void make_up(View view) {
        if (old_edit.getText().toString().equals("")) {
            ToastShow("旧密码不能为空");
        } else {
            if (old_edit.getText().toString().equals(new_edit.getText().toString())) {
                ToastShow("新旧密码不能一致");
            } else {
                if (new_edit.getText().length() < 6) {
                    ToastShow("密码要6位以上");
                } else {
                    if (!check_edit.getText().toString().equals(new_edit.getText().toString())) {
                        ToastShow("俩次密码不一致");
                    } else {
                        make_btn.setClickable(false);
                        JsonTool jsonTool = new JsonTool();
                        jsonTool.put_key("uid", "oldpsw", "newpsw", "checkpsw", "token", "tokentype")
                                .put_value(potting.getItemInt("uid"),
                                        old_edit.getText().toString(),
                                        new_edit.getText().toString(),
                                        check_edit.getText().toString(),
                                        potting.getItemString("token"),
                                        1);
                        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.MODIFYPASS, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(BaseBean baseBean) {
                                        if (baseBean != null) {
                                            if (baseBean.getCode().equals("0")) {
                                                JsonTool jsonTool1 = new JsonTool();
                                                jsonTool1.put_key("tel", "psw", "type")
                                                        .put_value(potting.getItemString("tel"), new_edit.getText().toString(), 1);
                                                OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.LOGIN, jsonTool1.getJson().toString(), new Subscriber<String>() {
                                                    @Override
                                                    public void onCompleted() {

                                                    }

                                                    @Override
                                                    public void onError(Throwable e) {

                                                    }

                                                    @Override
                                                    public void onNext(String s) {
                                                        if (s != null) {
                                                            LoginBean loginbean = (LoginBean) JsonUtil.fromJson(s, LoginBean.class);
                                                            LoginBean.DataBean data = loginbean.getData();
                                                            potting.setItemString("token", data.getToken()).build();
                                                            ToastShow("修改成功");
                                                        }
                                                        make_btn.setClickable(true);
                                                    }
                                                });
                                            } else {
                                                ToastShow(baseBean.getInfo());
                                            }
                                        }
                                    }
                                }

                        );
                    }
                }
            }
        }

    }
}
