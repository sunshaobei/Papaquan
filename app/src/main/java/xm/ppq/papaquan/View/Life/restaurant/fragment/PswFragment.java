package xm.ppq.papaquan.View.Life.restaurant.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.life.RestaurantBean;
import xm.ppq.papaquan.Bean.life.UserRestBean;
import xm.ppq.papaquan.Presenter.life.restaurant.Mutual_Restaurant;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.restaurant.Round_Restaurant;
import xm.ppq.papaquan.life.Tool.MakeDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class PswFragment extends Fragment implements Round_Restaurant {

    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.psw)
    View psw;
    @BindView(R.id.showerweima)
    View erweima;
    @BindView(R.id.frag_user_make)
    TextView frag_user_make;
    @BindView(R.id.erweima)
    ImageView QR_code;
    @BindView(R.id.hexiaoma)
    TextView hexiaoma;
    @BindView(R.id.money)
    EditText money;
    @BindView(R.id.psd_edit)
    EditText psd_edit;
    @BindView(R.id.money_psw)
    TextView money_psw;

    private String sid;
    private SharedPreferencesPotting potting;
    private int position;
    private Dismiss dismiss;
    private Mutual_Restaurant mutual_restaurant;
    private String pid;

    @SuppressLint("ValidFragment")
    public PswFragment(int position, String sid, String pid) {
        this.position = position;
        this.sid = sid;
        this.pid = pid;
    }

    public PswFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_psw, container, false);
        ButterKnife.bind(this, view);
        dismiss = (Dismiss) getActivity();
        mutual_restaurant = new Mutual_Restaurant(this);
        makeDialog = new MakeDialog(getContext());
        makeDialog.setOnDialogListener(view1 -> getActivity().finish());
        makeDialog.setOnDismissListener(dialog -> getActivity().finish());
        if (position == 0) {
            psw.setVisibility(View.VISIBLE);
            erweima.setVisibility(View.GONE);
        } else {
            psw.setVisibility(View.GONE);
            erweima.setVisibility(View.VISIBLE);
        }
        frag_user_make.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sid", getSid())
                        .put("uid", getUid())
                        .put("money", money.getText().toString())
                        .put("token", potting.getItemString("token"))
                        .put("tokentype", 1);
                if (data != null) {
                    jsonObject.put("rebate", data.getRebate())
                            .put("pwd", psd_edit.getText().toString());
                    if (!money.getText().toString().equals("")) {
                        jsonObject.put("rebatemoney", zhe_money);
                    }
                }
                if (uid != null) {
                    jsonObject.put("clerk", uid);
                }
                mutual_restaurant.PlaceOrder(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        mutual_restaurant.GetOrder();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (otherBeen != null)
                    uid = String.valueOf(otherBeen.get(position).getUid());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double d = Double.valueOf(s.toString());
                    zhe_money = Double.parseDouble(df.format((d * data.getRebate()) / 10));
                    money_psw.setText(zhe_money + "元");
                } catch (NumberFormatException e) {
                    new NullPointerException("PswFragment折扣金额为零，已try");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        activity = (Round_Restaurant) getActivity();
        return view;
    }

    private double zhe_money;
    private DecimalFormat df = new DecimalFormat("0.00");
    private String uid;

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getApp_Title() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setInfo(RestaurantBean.DataBean dataBean) {

    }

    private ArrayList<UserRestBean.OtherBean> otherBeen;

    @Override
    public void setList(ArrayList<UserRestBean.OtherBean> otherBeen) {
        this.otherBeen = otherBeen;
        ArrayList<String> list = new ArrayList<>();
        for (UserRestBean.OtherBean otherBean : otherBeen) {
            list.add(otherBean.getName());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner, list);
        stringArrayAdapter.setDropDownViewResource(R.layout.item_spinner2);
        spinner.setAdapter(stringArrayAdapter);
    }

    private UserRestBean.DataBean data;

    @Override
    public void setData(UserRestBean.DataBean data) {
        this.data = data;
        ImageLoading.common(getActivity(), BaseUrl.BITMAP + data.getQrimg(), QR_code, R.drawable.default_icon_zheng);
        hexiaoma.setText(data.getUsecode());
    }

    @Override
    public void setOther(ArrayList<RestaurantBean.Other> other) {

    }

    private MakeDialog makeDialog;

    @Override
    public void ToastShow(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        if (result.equals("核销成功")) {
            dismiss.diss();
            makeDialog.setMoney(String.valueOf(zhe_money));
            makeDialog.setType("折扣");
            makeDialog.setContent(activity.getApp_Title(), activity.getName());
            makeDialog.setIdAll(Integer.valueOf(pid), Integer.valueOf(sid));
            makeDialog.show();
        }
    }

    private Round_Restaurant activity;

    @Override
    public void setUse(int i, String title) {
        pid = String.valueOf(i);
        activity.setUse(i, title);
    }
}
