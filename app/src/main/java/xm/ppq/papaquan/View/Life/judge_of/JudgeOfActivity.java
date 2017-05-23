package xm.ppq.papaquan.View.Life.judge_of;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import org.feezu.liuli.timeselector.TimeSelector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.JudgeOfAdapter;
import xm.ppq.papaquan.Bean.life.JudgeOfBean;
import xm.ppq.papaquan.Presenter.life.judge_of.Mutual_JudgeOf;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 评价管理 on 2017/4/15.
 */

public class JudgeOfActivity extends BaseActivity implements Round_JudgeOf {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.judge_of_list)
    ListView judge_of_list;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.time)
    TextView time;

    private JudgeOfAdapter adapter;
    private TimeSelector start_select;
    private TimeSelector end_select;
    private String start = "0";
    private String end = "0";
    private SharedPreferencesPotting potting;
    private String sid;
    private int page = 0;
    private boolean isMore = false;
    private Mutual_JudgeOf mutual_judgeof;

    @Override
    protected int getLayout() {
        return R.layout.activity_judge_of;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("评价管理");
        sid = getData("sid");
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_judgeof = new Mutual_JudgeOf(this);
        start_select = new TimeSelector(this, time -> {
            start = time;
            if (end_select != null) {
                start_select.dismiss();
                end_select.show();
            }
        }, "2001-01-01 00:00", "2080-12-01 00:00");
        start_select.setMode(TimeSelector.MODE.YMD);
        start_select.setTitle("请选择开始时间");
        end_select = new TimeSelector(this, time1 -> {
            end = time1;
            page = 0;
            dataBeen.clear();
            mutual_judgeof.DisposeString();
        }, "2001-01-01 00:00", "2080-12-01 00:00");
        end_select.setMode(TimeSelector.MODE.YMD);
        end_select.setTitle("请选择结束时间");
    }

    private int type = 0;

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        type = 0;
                        break;
                    case 1:
                        type = 1;
                        break;
                    case 2:
                        type = 2;
                        break;
                    case 3:
                        type = 3;
                        break;
                }
                page = 0;
                dataBeen.clear();
                mutual_judgeof.getInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        judge_of_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    if (isMore == false) {
                        isMore = true;
                        mutual_judgeof.getInfo();
                    }
                }
            }
        });
    }

    public void time_select(View view) {
        start_select.show();
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
    public int getPage() {
        return page;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public String getStart() {
        return start;
    }

    @Override
    public String getEnd() {
        return end;
    }

    @Override
    public void setTime(String time) {
        this.time.setText(time);
    }

    private ArrayList<JudgeOfBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    public void setBean(ArrayList<JudgeOfBean.DataBean> dataBeen) {
        if (dataBeen.size() > 0) {
            page++;
            isMore = false;
        }
        this.dataBeen.addAll(dataBeen);
        if (adapter == null) {
            adapter = new JudgeOfAdapter(this, this.dataBeen, R.layout.judge_of_item);
            adapter.setSid(sid);
            judge_of_list.setAdapter(adapter);
        } else {
            adapter.setList(this.dataBeen);
            adapter.notifyDataSetChanged();
        }
    }
}
