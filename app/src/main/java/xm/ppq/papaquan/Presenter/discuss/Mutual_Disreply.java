package xm.ppq.papaquan.Presenter.discuss;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.DisRepBean;
import xm.ppq.papaquan.Bean.NewsReplyBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.discuss_reply.fragment.Round_Reply;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public class Mutual_Disreply implements DisreplyPresenter, ReplyDisListenre {

    private Round_Reply round_reply;
    private Context context;
    private SharedPreferencesPotting potting;
    private ArrayList<NewsReplyBean.Data> datas;

    public Mutual_Disreply(Context context, Round_Reply round_reply) {
        this.context = context;
        this.round_reply = round_reply;
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    public void getData(int pager) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("page", pager);
            jsonObject.put("tokentype", 1);
            jsonObject.put("token", potting.getItemString("token"));
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.REPLTME, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (datas != null) {
                        round_reply.setData(datas);
                    }
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (JsonUtil.getString(s, "code").equals("0")) {
                        NewsReplyBean newsReplyBean = (NewsReplyBean) JsonUtil.fromJson(s, NewsReplyBean.class);
                        datas = (ArrayList<NewsReplyBean.Data>) newsReplyBean.data;
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replyDiscu(String rid, int cid, String touid, int tid, String content) {

    }
}
