package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 16:52.
 */

public class JudgePanicBuyBean {

    private String info;
    private int code;
    private DataBean data;
    private String other;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public static class DataBean {

        private int allcount;
        private int piccount;
        private List<ListBean> list;

        public int getAllcount() {
            return allcount;
        }

        public void setAllcount(int allcount) {
            this.allcount = allcount;
        }

        public int getPiccount() {
            return piccount;
        }

        public void setPiccount(int piccount) {
            this.piccount = piccount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private int id;
            private int uid;
            private int sid;
            private int type;
            private int useid;
            private int typeid;
            private String content;
            private String createtime;
            private Object business_reply;
            private String title;
            private String nickname;
            private String headurl;
            private List<String> picture;

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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
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

            public List<String> getPicture() {
                return picture;
            }

            public void setPicture(List<String> picture) {
                this.picture = picture;
            }
        }
    }
}
