package xm.ppq.papaquan.View.Life.panic_buying;

import java.util.List;

import xm.ppq.papaquan.Bean.Panic_BuyingBean;
import xm.ppq.papaquan.Bean.life.Panic_buyUserData;
import xm.ppq.papaquan.Bean.life.Panic_buyingData;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface Round_PanicBuying {

    int getPid();

    int getUid();

    String getToken();

    void RushError();

    void Toast(String result);

    void JudgeStatus(int TimeStatus, long time, String text, String color);

    void setBean(Panic_BuyingBean.DataBean dataBean);

    void purchasingSuccess(String oid);

    void setBuyUserlist(List<Panic_buyUserData.DataBean> list);

    void setPicNTextData(String s);
}