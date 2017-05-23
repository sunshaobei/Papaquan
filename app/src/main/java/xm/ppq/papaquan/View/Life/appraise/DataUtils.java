package xm.ppq.papaquan.View.Life.appraise;

import xm.ppq.papaquan.Bean.life.TitleBean;
import xm.ppq.papaquan.Bean.life.TitleZBean;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public interface DataUtils {

    int getSid();

    int getPid();

    void appraiseSuccess(String info);

    void appraiseError(String error);

    void setTitle_J(TitleBean.DataBean dataBean);

    void setTitle_Z(TitleZBean.DataBean dataBean);
}
