package xm.ppq.papaquan.View.trendtopicdetail;

import java.util.List;

import xm.ppq.papaquan.Bean.GetComBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;

/**
 * Created by Administrator on 2017/3/22.
 */

public interface Round_Trend {

    int getUid();

    String getToken();

    String getTid();

    void setAdvert(String url);

    void setVideo(String video, String url);

    void setLevel(int Level);

    void IsShow(String uid);

    void ShowToast(String result);

    void setList(List<GetComBean.Data> getdata);

    void setFollow(String result);

    void setLove(int resource, int colors);

    void setPicList(List<String> list);

    void setComment_Reward_Like_Read_TopicUserdata_Createtime_Content(int comment, String reward, int like, int read, String topic, String createtime, String content, String url);

    void setTrend(String like);

    void setCri();

    void setRewareList(List<String> list);

    void setReawrenum(int num);

    void setShare(String result, String head, int uid);

    void setError();

}