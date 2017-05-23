package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 11:32.
 */

public class RestaurantBean {

    private String info;
    private int code;
    private DataBean data;
    private ArrayList<Other> other;

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

    public ArrayList<Other> getOther() {
        return other;
    }

    public void setOther(ArrayList<Other> other) {
        this.other = other;
    }

    public static class Other {

        private int uid;
        private String content;
        private ArrayList<String> picture;
        private long createtime;
        private String nickname;
        private String headurl;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public ArrayList<String> getPicture() {
            return picture;
        }

        public void setPicture(ArrayList<String> picture) {
            this.picture = picture;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }

    public static class DataBean {

        private String logo;
        private String title;
        private String browse;
        private String usenum;
        private String rebate;
        private String appnum;
        private String name;
        private String lng;
        private String lat;
        private String address;
        private String tel;
        private String business_hours;
        private String content;
        private int wifi;
        private int air;
        private int car;
        private int room;
        private int isfiveday;
        private long vip_end;
        private String headurl;
        private String cardnum;
        private String use;
        private int status;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getUse() {
            return use;
        }

        public void setUse(String use) {
            this.use = use;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrowse() {
            return browse;
        }

        public void setBrowse(String browse) {
            this.browse = browse;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getAppnum() {
            return appnum;
        }

        public void setAppnum(String appnum) {
            this.appnum = appnum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getWifi() {
            return wifi;
        }

        public void setWifi(int wifi) {
            this.wifi = wifi;
        }

        public int getAir() {
            return air;
        }

        public void setAir(int air) {
            this.air = air;
        }

        public int getCar() {
            return car;
        }

        public void setCar(int car) {
            this.car = car;
        }

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }

        public int getIsfiveday() {
            return isfiveday;
        }

        public void setIsfiveday(int isfiveday) {
            this.isfiveday = isfiveday;
        }

        public long getVip_end() {
            return vip_end;
        }

        public void setVip_end(long vip_end) {
            this.vip_end = vip_end;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }
}