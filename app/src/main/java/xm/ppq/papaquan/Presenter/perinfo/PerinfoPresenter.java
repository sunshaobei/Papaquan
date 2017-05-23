package xm.ppq.papaquan.Presenter.perinfo;

import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.PerInfoBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.perinfo.Round_Perinfo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PerinfoPresenter implements Mutual_Perinfo {

    private Round_Perinfo round_perinfo;
    private PerInfoBean bean;

    public PerinfoPresenter(Round_Perinfo round_perinfo) {
        this.round_perinfo = round_perinfo;
    }

    @Override
    public void AllInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", round_perinfo.getToken());
            jsonObject.put("tokentype", 1);
            jsonObject.put("uid", round_perinfo.getUid());
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.PERINFO, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (bean != null) {
                        round_perinfo.setUid(String.valueOf(bean.data.info.uid));
                        round_perinfo.setLevel("LV." + bean.data.info.level);
                        round_perinfo.setPhone(bean.data.info.tel);
                        round_perinfo.setNickname(bean.data.info.nickname);
                        round_perinfo.setSignture(bean.data.info.signature);
                        round_perinfo.setHeadUrl(bean.data.info.headurl);
                        round_perinfo.setAddress(bean.data.info.province == null ? "" : bean.data.info.province + " " + bean.data.info.city == null ? "" : bean.data.info.city);
                        if (bean.data.info.wxname == null) {
                            round_perinfo.setWX("未绑定");
                        } else {
                            if (bean.data.info.wxname.equals("")) {
                                round_perinfo.setWX("未绑定");
                            } else {
                                round_perinfo.setWX(bean.data.info.wxname);
                            }
                        }
                        if (bean.data.info.sex == 2) {
                            round_perinfo.setSex("女");
                        } else if (bean.data.info.sex == 0) {
                            round_perinfo.setSex("无");
                        } else {
                            round_perinfo.setSex("男");
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null && !s.equals("")) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            bean = (PerInfoBean) JsonUtil.fromJson(s, PerInfoBean.class);
                        } else {
                            Log.e("Error", "用户不存在");
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ReviseN_S(String type, String info) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("info", info);
            jsonObject.put("uid", round_perinfo.getUid());
            jsonObject.put("type", type);
            jsonObject.put("token", round_perinfo.getToken());
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.MAKE_N_S_S, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean != null) {
                        switch (baseBean.getCode()) {
                            case "0":
                                round_perinfo.setToast("修改成功");
                                break;
                            case "1":
                                round_perinfo.setToast("修改失败，请重试");
                                break;
                            case "2":
                                round_perinfo.setToast("不能为空");
                                break;
                            case "3":
                                round_perinfo.setToast("用户名已被使用,请重新更改");
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
