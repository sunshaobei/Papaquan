package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/20.
 */

public class ReplyBean {

    private String uid;
    private String cid;
    private String content;
    private String token;
    private String tid;
    private String touid;
    private String pid;
    private String name;
    private String img;

    public ReplyBean(String uid, String cid, String content, String token, String tid, String touid, String pid, String name, String img) {
        this.uid = uid;
        this.cid = cid;
        this.content = content;
        this.token = token;
        this.tid = tid;
        this.touid = touid;
        this.pid = pid;
        this.name = name;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getUid() {
        return uid;
    }

    public String getCid() {
        return cid;
    }

    public String getContent() {
        return content;
    }

    public String getToken() {
        return token;
    }

    public String getTid() {
        return tid;
    }

    public String getTouid() {
        return touid;
    }

    public String getPid() {
        return pid;
    }
}
