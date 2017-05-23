package xm.ppq.papaquan.Presenter.viewmanage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ViewManageBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.vipmanage.Round_ViewManage;

/**
 * Created by fcw on 2017/4/24.
 */

public class Mutual_ViewManage implements ViewManagePresenter {

    private Round_ViewManage round_viewManage;

    public Mutual_ViewManage(Round_ViewManage round_viewManage) {
        this.round_viewManage = round_viewManage;
    }

    @Override
    public void getViewInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", round_viewManage.getSid())
                    .put("uid", round_viewManage.getUid())
                    .put("token", round_viewManage.getToken())
                    .put("tokentype", 1)
                    .put("lenhgt", 10)
                    .put("page", round_viewManage.getPage());
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOWVIP, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        ViewManageBean bean = (ViewManageBean) JsonUtil.fromJson(s, ViewManageBean.class);
                        round_viewManage.setOther(bean.getOther());
                        round_viewManage.setBean((ArrayList<ViewManageBean.DataBean>) bean.getData());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}