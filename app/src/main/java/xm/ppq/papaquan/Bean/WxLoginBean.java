package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/4/7.
 */

public class WxLoginBean {

    public String info;
    public String code;
    public Data data;
    public String other;

    public class Data {
        public int uid;
        public String token;
    }

}