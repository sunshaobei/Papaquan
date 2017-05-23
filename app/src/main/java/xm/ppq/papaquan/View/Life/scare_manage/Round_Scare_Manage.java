package xm.ppq.papaquan.View.Life.scare_manage;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.Scare_ManageBean;
import xm.ppq.papaquan.Bean.life.Scare_Manage_2Bean;

/**
 * Created by EdgeDi on 19:14.
 */

public interface Round_Scare_Manage {

    String getSid();

    int getUid();

    String getToken();

    int getPage();

    void setBean(ArrayList<Scare_ManageBean.DataBean> bean);

    void setBean2(ArrayList<Scare_Manage_2Bean.DataBean> dataBeen);
}