package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class FollowerandFollowBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String uidone;
        public String uidtwo;
        public String createtime;
        public String nickname;
        public String signature;
        public String headurl;
        public String id;
        public String eachother;
        public String operate;
        public String type;
    }
}
