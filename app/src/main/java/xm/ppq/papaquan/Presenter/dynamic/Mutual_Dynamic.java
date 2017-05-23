package xm.ppq.papaquan.Presenter.dynamic;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import xm.ppq.papaquan.Model.dynamic.Dispose_Dynamci;
import xm.ppq.papaquan.Tool.ConnectionUtils;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.published_dynamic.Round_Dynamic;

/**
 * Created by sunshaobei on 2017/3/13.
 */

public class Mutual_Dynamic implements DynamicPresenter {
    private Round_Dynamic round_dynamic;
    private Dispose_Dynamci dispose_dynamci;

    public Mutual_Dynamic(Round_Dynamic round_dynamic) {
        this.round_dynamic = round_dynamic;
        dispose_dynamci = new Dispose_Dynamci(handler);
    }

    @Override
    public void send() {
        int getuid = round_dynamic.getUid();
        String getconcent = round_dynamic.getContent();
        List<String> getpicture = round_dynamic.getPicture();
        String getcitycode = round_dynamic.getCityCode();
        String gettoken = round_dynamic.getToken();

        if (ConnectionUtils.isConnected((Activity) round_dynamic))
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", getuid);
                jsonObject.put("content", getconcent);
                jsonObject.put("token", gettoken);
                jsonObject.put("tokentype", 1);
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < getpicture.size(); i++) {
                    jsonArray.put(getpicture.get(i));
                }
                jsonObject.put("video", round_dynamic.getVideoPath());
                jsonObject.put("videopic", round_dynamic.getVideoPic());
                jsonObject.put("picture", jsonArray);
                jsonObject.put("videotype", 1);
                jsonObject.put("citycode", getcitycode);
                dispose_dynamci.public_dynamciData(jsonObject, BaseUrl.PUBLIC_DYNAMIC);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    round_dynamic.sendSuccess();
                    break;
                case -2:
                    round_dynamic.sendError((String) msg.obj);
                    break;
            }
        }
    };

}
