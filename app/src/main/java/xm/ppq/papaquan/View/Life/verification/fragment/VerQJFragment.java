/*消费码:1为抢购2为精选*/
package xm.ppq.papaquan.View.Life.verification.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.MyToast;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.verification.ObtainInfo;
import xm.ppq.papaquan.qr_code.WeChatCaptureActivity;

/**
 * Created by 核销抢购/精选 on 2017/4/12.
 */

public class VerQJFragment extends LoadingOneFragment {

    @BindView(R.id.ver_code_text)
    TextView ver_code_text;
    @BindView(R.id.code)
    EditText code;

    private View view;
    private SharedPreferencesPotting potting;
    private ObtainInfo obtainInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_verqj, container, false);
            ButterKnife.bind(this, view);
            obtainInfo = (ObtainInfo) getActivity();
        }
        return view;
    }

    @Override
    public void loadData() {
        potting = new SharedPreferencesPotting(getContext(), "my_login");
    }

    @BindView(R.id.editText3)
    EditText editText3;

    @Override
    public void setListener() {
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 5) {
                    JsonTool jsonTool = new JsonTool();
                    jsonTool.put_key("uid", "token", "tokentype", "sid", "code")
                            .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, obtainInfo.getSid(), s.toString());
                    OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.CIDENAME, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                                    LinkedTreeMap<String, String> map = (LinkedTreeMap<String, String>) baseBean.getData();
                                    editText3.setText(map.get("title"));
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ver_code_text.setOnClickListener(v -> {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "token", "tokentype", "sid", "code", "num")
                    .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, obtainInfo.getSid(), code.getText().toString(), edit_order.getText().toString());
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.SHOPUSE, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                            MyToast.makeText(getContext(), 1000, Gravity.CENTER).setTextView("核销成功\n别忘记让用户评价哦～").show();
                            code.setText("");
                            editText3.setText("");
                            edit_order.setText("1");
                        } else {
                            Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        });
    }

    @OnClick(R.id.qr_code)
    public void qr_code(View view) {
        Intent intent = new Intent(getContext(), WeChatCaptureActivity.class);
        getActivity().startActivityForResult(intent, 0x89);
    }

    public void setCode(String code) {
        if (isVisibleToUser) {
            this.code.setText(code);
        }
    }

    @BindView(R.id.edit_order)
    EditText edit_order;

    @OnClick({R.id.minus_order, R.id.add_order})
    public void onClick(View view) {
        int v = Integer.valueOf(edit_order.getText().toString());
        switch (view.getId()) {
            case R.id.minus_order:
                if (v >= 2) {
                    v--;
                    edit_order.setText(String.valueOf(v));
                }
                break;
            case R.id.add_order:
                v++;
                edit_order.setText(String.valueOf(v));
                break;
        }
    }
}