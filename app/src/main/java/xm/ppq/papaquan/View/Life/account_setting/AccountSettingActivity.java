package xm.ppq.papaquan.View.Life.account_setting;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 账号设置 on 2017/4/14.
 */

public class AccountSettingActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    @Override
    protected int getLayout() {
        return R.layout.activity_account_setting;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("退出登录");
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }
}
