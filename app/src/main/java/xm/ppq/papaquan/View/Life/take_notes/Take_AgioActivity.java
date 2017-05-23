package xm.ppq.papaquan.View.Life.take_notes;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.Take_otesAdapter;
import xm.ppq.papaquan.Bean.life.TakeAllBean;
import xm.ppq.papaquan.Bean.life.VerAgioBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 16:46.
 */

public class Take_AgioActivity extends BaseActivity implements OnItemSelectedListener {

    @BindView(R.id.shop_num_spinner)
    Spinner shop_num_spinner;
    @BindView(R.id.discount_num_spinner)
    Spinner discount_num_spinner;
    @BindView(R.id.agio_list)
    ListView agio_list;

    private SharedPreferencesPotting potting;
    private String sid;
    private ArrayList<VerAgioBean.DataBean.AdminBean> adminBeen;
    private ArrayList<VerAgioBean.DataBean.RebateBean> rebateBeen;

    @Override
    protected int getLayout() {
        return R.layout.activity_take_agio;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        sid = getData("sid");
        finish_result.setText("");
        center_result.setText("折扣核销记录");
        potting = new SharedPreferencesPotting(this, "my_login");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "uid", "token", "tokentype")
                .put_value(sid, potting.getItemInt("uid"), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETSHOPCLERK, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (adminBeen != null) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < adminBeen.size(); i++) {
                        list.add(adminBeen.get(i).getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    shop_num_spinner.setAdapter(adapter);
                }
                if (rebateBeen != null) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < rebateBeen.size(); i++) {
                        list.add(rebateBeen.get(i).getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    discount_num_spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    VerAgioBean verAgioBean = (VerAgioBean) JsonUtil.fromJson(s, VerAgioBean.class);
                    if (verAgioBean.getCode() == 0) {
                        adminBeen = (ArrayList<VerAgioBean.DataBean.AdminBean>) verAgioBean.getData().getAdmin();
                        rebateBeen = (ArrayList<VerAgioBean.DataBean.RebateBean>) verAgioBean.getData().getRebate();
                    }
                }
            }
        });
        start = new TimeSelector(this, time -> {
            start_time = time;
            end.show();
        }, "2005-01-01 00:00", "2080-12-30 00:00");
        start.setMode(TimeSelector.MODE.YMD);
        start.setTitle("请选择开始时间");
        end = new TimeSelector(this, time -> {
            end_time = time;
            DisposeString();
            getInfo();
        }, "2005-01-01 00:00", "2080-12-30 00:00");
        end.setMode(TimeSelector.MODE.YMD);
        end.setTitle("请选择结束时间");
    }

    @Override
    protected void setListener() {
        shop_num_spinner.setOnItemSelectedListener(this);
        discount_num_spinner.setOnItemSelectedListener(this);
        finish_result.setOnClickListener(v -> finish());
    }

    private String start_time = "0";
    private String end_time = "0";
    private TimeSelector start;
    private TimeSelector end;
    private String clerk = "0";
    private String rebate = "0";

    public void time_select(View view) {
        start.show();
    }

    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private int page = 0;

    public void DisposeString() {
        String start = start_time;
        String end = end_time;
        String s_year = start.substring(0, 4);
        String s_moon = start.substring(5, 7);
        String s_day = start.substring(8, 10);
        String e_year = end.substring(0, 4);
        String e_moon = end.substring(5, 7);
        String e_day = end.substring(8, 10);
        time.setText(s_moon + "-" + s_day + "至" + e_moon + "-" + e_day);
        start_time = s_year + "-" + s_moon;
        end_time = e_year + "-" + e_moon;
    }

    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.z_last)
    TextView z_last;

    private ArrayList<TakeAllBean.DataBean> dataBeen;
    private Take_otesAdapter adapter;

    public void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "sid", "token", "tokentype", "page", "length", "starttime", "endtime", "clerk", "rebate")
                .put_value(potting.getItemInt("uid"), sid, potting.getItemString("token"), 1,
                        page, 10, start_time, end_time, clerk, rebate);
        Log.e("eee", jsonTool.getJson().toString());
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.DISCOUNTTAKE, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (dataBeen != null) {
                    if (adapter == null) {
                        adapter = new Take_otesAdapter(getActivity(), dataBeen, R.layout.record_list_item);
                        agio_list.setAdapter(adapter);
                    } else {
                        adapter.setList(dataBeen);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    TakeAllBean takeAllBean = (TakeAllBean) JsonUtil.fromJson(s, TakeAllBean.class);
                    if (takeAllBean.getCode() == 0) {
                        money.setText("消费：" + takeAllBean.getOther().getMoney() + "元");
                        z_last.setText("折扣：" + takeAllBean.getOther().getRebatemoney() + "元");
                        dataBeen = (ArrayList<TakeAllBean.DataBean>) takeAllBean.getData();
                    }
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.shop_num_spinner) {
            clerk = String.valueOf(adminBeen.get(position).getId());
        } else {
            rebate = String.valueOf(rebateBeen.get(position).getValue());
        }
        getInfo();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}