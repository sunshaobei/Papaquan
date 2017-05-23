package xm.ppq.papaquan.Presenter.search;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;

/**
 * Created by Administrator on 2017/4/8.
 */

public class Mutual_Search implements SearchPresenter {

    private Round_papa_news round_papa_news;
    public Mutual_Search(Round_papa_news round_papa_news) {
        this.round_papa_news = round_papa_news;
    }

    @Override
    public void SearchNews(String search, int page, int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("search", search)
                    .put("page", page)
                    .put("length", 10)
                    .put("citycode", round_papa_news.getCityCode())
                    .put("uid", round_papa_news.getUid());
            OkPotting.getInstance(BaseUrl.PAPA_URL)
                    .AskOne(BaseUrl.HOMESEARCH, jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            ShowNewsBigBean bigBean = (ShowNewsBigBean) JsonUtil.fromJson(s, ShowNewsBigBean.class);
                            if (bigBean != null) {
                                switch (bigBean.code) {
                                    case "0":
                                        if (type == 1) {
                                            round_papa_news.setRefreshList(bigBean.data);
                                        } else {
                                            round_papa_news.setList(bigBean.data);
                                        }
                                        break;
                                }
                            }
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
