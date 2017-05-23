package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EdgeDi on 16:52.
 */

public class JudgeRebateBean {

    private String info;
    private int code;
    private OtherBean other;
    private List<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {

        private int allnum;
        private int haspic;

        public int getAllnum() {
            return allnum;
        }

        public void setAllnum(int allnum) {
            this.allnum = allnum;
        }

        public int getHaspic() {
            return haspic;
        }

        public void setHaspic(int haspic) {
            this.haspic = haspic;
        }
    }

    public static class DataBean {

        private int id;
        private int uid;
        private int sid;
        private int type;
        private int useid;
        private int typeid;
        private String content;
        private ArrayList<String> picture;
        private int createtime;
        private Object business_reply;
        private String title;
        private String nickname;
        private String headurl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUseid() {
            return useid;
        }

        public void setUseid(int useid) {
            this.useid = useid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public ArrayList<String> getPicture() {
            return picture;
        }

        public void setPicture(ArrayList<String> picture) {
            this.picture = picture;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public Object getBusiness_reply() {
            return business_reply;
        }

        public void setBusiness_reply(Object business_reply) {
            this.business_reply = business_reply;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }
}
