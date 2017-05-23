package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class GetComBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String id;
        public String topic_uid;
        public String tid;
        public String com_uid;
        public String content;
        public String img;
        public String likes;
        public int reply_num;
        public String time;
        public String status;
        public String focus;
        public int isLike;
        public CommentUserdata commentUserdata;
        public List<Reply> reply;

        public class CommentUserdata {
            public String uid;
            public String nickname;
            public String headurl;
            public int level;
        }

        public class Reply {
            public String id;
            public String reply_uid;
            public String accept_uid;
            public String content;
            public String img;
            public String time;
            public int islike;
            public String reply_nickname;
            public String accept_nickname;
        }
    }
}
