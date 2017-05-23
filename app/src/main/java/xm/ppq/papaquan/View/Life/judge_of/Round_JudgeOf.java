package xm.ppq.papaquan.View.Life.judge_of;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.JudgeOfBean;

/**
 * Created by EdgeDi on 11:18.
 */

public interface Round_JudgeOf {

    int getUid();

    String getToken();

    int getPage();

    int getType();

    String getSid();

    String getStart();

    String getEnd();

    void setTime(String time);

    void setBean(ArrayList<JudgeOfBean.DataBean> dataBeen);

}