package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 20:56.
 */

public class RecordListBean {

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

        private double countmoney;

        public double getCountmoney() {
            return countmoney;
        }

        public void setCountmoney(double countmoney) {
            this.countmoney = countmoney;
        }
    }

    public static class DataBean {

        private int cid;
        private int uid;
        private String money;
        private String clerk_name;
        private String title;
        private String nickname;
        private String createtime;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getClerk_name() {
            return clerk_name;
        }

        public void setClerk_name(String clerk_name) {
            this.clerk_name = clerk_name;
        }

        public String isTitle() {
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

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}