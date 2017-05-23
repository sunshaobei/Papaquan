package xm.ppq.papaquan.View.Life.take_notes.fragment;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.RecordBean;
import xm.ppq.papaquan.Bean.life.RecordListBean;

/**
 * Created by EdgeDi on 19:01.
 */

public interface Round_Record {

    void setStartTime(String time);

    void setEndTime(String time);

    void setTime(String time);

    String getStartTime();

    String getEndTime();

    String getSid();

    int getType();

    int getUid();

    String getToken();

    void setMoney(String result);

    void setTopBean(RecordBean.DataBean dataBean);

    void setListData(ArrayList<RecordListBean.DataBean> data);
}