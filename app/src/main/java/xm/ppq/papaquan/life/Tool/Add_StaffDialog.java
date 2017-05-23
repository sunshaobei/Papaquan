package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_sunshaobei2017.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.StaffBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Add_StaffDialog extends BaseDialog {

    private String sid;
    private SharedPreferencesPotting potting;
    private String type = "增加";

    public Add_StaffDialog(Context context, int themeResId) {
        super(context, R.style.dialog);
    }

    public Add_StaffDialog(Context context, String sid) {
        super(context, R.style.dialog);
        this.sid = sid;
    }

    public Add_StaffDialog(Context context, String type, StaffBean.DataBean dataBean) {
        super(context, R.style.dialog);
        this.type = type;
        this.dataBean = dataBean;
    }

    private StaffBean.DataBean dataBean;

    @Override
    protected int getLayout() {
        return R.layout.add_staff;
    }

    private TextView admin_name;
    private TextView name;
    private EditText admin_uid;
    private TextView admin_finish;
    private TextView admin_sure;

    public void setDataBean(StaffBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void initUI() {
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        admin_name = bind(R.id.admin_name);
        name = bind(R.id.name);
        admin_uid = bind(R.id.admin_uid);
        admin_finish = bind(R.id.admin_finish);
        admin_finish.setOnClickListener(v -> dismiss());
        admin_sure = bind(R.id.admin_sure);
        admin_uid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                JsonTool jsonTool = new JsonTool();
                jsonTool.put_key("uid", "token", "tokentype", "touid")
                        .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, s);
                OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.GETUSER, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("0")) {
                            name.setText(JsonUtil.getString(baseBean.getData().toString(), "nickname"));
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        admin_sure.setOnClickListener(v -> {
            if (type.equals("增加")) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("sid", sid)
                            .put("uid", potting.getItemInt("uid"))
                            .put("touid", admin_uid.getText().toString())
                            .put("token", potting.getItemString("token"))
                            .put("tokentype", 1);
                    OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.ADDADMIN, jsonObject.toString(), new Subscriber<BaseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (baseBean.getCode().equals("0")) {
                                Toast.makeText(getContext(), "新增成功", Toast.LENGTH_SHORT).show();
                                EventBus.getDefault().post(new String("refresh"));
                                dismiss();
                            } else {
                                Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                changeUser();
            }
        });
        if (type.equals("增加")) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", potting.getItemInt("uid"))
                        .put("token", potting.getItemString("token"))
                        .put("tokentype", 1)
                        .put("sid", sid);
                OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.ADMINNUMBER, jsonObject.toString(), new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (baseBean.getCode().equals("0")) {
                            admin_name.setText(baseBean.getData().toString());
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            admin_name.setText(dataBean.getName());
        }

    }

    private void changeUser() {
        //TODO 少一个touid参数记住
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", dataBean.getId())
                    .put("sid", dataBean.getSid())
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1)
                    .put("touid", admin_uid.getText().toString());
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.EDITADMIN, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean.getCode().equals("0")) {
                        Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new String("refresh"));
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}