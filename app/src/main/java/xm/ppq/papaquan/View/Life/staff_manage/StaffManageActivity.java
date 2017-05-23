package xm.ppq.papaquan.View.Life.staff_manage;

import android.graphics.Color;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.StaffAdapter;
import xm.ppq.papaquan.Bean.StaffBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.life.Tool.Add_StaffDialog;

/**
 * Created by 员工管理 on 2017/4/15.
 */

public class StaffManageActivity extends BaseActivity implements MakeOver {

    @BindView(R.id.staff_list)
    ListView staff_list;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.right_title)
    TextView right_title;

    private StaffAdapter adapter;
    private String sid;
    private SharedPreferencesPotting potting;
    private ArrayList<StaffBean.DataBean> dataBeen = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_staff_manage;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //事件巴士
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        switch (s) {
            case "refresh":
                getData();
                break;
        }
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        sid = getData("sid");
        potting = new SharedPreferencesPotting(this, "my_login");
        finish_result.setText("");
        center_result.setText("员工管理");
        right_title.setText("+新增账号");
        adapter = new StaffAdapter(StaffManageActivity.this, dataBeen, R.layout.staff_item_list);
        staff_list.setAdapter(adapter);
        right_title.setTextColor(Color.parseColor("#ffffff"));
        getData();
    }

    private void getData() {
        try {
            JSONObject jsonObjec = new JSONObject();
            jsonObjec.put("sid", sid)
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SFAFF, jsonObjec.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    StaffBean staffBean = GsonUtils.parseJSON(s, StaffBean.class);
                    if (staffBean != null) {
                        if (staffBean.getCode() == 0) {
                            List<StaffBean.DataBean> data = staffBean.getData();
                            dataBeen.clear();
                            dataBeen.addAll(data);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Add_StaffDialog add;
    private Add_StaffDialog make;

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        right_title.setOnClickListener(v -> {
            add = new Add_StaffDialog(this, sid);
            add.show();
        });
    }

    @Override
    public void Make_Up(StaffBean.DataBean dataBean) {
        make = new Add_StaffDialog(this, "修改", dataBean);
        make.show();
    }
}