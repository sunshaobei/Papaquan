package xm.ppq.papaquan.Model.register;

import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import javax.security.auth.login.LoginException;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Dispose_register implements Register_Model {

    private Handler handler;

    public Dispose_register(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void Request_data(JSONObject jsonObject, String url) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(BaseBean baseBean) {
                switch (baseBean.getCode()) {
                    case "0":
                        if (url.equals(BaseUrl.REGISTER))handler.obtainMessage(4100).sendToTarget();
                        else handler.obtainMessage(4001).sendToTarget();
                        break;
                    case "1":
                        handler.obtainMessage(4003,baseBean.getInfo()).sendToTarget();
                        break;
                    case "2":
                        handler.obtainMessage(4003,baseBean.getInfo()).sendToTarget();
                        break;
                    case "3":
                        handler.obtainMessage(4003,baseBean.getInfo()).sendToTarget();
                        break;
                    case "4":
                        handler.obtainMessage(4003,baseBean.getInfo()).sendToTarget();
                        break;
                }
            }

        });
    }

}
