package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 15:32.
 */

public class HomePagerOtherBean {

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
        private RebateBean rebate;
        private List<PanicBean> panic;
        private List<CouBean> cou;

        public RebateBean getRebate() {
            return rebate;
        }

        public void setRebate(RebateBean rebate) {
            this.rebate = rebate;
        }

        public List<PanicBean> getPanic() {
            return panic;
        }

        public void setPanic(List<PanicBean> panic) {
            this.panic = panic;
        }

        public List<CouBean> getCou() {
            return cou;
        }

        public void setCou(List<CouBean> cou) {
            this.cou = cou;
        }

        public static class RebateBean {

            private String id;
            private String title;
            private String fivetype;
            private String usenum;
            private String rebate;
            private String discount;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFivetype() {
                return fivetype;
            }

            public void setFivetype(String fivetype) {
                this.fivetype = fivetype;
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

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }
        }

        public static class PanicBean {

            private int id;
            private String img;
            private String title;
            private int start_time;
            private int end_time;
            private int consumption_deadline;
            private String price;
            private String buying_price;
            private int browse;
            private String uphour;
            private int nowtime;

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

            public int getNowtime() {
                return nowtime;
            }

            public void setNowtime(int nowtime) {
                this.nowtime = nowtime;
            }
        }

        public static class CouBean {

            private int id;
            private String title;
            private String img;
            private String price;
            private String costprice;
            private int uphour;
            private int buynum;
            private int browse;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getCostprice() {
                return costprice;
            }

            public void setCostprice(String costprice) {
                this.costprice = costprice;
            }

            public int getUphour() {
                return uphour;
            }

            public void setUphour(int uphour) {
                this.uphour = uphour;
            }

            public int getBuynum() {
                return buynum;
            }

            public void setBuynum(int buynum) {
                this.buynum = buynum;
            }

            public int getBrowse() {
                return browse;
            }

            public void setBrowse(int browse) {
                this.browse = browse;
            }
        }
    }
}