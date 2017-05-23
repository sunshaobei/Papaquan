package xm.ppq.papaquan.Presenter.discuss;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.DisComment;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.discuss_reply.fragment.Round_Discuss;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public class Mutual_Discomment implements DiscommentPresenter, ReplyDisListenre {
    private Round_Discuss round_discuss;
    private SharedPreferencesPotting potting;

    public Mutual_Discomment(Context context, Round_Discuss round_discuss) {
        this.round_discuss = round_discuss;
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
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.COMMENTS, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        String code = JsonUtil.getString(s, "code");
                        if (!code.equals("0")) round_discuss.setError("获取信息失败");
                        else {
                            DisComment disComment = (DisComment) JsonUtil.fromJson(s, DisComment.class);
                            List<DisComment.DataBean> dataList = disComment.getData();
                           round_discuss.setData(dataList);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void replyDiscu(String rid, int cid, String touid, int tid, String content) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rid", rid);
            jsonObject.put("cid", cid);
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("touid", touid);
            jsonObject.put("tid", tid);
            jsonObject.put("content", content);
            jsonObject.put("tokentype", 1);
            jsonObject.put("token", potting.getItemString("token"));
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne("Index/reply", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        String code = JsonUtil.getString(s, "code");
                        if (code.equals("0")) {
                            round_discuss.replySuccess("回复成功");
                        } else {
                            round_discuss.setError("回复失败");
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
