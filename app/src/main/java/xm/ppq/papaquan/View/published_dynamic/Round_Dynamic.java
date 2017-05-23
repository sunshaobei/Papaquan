package xm.ppq.papaquan.View.published_dynamic;

import java.util.List;

import xm.ppq.papaquan.Bean.TopicBean;

/**
 * Created by sunshaobei on 2017/3/10.
 */

public interface Round_Dynamic {

    void sendSuccess();

    void sendError(String error);

    int getUid();

    String getContent();

    List<String> getPicture();

    String getCityCode();

    String getToken();

    String getVideoPath();

    String getVideoPic();

}
