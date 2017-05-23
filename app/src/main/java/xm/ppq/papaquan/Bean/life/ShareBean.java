package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 14:23.
 */

public class ShareBean {

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

        private int citycode;
        private String dynamic;
        private String live;
        private String shop;
        private String panic_buying;
        private String discount;
        private String coupon;
        private String red;
        private String integral;
        private String publicname;
        private String shareurl;
        private String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getPublicname() {
            return publicname;
        }

        public void setPublicname(String publicname) {
            this.publicname = publicname;
        }

        public int getCitycode() {
            return citycode;
        }

        public void setCitycode(int citycode) {
            this.citycode = citycode;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public String getLive() {
            return live;
        }

        public void setLive(String live) {
            this.live = live;
        }

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public String getPanic_buying() {
            return panic_buying;
        }

        public void setPanic_buying(String panic_buying) {
            this.panic_buying = panic_buying;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
        }

        public String getRed() {
            return red;
        }

        public void setRed(String red) {
            this.red = red;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }
    }
}