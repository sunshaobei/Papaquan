package xm.ppq.papaquan.View.mine_integral;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.mybanner.Mybanner;
import myview.mybanner.OnItemClickListener;
import xm.ppq.papaquan.Adapter.IntegralAdapter;
import xm.ppq.papaquan.Bean.life.IntergalHeadBean;
import xm.ppq.papaquan.Bean.life.IntergalListBean;
import xm.ppq.papaquan.Presenter.mine_integral.Mutual_Integral;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.balance_detail.ExchangeHistoryActivity;
import xm.ppq.papaquan.View.owncoin.ExchangeDetailActivity;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by 积分中心 on 2017/3/21.
 */

public class Mine_IntegralActivity extends BaseActivity implements Round_Integral {

    @BindView(R.id.integral_list)
    ListView integral_list;
    @BindView(R.id.finish_result)
    TextView finish;

    private Mutual_Integral mutual_integral;
    private IntegralAdapter adapter;
    private SharedPreferencesPotting potting;
    private TextView jifenye;
    private Mybanner mybanner;
    private TextView duihuanjl;
    private ShareDialog shareDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_integral;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        finish.setText("");
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("积分", url, BaseUrl.Image_url);
        mutual_integral = new Mutual_Integral(this);
        mutual_integral.GetList();
        mutual_integral.getHeadData(this);
        initListviewHead();
    }

    private void initListviewHead() {
        View head = getLayoutInflater().inflate(R.layout.interal_item_head, null);
        mybanner = (Mybanner) head.findViewById(R.id.mybanner);
        jifenye = (TextView) head.findViewById(R.id.jifen_y_e);
        duihuanjl = (TextView) head.findViewById(R.id.duihuan_j_l);
        duihuanjl.setOnClickListener(v -> {
            startActivity(new Intent(this, ExchangeHistoryActivity.class));
        });
        integral_list.addHeaderView(head);
        adapter = new IntegralAdapter(this, been, R.layout.integral_item);
        integral_list.setAdapter(adapter);
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
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public void setList(IntergalListBean data) {
        been.clear();
        been.addAll(data.getData());
        if (potting.getItemInt("uid") == 0) adapter.setGold(-1);
        else adapter.setGold(Integer.parseInt(data.getOther().getGold()));
        adapter.notifyDataSetChanged();
    }

    private ArrayList<String> bannerList = new ArrayList<>();

    @Override
    public void setHeadData(IntergalHeadBean data) {
        jifenye.setText(Html.fromHtml("<font color=#000000><big>" + data.getOther().getGold() + "<big/></font><br/>" + "积分余额"));
        duihuanjl.setText(Html.fromHtml("<font color=#000000><big>" + data.getOther().getExchangeNum() + "<big/></font><br/>" + "兑换记录"));
        List<IntergalHeadBean.DataBean> data1 = data.getData();
        bannerList.clear();
        for (int i = 0; i < data1.size(); i++) {
            bannerList.add(BaseUrl.BITMAP + data1.get(i).getImg());
        }
        mybanner.setBanner(getSupportFragmentManager(), bannerList);
        mybanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                int link_type = data1.get(position).getLink_type();
                if (link_type == 0) {
                    LinkSkip.Link(getActivity(), data1.get(position).getLink_val(), data1.get(position).getSingleid());
                } else {
                    LinkSkip.Go2Chrome(getActivity(), data1.get(position).getLink());
                }
            }
        });
    }

    private ArrayList<IntergalListBean.DataBean> been = new ArrayList<>();

    @Override
    protected void setListener() {
        finish.setOnClickListener(v -> finish());
        integral_list.setOnItemClickListener((parent, view, position, id) -> {
            if (been.get(position - integral_list.getHeaderViewsCount()).getExchange() < been.get(position - integral_list.getHeaderViewsCount()).getNum()) {
                Intent intent = new Intent(this, ExchangeDetailActivity.class);
                intent.putExtra("gid", been.get(position - integral_list.getHeaderViewsCount()).getId());
                startActivityForResult(intent, 102);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            setResult(IntentCode.ResultCode.BACKTOLIFE);
            finish();
        }
    }

    public void go_home(View view) {
        finish();
    }

    private String url = "index/gold/index?city=" + BaseUrl.citycode;

    public void share(View view) {
        shareDialog.Show(view);
    }
}