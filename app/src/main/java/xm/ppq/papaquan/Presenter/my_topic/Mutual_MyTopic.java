package xm.ppq.papaquan.Presenter.my_topic;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.MyTopicBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.my_topic.Round_MyTopic;

/**
 * Created by Administrator on 2017/3/24.
 */

public class Mutual_MyTopic implements MyTopicPresenter {

    private Round_MyTopic round_myTopic;
    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    public Mutual_MyTopic(Round_MyTopic round_myTopic) {
        this.round_myTopic = round_myTopic;
    }

    @Override
    public void start(int page) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", round_myTopic.getUid())
                    .put("page", page)
                    .put("token", round_myTopic.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.DYNAMIC, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (datas != null) {
                        round_myTopic.setList(datas);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            MyTopicBean myTopicBean = (MyTopicBean) JsonUtil.fromJson(s, MyTopicBean.class);
                            datas.clear();
                            datas.addAll(myTopicBean.data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
