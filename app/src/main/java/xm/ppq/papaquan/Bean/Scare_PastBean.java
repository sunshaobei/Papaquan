package xm.ppq.papaquan.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/17.
 */

public class Scare_PastBean {

    private String info;
    private int code;
    private ArrayList<Data> data;
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

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public ArrayList<Other> getOther() {
        return other;
    }

    public void setOther(ArrayList<Other> other) {
        this.other = other;
    }

    public class Other {

        private int id;
        private String img;
        private String title;
        private String link;
        private int link_type;
        private String note;
        private String citycode;

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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getLink_type() {
            return link_type;
        }

        public void setLink_type(int link_type) {
            this.link_type = link_type;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }
    }

    public class Data {

        private int id;
        private int sid;
        private String img;
        private String title;
        private int start_time;
        private int end_time;
        private int consumption_deadline;
        private String price;
        private String buying_price;
        private String vip_price;
        private int browse;
        private String uphour;
        private int citycode;
        private int display;
        private String shopname;
        private int status;
        private int nowtime;

        public int getNowtime() {
            return nowtime;
        }

        public void setNowtime(int nowtime) {
            this.nowtime = nowtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
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

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getConsumption_deadline() {
            return consumption_deadline;
        }

        public void setConsumption_deadline(int consumption_deadline) {
            this.consumption_deadline = consumption_deadline;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBuying_price() {
            return buying_price;
        }

        public void setBuying_price(String buying_price) {
            this.buying_price = buying_price;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public int getCitycode() {
            return citycode;
        }

        public void setCitycode(int citycode) {
            this.citycode = citycode;
        }

        public int getDisplay() {
            return display;
        }

        public void setDisplay(int display) {
            this.display = display;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}