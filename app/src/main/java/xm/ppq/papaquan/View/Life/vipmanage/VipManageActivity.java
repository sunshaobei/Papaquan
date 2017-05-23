package xm.ppq.papaquan.View.Life.vipmanage;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.VipManageAdapter;
import xm.ppq.papaquan.Bean.life.ViewManageBean;
import xm.ppq.papaquan.Presenter.viewmanage.Mutual_ViewManage;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by vip管理 on 2017/4/13.
 */

public class VipManageActivity extends BaseActivity implements Round_ViewManage {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.vip_manage_list)
    ListView vip_manage_list;
    @BindView(R.id.vip_number)
    TextView vip_number;
    @BindView(R.id.vip_money_two)
    TextView vip_money_two;

    private int page = 0;
    private boolean isMore = false;

    private VipManageAdapter adapter;

    private String sid;
    private SharedPreferencesPotting potting;
    private Mutual_ViewManage mutual_viewManage;

    @Override
    protected int getLayout() {
        return R.layout.activity_vip_manage;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("会员管理");
        sid = getData("sid");
        mutual_viewManage = new Mutual_ViewManage(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        dataBeen = new ArrayList<>();
        mutual_viewManage.getViewInfo();
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        vip_manage_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    if (isMore == false) {
                        isMore = true;
                        mutual_viewManage.getViewInfo();
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
    public int getPage() {
        return page;
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
    public void setOther(ViewManageBean.OtherBean other) {
        vip_number.setText("累计会员：" + other.getCount() + "人");
        vip_number.setText("消费2次以上占比：" + other.getPercent() + "%");
    }

    private ArrayList<ViewManageBean.DataBean> dataBeen;

    @Override
    public void setBean(ArrayList<ViewManageBean.DataBean> dataBeen) {
        if (dataBeen.size() > 0) {
            this.dataBeen.addAll(dataBeen);
            if (adapter == null) {
                adapter = new VipManageAdapter(this, this.dataBeen, R.layout.view_manage_item);
                vip_manage_list.setAdapter(adapter);
            } else {
                adapter.setList(this.dataBeen);
                adapter.notifyDataSetChanged();
            }
            isMore = false;
            page++;
        }
    }
}
