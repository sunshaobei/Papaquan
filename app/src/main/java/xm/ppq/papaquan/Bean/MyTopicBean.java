package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class MyTopicBean {

    public String info;
    public String code;
    public List<ShowNewsBigBean.Data> data;
    public other other;

    public class other {
        public String uid;
        public String sex;
        public String headurl;
        public String nickname;
        public String signature;
        public String gold;
        public String createtime;
        public String province;
        public String city;
        public String area;
        public String exper;
        public String vip_end;
        public String fansnum;
        public String topicnum;
        public String subnum;
        public String isfans;
        public String isfollow;
        public int level;
    }
}
