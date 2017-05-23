package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public class WaittingData {


    /**
     * info : success
     * code : 0
     * data : {"id":27,"pid":35,"sid":23,"is_vip":1,"pay_money":"30","createtime":1493003245,"usecode":null,"qrcode":null,"is_pay":1,"shop_name":"孙氏集团","lat":"24.47951","lng":"118.08948","address":"厦门","panic_info":{"price":"50","vip_price":"0","buying_price":"30","title":"抢抢抢","img":"ppqpc/nrihcB8wSZRmtrrKbSSawd.png","uphour":"0","consumption_deadline":"2017-04-30 10:40:39"},"clerk_list":[{"clerk_id":19,"name":"店长"},{"clerk_id":71,"name":"店员1"},{"clerk_id":98,"name":"店员2"},{"clerk_id":99,"name":"店员3"}]}
     * other : null
     */

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
        /**
         * id : 27
         * pid : 35
         * sid : 23
         * is_vip : 1
         * pay_money : 30
         * createtime : 1493003245
         * usecode : null
         * qrcode : null
         * is_pay : 1
         * shop_name : 孙氏集团
         * lat : 24.47951
         * lng : 118.08948
         * address : 厦门
         * panic_info : {"price":"50","vip_price":"0","buying_price":"30","title":"抢抢抢","img":"ppqpc/nrihcB8wSZRmtrrKbSSawd.png","uphour":"0","consumption_deadline":"2017-04-30 10:40:39"}
         * clerk_list : [{"clerk_id":19,"name":"店长"},{"clerk_id":71,"name":"店员1"},{"clerk_id":98,"name":"店员2"},{"clerk_id":99,"name":"店员3"}]
         */

        private int id;
        private int pid;
        private int sid;
        private int is_vip;
        private String pay_money;
        private int createtime;
        private Object usecode;
        private Object qrcode;
        private int is_pay;
        private String shop_name;
        private String lat;
        private String lng;
        private String address;
        private PanicInfoBean panic_info;
        private List<ClerkListBean> clerk_list;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public Object getUsecode() {
            return usecode;
        }

        public void setUsecode(Object usecode) {
            this.usecode = usecode;
        }

        public Object getQrcode() {
            return qrcode;
        }

        public void setQrcode(Object qrcode) {
            this.qrcode = qrcode;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public PanicInfoBean getPanic_info() {
            return panic_info;
        }

        public void setPanic_info(PanicInfoBean panic_info) {
            this.panic_info = panic_info;
        }

        public List<ClerkListBean> getClerk_list() {
            return clerk_list;
        }

        public void setClerk_list(List<ClerkListBean> clerk_list) {
            this.clerk_list = clerk_list;
        }

        public static class PanicInfoBean {
            /**
             * price : 50
             * vip_price : 0
             * buying_price : 30
             * title : 抢抢抢
             * img : ppqpc/nrihcB8wSZRmtrrKbSSawd.png
             * uphour : 0
             * consumption_deadline : 2017-04-30 10:40:39
             */

            private String price;
            private String vip_price;
            private String buying_price;
            private String title;
            private String img;
            private String uphour;
            private String consumption_deadline;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getBuying_price() {
                return buying_price;
            }

            public void setBuying_price(String buying_price) {
                this.buying_price = buying_price;
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

            public String getUphour() {
                return uphour;
            }

            public void setUphour(String uphour) {
                this.uphour = uphour;
            }

            public String getConsumption_deadline() {
                return consumption_deadline;
            }

            public void setConsumption_deadline(String consumption_deadline) {
                this.consumption_deadline = consumption_deadline;
            }
        }

        public static class ClerkListBean {
            /**
             * clerk_id : 19
             * name : 店长
             */

            private int clerk_id;
            private String name;

            public int getClerk_id() {
                return clerk_id;
            }

            public void setClerk_id(int clerk_id) {
                this.clerk_id = clerk_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
