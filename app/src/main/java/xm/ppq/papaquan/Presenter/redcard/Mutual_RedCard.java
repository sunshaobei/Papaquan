package xm.ppq.papaquan.Presenter.redcard;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.RedCardVipBean;
import xm.ppq.papaquan.Bean.life.RedcardData;
import xm.ppq.papaquan.Bean.life.RedcardPartshopData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.redcard.Round_RedCard;

/**
 * Created by Administrator on 2017/4/10.
 */

public class Mutual_RedCard implements RedCardPresenter {

    private Integer[] one = new Integer[]{R.mipmap.redfive_0, R.mipmap.redfive_1,
            R.mipmap.redfive_2, R.mipmap.redfive_3, R.mipmap.redfive_4, R.mipmap.redfive_5, R.mipmap.redfive_6, R.mipmap.redfive_7};
    private String[] two = new String[]{"专属标识", "五折特权", "折扣特权", "抢购特惠", "积分特权", "专属活动", "兑换特权", "专享福利"};
    private String[] three = new String[]{"彰显尊贵身份", "消费五折特权", "消费折扣特权", "抢购更便宜", "签到积分更多", "红卡专属活动", "兑换更省积分", "不定期福利"};

    private Round_RedCard round_redCard;
    private final SharedPreferencesPotting my_login;

    public Mutual_RedCard(Round_RedCard round_redCard) {
        this.round_redCard = round_redCard;
        my_login = new SharedPreferencesPotting((Context) round_redCard, "my_login");
    }

    @Override
    public void startVip() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                ArrayList<RedCardVipBean> list = new ArrayList<>();
                for (int i = 0; i < 8; i++) {
                    list.add(new RedCardVipBean(one[i], two[i], three[i]));
                }
                handler.obtainMessage(4001, list).sendToTarget();
                list = null;
            }
        }.start();
    }

    @Override
    public void startShop() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", my_login.getItemString("citycode"));
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.PARTSHOPSHOW, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        RedcardPartshopData dataBean = (RedcardPartshopData) JsonUtil.fromJson(o, RedcardPartshopData.class);
                        round_redCard.setShowList(dataBean);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4001:
                    round_redCard.setList((ArrayList<RedCardVipBean>) msg.obj);
                    break;
            }
        }
    };

    public void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("citycode", my_login.getItemString("citycode"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.MYCARD, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        RedcardData dataBean = (RedcardData) JsonUtil.fromJson(o, RedcardData.class);
                        round_redCard.setData(dataBean);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}