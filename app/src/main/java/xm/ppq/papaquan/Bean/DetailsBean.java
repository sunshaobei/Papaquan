package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/22.
 */

public class DetailsBean {

    public String info;
    public String code;
    public Data data;
    public String other;

    public class Data {
        public String id;
        public String topic_uid;
        public String tid;
        public String com_uid;
        public String content;
        public String img;
        public int likes;
        public int reply_num;
        public String time;
        public String status;
        public String nickname;
        public String headurl;
        public String focus;
        public int level;
        public int isLike;
    }
}
