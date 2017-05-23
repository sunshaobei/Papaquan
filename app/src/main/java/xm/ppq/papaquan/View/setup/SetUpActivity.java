package xm.ppq.papaquan.View.setup;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DataCleanManager;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.about.AboutActivity;
import xm.ppq.papaquan.View.conceal.ConcealActivity;
import xm.ppq.papaquan.View.news_warn.NewsWarnActivity;
import xm.ppq.papaquan.View.revise_password.RevisePassWordActivity;

import static xm.ppq.papaquan.Tool.DataCleanManager.getFolderSize;
import static xm.ppq.papaquan.Tool.DataCleanManager.getFormatSize;

/**
 * Created by Administrator on 2017/3/9.
 */

public class SetUpActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.center_result)
    TextView center_text;
    @BindView(R.id.cache_text)
    TextView cache_text;
    @BindView(R.id.clear_lin)
    LinearLayout clear_lin;
    @BindView(R.id.log_off)
    TextView log_off;
    @BindView(R.id.finish_result)
    TextView finish;
    @BindView(R.id.bar)
    LinearLayout statusbar;
    @BindView(R.id.make_password)
    LinearLayout make_password;
    @BindView(R.id.news_warn_lin)
    LinearLayout news_warn_lin;
    @BindView(R.id.about_lin)
    LinearLayout about_lin;
    @BindView(R.id.conceal_lin)
    LinearLayout conceal_lin;

    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusbar);
        finish.setText("");
        pushService = PushServiceFactory.getCloudPushService();
        potting = new SharedPreferencesPotting(this, "my_login");
        center_text.setText("设置");
        cache_text.setText(AcquireSize());
    }

    private CloudPushService pushService;

    @Override
    protected void setListener() {
        finish.setOnClickListener(this);
        clear_lin.setOnClickListener(this);
        log_off.setOnClickListener(this);
        make_password.setOnClickListener(v -> {
            if (potting.isLogin()) Skip(RevisePassWordActivity.class);
        });
        news_warn_lin.setOnClickListener(v -> {
            if (potting.isLogin()) Skip(NewsWarnActivity.class);
        });
        about_lin.setOnClickListener(v -> Skip(AboutActivity.class));
        conceal_lin.setOnClickListener(v -> Skip(ConcealActivity.class));
    }

    private String AcquireSize() {
        try {
            return getFormatSize(getFolderSize(new File(this.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0Byte";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finish_result:
                finish();
                break;
            case R.id.clear_lin:
                String ceche = String.valueOf(getCacheDir());
                DataCleanManager.deleteFolderFile(ceche, true);
                ToastShow("已清除缓存!");
                cache_text.setText(AcquireSize());
                break;
            case R.id.log_off:
                String citycode = potting.getItemString("citycode");
                potting.getClear();
                potting.setItemString("citycode", citycode)
                        .build();
                pushService.unbindAccount(new CommonCallback() {
                    @Override
                    public void onSuccess(String s) {
                        Log.e("TAG", "解绑成功");
                    }

                    @Override
                    public void onFailed(String s, String s1) {
                        Log.e("TAG", "解绑失败" + s + ":" + s1);

                    }
                });
                ToastShow("已退出登录");
                IMUtils.loginout();
                IMUtils.setUserId("0");
                IMUtils.login();
                finish();
                break;
        }
    }
}
