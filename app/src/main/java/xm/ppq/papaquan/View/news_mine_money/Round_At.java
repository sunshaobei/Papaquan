package xm.ppq.papaquan.View.news_mine_money;

import java.util.List;

import xm.ppq.papaquan.Bean.AiteBean;
import xm.ppq.papaquan.Bean.MoneyBean;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public interface Round_At {

    void setAtData(List<AiteBean.Data> s);

    void setMoney(List<MoneyBean.Data> datas);

    void setError(String s);
}
