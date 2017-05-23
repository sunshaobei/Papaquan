package xm.ppq.papaquan.Presenter.retrieve;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import xm.ppq.papaquan.Model.retrieve.Dispose_retrieve;
import xm.ppq.papaquan.Tool.ConnectionUtils;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.retrieve.RetrieveActivity;
import xm.ppq.papaquan.View.retrieve.Round_Retrieve;

/**
 * Created by sunshaobei on 2017/3/7.
 */

public class Mutual_Retrieve implements RetrievePresenter {

    private Round_Retrieve round_retrieve;
    private Dispose_retrieve dispose_retrieve;

    public Mutual_Retrieve(Round_Retrieve round_retrieve) {
        this.round_retrieve = round_retrieve;
        dispose_retrieve = new Dispose_retrieve(handler);
    }

    @Override
    public void getCode() {
        String phone = round_retrieve.getPhone();

        if (phone.length() == 11) {
            if (ConnectionUtils.isConnected((RetrieveActivity) round_retrieve)) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tel", phone);
                    jsonObject.put("type", "psw");
                    dispose_retrieve.Request_data_getcode(jsonObject, BaseUrl.SENDCODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                round_retrieve.Error("网络错误");
            }
        } else if (phone == null || phone.equals("")) {
            round_retrieve.Error("手机号码不能为空");
        } else {
            round_retrieve.Error("手机号码格式错误");
        }
    }

    @Override
    public void setCode() {
        String phone = round_retrieve.getPhone();
        String code = round_retrieve.getCode();
        if (phone.length() == 11) {
            if (ConnectionUtils.isConnected((RetrieveActivity) round_retrieve)) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tel", phone);
                    jsonObject.put("code", code);
                    dispose_retrieve.Request_data_setcode(jsonObject, BaseUrl.VALCODE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                round_retrieve.Error("网络错误");
            }

        } else if (phone != null && phone.equals("")) {
            round_retrieve.Error("手机号码不能为空");
        } else {
            round_retrieve.Error("手机号码格式错误");
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    round_retrieve.getCodeSuccess("发送成功");
                    break;
                case 4002:
                    round_retrieve.Error("发送失败");
                    break;
                case 4003:
                    round_retrieve.Error("请求失败");
                    break;
                case 4004:
                    round_retrieve.setCodeSuccess("验证成功", (String) msg.obj);
                    break;
                case 4005:
                    round_retrieve.Error("验证码错误");
                    break;
                case 4006:
                    round_retrieve.Error("操作频繁请稍后再试");
                    break;
            }
        }
    };

}
