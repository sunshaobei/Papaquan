package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 20:19.
 */

public class TakeAllBean {

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

        private double money;
        private double rebatemoney;

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getRebatemoney() {
            return rebatemoney;
        }

        public void setRebatemoney(double rebatemoney) {
            this.rebatemoney = rebatemoney;
        }
    }

    public static class DataBean {

        private int id;
        private int uid;
        private int sid;
        private String money;
        private String rebate;
        private String rebatemoney;
        private String clerkname;
        private String canceltime;
        private String nickname;

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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getRebatemoney() {
            return rebatemoney;
        }

        public void setRebatemoney(String rebatemoney) {
            this.rebatemoney = rebatemoney;
        }

        public String getClerkname() {
            return clerkname;
        }

        public void setClerkname(String clerkname) {
            this.clerkname = clerkname;
        }

        public String getCanceltime() {
            return canceltime;
        }

        public void setCanceltime(String canceltime) {
            this.canceltime = canceltime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}