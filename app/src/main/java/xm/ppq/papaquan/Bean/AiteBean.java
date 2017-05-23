package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by @sunshaobei on 2017/3/2.
 */

public class AiteBean {

    public String info;
    public String code;
    public String other;
    public List<Data> data;

    public class Data {
        public String id;
        public String tid;
        public String uid;
        public String touid;
        public String status;
        public String money;
        public String is_read;
        public String createtime;
        public topiclist topiclist;
        public rewarduser rewarduser;
        public myuser myuser;
    }

    public class topiclist {
        public String content;
        public String video;
        public String picture;
        public String createtime;
    }

    public class rewarduser {
        public String uid;
        public String nickname;
        public String headurl;
    }

    public class myuser {
        public String uid;
        public String nickname;
        public String headurl;
    }
}