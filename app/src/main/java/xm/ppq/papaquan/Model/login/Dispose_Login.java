package xm.ppq.papaquan.Model.login;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.LoginBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

import static android.R.attr.data;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Dispose_Login implements LoginModel {

    private OkPotting okPotting;
    private Handler handler;

    public Dispose_Login(Handler handler) {
        this.handler = handler;
        okPotting = OkPotting.getInstance(BaseUrl.PAPA_URL);
    }

    @Override
    public void startAsk(JSONObject jsonObject) {
        okPotting.AskOne(BaseUrl.LOGIN, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handler.obtainMessage(4003).sendToTarget();
            }

            @Override
            public void onNext(String s) {
                LoginBean loginbean = (LoginBean) JsonUtil.fromJson(s, LoginBean.class);
                LoginBean.DataBean data = loginbean.getData();
                switch (loginbean.getCode()) {
                    case 0:
                        handler.obtainMessage(4001, data).sendToTarget();
                        break;
                    case 1:
                        handler.obtainMessage(4002).sendToTarget();
                        break;
                }
            }

        });
    }
}
