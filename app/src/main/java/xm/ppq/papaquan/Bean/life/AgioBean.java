package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 20:02.
 */

public class AgioBean {

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

        private int id;
        private String title;
        private int fivetype;
        private int usenum;
        private String allmoney;
        private String rebatemoney;
        private String rebate;
        private String discount;
        private List<String> fiveday;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getFivetype() {
            return fivetype;
        }

        public void setFivetype(int fivetype) {
            this.fivetype = fivetype;
        }

        public int getUsenum() {
            return usenum;
        }

        public void setUsenum(int usenum) {
            this.usenum = usenum;
        }

        public String getAllmoney() {
            return allmoney;
        }

        public void setAllmoney(String allmoney) {
            this.allmoney = allmoney;
        }

        public String getRebatemoney() {
            return rebatemoney;
        }

        public void setRebatemoney(String rebatemoney) {
            this.rebatemoney = rebatemoney;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public List<String> getFiveday() {
            return fiveday;
        }

        public void setFiveday(List<String> fiveday) {
            this.fiveday = fiveday;
        }
    }
}
