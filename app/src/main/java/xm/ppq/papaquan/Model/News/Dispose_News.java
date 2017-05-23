package xm.ppq.papaquan.Model.News;

import android.content.Context;
import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.NewsBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.main.frame.Round_news;

import static android.R.attr.data;

/**
 * Created by sunshaobei on 2017/3/15.
 */

public class Dispose_News implements News_Model {
    private Round_news round_news;
    private SharedPreferencesPotting potting;

    public Dispose_News(Round_news round_news, Context context) {
        this.round_news = round_news;
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    public void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("token", potting.getItemString("token"));
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.MESSAGE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {

                }
                @Override
                public void onNext(String s) {
                    NewsBean newsBean = (NewsBean) JsonUtil.fromJson(s, NewsBean.class);
                    if (newsBean.getData() != null) {
                        NewsBean.DataBean data = newsBean.getData();
                        int AtCount = data.getActcount();
                        int messageCount = data.getMessageCount();
                        int rewardCount = data.getRewardCount();
                        if (AtCount > 99) {
                            AtCount = 99;
                        }
                        if (messageCount > 99) {
                            messageCount = 99;
                        }
                        if (rewardCount > 99) {
                            rewardCount = 99;
                        }
                        round_news.setData(AtCount, messageCount, rewardCount);
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
