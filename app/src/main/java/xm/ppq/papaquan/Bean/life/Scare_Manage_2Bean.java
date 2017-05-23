package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 20:55.
 */

public class Scare_Manage_2Bean {

    private String info;
    private int code;
    private int other;
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

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
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
        private String img;
        private String title;
        private String price;
        private String costprice;
        private int uphour;
        private String stime;
        private String etime;
        private int buynum;
        private int browse;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCostprice() {
            return costprice;
        }

        public void setCostprice(String costprice) {
            this.costprice = costprice;
        }

        public int getUphour() {
            return uphour;
        }

        public void setUphour(int uphour) {
            this.uphour = uphour;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public int getBuynum() {
            return buynum;
        }

        public void setBuynum(int buynum) {
            this.buynum = buynum;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}