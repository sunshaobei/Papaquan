package xm.ppq.papaquan.Model.retrieve;

import android.os.Handler;

import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by sunshaobei on 2017/3/7.
 */

public class Dispose_retrieve implements Retrieve_Model {


    private Handler handler;

    public Dispose_retrieve(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void Request_data_getcode(JSONObject jsonObject, String url) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean s) {
                switch (s.getCode()) {
                    case "0":
                        handler.obtainMessage(4001).sendToTarget();
                        break;
                    case "1":
                        handler.obtainMessage(4002).sendToTarget();
                        break;
                    case "3":
                        handler.obtainMessage(4006).sendToTarget();
                        break;
                }
            }
        });
    }

    @Override
    public void Request_data_setcode(JSONObject jsonObject, String url) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    switch (JsonUtil.getString(s, "code")) {
                        case "0":
                            handler.obtainMessage(4004, JsonUtil.getString(s, "data")).sendToTarget();
                            break;
                        case "1":
                            handler.obtainMessage(4005).sendToTarget();
                            break;
                        case "3":
                            handler.obtainMessage(4006).sendToTarget();
                            break;
                    }
                }
            }
        });


    }
}
