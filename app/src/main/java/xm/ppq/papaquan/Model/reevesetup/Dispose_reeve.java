package xm.ppq.papaquan.Model.reevesetup;

import android.os.Handler;

import org.json.JSONObject;

import rx.Subscriber;
import rx.Subscription;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by sunshaobei on 2017/3/8.
 */

public class Dispose_reeve implements Reeve_Model {

    private Handler handler;

    public Dispose_reeve(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void OkHttp_ReSetPsw(JSONObject jsonObject, String url) {
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
                        handler.obtainMessage(4001).sendToTarget();
                        break;
                    case "1":
                        handler.obtainMessage(4002).sendToTarget();
                        break;
                    case "2":
                        handler.obtainMessage(4003).sendToTarget();
                        break;
                    case "3":
                        handler.obtainMessage(4004).sendToTarget();
                        break;
                }
            }
        });
    }
}
