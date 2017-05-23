package xm.ppq.papaquan.View.Life.agio_manage;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.life.AgioBean;
import xm.ppq.papaquan.Presenter.life.agio.Mutual_Agio;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.redcardfive.ReCardFiveActivity;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;

/**
 * Created by 折扣管理 on 2017/4/14.
 */

public class AgioManageActivity extends BaseActivity implements Round_Agio {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private String sid;
    private SharedPreferencesPotting potting;
    private Mutual_Agio mutual_agio;

    @Override
    protected int getLayout() {
        return R.layout.activity_agio_manage;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("折扣管理");
        sid = getData("sid");
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_agio = new Mutual_Agio(this);
        mutual_agio.getInfo();
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

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

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.zhekou)
    TextView zhekou;
    @BindView(R.id.make_num)
    TextView make_num;
    @BindView(R.id.five_zhekou)
    TextView five_zhekou;
    @BindView(R.id.add_up_money)
    TextView add_up_money;
    @BindView(R.id.after_folding)
    TextView after_folding;
    @BindView(R.id.uuss)
    LinearLayout uuss;

    @Override
    public void setBean(AgioBean.DataBean dataBean) {
        if (dataBean != null) {
            uuss.setVisibility(View.VISIBLE);
            title.setText(dataBean.getTitle());
            zhekou.setText(Stringutil.ThreeString(dataBean.getDiscount(), "10"));
            make_num.setText("使用" + dataBean.getUsenum() + "人次");
            String zhekou = "";
            for (int i = 0; i < dataBean.getFiveday().size(); i++) {
                zhekou += dataBean.getFiveday().get(i) + "号";
            }
            five_zhekou.setText("五折日:每月" + zhekou);
            add_up_money.setText("累计消费:" + Stringutil.ThreeString(dataBean.getAllmoney(), "0"));
            after_folding.setText("折后金额:" + Stringutil.ThreeString(dataBean.getRebatemoney(), "0"));
            ssid = String.valueOf(dataBean.getId());
        } else {
            uuss.setVisibility(View.GONE);
        }
    }

    private String ssid;

    public void zhekou(View view) {
        if (ssid != null) Skip(RestaurantActivity.class, "sid", ssid);

    }
}