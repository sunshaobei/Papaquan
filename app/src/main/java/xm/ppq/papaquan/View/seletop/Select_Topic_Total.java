package xm.ppq.papaquan.View.seletop;

import java.util.List;

import xm.ppq.papaquan.Bean.TopicBean;

/**
 * Created by Administrator on 2017/2/21.
 */

public interface Select_Topic_Total {

    int getUid();

    String getToken();

    void setTopicList(List<TopicBean.Data> topicList);
}
