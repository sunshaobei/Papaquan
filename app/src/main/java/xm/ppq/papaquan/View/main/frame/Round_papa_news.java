package xm.ppq.papaquan.View.main.frame;

import java.util.List;

import xm.ppq.papaquan.Bean.ShowNewsBigBean;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface Round_papa_news {

    String getTid();

    String getCityCode();

    String getToken();

    int getUid();

    void setList(List<ShowNewsBigBean.Data> list);

    void setRefreshList(List<ShowNewsBigBean.Data> list);

    void showInfo(String s);

    void startMutual();
}
