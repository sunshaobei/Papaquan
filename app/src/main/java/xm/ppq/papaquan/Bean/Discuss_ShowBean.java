package xm.ppq.papaquan.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */

public class Discuss_ShowBean {
    public String info;
    public String code;
    public Data data;
    public String other;

    public class Data {
        public String id;
        public String uid;
        public String title;
        public String hotid;
        public String content;
        public String videopic;
        public String videotype;
        public String video;
        public List<String> picture;
        public String createtime;
        public String status;
        public int read_num;
        public int likes_num;
        public int comment_num;
        public String at;
        public String citycode;
        public String starttime;
        public String endtime;
        public String reward_num;
        public List<RewardUserdata> rewardUserdata;
        public String focus;
        public TopicUserData topicUserdata;
        public int rewardCount;
        public String likeCount;
        public List<LikeUserDate> likeUserdata;
        public String isLike;
        public ArrayList<AdInfo> adInfo;

        public class LikeUserDate {
            public String uid;
            public String nickname;
        }

        public class AdInfo {
            public String img;
            public String link;
            public String looktype;
            public String note;
            public String start;
            public String end;
        }

        public class RewardUserdata {
            public String uid;
            public String headurl;
        }

        public class TopicUserData {
            public String nickname;
            public String headurl;
            public int level;
        }
    }
}