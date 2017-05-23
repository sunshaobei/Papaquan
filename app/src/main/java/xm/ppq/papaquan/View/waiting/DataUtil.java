package xm.ppq.papaquan.View.waiting;

import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.WaittingData;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public interface DataUtil {

    int getId();

    String getUrl();

    String getType();

    void setData(WaittingData.DataBean data);

    void setBean(Order_PayBean bean);
}
