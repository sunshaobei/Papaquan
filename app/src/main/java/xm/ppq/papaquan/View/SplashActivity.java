package xm.ppq.papaquan.View;

import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.ShareBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.main.MainActivity;

/**
 * Created by EdgeDi on 16:46.
 */

public class SplashActivity extends BaseActivity {

    private SharedPreferencesPotting potting;

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected int getLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("city").put_value(potting.getItemString("citycode"));
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SAGER, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    ShareBean shareBean = (ShareBean) JsonUtil.fromJson(s, ShareBean.class);
                    ShareBean.DataBean data = shareBean.getData();
                    BaseUrl.URL = data.getShareurl();
                    BaseUrl.dynamic = data.getDynamic();
                    BaseUrl.live = data.getLive();
                    BaseUrl.shop = data.getShop();
                    BaseUrl.panic_buying = data.getPanic_buying();
                    BaseUrl.discount = data.getDiscount();
                    BaseUrl.coupon = data.getCoupon();
                    BaseUrl.red = data.getRed();
                    BaseUrl.integral = data.getIntegral();
                    BaseUrl.citycode = String.valueOf(data.getCitycode());
                    BaseUrl.publicname = data.getPublicname();
                    BaseUrl.Image_url = BaseUrl.BITMAP + data.getLogo();
                }
            }
        });
        imageView.postDelayed(() -> {
            Skip(MainActivity.class);
            finish();
        }, 2500);

    }
    @Override
    protected void setListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkPotting.getInstance(BaseUrl.LIFE_URL).OnCancel();
    }
}