package xm.ppq.papaquan.View.Life.enter;

import android.view.View;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.merchant.MerchantActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;

/**
 * Created by EdgeDi on 10:09.
 */

public class EnterActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_enter;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    public void Skip(View view) {
        Skip(TenantActivity.class);
    }
}
