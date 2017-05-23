package xm.ppq.papaquan.View.Life.goodscommet;

import xm.ppq.papaquan.Bean.life.JudgePanicBuyBean;
import xm.ppq.papaquan.Bean.life.JudgeRebateBean;

/**
 * Created by EdgeDi on 15:11.
 */

public interface Round_GoodsComment {

    String getUrl();

    int getPid();

    String getType();

    int getPage();

    int getUid();

    String getToken();

    void setPanicBuy(JudgePanicBuyBean.DataBean dataBean);

    void setRebate(JudgeRebateBean bean);

}