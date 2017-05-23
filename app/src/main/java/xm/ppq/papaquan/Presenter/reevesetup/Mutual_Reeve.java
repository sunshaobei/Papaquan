package xm.ppq.papaquan.Presenter.reevesetup;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import xm.ppq.papaquan.Model.reevesetup.Dispose_reeve;
import xm.ppq.papaquan.Model.reevesetup.Reeve_Model;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.reevesetup.Round_Reeve;

import static xm.ppq.papaquan.R2.drawable.password;

/**
 * Created by sunshaobei on 2017/3/8.
 */

public class Mutual_Reeve implements Reeve_presenter {


    private Round_Reeve round_reeve;
    private Dispose_reeve dispose_reeve;

    public Mutual_Reeve(Round_Reeve round_reeve) {
        this.round_reeve = round_reeve;
        dispose_reeve = new Dispose_reeve(handler);
    }

    @Override
    public void reSetPsw() {
        String psw1 = round_reeve.getPsw1();
        String psw2 = round_reeve.getPsw2();
        String phone = round_reeve.getPhone();
        if (psw1.equals(psw2)) {
            if (psw1.length() >= 6) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("tel", phone);
                    jsonObject.put("newpsw", psw1);
                    jsonObject.put("rand", round_reeve.getRandom());
                    dispose_reeve.OkHttp_ReSetPsw(jsonObject, BaseUrl.RESETPSW);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (psw1.equals("")) {
                round_reeve.reSetError("密码不能为空");
            } else {
                round_reeve.reSetError("密码需不小于六位");
            }
        } else {
            round_reeve.reSetError("两次密码输入不一致");
        }


    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    round_reeve.reSetSuccess("修改成功");
                    break;
                case 4002:
                    round_reeve.reSetError("修改失败");
                    break;
                case 4003:
                    round_reeve.reSetError("不能为空");
                    break;
                case 4004:
                    round_reeve.reSetError("新旧不能一致");
                    break;
            }
        }
    };

}
