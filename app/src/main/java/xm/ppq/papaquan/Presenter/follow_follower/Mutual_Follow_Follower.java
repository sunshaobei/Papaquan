package xm.ppq.papaquan.Presenter.follow_follower;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.FollowerandFollowBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.follower_follow.Round_FerFow;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Mutual_Follow_Follower implements Follow_Follower_Presenter {

    private Round_FerFow round_ferFow;
    private List<FollowerandFollowBean.Data> datas = new ArrayList<>();

    public Mutual_Follow_Follower(Round_FerFow round_ferFow) {
        this.round_ferFow = round_ferFow;
    }

    @Override
    public void start(int page, String search) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", round_ferFow.getUid());
            jsonObject.put("page", page);
            jsonObject.put("type", round_ferFow.getType());
            jsonObject.put("length", "1000");
            jsonObject.put("search", search);
            jsonObject.put("token", round_ferFow.getToken());
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.FOLLOWER_FOLLOW_BLACK_LIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    round_ferFow.setAdapter(datas);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            FollowerandFollowBean bean = (FollowerandFollowBean) JsonUtil.fromJson(s, FollowerandFollowBean.class);
                            datas = bean.data;
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Operate(String itemUid, String type, String url) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("uid", round_ferFow.getUid());
            jsonObject.put("uidtwo", itemUid);
            jsonObject.put("uidone", round_ferFow.getUid());
            jsonObject.put("token", round_ferFow.getToken());
            jsonObject.put("tokentype", 1);
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
                            round_ferFow.setSyntony(0);
                            break;
                        case "1":
                            round_ferFow.setSyntony(1);
                            break;
                        case "2":
                            round_ferFow.setSyntony(2);
                            break;
                        case "3":
                            round_ferFow.setSyntony(3);
                            break;
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
