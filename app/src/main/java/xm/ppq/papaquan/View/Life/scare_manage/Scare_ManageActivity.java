package xm.ppq.papaquan.View.Life.scare_manage;

import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ScareManageAdapter;
import xm.ppq.papaquan.Bean.life.Scare_ManageBean;
import xm.ppq.papaquan.Bean.life.Scare_Manage_2Bean;
import xm.ppq.papaquan.Presenter.life.scare_manage.Mutual_Scare_Manage;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 抢购管理/精选管理 on 2017/4/14.
 */

public class Scare_ManageActivity extends BaseActivity implements Round_Scare_Manage {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.scare_list)
    ListView scare_list;

    private ScareManageAdapter adapter;
    private String sid;
    private SharedPreferencesPotting potting;
    private int page = 0;
    private boolean isMore = false;

    private Mutual_Scare_Manage mutual_scare_manage;

    @Override
    protected int getLayout() {
        return R.layout.activity_scare_manage;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        sid = getData("sid");
        potting = new SharedPreferencesPotting(this, "my_login");
        center_result.setText(getData("type"));
        mutual_scare_manage = new Mutual_Scare_Manage(this);
        if (getData("type").equals("抢购管理")) {
            mutual_scare_manage.getInfo();
        } else {
            mutual_scare_manage.getInfo2();
        }
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        scare_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    if (isMore == false) {
                        isMore = true;
                        mutual_scare_manage.getInfo();
                    }
                }
            }
        });
    }

    @Override
    public String getSid() {
        return sid;
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

    private ArrayList<Scare_ManageBean.DataBean> bean = new ArrayList<>();
    private ArrayList<Scare_Manage_2Bean.DataBean> dataBeen = new ArrayList<>();

    @Override
    public void setBean(ArrayList<Scare_ManageBean.DataBean> bean) {
        if (bean.size() > 0) {
            this.bean.addAll(bean);
            if (adapter == null) {
                adapter = new ScareManageAdapter<>(Scare_ManageActivity.this, this.bean, R.layout.scare_manage_item);
                scare_list.setAdapter(adapter);
            } else {
                adapter.setList(this.bean);
                adapter.notifyDataSetChanged();
            }
            page++;
            isMore = true;
        }
    }

    @Override
    public void setBean2(ArrayList<Scare_Manage_2Bean.DataBean> dataBeen) {
        if (dataBeen.size() > 0) {
            this.dataBeen.addAll(dataBeen);
            if (adapter == null) {
                adapter = new ScareManageAdapter<>(this, this.dataBeen, R.layout.scare_manage_item);
                adapter.setType(3);
                scare_list.setAdapter(adapter);
            } else {
                adapter.setList(this.dataBeen);
                adapter.notifyDataSetChanged();
            }
            page++;
            isMore = true;
        }
    }
}