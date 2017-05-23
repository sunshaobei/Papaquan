package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/25.
 */

public class LifeHomeData {

    private String info;
    private int code;
    private DataBean data;
    private Other other;

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

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    public static class Other {
        private int message;

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }
    }

    public static class DataBean {

        private TodaypanicBean todaypanic;
        private List<BannerBean> banner;
        private List<NavigationIconBean> navigation_icon;
        private List<NewShopBean> new_shop;
        private List<MiddleIconBean> middle_icon;

        public TodaypanicBean getTodaypanic() {
            return todaypanic;
        }

        public void setTodaypanic(TodaypanicBean todaypanic) {
            this.todaypanic = todaypanic;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<NavigationIconBean> getNavigation_icon() {
            return navigation_icon;
        }

        public void setNavigation_icon(List<NavigationIconBean> navigation_icon) {
            this.navigation_icon = navigation_icon;
        }

        public List<NewShopBean> getNew_shop() {
            return new_shop;
        }

        public void setNew_shop(List<NewShopBean> new_shop) {
            this.new_shop = new_shop;
        }

        public List<MiddleIconBean> getMiddle_icon() {
            return middle_icon;
        }

        public void setMiddle_icon(List<MiddleIconBean> middle_icon) {
            this.middle_icon = middle_icon;
        }

        public static class TodaypanicBean {

            private int pid;
            private String title;
            private String img;
            private String price;
            private String buying_price;
            private int end_time;
            private String discount;
            private int nowtime;

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
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

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public int getNowtime() {
                return nowtime;
            }

            public void setNowtime(int nowtime) {
                this.nowtime = nowtime;
            }
        }

        public static class BannerBean {

            private int id;
            private String img;
            private String title;
            private String link;
            private int link_type;
            private int link_val;
            private int singleid;
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

            public int getLink_val() {
                return link_val;
            }

            public void setLink_val(int link_val) {
                this.link_val = link_val;
            }

            public int getSingleid() {
                return singleid;
            }

            public void setSingleid(int singleid) {
                this.singleid = singleid;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }
        }

        public static class NavigationIconBean {

            private String img;
            private String title;
            private String link_type;
            private String link_val;
            private String link_name;
            private String link;
            private String singleid;

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

            public String getLink_type() {
                return link_type;
            }

            public void setLink_type(String link_type) {
                this.link_type = link_type;
            }

            public String getLink_val() {
                return link_val;
            }

            public void setLink_val(String link_val) {
                this.link_val = link_val;
            }

            public String getLink_name() {
                return link_name;
            }

            public void setLink_name(String link_name) {
                this.link_name = link_name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getSingleid() {
                return singleid;
            }

            public void setSingleid(String singleid) {
                this.singleid = singleid;
            }
        }

        public static class NewShopBean {

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class MiddleIconBean {

            private String img;
            private String title;
            private String stitle;
            private int link_type;
            private int link_val;
            private String link_name;
            private String link;
            private int singleid;

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

            public String getStitle() {
                return stitle;
            }

            public void setStitle(String stitle) {
                this.stitle = stitle;
            }

            public int getLink_type() {
                return link_type;
            }

            public void setLink_type(int link_type) {
                this.link_type = link_type;
            }

            public int getLink_val() {
                return link_val;
            }

            public void setLink_val(int link_val) {
                this.link_val = link_val;
            }

            public String getLink_name() {
                return link_name;
            }

            public void setLink_name(String link_name) {
                this.link_name = link_name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getSingleid() {
                return singleid;
            }

            public void setSingleid(int singleid) {
                this.singleid = singleid;
            }
        }
    }
}