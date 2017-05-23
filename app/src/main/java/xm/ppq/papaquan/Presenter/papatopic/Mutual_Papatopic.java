package xm.ppq.papaquan.Presenter.papatopic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.main.frame.Round_PapaTopic;

/**
 * Created by sunshaobei on 2017/3/14.
 */

public class Mutual_Papatopic implements PapatopicPresenter {

    private Round_PapaTopic round_papaTopic;

    public Mutual_Papatopic(Round_PapaTopic round_papaTopic) {
        this.round_papaTopic = round_papaTopic;
    }

    private List<PapaTopicBean.DataBean> data;

    @Override
    public void getData(int pager,int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page", pager);
            jsonObject.put("citycode", round_papaTopic.getCityCode())
                    .put("type",type);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.HOTSLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (data != null) {
                        round_papaTopic.setPapaTopicList(data);
                    }
                }
                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            PapaTopicBean papaTopicBean = (PapaTopicBean) JsonUtil.fromJson(s, PapaTopicBean.class);
                            data = papaTopicBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getData(int pager,int type,String s) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page", pager);
            jsonObject.put("citycode", round_papaTopic.getCityCode())
                    .put("search",s)
                    .put("type",type);
            if (type ==3)
            {
                jsonObject.put("uid",round_papaTopic.getUid())
                        .put("token",round_papaTopic.getToken())
                        .put("tokentype",1);
            }
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.HOTSLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (data != null) {
                        round_papaTopic.setPapaTopicList(data);
                    }
                }
                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            PapaTopicBean papaTopicBean = (PapaTopicBean) JsonUtil.fromJson(s, PapaTopicBean.class);
                            data = papaTopicBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
