package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public class NewsReplyBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String id;
        public String tid;
        public String cid;
        public String reply_uid;
        public String accept_uid;
        public String content;
        public String img;
        public String likes;
        public String time;
        public String pid;
        public String status;
        public String citycode;
        public Replydata replydata;
        public Topicdata topicdata;
        public TopicUserdata topicUserdata;
        public String mynickname;
        public String saycontent;
        public String tonickname;
        public String touid;

        public class Replydata {
            public String uid;
            public String nickname;
            public String headurl;
        }

        public class Topicdata {
            public String uid;
            public String content;
            public String video;
            public String picture;
            public String createtime;
        }

        public class TopicUserdata {
            public String uid;
            public String nickname;
            public String headurl;
        }
    }
}
