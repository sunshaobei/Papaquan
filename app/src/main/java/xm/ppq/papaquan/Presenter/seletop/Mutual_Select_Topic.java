package xm.ppq.papaquan.Presenter.seletop;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.TopicBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.seletop.Select_Topic_Total;

/**
 * Created by Administrator on 2017/2/21.
 */

public class Mutual_Select_Topic implements SelectTopicPresenter {

    private Select_Topic_Total select_topic_total;
    private ArrayList<TopicBean.Data> datas = new ArrayList<>();

    public Mutual_Select_Topic(Select_Topic_Total select_topic_total) {
        this.select_topic_total = select_topic_total;
    }

    @Override
    public void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page", 0);
            jsonObject.put("token", select_topic_total.getToken());
            jsonObject.put("tokentype", 1);
            jsonObject.put("uid", select_topic_total.getUid());
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.SUBLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (datas.size() > 0) {
                        select_topic_total.setTopicList(datas);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            TopicBean topicBean = (TopicBean) JsonUtil.fromJson(s, TopicBean.class);
                            datas.clear();
                            datas.addAll(topicBean.data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}