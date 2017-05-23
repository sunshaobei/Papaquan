package xm.ppq.papaquan.View.Life.vipmanage;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.ViewManageBean;

/**
 * Created by fcw on 2017/4/24.
 */

public interface Round_ViewManage {

    String getSid();

    int getPage();

    int getUid();

    String getToken();

    void setOther(ViewManageBean.OtherBean other);

    void setBean(ArrayList<ViewManageBean.DataBean> dataBeen);
}
