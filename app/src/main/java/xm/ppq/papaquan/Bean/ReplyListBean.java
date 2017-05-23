package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class ReplyListBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String id;
        public String reply_uid;
        public String accept_uid;
        public String content;
        public String img;
        public String time;
        public String pid;
        public String replyNickname;
        public String replyHeadurl;
        public String toNickname;
    }
}
