package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/27.
 */

public class Panic_buyUserData {

    /**
     * info : success
     * code : 0
     * data : [{"nickname":"彭**晏","paytime":"04-25 13:55:03","is_vip":1,"remark":"有了红卡就是要抢抢抢!!!"},{"nickname":"0**1","paytime":"04-25 14:24:15","is_vip":0,"remark":"还好赶上了\u2026"}]
     * other : null
     */

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
        /**
         * nickname : 彭**晏
         * paytime : 04-25 13:55:03
         * is_vip : 1
         * remark : 有了红卡就是要抢抢抢!!!
         */

        private String nickname;
        private String paytime;
        private int is_vip;
        private String remark;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
