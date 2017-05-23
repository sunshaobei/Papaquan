package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/11.
 */

public class ProductDetail {

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
        private int gold;
        private String price;
        private int num;
        private int exchange;
        private int browse;
        private int exchangeable;
        private String content;
        private int haschange;
        private String msg;
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

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getExchange() {
            return exchange;
        }

        public void setExchange(int exchange) {
            this.exchange = exchange;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getExchangeable() {
            return exchangeable;
        }

        public void setExchangeable(int exchangeable) {
            this.exchangeable = exchangeable;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getHaschange() {
            return haschange;
        }

        public void setHaschange(int haschange) {
            this.haschange = haschange;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<String> getLogo() {
            return logo;
        }

        public void setLogo(List<String> logo) {
            this.logo = logo;
        }
    }
}
