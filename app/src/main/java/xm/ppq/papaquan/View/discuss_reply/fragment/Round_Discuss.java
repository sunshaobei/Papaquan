package xm.ppq.papaquan.View.discuss_reply.fragment;

import java.util.List;

import xm.ppq.papaquan.Bean.DisComment;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public interface Round_Discuss {
    void setData(List<DisComment.DataBean> list);
    void setError(String s);
    void replySuccess(String s);
}
