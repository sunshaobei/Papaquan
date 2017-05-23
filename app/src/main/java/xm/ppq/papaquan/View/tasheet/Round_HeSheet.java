package xm.ppq.papaquan.View.tasheet;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.ShowNewsBigBean;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface Round_HeSheet {

    int getUid();

    int getPage();

    String getToken();

    void setNickName(String nickName);

    void setHeadUrl(String headUrl);

    void setT_F_F(String topic, String Follow, String Follower);

    void setSignature(String signature);

    void setAddress(String address);

    void setVip_End(long end);

    void setSexIcon(String sexIcon);

    void IsFollow(String follow);

    void setToast(String result);

    void setLevel(int level);

    void setData(ArrayList<ShowNewsBigBean.Data> list, int type);

    void setCreateTime(String createTime);

}