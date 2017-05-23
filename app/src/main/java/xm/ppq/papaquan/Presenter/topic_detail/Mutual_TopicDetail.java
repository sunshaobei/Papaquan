package xm.ppq.papaquan.Presenter.topic_detail;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xm.ppq.papaquan.Bean.HotTalisBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BlurUtil;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.topic_deail.Round_Topicdetail;

/**
 * Created by Administrator on 2017/3/24.
 */

public class Mutual_TopicDetail implements TopicDetailPresenter {

    private Round_Topicdetail round_topicdetail;
    private HotTalisBean.Other other;

    public Mutual_TopicDetail(Activity activity) {
        round_topicdetail = (Round_Topicdetail) activity;
    }

    public Mutual_TopicDetail(Activity activity, Round_Topicdetail round_topicdetail) {
        this.round_topicdetail = round_topicdetail;
    }

    @Override
    public void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hotid", round_topicdetail.getHotid());
            jsonObject.put("page", 0);
            jsonObject.put("type", 1);
            jsonObject.put("uid", round_topicdetail.getUid());
            if (round_topicdetail.getHotid().startsWith("#")) {
                jsonObject.put("citycode", round_topicdetail.getCitycode());
            }
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.HOTDETAIL, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (other != null) {
                        round_topicdetail.setShare(other.title, other.headurl);
                        round_topicdetail.setHot_Num("热度：" + other.heat + "   参与：" + other.joinnum);
                        round_topicdetail.setHotTitle(other.title);
                        round_topicdetail.setName(other.nickname);
                        round_topicdetail.setIcon(other.headurl);
                        round_topicdetail.setBackground(BaseUrl.BITMAP + other.img);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            HotTalisBean hotTalisBean = (HotTalisBean) JsonUtil.fromJson(s, HotTalisBean.class);
                            other = hotTalisBean.other;
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<ShowNewsBigBean.Data> datas = new ArrayList<>();

    @Override
    public void setFragment(String hotid, String type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hotid", hotid)
                    .put("type", type)
                    .put("page", round_topicdetail.getPager())
                    .put("uid", round_topicdetail.getUid());
            if (hotid.startsWith("#")) {
                jsonObject.put("citycode", round_topicdetail.getCitycode());
            }
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.HOTDETAIL, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    round_topicdetail.setList(datas);
                    if (other != null) {
                        round_topicdetail.setHotTitle(other.title);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            HotTalisBean talisBean = (HotTalisBean) JsonUtil.fromJson(s, HotTalisBean.class);
                            datas = talisBean.data;
                            other = talisBean.other;
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}