package xm.ppq.papaquan.View.Life.restaurant;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.RestaurantBean;
import xm.ppq.papaquan.Bean.life.UserRestBean;

/**
 * Created by EdgeDi on 10:49.
 */

public interface Round_Restaurant {

    int getUid();

    String getSid();

    String getToken();

    String getApp_Title();

    String getName();

    void setInfo(RestaurantBean.DataBean dataBean);

    void setList(ArrayList<UserRestBean.OtherBean> otherBeen);

    void setData(UserRestBean.DataBean data);

    void setOther(ArrayList<RestaurantBean.Other> other);

    void ToastShow(String result);

    void setUse(int i, String title);
}