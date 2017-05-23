package xm.ppq.papaquan.View.mine_integral;

import android.view.View;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.IntergalHeadBean;
import xm.ppq.papaquan.Bean.life.IntergalListBean;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface Round_Integral {

    int getUid();

    String getToken();

    String getCityCode();

    void setList(IntergalListBean bean);

    void setHeadData(IntergalHeadBean data);

}