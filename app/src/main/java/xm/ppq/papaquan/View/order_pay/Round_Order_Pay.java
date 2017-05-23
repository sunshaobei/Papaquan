package xm.ppq.papaquan.View.order_pay;

import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.PaincPayBean;

/**
 * Created by EdgeDi on 15:18.
 */

public interface Round_Order_Pay {

    String getOid();

    int getUid();

    String getToken();

    String getType();

    void Toast(String result);

    void Skip();

    void setBean(Order_PayBean.DataBean dataBean);

    void setPaincBean(PaincPayBean.DataBean dataBean);
}
