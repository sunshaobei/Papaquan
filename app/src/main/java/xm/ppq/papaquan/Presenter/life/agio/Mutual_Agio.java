package xm.ppq.papaquan.Presenter.life.agio;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.AgioBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.agio_manage.Round_Agio;

/**
 * Created by EdgeDi on 19:57.
 */

public class Mutual_Agio implements AgioPresenter {

    private Round_Agio round_agio;

    public Mutual_Agio(Round_Agio round_agio) {
        this.round_agio = round_agio;
    }

    @Override
    public void getInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", round_agio.getSid())
                    .put("uid", round_agio.getUid())
                    .put("token", round_agio.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPREBARE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        AgioBean agioBean = (AgioBean) JsonUtil.fromJson(s, AgioBean.class);
                        if (agioBean.getCode() == 0) {
                            round_agio.setBean(agioBean.getData());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
