package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 20:22.
 */

public class PaincPayBean {

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
        private int pid;
        private int sid;
        private int is_vip;
        private String pay_money;
        private int createtime;
        private Object usecode;
        private Object qrcode;
        private int is_pay;
        private String shop_name;
        private PanicInfoBean panic_info;
        private int nowtime;
        private String mymoney;

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

        public PanicInfoBean getPanic_info() {
            return panic_info;
        }

        public void setPanic_info(PanicInfoBean panic_info) {
            this.panic_info = panic_info;
        }

        public int getNowtime() {
            return nowtime;
        }

        public void setNowtime(int nowtime) {
            this.nowtime = nowtime;
        }

        public String getMymoney() {
            return mymoney;
        }

        public void setMymoney(String mymoney) {
            this.mymoney = mymoney;
        }

        public static class PanicInfoBean {

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
    }
}