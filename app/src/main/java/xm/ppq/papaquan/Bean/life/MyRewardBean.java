package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 17:16.
 */

public class MyRewardBean {

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

        private int one;
        private int two;
        private int oneMoney;
        private int twoMoney;

        public int getOne() {
            return one;
        }

        public void setOne(int one) {
            this.one = one;
        }

        public int getTwo() {
            return two;
        }

        public void setTwo(int two) {
            this.two = two;
        }

        public int getOneMoney() {
            return oneMoney;
        }

        public void setOneMoney(int oneMoney) {
            this.oneMoney = oneMoney;
        }

        public int getTwoMoney() {
            return twoMoney;
        }

        public void setTwoMoney(int twoMoney) {
            this.twoMoney = twoMoney;
        }
    }

    public static class DataBean {

        private int cuid;
        private String money;
        private String time;
        private int level;
        private String nickname;

        public int getCuid() {
            return cuid;
        }

        public void setCuid(int cuid) {
            this.cuid = cuid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}