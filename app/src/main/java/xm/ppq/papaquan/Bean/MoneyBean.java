package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MoneyBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String id;
        public String uid;
        public String content;
        public String video;
        public List<String> picture;
        public String time;
        public String nickname;
        public String headurl;
    }
}