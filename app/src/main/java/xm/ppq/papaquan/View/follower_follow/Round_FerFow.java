package xm.ppq.papaquan.View.follower_follow;

import java.util.List;

import xm.ppq.papaquan.Bean.FollowerandFollowBean;

/**
 * Created by Administrator on 2017/3/14.
 */

public interface Round_FerFow {

    String getType();

    int getUid();

    String getToken();

    void setAdapter(List<FollowerandFollowBean.Data> datas);

    void setG_Q_P(String itemUid, String type, String url);

    void setSyntony(int type);
}
