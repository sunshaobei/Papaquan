package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 14:05.
 */

public class MerChat_HomePageBean {

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
        private int browse;
        private int industry_two;
        private int show;
        private int check;
        private int starttime;
        private String endtime;
        private String business_hours;
        private String industry;
        private String content;
        private String consume;
        private int wifi;
        private int car;
        private int air;
        private int room;
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

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getIndustry_two() {
            return industry_two;
        }

        public void setIndustry_two(int industry_two) {
            this.industry_two = industry_two;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getCheck() {
            return check;
        }

        public void setCheck(int check) {
            this.check = check;
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

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getConsume() {
            return consume;
        }

        public void setConsume(String consume) {
            this.consume = consume;
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

        public List<String> getLogo() {
            return logo;
        }

        public void setLogo(List<String> logo) {
            this.logo = logo;
        }
    }
}