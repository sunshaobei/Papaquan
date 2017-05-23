package xm.ppq.papaquan.Presenter.papanews;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.PapaTopicData;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;

/**
 * Created by Administrator on 2017/3/15.
 */

public class Mutual_PapaNews implements PapaNewsPresenter {

    private Round_papa_news papa_news;
    private List<ShowNewsBigBean.Data> datas = new ArrayList<>();

    public Mutual_PapaNews(Round_papa_news papa_news) {
        this.papa_news = papa_news;
    }

    @Override
    public void start(int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", papa_news.getCityCode());
            jsonObject.put("tid", papa_news.getTid());
            jsonObject.put("uid", papa_news.getUid());
            jsonObject.put("app",1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.INDEX, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    papa_news.showInfo("");
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        papa_news.showInfo(JsonUtil.getString(s, "info"));
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            ShowNewsBigBean bigBean = (ShowNewsBigBean) JsonUtil.fromJson(s, ShowNewsBigBean.class);
                            datas = bigBean.data;
                            if (type == 1) {
                                papa_news.setList(datas);
                            } else {
                                papa_news.setRefreshList(datas);
                            }
                            String other = JsonUtil.getString(s, "other");
                            other = other.replace("[", "{\"list\":[");
                            other = other.replace("]", "]}");
                            PapaTopicData data = (PapaTopicData) JsonUtil.fromJson(other, PapaTopicData.class);
                            if (data != null) {
                                List<PapaTopicData.TopicData> list = data.getList();
                                EventBus.getDefault().post(list);
                            }
                        } else {
                            papa_news.showInfo(JsonUtil.getString(s, "info"));
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getData(int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tid", papa_news.getTid());
            jsonObject.put("uid", papa_news.getUid());
            jsonObject.put("token", papa_news.getToken());
            jsonObject.put("tokentype", "1");
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.FTOPUCLIST, jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {}
                        @Override
                        public void onError(Throwable e) {
                            Log.e("Throwable", e.toString());
                        }

                        @Override
                        public void onNext(String s) {
                            if (s != null) {
                                ShowNewsBigBean bigBean = (ShowNewsBigBean) JsonUtil.fromJson(s, ShowNewsBigBean.class);
                                if (bigBean != null)
                                    switch (bigBean.code) {
                                        case "0":
                                            if (bigBean.data != null) {
                                                if (type == 1) {
                                                    papa_news.setList(bigBean.data);
                                                } else {
                                                    papa_news.setRefreshList(bigBean.data);
                                                }
                                            }
                                            break;
                                        case "-1":
                                            papa_news.showInfo("您尚未登录，无法使用此功能");
                                            break;
                                        case "-2":
                                            papa_news.showInfo("登录已失效，请重新登录");
                                            break;
                                        default:
                                            papa_news.showInfo("token失效");
                                            break;
                                    }
                            }
                        }
                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
