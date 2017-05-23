package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 9:54.
 */

public class ChooseBean {

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
        private String name;
        private String logo;
        private String lng;
        private String lat;
        private int coupon_sales;
        private ArrayList<CouponBean> coupon;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
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

        public int getCoupon_sales() {
            return coupon_sales;
        }

        public void setCoupon_sales(int coupon_sales) {
            this.coupon_sales = coupon_sales;
        }

        public ArrayList<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(ArrayList<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public static class CouponBean {

            private String title;
            private String uphour;
            private String price;
            private String costprice;
            private int cid;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUphour() {
                return uphour;
            }

            public void setUphour(String uphour) {
                this.uphour = uphour;
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

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }
        }
    }
}