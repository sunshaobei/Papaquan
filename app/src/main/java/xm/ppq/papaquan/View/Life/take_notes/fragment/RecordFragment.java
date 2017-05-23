package xm.ppq.papaquan.View.Life.take_notes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.feezu.liuli.timeselector.TimeSelector;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ScareFAdapter;
import xm.ppq.papaquan.Adapter.Take_otesAdapter;
import xm.ppq.papaquan.Adapter.life.RecordAdapter;
import xm.ppq.papaquan.Bean.life.RecordBean;
import xm.ppq.papaquan.Bean.life.RecordListBean;
import xm.ppq.papaquan.Presenter.life.record.Mutual_Record;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.LoadingOneFragment;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;

/**
 * Created by Administrator on 2017/4/12.
 */

public class RecordFragment extends LoadingOneFragment implements Round_Record, AdapterView.OnItemSelectedListener {

    @BindView(R.id.record_list_view)
    ListView record_list_view;
    @BindView(R.id.shop_spinner)
    Spinner shop_spinner;
    @BindView(R.id.discount_spinner)
    Spinner discount_spinner;
    @BindView(R.id.time_select)
    RelativeLayout time_select;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.money)
    TextView money;

    private View view;
    private int type;
    private String sid;
    private SharedPreferencesPotting potting;
    private Mutual_Record mutual_record;
    private RecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_record, container, false);
            ButterKnife.bind(this, view);
            potting = new SharedPreferencesPotting(getContext(), "my_login");
        }
        return view;
    }

    private TimeSelector start;
    private TimeSelector end;
    private String start_time = "0";
    private String end_time = "0";

    @Override
    public void loadData() {
        sid = getArguments().getString("sid");
        type = getArguments().getInt("type", 0);
        mutual_record = new Mutual_Record(this);
        mutual_record.getInfo();
        start = new TimeSelector(getContext(), time -> {
            start_time = time;
            end.show();
        }, "2005-01-01 00:00", "2080-12-30 00:00");
        start.setMode(TimeSelector.MODE.YMD);
        start.setTitle("请选择开始时间");
        end = new TimeSelector(getContext(), time -> {
            end_time = time;
            mutual_record.DisposeString();
            mutual_record.TakeInfo(clerk, product_id);
        }, "2005-01-01 00:00", "2080-12-30 00:00");
        end.setMode(TimeSelector.MODE.YMD);
        end.setTitle("请选择结束时间");
        mutual_record.TakeInfo(clerk, product_id);
    }

    private String clerk = "0";
    private String product_id = "0";

    @Override
    public void setListener() {
        discount_spinner.setOnItemSelectedListener(this);
        shop_spinner.setOnItemSelectedListener(this);
        time_select.setOnClickListener(v -> start.show());
    }

    @Override
    public void setStartTime(String time) {
        start_time = time;
    }

    @Override
    public void setEndTime(String time) {
        end_time = time;
    }

    @Override
    public void setTime(String time) {
        this.time.setText(time);
    }

    @Override
    public String getStartTime() {
        return start_time;
    }

    @Override
    public String getEndTime() {
        return end_time;
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void setMoney(String result) {
        money.setText(result);
    }

    private ArrayList<RecordBean.DataBean.AdminBean> adminBeen;
    private ArrayList<RecordBean.DataBean.ProductBean> productBeen;

    @Override
    public void setTopBean(RecordBean.DataBean dataBean) {
        if (dataBean.getAdmin() != null) {
            adminBeen = (ArrayList<RecordBean.DataBean.AdminBean>) dataBean.getAdmin();
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < dataBean.getAdmin().size(); i++) {
                list.add(dataBean.getAdmin().get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            shop_spinner.setAdapter(adapter);
        }
        if (dataBean.getProduct() != null) {
            productBeen = (ArrayList<RecordBean.DataBean.ProductBean>) dataBean.getProduct();
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < dataBean.getProduct().size(); i++) {
                list.add(dataBean.getProduct().get(i).getTitle());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            discount_spinner.setAdapter(adapter);
        }
    }

    @Override
    public void setListData(ArrayList<RecordListBean.DataBean> data) {
        if (adapter == null) {
            adapter = new RecordAdapter(getContext(), data, R.layout.record_frag_item);
            record_list_view.setAdapter(adapter);
        } else {
            adapter.setList(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.shop_spinner) {
            clerk = String.valueOf(adminBeen.get(position).getId());
        } else {
            product_id = String.valueOf(productBeen.get(position).getId());
        }
        mutual_record.TakeInfo(clerk, product_id);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
