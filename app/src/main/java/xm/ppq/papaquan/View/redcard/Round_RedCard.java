package xm.ppq.papaquan.View.redcard;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.RedCardVipBean;
import xm.ppq.papaquan.Bean.life.RedcardData;
import xm.ppq.papaquan.Bean.life.RedcardPartshopData;

/**
 * Created by Administrator on 2017/4/10.
 */

public interface Round_RedCard {
    void setList(ArrayList<RedCardVipBean> list);
    void setShowList(RedcardPartshopData data);
    void setData(RedcardData dataBean);
}