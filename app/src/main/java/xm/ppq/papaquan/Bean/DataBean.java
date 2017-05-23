package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/17.
 */

public class DataBean {

    private String tid;
    private String uid;
    private String token;

    public DataBean(String tid, String uid, String token) {
        this.tid = tid;
        this.uid = uid;
        this.token = token;
    }

    public String getTid() {
        return tid;
    }

    public String getUid() {
        return uid;
    }

    public String getToken() {
        return token;
    }
}
