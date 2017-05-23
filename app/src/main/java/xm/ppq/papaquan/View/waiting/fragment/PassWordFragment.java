package xm.ppq.papaquan.View.waiting.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.WaittingData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.waiting.DataUtil;
import xm.ppq.papaquan.life.Tool.MakeDialog;

/**
 * Created by 密码核销 on 2017/4/11.
 */

public class PassWordFragment<T> extends Fragment {

    private int pid;
    private int sid;

    @BindView(R.id.edit_order)
    EditText edit_order;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.suretouse)
    TextView suretouse;
    @BindView(R.id.editText)
    TextView editText;
    @BindView(R.id.minus_order)
    ImageView minus_order;
    @BindView(R.id.add_order)
    ImageView add_order;

    private ArrayList<T> list = new ArrayList<>();
    private View view;
    private int make_num;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> mitems = new ArrayList<>();
    private int clerk_id;
    private String clerk_name;
    private SharedPreferencesPotting my_login;
    private String type;
    private MakeDialog makeDialog;
    private DataUtil dataUtil;

    public PassWordFragment() {
    }

    public PassWordFragment(List<T> list, int pid, int sid, String type, int make_num) {
        this.pid = pid;
        this.sid = sid;
        this.list.clear();
        this.list.addAll(list);
        this.type = type;
        this.make_num = make_num;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_password, container, false);
            ButterKnife.bind(this, view);
            my_login = new SharedPreferencesPotting(getActivity(), "my_login");
            dataUtil = (DataUtil) getActivity();
        }
        loadData();
        setListener();
        return view;
    }

    public void loadData() {
        if (type.equals("抢购")) {
            for (T t : list) {
                mitems.add(((WaittingData.DataBean.ClerkListBean) t).getName());
            }
            clerk_id = ((WaittingData.DataBean.ClerkListBean) list.get(0)).getClerk_id();
            clerk_name = ((WaittingData.DataBean.ClerkListBean) list.get(0)).getName();
        } else {
            for (T t : list) {
                mitems.add(((Order_PayBean.Other) t).getName());
            }
            clerk_id = ((Order_PayBean.Other) list.get(0)).getUid();
            clerk_name = ((Order_PayBean.Other) list.get(0)).getName();
        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, mitems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        edit_order.setFocusable(false);
        edit_order.setTextColor(Color.parseColor("#999999"));
        makeDialog = new MakeDialog(getContext());
        makeDialog.setOnDialogListener(view1 -> getActivity().finish());
        makeDialog.setOnDismissListener(dialog -> getActivity().finish());
    }

    public void setListener() {
        if (type.equals("精选")) {
            minus_order.setOnClickListener(v -> {
                int i = Integer.parseInt(edit_order.getText().toString());
                if (i >= 2) {
                    i--;
                    edit_order.setText(String.valueOf(i));
                }
            });
            add_order.setOnClickListener(v -> {
                int i = Integer.parseInt(edit_order.getText().toString());
                if (i < make_num) {
                    i++;
                    edit_order.setText(String.valueOf(i));
                }
            });
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (type.equals("抢购")) {
                    clerk_id = ((WaittingData.DataBean.ClerkListBean) list.get(position)).getClerk_id();
                    clerk_name = ((WaittingData.DataBean.ClerkListBean) list.get(position)).getName();
                } else {
                    clerk_id = ((Order_PayBean.Other) list.get(position)).getUid();
                    clerk_name = ((Order_PayBean.Other) list.get(position)).getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        suretouse.setOnClickListener(v -> {
            if (type.equals("抢购")) {
                panic();
            } else {
                coupon();
            }
        });
    }

    /**
     * 精选核销
     */
    private void coupon() {
        if (!TextUtils.isEmpty(editText.getText())) {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "oid", "num", "clerk", "pwd", "token", "tokentype")
                    .put_value(my_login.getItemInt("uid"), pid, edit_order.getText().toString(), clerk_id, editText.getText().toString()
                            , my_login.getItemString("token"), 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.COUPON_USE, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                            makeDialog.setType("精选");
                            makeDialog.setIdAll(dataUtil.getId(), sid);
                            makeDialog.show();
                        } else {
                            Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }

    /**
     * 抢购核销
     */
    private void panic() {
        if (!TextUtils.isEmpty(editText.getText())) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", my_login.getItemInt("uid"))
                        .put("token", my_login.getItemString("token"))
                        .put("tokentype", 1)
                        .put("clerk_id", clerk_id)
                        .put("pid", pid)
                        .put("pwd", editText.getText().toString())
                        .put("sid", sid)
                        .put("clerk_name", clerk_name);
                OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PSWWRITEOFF, jsonObject.toString(), new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        if (o != null) {
                            String code = JsonUtil.getString(o, "code");
                            if (code != null && code.equals("0")) {
                                makeDialog.setType("抢购");
                                makeDialog.setIdAll(pid, sid);
                                makeDialog.show();
                            } else {
                                Toast.makeText(getActivity(), JsonUtil.getString(o, "info"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}