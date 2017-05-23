package xm.ppq.papaquan.Presenter.life.lifehome;

import android.app.Activity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.lifehome.SetData;

/**
 * Created by sunshaobei on 2017/4/25.
 */

public class PofLifeHome implements Presenter {

    private SetData setData;
    private final SharedPreferencesPotting my_login;

    public PofLifeHome(Activity activity, SetData setData) {
        this.setData = setData;
        my_login = new SharedPreferencesPotting(activity, "my_login");
    }

    @Override
    public void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", my_login.getItemString("citycode"));
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.LIFEHOME, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    String code = JsonUtil.getString(o, "code");
                    if (code != null && code.equals("0")) {
                        LifeHomeData lifeHomeData = (LifeHomeData) JsonUtil.fromJson(o, LifeHomeData.class);
                        if (lifeHomeData != null) {
                            setData.setData(lifeHomeData);
                            EventBus.getDefault().post(lifeHomeData.getOther());
                        }
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}