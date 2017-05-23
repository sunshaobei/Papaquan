package xm.ppq.papaquan.Bean;

/**
 * Created by sunshaobei on 2017/3/15.
 */

public class NewsBean {


    /**
     * info : success
     * code : 0
     * data : {"actcount":0,"messageCount":0,"rewardCount":0}
     * other : null
     */

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
        /**
         * actcount : 0
         * messageCount : 0
         * rewardCount : 0
         */

        private int actcount;
        private int messageCount;
        private int rewardCount;

        public int getActcount() {
            return actcount;
        }

        public void setActcount(int actcount) {
            this.actcount = actcount;
        }

        public int getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(int messageCount) {
            this.messageCount = messageCount;
        }

        public int getRewardCount() {
            return rewardCount;
        }

        public void setRewardCount(int rewardCount) {
            this.rewardCount = rewardCount;
        }
    }
}
