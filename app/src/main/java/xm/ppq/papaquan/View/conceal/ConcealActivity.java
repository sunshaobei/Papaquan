package xm.ppq.papaquan.View.conceal;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 隐私设置 on 2017/4/9.
 */

public class ConcealActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    @Override
    protected int getLayout() {
        return R.layout.activity_conceal;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("隐私设置");
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

}
