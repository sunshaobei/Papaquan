package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class FollowGridBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String uid;
        public String nickname;
        public String headurl;
        public boolean isSelect;
    }
}
