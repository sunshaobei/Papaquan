package xm.ppq.papaquan.View.about;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.webview_protocol.WebView_ProtocolActivity;

/**
 * Created by 关于啪啪圈 on 2017/4/9.
 */

public class AboutActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.version_number)
    TextView version_number;

    @Override
    protected int getLayout() {
        return R.layout.actiivty_about;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("关于啪啪圈");
        version_number.setText("啪啪圈 " + getVersion());
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    public void read(View view) {
        Intent intent = new Intent(this, WebView_ProtocolActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }
}
