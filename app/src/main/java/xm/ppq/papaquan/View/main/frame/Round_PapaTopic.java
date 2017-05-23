package xm.ppq.papaquan.View.main.frame;

import java.util.List;

import xm.ppq.papaquan.Bean.PapaTopicBean;

/**
 * Created by sunshaobei on 2017/3/14.
 */

public interface Round_PapaTopic {

    String getCityCode();

    void setPapaTopicList(List<PapaTopicBean.DataBean> topicList);

    int getUid();
    String getToken();

}
