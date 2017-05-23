package xm.ppq.papaquan.Presenter.life.scare_manage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.Scare_ManageBean;
import xm.ppq.papaquan.Bean.life.Scare_Manage_2Bean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.scare_manage.Round_Scare_Manage;

/**
 * Created by EdgeDi on 19:18.
 */

public class Mutual_Scare_Manage implements Scare_MangePresenter {

    private Round_Scare_Manage round_scare_manage;

    public Mutual_Scare_Manage(Round_Scare_Manage round_scare_manage) {
        this.round_scare_manage = round_scare_manage;
    }

    private ArrayList<Scare_ManageBean.DataBean> dataBeen;

    @Override
    public void getInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", round_scare_manage.getSid())
                    .put("uid", round_scare_manage.getUid())
                    .put("token", round_scare_manage.getToken())
                    .put("tokentype", 1)
                    .put("page", round_scare_manage.getPage())
                    .put("length", 10);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPPANIC, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeen != null) {
                        round_scare_manage.setBean(dataBeen);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        Scare_ManageBean scare_manageBean = (Scare_ManageBean) JsonUtil.fromJson(s, Scare_ManageBean.class);
                        if (scare_manageBean.getCode() == 0) {
                            dataBeen = (ArrayList<Scare_ManageBean.DataBean>) scare_manageBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getInfo2() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("sid", "uid", "token", "tokentype", "page", "length")
                .put_value(round_scare_manage.getSid(), round_scare_manage.getUid(), round_scare_manage.getToken(), 1, round_scare_manage.getPage(), 10);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPCOUPON, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    Scare_Manage_2Bean scare_manage_2Bean = (Scare_Manage_2Bean) JsonUtil.fromJson(s, Scare_Manage_2Bean.class);
                    if (scare_manage_2Bean.getCode() == 0) {
                        round_scare_manage.setBean2((ArrayList<Scare_Manage_2Bean.DataBean>) scare_manage_2Bean.getData());
                    }
                }
            }
        });
    }
}
