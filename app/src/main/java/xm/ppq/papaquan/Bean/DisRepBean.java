package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by 评论和回复 on 2017/3/2.
 */

public class DisRepBean {

    private String info;
    private int code;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DisRepBean.DataBean> getData() {
        return data;
    }

    public void setData(List<DisRepBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int id;
        private int tid;
        private int cid;
        private int reply_uid;
        private int accept_uid;
        private String content;
        private String img;
        private int likes;
        private String time;
        private int pid;
        private int status;
        private DataBean.ReplydataBean replydata;
        private DataBean.TopicdataBean topicdata;
        private DataBean.TopicUserdataBean topicUserdata;
        private String mynickname;
        private String saycontent;
        private String tonickname;
        private String touid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getReply_uid() {
            return reply_uid;
        }

        public void setReply_uid(int reply_uid) {
            this.reply_uid = reply_uid;
        }

        public int getAccept_uid() {
            return accept_uid;
        }

        public void setAccept_uid(int accept_uid) {
            this.accept_uid = accept_uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public DataBean.ReplydataBean getReplydata() {
            return replydata;
        }

        public void setReplydata(DataBean.ReplydataBean replydata) {
            this.replydata = replydata;
        }

        public DataBean.TopicdataBean getTopicdata() {
            return topicdata;
        }

        public void setTopicdata(DataBean.TopicdataBean topicdata) {
            this.topicdata = topicdata;
        }

        public DataBean.TopicUserdataBean getTopicUserdata() {
            return topicUserdata;
        }

        public void setTopicUserdata(DataBean.TopicUserdataBean topicUserdata) {
            this.topicUserdata = topicUserdata;
        }

        public String getMynickname() {
            return mynickname;
        }

        public void setMynickname(String mynickname) {
            this.mynickname = mynickname;
        }

        public String getSaycontent() {
            return saycontent;
        }

        public void setSaycontent(String saycontent) {
            this.saycontent = saycontent;
        }

        public String getTonickname() {
            return tonickname;
        }

        public void setTonickname(String tonickname) {
            this.tonickname = tonickname;
        }

        public String getTouid() {
            return touid;
        }

        public void setTouid(String touid) {
            this.touid = touid;
        }

        public static class ReplydataBean {
            /**
             * uid : 21
             * nickname : 二一
             * headurl : http://img2.imgtn.bdimg.com/it/u=2894565662,2609023486&fm=23&gp=0.jpg
             */

            private String uid;
            private String nickname;
            private String headurl;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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

        public static class TopicdataBean {
            /**
             * uid : 1
             * content : sdfasdf
             * video :
             * picture :
             * createtime : 1970-01-01 08:00:00
             */

            private String uid;
            private String content;
            private String video;
            private String picture;
            private String createtime;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }

        public static class TopicUserdataBean {
            /**
             * uid : 1
             * nickname : no.1
             * headurl : http://www.wzfzl.cn/uploads/allimg/170221/09205aB2-1.jpg
             */

            private String uid;
            private String nickname;
            private String headurl;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
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
}
