package xm.ppq.papaquan.Presenter.panic_buying;

import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;
import com.tencent.mapsdk.raster.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.Panic_BuyingBean;
import xm.ppq.papaquan.Bean.life.Panic_buyUserData;
import xm.ppq.papaquan.Bean.life.Panic_buyingData;
import xm.ppq.papaquan.Bean.life.PicNTextDetailData;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.panic_buying.Round_PanicBuying;
import xm.ppq.papaquan.life.Tool.TimeTextView;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Mutual_Panic_Buying implements Panic_BuyingPresenter {

    private Round_PanicBuying round_panicBuying;
    private Panic_BuyingBean.DataBean dataBean;

    public Mutual_Panic_Buying(Round_PanicBuying round_panicBuying) {
        this.round_panicBuying = round_panicBuying;
    }

    @Override
    public void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", round_panicBuying.getPid())
                    .put("uid", round_panicBuying.getUid())
                    .put("length", 10)
                    .put("page", 0)
                    .put("token", round_panicBuying.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.DETAILS, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBean != null) {
                        round_panicBuying.setBean(dataBean);
                        int timetype = 0;
                        long time = 0;
                        String text = "";
                        String color = "";
                        switch (dataBean.getStatus()) {
                            case 0://未开始
                                timetype = TimeTextView.START;
                                time = dataBean.getStart_time();
                                text = "即将开始";
                                color = "#19ac87";
                                break;
                            case 1://正在抢购
                                timetype = TimeTextView.STOP;
                                time = dataBean.getEnd_time();
                                text = "快抢完了";
                                color = "#e60012";
                                break;
                            case 2://抢完了
                                timetype = TimeTextView.STOP;
                                time = dataBean.getEnd_time();
                                text = "已抢完";
                                color = "#e60012";
                                break;
                            case 3://已结束
                                timetype = TimeTextView.STOP;
                                time = dataBean.getEnd_time();
                                text = "已结束";
                                color = "#e60012";
                                break;
                            case 4://已过期
                                timetype = TimeTextView.STOP;
                                time = dataBean.getEnd_time();
                                text = "已过期";
                                color = "#e60012";
                                break;
                            case 5://抢到了没付款
                                timetype = TimeTextView.EXPIRE;
                                time = dataBean.getConsumption_deadline();
                                text = "快去使用把";
                                color = "#e60012";
                                break;
                            case 6://已付款
                                timetype = TimeTextView.EXPIRE;
                                time = dataBean.getConsumption_deadline();
                                text = "快去使用把";
                                color = "#e60012";
                                break;
                            case 7://用了没评价
                                timetype = TimeTextView.EXPIRE;
                                time = dataBean.getConsumption_deadline();
                                text = "等待评价";
                                color = "#e60012";
                                break;
                            case 8://已评价
                                timetype = TimeTextView.EXPIRE;
                                time = dataBean.getConsumption_deadline();
                                text = "感谢惠顾";
                                color = "#19ac87";
                                break;
                        }
                        round_panicBuying.JudgeStatus(timetype, time, text, color);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        Panic_BuyingBean panic_buyingBean = (Panic_BuyingBean) JsonUtil.fromJson(s, Panic_BuyingBean.class);
                        if (panic_buyingBean.getCode() == 0) {
                            dataBean = panic_buyingBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void getBuyUserList(int page) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", round_panicBuying.getPid())
                    .put("uid", round_panicBuying.getUid())
                    .put("length", 10)
                    .put("page", page)
                    .put("token", round_panicBuying.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PANICBUYUSERLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (!TextUtils.isEmpty(o)) {
                        Panic_buyUserData data = (Panic_buyUserData) JsonUtil.fromJson(o, Panic_buyUserData.class);
                        if (data.getData() != null)
                            round_panicBuying.setBuyUserlist(data.getData());
                    }
                }
            });
        } catch (Exception e) {

        }
    }


    @Override
    public void HumanList() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", round_panicBuying.getPid())
                    .put("page", 0)
                    .put("uid", round_panicBuying.getUid())
                    .put("lenght", 10)
                    .put("token", round_panicBuying.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.BUYUSERLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double Calculated_distance(LatLng start, LatLng end) {
        double lat1 = (Math.PI / 180) * start.getLatitude();
        double lat2 = (Math.PI / 180) * end.getLatitude();

        double lon1 = (Math.PI / 180) * start.getLongitude();
        double lon2 = (Math.PI / 180) * end.getLongitude();

        //地球半径
        double R = 6371;

        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d;
    }

    @Override
    public void purchasing() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pid", round_panicBuying.getPid())
                    .put("uid", round_panicBuying.getUid())
                    .put("token", round_panicBuying.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.PANICBUY, jsonObject.toString(), new Subscriber<BaseBean>() {
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
                            LinkedTreeMap<String, String> map = (LinkedTreeMap<String, String>) baseBean.getData();
                            round_panicBuying.purchasingSuccess(map.get("orderid"));
                        } else if (baseBean.getCode().equals("4")) {
                            round_panicBuying.RushError();
                        } else if (baseBean.getCode().equals("7")) {
                            round_panicBuying.RushError();
                        } else {
                            round_panicBuying.Toast(baseBean.getInfo());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPicNTextInfo(int pid) {
        try {
             JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid", pid);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PICNTEXT, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    String code = JsonUtil.getString(o, "code");
                    if (code != null && code.equals("0")) {
                        PicNTextDetailData data = (PicNTextDetailData) JsonUtil.fromJson(o, PicNTextDetailData.class);
                        PicNTextDetailData.DataBean data1 = data.getData();
                        String picdetails = data1.getPicdetails();
                        round_panicBuying.setPicNTextData(picdetails);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
