package xm.ppq.papaquan.Presenter.life.restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ResBean;
import xm.ppq.papaquan.Bean.life.RestaurantBean;
import xm.ppq.papaquan.Bean.life.UserRestBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.restaurant.Round_Restaurant;

/**
 * Created by EdgeDi on 10:51.
 */

public class Mutual_Restaurant implements RestaurantPresenter {

    private Round_Restaurant round_restaurant;

    public Mutual_Restaurant(Round_Restaurant round_restaurant) {
        this.round_restaurant = round_restaurant;
    }

    private RestaurantBean.DataBean dataBean;
    private ArrayList<RestaurantBean.Other> others;

    /**
     * 请求折扣详情
     */
    @Override
    public void StartInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", round_restaurant.getSid())
                    .put("uid", round_restaurant.getUid())
                    .put("token", round_restaurant.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.DISCOUNT, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBean != null) {
                        round_restaurant.setInfo(dataBean);
                    }
                    if (others != null) {
                        round_restaurant.setOther(others);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        RestaurantBean restaurantBean = (RestaurantBean) JsonUtil.fromJson(s, RestaurantBean.class);
                        if (restaurantBean.getCode() == 0) {
                            dataBean = restaurantBean.getData();
                            others = restaurantBean.getOther();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<UserRestBean.OtherBean> otherBeen;
    private UserRestBean.DataBean userdatabean;

    /**
     * 生成核销订单
     */
    @Override
    public void GetOrder() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", round_restaurant.getUid())
                    .put("sid", round_restaurant.getSid())
                    .put("token", round_restaurant.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.USE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (otherBeen != null) {
                        round_restaurant.setList(otherBeen);
                    }
                    if (userdatabean != null) {
                        round_restaurant.setData(userdatabean);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        UserRestBean userRestBean = (UserRestBean) JsonUtil.fromJson(s, UserRestBean.class);
                        if (userRestBean.getCode() == 0) {
                            otherBeen = userRestBean.getOther();
                            userdatabean = userRestBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交订单
     */
    @Override
    public void PlaceOrder(JSONObject jsonObject) {
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.CONFIRMUSE, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    ResBean resBean = (ResBean) JsonUtil.fromJson(s, ResBean.class);
                    if (resBean.getCode() == 0) {
                        round_restaurant.setUse(resBean.getData().getUse(), "去评价");
                        round_restaurant.ToastShow("核销成功");
                    } else {
                        round_restaurant.ToastShow(resBean.getInfo());
                    }
                }
            }
        });
    }
}