package xm.ppq.papaquan.Bean;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 10:44.
 */

public class DiscountBean {

    private String info;
    private int code;
    private String other;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int sid;
        private String uphour;
        private String usenum;
        private String title;
        private String name;
        private String logo;
        private String lng;
        private String lat;
        private String discount;
        private double km;

        public double getKm() {
            return km;
        }

        public void setKm(double km) {
            this.km = km;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

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

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}