package xm.ppq.papaquan.Presenter.login;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONObject;

import xm.ppq.papaquan.Bean.LoginBean;
import xm.ppq.papaquan.Model.login.Dispose_Login;
import xm.ppq.papaquan.Tool.StringJudge;
import xm.ppq.papaquan.View.login.Round_Login;

/**
 * Created by Administrator on 2017/3/7.
 */

public class Mutual_Login implements LoginPresenter {

    private Round_Login round_login;
    private Dispose_Login dispose_login;

    public Mutual_Login(Round_Login round_login) {
        this.round_login = round_login;
        dispose_login = new Dispose_Login(handler);
    }

    @Override
    public void startLogin() {
        String phone = round_login.getPhone();
        String password = round_login.getPassWord();
        if (StringJudge.ISnull(phone)) {
            if (StringJudge.ISnull(password)) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tel", phone);
                    jsonObject.put("psw", password);
                    jsonObject.put("type", round_login.getType());
                    dispose_login.startAsk(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                round_login.OnSuccess("密码不能为空");
            }
        } else {
            round_login.OnSuccess("手机号码不能为空");
        }
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = null;
            switch (msg.what) {
                case 4001:
                    result = "登录成功";
                    round_login.setLoginBean((LoginBean.DataBean) msg.obj);
                    break;
                case 4002:
                    result = "密码错误或账号不存在";
                    break;
                case 4003:
                    result = "密码错误或账号不存在";
                    break;
            }
            round_login.OnSuccess(result);
        }
    };
}
