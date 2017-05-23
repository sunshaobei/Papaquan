package xm.ppq.papaquan.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/23.
 */

public class HotTalisBean {

    public String info;
    public String code;
    public ArrayList<ShowNewsBigBean.Data> data;
    public Other other;

    public class Other {
        public String uid;
        public String citycode;
        public String heat;
        public String joinnum;
        public String title;
        public String img;
        public String nickname;
        public String headurl;
        public String level;
    }

}