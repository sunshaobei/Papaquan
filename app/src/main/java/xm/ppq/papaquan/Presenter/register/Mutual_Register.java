package xm.ppq.papaquan.Presenter.register;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import xm.ppq.papaquan.Model.register.Dispose_register;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.register.Round_Register;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Mutual_Register implements RegisterPresenter {

    private Round_Register round_register;
    private Dispose_register dispose_register;

    public Mutual_Register(Round_Register round_register) {
        this.round_register = round_register;
        dispose_register = new Dispose_register(handler);
    }

    /**
     * 获取验证码
     */
    @Override
    public void SendCode(View Button) {
        String phone = round_register.getPhone();
        if (phone != null && !phone.equals("")) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tel", phone);
                jsonObject.put("type", "reg");
                dispose_register.Request_data(jsonObject, BaseUrl.SENDCODE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            round_register.ToastError("手机号码不能为空");
        }
    }

    /**
     * 注册
     */
    @Override
    public void Register() {
        String phone = round_register.getPhone();
        String password = round_register.getPassword();
        String code = round_register.getCode();
        if (phone != null && !phone.equals("")) {
            if (password != null && !password.equals("")) {
                if (code != null && !code.equals("")) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("tel", phone);
                        jsonObject.put("psw", password);
                        jsonObject.put("code", code);
                        jsonObject.put("citycode", round_register.getCityCode());
                        dispose_register.Request_data(jsonObject, BaseUrl.REGISTER);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    round_register.ToastError("验证码不能为空");
                }
            } else {
                round_register.ToastError("密码不能为空");
            }
        } else {
            round_register.ToastError("手机号码不能为空");
        }
    }


    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    round_register.setSuccess("验证码");
                    break;
                case 4100:
                    round_register.setSuccess("");
                    break;
                case 4003:
                    round_register.ToastError("账号已被注册!");
                    break;
            }
        }
    };

}
