package xm.ppq.papaquan.View.Life.balance;

import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.life.BalanceAdapter;
import xm.ppq.papaquan.Bean.BalanceBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 13:21.
 */

public class BalanceActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.balance_list)
    ListView balance_list;

    private SharedPreferencesPotting potting;
    private BalanceAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_balance;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("余额明细");
        potting = new SharedPreferencesPotting(this, "my_login");
        getInfo();
    }

    private int page = 0;
    private ArrayList<BalanceBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        balance_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        if (!isLoading) {
                            page++;
                            isLoading = true;
                            getInfo();
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private boolean isLoading = false;

    public void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "page", "length")
                .put_value(potting.getItemInt("uid"), page, 10);
        OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.COSTDETAIL, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (dataBeen.size() > 0) {
                    if (adapter == null) {
                        adapter = new BalanceAdapter(BalanceActivity.this, dataBeen, R.layout.detail_item);
                        balance_list.setAdapter(adapter);
                    } else {
                        adapter.setList(dataBeen);
                        adapter.notifyDataSetChanged();
                    }
                    isLoading = false;
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    BalanceBean balanceBean = (BalanceBean) JsonUtil.fromJson(s, BalanceBean.class);
                    if (balanceBean.getCode() == 0) {
                        dataBeen.addAll(balanceBean.getData());
                    }
                }
            }
        });
    }
}