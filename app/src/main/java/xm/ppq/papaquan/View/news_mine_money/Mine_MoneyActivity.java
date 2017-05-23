package xm.ppq.papaquan.View.news_mine_money;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.AiteMineAdapter;
import xm.ppq.papaquan.Adapter.MoneyMineAdapter;
import xm.ppq.papaquan.Bean.AiteBean;
import xm.ppq.papaquan.Bean.MoneyBean;
import xm.ppq.papaquan.Presenter.mine_money.Mutual_At;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by @我的，打赏 on 2017/3/2.
 */

public class Mine_MoneyActivity extends BaseActivity implements View.OnClickListener, Round_At {

    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.aite_mine_money_list)
    ListView aite_mine_money_list;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.finish_result)
    TextView finish;

    private String result;
    private AiteMineAdapter aiteMineAdapter;
    private MoneyMineAdapter adapter;
    private Mutual_At mutual_at;
    private ArrayList<AiteBean.Data> atdataList = new ArrayList<>();
    private ArrayList<MoneyBean.Data> money = new ArrayList<>();
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_aite;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_at = new Mutual_At(this);
        result = getData("mom");
        if (result.equals("@我的")) {
            center_result.setText(result);
            aite_mine_money_list.setOnItemClickListener(((parent, view, position, id) -> Skip(TrendTopicDetailActivity.class, "tid", money.get(position).id)));
            // @我的
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", potting.getItemInt("uid"))
                        .put("type", "1")
                        .put("token", potting.getItemString("token"))
                        .put("tokentype", 1)
                        .put("page", 0);
                mutual_at.getData(0, BaseUrl.COMMENTS_De, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            center_result.setText(result);
            aite_mine_money_list.setOnItemClickListener(((parent, view, position, id) -> Skip(TrendTopicDetailActivity.class, "tid", atdataList.get(position).tid)));
            // 打赏
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", potting.getItemInt("uid"))
                        .put("page", 0)
                        .put("token", potting.getItemString("token"))
                        .put("tokentype", 1);
                mutual_at.getData(1, BaseUrl.WARDMY, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setListener() {
        finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_result:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        atdataList.clear();
        money.clear();
    }

    //打赏我的
    @Override
    public void setAtData(List<AiteBean.Data> s) {
        atdataList.addAll(s);
        if (aiteMineAdapter == null) {
            aiteMineAdapter = new AiteMineAdapter(this, atdataList, R.layout.aite_money_item);
            aite_mine_money_list.setAdapter(aiteMineAdapter);
        } else {
            aiteMineAdapter.setList(atdataList);
            aiteMineAdapter.notifyDataSetChanged();
        }
    }

    //艾特我的
    @Override
    public void setMoney(List<MoneyBean.Data> datas) {
        money.addAll(datas);
        if (adapter == null) {
            adapter = new MoneyMineAdapter(this, money, R.layout.aite_mine_item);
            aite_mine_money_list.setAdapter(adapter);
        } else {
            adapter.setList(money);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setError(String s) {

    }
}
