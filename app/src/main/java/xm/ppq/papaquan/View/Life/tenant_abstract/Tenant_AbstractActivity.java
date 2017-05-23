package xm.ppq.papaquan.View.Life.tenant_abstract;

import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 商户简介 on 2017/4/14.
 */

public class Tenant_AbstractActivity extends BaseActivity {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private String sid;

    @Override
    protected int getLayout() {
        return R.layout.activity_tenant_abstract;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("商户简介");
        sid = getData("sid");
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.GETINTRODUCTION, "{\"sid\":" + sid + "}", new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean.getCode().equals("0")) {
                    String content = JsonUtil.getString(baseBean.getData().toString(), "content");

                }
            }
        });
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }
}
