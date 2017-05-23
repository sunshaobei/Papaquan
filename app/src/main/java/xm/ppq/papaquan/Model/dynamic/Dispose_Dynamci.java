package xm.ppq.papaquan.Model.dynamic;

import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by sunshaobei on 2017/3/13.
 */

public class Dispose_Dynamci implements Dynamic_Model {

    private Handler handler;

    public Dispose_Dynamci(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void public_dynamciData(JSONObject jsonObject, String url) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                handler.obtainMessage(-2, "请求错误").sendToTarget();
            }

            @Override
            public void onNext(BaseBean s) {
                if (s != null) {
                    if (s.getCode().equals("0")) {
                        handler.obtainMessage(4001).sendToTarget();
                    } else {
                        handler.obtainMessage(-2).sendToTarget();
                    }
                } else {
                    handler.obtainMessage(-2, s.getInfo()).sendToTarget();
                }
            }
        });
    }

}