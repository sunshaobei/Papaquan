package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/9.
 */

public class SearchGoodsData1 {


    private String info;
    private int code;
    private String other;
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String title;
        private int sid;
        private int id;
        private String fiveday;
        private int fivetype;
        private int uphour;
        private int usenum;
        private String name;
        private String lng;
        private String lat;
        private String logo;
        private String discount;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFiveday() {
            return fiveday;
        }

        public void setFiveday(String fiveday) {
            this.fiveday = fiveday;
        }

        public int getFivetype() {
            return fivetype;
        }

        public void setFivetype(int fivetype) {
            this.fivetype = fivetype;
        }

        public int getUphour() {
            return uphour;
        }

        public void setUphour(int uphour) {
            this.uphour = uphour;
        }

        public int getUsenum() {
            return usenum;
        }

        public void setUsenum(int usenum) {
            this.usenum = usenum;
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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}
