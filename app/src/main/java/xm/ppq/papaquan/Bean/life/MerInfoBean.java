package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 15:36.
 */

public class MerInfoBean {

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
        private String name;
        private String address;
        private String tel;
        private String lat;
        private String lng;
        private int shoptype;
        private int area;
        private int industry;
        private int industry_two;
        private int citycode;
        private String business_hours;
        private int wifi;
        private int car;
        private int air;
        private int room;
        private String consume;
        private String business_license;
        private Object overtime;
        private Object registration;
        private Object shopname;
        private String business_certificate;
        private String linkname;
        private String linkphone;
        private List<String> logo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public int getShoptype() {
            return shoptype;
        }

        public void setShoptype(int shoptype) {
            this.shoptype = shoptype;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getIndustry() {
            return industry;
        }

        public void setIndustry(int industry) {
            this.industry = industry;
        }

        public int getIndustry_two() {
            return industry_two;
        }

        public void setIndustry_two(int industry_two) {
            this.industry_two = industry_two;
        }

        public int getCitycode() {
            return citycode;
        }

        public void setCitycode(int citycode) {
            this.citycode = citycode;
        }

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public int getWifi() {
            return wifi;
        }

        public void setWifi(int wifi) {
            this.wifi = wifi;
        }

        public int getCar() {
            return car;
        }

        public void setCar(int car) {
            this.car = car;
        }

        public int getAir() {
            return air;
        }

        public void setAir(int air) {
            this.air = air;
        }

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }

        public String getConsume() {
            return consume;
        }

        public void setConsume(String consume) {
            this.consume = consume;
        }

        public String getBusiness_license() {
            return business_license;
        }

        public void setBusiness_license(String business_license) {
            this.business_license = business_license;
        }

        public Object getOvertime() {
            return overtime;
        }

        public void setOvertime(Object overtime) {
            this.overtime = overtime;
        }

        public Object getRegistration() {
            return registration;
        }

        public void setRegistration(Object registration) {
            this.registration = registration;
        }

        public Object getShopname() {
            return shopname;
        }

        public void setShopname(Object shopname) {
            this.shopname = shopname;
        }

        public String getBusiness_certificate() {
            return business_certificate;
        }

        public void setBusiness_certificate(String business_certificate) {
            this.business_certificate = business_certificate;
        }

        public String getLinkname() {
            return linkname;
        }

        public void setLinkname(String linkname) {
            this.linkname = linkname;
        }

        public String getLinkphone() {
            return linkphone;
        }

        public void setLinkphone(String linkphone) {
            this.linkphone = linkphone;
        }

        public List<String> getLogo() {
            return logo;
        }

        public void setLogo(List<String> logo) {
            this.logo = logo;
        }
    }
}