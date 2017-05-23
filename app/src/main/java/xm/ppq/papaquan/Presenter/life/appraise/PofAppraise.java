package xm.ppq.papaquan.Presenter.life.appraise;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.TakeAllBean;
import xm.ppq.papaquan.Bean.life.TitleBean;
import xm.ppq.papaquan.Bean.life.TitleZBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.appraise.DataUtils;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public class PofAppraise implements Presenter {

    private DataUtils dataUtils;
    private final SharedPreferencesPotting my_login;

    public PofAppraise(DataUtils dataUtils) {
        this.dataUtils = dataUtils;
        my_login = new SharedPreferencesPotting((Context) dataUtils, "my_login");
    }

    /**
     * 抢购评价
     *
     * @param pid
     * @param content
     * @param piclist
     */
    @Override
    public void appraise(int pid, String content, List<String> piclist) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", pid)
                    .put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1)
                    .put("sid", dataUtils.getSid());
            if (!TextUtils.isEmpty(content)) {
                jsonObject.put("content", content);
            } else {
                jsonObject.put("content", "");
            }
            if (piclist.size() > 0) {
                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < piclist.size(); i++) {
                    jsonArray.put(piclist.get(i));
                }
                jsonObject.put("picture", jsonArray);
            } else {
                jsonObject.put("picture", "");
            }
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PANICCOMMENT, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (!TextUtils.isEmpty(o)) {
                        String code = JsonUtil.getString(o, "code");
                        if (!TextUtils.isEmpty(code) && code.equals("0")) {
                            dataUtils.appraiseSuccess("评论成功");
                        } else {
                            dataUtils.appraiseError(JsonUtil.getString(o, "info"));
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 折扣评价
     *
     * @param pid
     * @param content
     * @param piclist
     */
    @Override
    public void Discount(int pid, String content, List<String> piclist) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1)
                    .put("use", pid)
                    .put("content", content);
            if (piclist != null) {
                JSONArray jsonArray = new JSONArray();
                for (String s : piclist) {
                    jsonArray.put(s);
                }
                jsonObject.put("img", jsonArray);
            }
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.RE_SUBAPP, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean != null) {
                        if (baseBean.getCode().equals("0")) {
                            dataUtils.appraiseSuccess("评价成功");
                        } else {
                            dataUtils.appraiseError(baseBean.getInfo());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 精选评价
     *
     * @param pid
     * @param content
     * @param piclist
     */
    @Override
    public void Coupon(int pid, String content, List<String> piclist) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1)
                    .put("use", pid)
                    .put("content", content);
            if (piclist != null) {
                JSONArray jsonArray = new JSONArray();
                for (String s : piclist) {
                    jsonArray.put(s);
                }
                jsonObject.put("img", jsonArray);
            }
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.CO_SUBAPP, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean != null) {
                        if (baseBean.getCode().equals("0")) {
                            dataUtils.appraiseSuccess("评价成功");
                        } else {
                            dataUtils.appraiseError(baseBean.getInfo());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getTitle(String type, String url) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("use", "uid", "token", "tokentype")
                .put_value(dataUtils.getPid(), my_login.getItemInt("uid"), my_login.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(url, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    if (type.equals("精选")) {
                        TitleBean titleBean = (TitleBean) JsonUtil.fromJson(s, TitleBean.class);
                        if (titleBean.getCode() == 0) {
                            dataUtils.setTitle_J(titleBean.getData());
                        }
                    } else {
                        TitleZBean titleZBean = (TitleZBean) JsonUtil.fromJson(s, TitleZBean.class);
                        if (titleZBean.getCode() == 0) {
                            dataUtils.setTitle_Z(titleZBean.getData());
                        }
                    }
                }
            }
        });
    }
}