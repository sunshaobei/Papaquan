package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/13.
 */

public class PerInfoBean {

    public String info;
    public int code;
    public DataBean data;
    public String other;

    public class DataBean {

        public InfoBean info;

        public class InfoBean {

            public int uid;
            public String tel;
            public String nickname;
            public String headurl;
            public int sex;
            public String signature;
            public String province;
            public String city;
            public String unionid;
            public String wxname;
            public String level;

        }
    }
}
