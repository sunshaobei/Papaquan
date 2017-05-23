package xm.ppq.papaquan.Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/21.
 */

public class TopicBean {
    public String info;
    public String code;
    public ArrayList<Data> data;
    public String other;

    public class Data implements Serializable{
        public String uid;
        public String nickname;
        public String headurl;
    }

}