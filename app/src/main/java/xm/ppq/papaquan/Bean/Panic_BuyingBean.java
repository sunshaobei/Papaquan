package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class Panic_BuyingBean {

    private String info;
    private int code;
    private DataBean data;
    private Object other;

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

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public static class DataBean {

        private String id;
        private String sid;
        private String title;
        private String quantity;
        private String unit;
        private String receiving_quantity;
        private String paynum;
        private String usenum;
        private long start_time;
        private long end_time;
        private long consumption_deadline;
        private String content;
        private String price;
        private String buying_price;
        private String vip_price;
        private String browse;
        private String uphour;
        private String pack;
        private String appraise;
        private String rule;
        private String citycode;
        private int nowtime;
        private int is_vip;
        private int status;
        private int orderid;
        private ShopInfoBean shop_info;
        private String stock;
        private List<String> img;
        private List<CouponListBean> coupon_list;
        private KFInfo kfInfo;

        public String getPaynum() {
            return paynum;
        }

        public KFInfo getKfInfo() {
            return kfInfo;
        }

        public void setKfInfo(KFInfo kfInfo) {
            this.kfInfo = kfInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getReceiving_quantity() {
            return receiving_quantity;
        }

        public void setReceiving_quantity(String receiving_quantity) {
            this.receiving_quantity = receiving_quantity;
        }

        public String isPaynum() {
            return paynum;
        }

        public void setPaynum(String paynum) {
            this.paynum = paynum;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public long getStart_time() {
            return start_time;
        }

        public void setStart_time(long start_time) {
            this.start_time = start_time;
        }

        public long getEnd_time() {
            return end_time;
        }

        public void setEnd_time(long end_time) {
            this.end_time = end_time;
        }

        public long getConsumption_deadline() {
            return consumption_deadline;
        }

        public void setConsumption_deadline(long consumption_deadline) {
            this.consumption_deadline = consumption_deadline;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getBrowse() {
            return browse;
        }

        public void setBrowse(String browse) {
            this.browse = browse;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getPack() {
            return pack;
        }

        public void setPack(String pack) {
            this.pack = pack;
        }

        public String getAppraise() {
            return appraise;
        }

        public void setAppraise(String appraise) {
            this.appraise = appraise;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public int getNowtime() {
            return nowtime;
        }

        public void setNowtime(int nowtime) {
            this.nowtime = nowtime;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class ShopInfoBean {
            /**
             * name : 老许馒头
             * address : 厦门集美中铁家园
             * lat : 24.557784
             * lng : 118.040623
             * tel : 34234324
             */

            private String name;
            private String address;
            private double lat;
            private double lng;
            private String tel;

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

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }
        }

        public static class CouponListBean {
            /**
             * img : ppqpc/yTxTh7sdjXXFfJWCYwDjQP.png
             * title : 测试
             * price : 0.1
             * costprice : 1
             * cid : 78
             */

            private String img;
            private String title;
            private String price;
            private String costprice;
            private int cid;

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

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }
        }

        public static class KFInfo {
            private String kuid;
            private String kfcode;
            private String phone;

            public String getKuid() {
                return kuid;
            }

            public void setKuid(String kuid) {
                this.kuid = kuid;
            }

            public String getKfcode() {
                return kfcode;
            }

            public void setKfcode(String kfcode) {
                this.kfcode = kfcode;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}