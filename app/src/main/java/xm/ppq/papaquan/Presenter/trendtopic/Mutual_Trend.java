package xm.ppq.papaquan.Presenter.trendtopic;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.Discuss_ShowBean;
import xm.ppq.papaquan.Bean.GetComBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.trendtopicdetail.Round_Trend;

/**
 * Created by Administrator on 2017/3/22.
 */

public class Mutual_Trend implements TrendPresenter {

    private Round_Trend round_trend;
    private Activity activity;
    private Discuss_ShowBean.Data data;
    private List<GetComBean.Data> getdata = new ArrayList<>();

    public Mutual_Trend(Activity round_trend) {
        this.round_trend = (Round_Trend) round_trend;
        this.activity = round_trend;
    }

    @Override
    public void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", round_trend.getTid());
            jsonObject.put("uid", round_trend.getUid());
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.GETTOPIC, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (data != null) {
                        if (data.content == null) {
                            round_trend.setShare(data.topicUserdata.nickname, data.topicUserdata.headurl, data.uid == null ? 0 : Integer.valueOf(data.uid));
                        } else {
                            round_trend.setShare(data.content, data.topicUserdata.headurl, data.uid == null ? 0 : Integer.valueOf(data.uid));
                        }
                        Criticism_num(1, data.id, 0);
                        round_trend.IsShow(data.uid == null ? "0" : data.uid);
                        if (data.video != null && !data.video.equals("")) {
                            round_trend.setVideo(data.video, data.videopic);
                        }
                        if (data.adInfo != null) if (data.adInfo.size() > 0)
                            round_trend.setAdvert(data.adInfo.get(0).img);
                        round_trend.setLevel(data.topicUserdata.level);
                        if (data.focus.equals("0")) {
                            round_trend.setFollow("+关注");
                        } else if (data.focus.equals("1")) {
                            round_trend.setFollow("已关注");
                        }
                        if (data.isLike.equals("0")) {
                            round_trend.setLove(R.drawable.love_false, activity.getResources().getColor(R.color.register_colors));
                        } else if (data.isLike.equals("1")) {
                            round_trend.setLove(R.drawable.love_true, activity.getResources().getColor(R.color.trend_red));
                        }
                        round_trend.setComment_Reward_Like_Read_TopicUserdata_Createtime_Content(data.comment_num, data.reward_num, data.likes_num
                                , data.read_num, data.topicUserdata.nickname, data.createtime, data.content, data.topicUserdata.headurl);
                        if (data.picture != null) {
                            if (data.picture.size() > 0) {
                                round_trend.setPicList(data.picture);
                            }
                        }
                        if (data.likeUserdata != null) {
                            if (data.likeUserdata.size() > 0) {
                                String name = "";
                                for (int i = 0; i < data.likeUserdata.size(); i++) {
                                    name += data.likeUserdata.get(i).nickname + ",";
                                }
                                round_trend.setTrend(name.substring(0, name.length() - 1));
                            }
                        }
                        if (data.rewardUserdata != null) {
                            if (data.rewardUserdata.size() > 0) {
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < data.rewardUserdata.size(); i++) {
                                    list.add(data.rewardUserdata.get(i).headurl);
                                }
                                round_trend.setRewareList(list);
                                list.clear();
                            }
                        }
                        round_trend.setReawrenum(data.rewardCount);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        switch (JsonUtil.getString(s, "code")) {
                            case "0":
                                Discuss_ShowBean bean = (Discuss_ShowBean) JsonUtil.fromJson(s, Discuss_ShowBean.class);
                                data = bean.data;
                                break;
                            case "-1":
                                round_trend.setError();
                                break;
                            case "-2":

                                break;
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Criticism_num(int type, String tid, int pager) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tid", tid)
                    .put("page", pager)
                    .put("type", type)
                    .put("uid", round_trend.getUid());
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.GETCOM, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            GetComBean getComBean = (GetComBean) JsonUtil.fromJson(s, GetComBean.class);
                            round_trend.setList(getComBean.data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Follow_Finish(String url, JSONObject jsonObject, String result) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                switch (baseBean.getCode()) {
                    case "0":
                        round_trend.setFollow(result);
                        round_trend.setCri();
                        break;
                    case "-1":
                        round_trend.ShowToast("您尚未登录，无法关注");
                        break;
                    case "-2":
                        round_trend.ShowToast("您的账号登录已失效");
                        break;
                }
            }
        });
    }

    @Override
    public void Love(String url, JSONObject jsonObject) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                switch (baseBean.getCode()) {
                    case "1":
                        round_trend.setLove(R.drawable.love_true, activity.getResources().getColor(R.color.trend_red));
                        break;
                    case "0":
                        round_trend.ShowToast("已点赞");
                        break;
                    case "-1":
                        round_trend.ShowToast("您尚未登录，无法喜欢");
                        break;
                    case "-2":
                        round_trend.ShowToast("您的账号登录已失效");
                        break;
                }
            }
        });
    }

    @Override
    public String getNickname() {
        if (data != null) return data.topicUserdata.nickname;
        return "";
    }

    @Override
    public String getId() {
        if (data != null) return data.id;
        return "";
    }

    @Override
    public String getUid() {
        if (data != null) return data.uid;
        return "";
    }

    @Override
    public String getUrl() {
        if (data != null) return data.topicUserdata.headurl;
        return "";
    }
}
