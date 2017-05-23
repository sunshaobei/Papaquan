package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 11:59.
 */

public class QuickAuditBean {

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

        private String name;
        private String logo;
        private int starttime;
        private String endtime;
        private int check;
        private int show;
        private int nowtime;
        private String oneyear;
        private String twoyear;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getStarttime() {
            return starttime;
        }

        public void setStarttime(int starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getCheck() {
            return check;
        }

        public void setCheck(int check) {
            this.check = check;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getNowtime() {
            return nowtime;
        }

        public void setNowtime(int nowtime) {
            this.nowtime = nowtime;
        }

        public String getOneyear() {
            return oneyear;
        }

        public void setOneyear(String oneyear) {
            this.oneyear = oneyear;
        }

        public String getTwoyear() {
            return twoyear;
        }

        public void setTwoyear(String twoyear) {
            this.twoyear = twoyear;
        }
    }
}