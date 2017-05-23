package xm.ppq.papaquan.Presenter.he_sheet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.MyTopicBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.tasheet.Round_HeSheet;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Mutual_HeSheet implements HeSheetPresenter {

    private Round_HeSheet round_heSheet;
    private MyTopicBean.other other;
    private ArrayList<ShowNewsBigBean.Data> dataBeen = new ArrayList<>();

    public Mutual_HeSheet(Round_HeSheet round_heSheet) {
        this.round_heSheet = round_heSheet;
    }

    @Override
    public void start(String Uuid, int type) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tauid", Uuid);
            jsonObject.put("uid", round_heSheet.getUid());
            jsonObject.put("page", round_heSheet.getPage());
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.OTHERPAGE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (other != null) {
                        round_heSheet.setNickName(other.nickname);
                        round_heSheet.setHeadUrl(other.headurl);
                        round_heSheet.setT_F_F(other.topicnum + "", other.subnum + "", other.fansnum + "");
                        round_heSheet.setSignature(other.signature);
                        round_heSheet.setSexIcon(other.sex);
                        round_heSheet.IsFollow(other.isfollow + "");
                        round_heSheet.setLevel(other.level);
                        round_heSheet.setCreateTime(other.createtime);
                        round_heSheet.setData(dataBeen, type);
                        round_heSheet.setAddress(other.city + other.area);
                        try {
                            round_heSheet.setVip_End(Long.parseLong(other.vip_end));
                        } catch (Exception e) {
                            round_heSheet.setVip_End(0);
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            MyTopicBean bean = (MyTopicBean) JsonUtil.fromJson(s, MyTopicBean.class);
                            other = bean.other;
                            dataBeen.clear();
                            dataBeen.addAll(bean.data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Finish_F_F(final String url, String Uuid) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uidtwo", Uuid);
            jsonObject.put("uidone", round_heSheet.getUid());
            jsonObject.put("type", "1");
            jsonObject.put("uid", round_heSheet.getUid());
            jsonObject.put("token", round_heSheet.getToken());
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String baseBean) {
                    switch (JsonUtil.getString(baseBean, "code")) {
                        case "0":
                            if (url == BaseUrl.DELSUB) {
                                round_heSheet.IsFollow("0");
                            } else {
                                round_heSheet.IsFollow("1");
                            }
                            break;
                        case "1":

                            break;
                        case "2":

                            break;
                        case "3":

                            break;
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}