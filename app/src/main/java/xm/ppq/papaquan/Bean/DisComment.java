package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public class DisComment {

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private int topic_uid;
        private int tid;
        private int com_uid;
        private String content;
        private Object img;
        private int likes;
        private int reply_num;
        private String time;
        private int status;
        private TopiclistBean topiclist;
        private SenduserBean senduser;
        private CommentuserBean commentuser;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTopic_uid() {
            return topic_uid;
        }

        public void setTopic_uid(int topic_uid) {
            this.topic_uid = topic_uid;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public int getCom_uid() {
            return com_uid;
        }

        public void setCom_uid(int com_uid) {
            this.com_uid = com_uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getReply_num() {
            return reply_num;
        }

        public void setReply_num(int reply_num) {
            this.reply_num = reply_num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public TopiclistBean getTopiclist() {
            return topiclist;
        }

        public void setTopiclist(TopiclistBean topiclist) {
            this.topiclist = topiclist;
        }

        public SenduserBean getSenduser() {
            return senduser;
        }

        public void setSenduser(SenduserBean senduser) {
            this.senduser = senduser;
        }

        public CommentuserBean getCommentuser() {
            return commentuser;
        }

        public void setCommentuser(CommentuserBean commentuser) {
            this.commentuser = commentuser;
        }

        public static class TopiclistBean {
            private String content;
            private String video;
            private String picture;
            private String createtime;

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

        public static class SenduserBean {

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

        public static class CommentuserBean {

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
