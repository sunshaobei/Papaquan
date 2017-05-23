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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.MyToast;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.verification.ObtainInfo;
import xm.ppq.papaquan.qr_code.Qr_CodeActivity;
import xm.ppq.papaquan.qr_code.WeChatCaptureActivity;

/**
 * Created by 核销折扣 on 2017/4/12.
 */

public class VerAgioFragment extends LoadingOneFragment {

    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.zhekou_num)
    TextView zhekou_num;
    @BindView(R.id.agio_money)
    EditText agio_money;
    @BindView(R.id.cancel_money)
    TextView cancel_money;
    @BindView(R.id.consume_code)
    EditText consume_code;

    private View view;
    private ObtainInfo obtainInfo;
    private SharedPreferencesPotting potting;
    private double agio = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_veragio, container, false);
            ButterKnife.bind(this, view);
            obtainInfo = (ObtainInfo) getActivity();
            potting = new SharedPreferencesPotting(getContext(), "my_login");
        }
        return view;
    }

    @Override
    public void loadData() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "uid", "token", "tokentype")
                .put_value(obtainInfo.getSid(), potting.getItemInt("uid"), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.TODAYRERBATE, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    String zhekou = JsonUtil.getString(baseBean.getData().toString(), "discount");
                    if (!zhekou.equals("请添加折扣")) {
                        if (zhekou.equals("null")) {
                            zhekou = "10";
                        }
                        zhekou_num.setText(zhekou + "折");
                        double i = Double.parseDouble(zhekou);
                        agio = i / 10;
                        code.setClickable(true);
                    } else {
                        zhekou_num.setText("今日暂无折扣");
                        code.setClickable(false);
                    }
                }
            }
        });
    }

    private double v = 0.0;

    @Override
    public void setListener() {
        code.setOnClickListener(v -> {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("sid", "uid", "token", "tokentype", "usecode", "paymoney", "rebate")
                    .put_value(obtainInfo.getSid(), potting.getItemInt("uid"), potting.getItemString("token"), 1, consume_code.getText().toString(), this.v, agio * 10);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.REBATUSE, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean.getCode().equals("0")) {
                        MyToast.makeText(getContext(), 1000, Gravity.CENTER).setTextView("核销成功\n别忘记让用户评价哦～").show();
                        consume_code.setText("");
                        agio_money.setText("");
                        cancel_money.setText("");
                    } else {
                        Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
        agio_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    v = Double.valueOf(s.toString()) * agio;
                    cancel_money.setText(v + "元");
                } else {
                    cancel_money.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.qr_code)
    public void qr_code(View view) {
        Intent intent = new Intent(getContext(), WeChatCaptureActivity.class);
        getActivity().startActivityForResult(intent, 0x89);
    }

    public void setCode(String code) {
        if (isVisibleToUser) {
            consume_code.setText(code);
        }
    }
}
