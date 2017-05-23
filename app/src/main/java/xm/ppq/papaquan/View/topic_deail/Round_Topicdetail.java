package xm.ppq.papaquan.View.topic_deail;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.ShowNewsBigBean;

/**
 * Created by sunshaobei on 2017/3/22.
 */

public interface Round_Topicdetail {

    String getHotid();

    String getPager();

    String getCitycode();

    int getUid();

    void setHotTitle(String Hottitle);

    void setHot_Num(String hot_num);

    void setName(String name);

    void setIcon(String icon);

    String getToken();

    void setBackground(String url);

    void setList(ArrayList<ShowNewsBigBean.Data> datas);

    void setShare(String title, String url);

}
