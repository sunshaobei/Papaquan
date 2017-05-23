package xm.ppq.papaquan.View.my_topic;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.ShowNewsBigBean;

/**
 * Created by Administrator on 2017/3/24.
 */

public interface Round_MyTopic {

    int getUid();

    String getToken();


    void setList(ArrayList<ShowNewsBigBean.Data> list);
}
