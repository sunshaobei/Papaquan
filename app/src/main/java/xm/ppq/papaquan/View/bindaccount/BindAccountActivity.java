package xm.ppq.papaquan.View.bindaccount;

import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by Administrator on 2017/2/20.
 */

public class BindAccountActivity extends BaseActivity {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.bar)
    LinearLayout statusBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_bind_account;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        center_text.setText("绑定账号");
    }

    @Override
    protected void setListener() {

    }
}
