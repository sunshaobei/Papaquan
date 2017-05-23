package xm.ppq.papaquan.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ShowNewsBigBean {

    public String info;
    public String code;
    public List<Data> data;
    public Object other;


    public static class Data {
        public String endtime;
        public int comment_num;
        public int likes_num;
        public List<String> picture;
        public String at;
        public String citycode;
        public String videotype;
        public String uid;
        public int reward_num;
        public int read_num;
        public String title;
        public String starttime;
        public String createtime;
        public String id;
        public String hotid;
        public String status;
        public String video;
        public String videopic;
        public String content;
        public String headurl;
        public String nickname;
        public int sub;
        public int like;
        public List<LikeBean> likeusers;
        public List<LikeBean> rewardusers;
        public List<Comments> comments;
        public int level;

        public static class Comments {
            public Comments(String id,String uid, String nickname, String content, String img) {
                this.uid = uid;
                this.nickname = nickname;
                this.content = content;
                this.img = img;
                this.id = id;
            }

            public String uid;
            public String nickname;
            public String content;
            public String img;
            public String id;
        }

    }
}