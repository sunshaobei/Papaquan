package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by fcw on 2017/4/24.
 */

public class IntergalListBean {

    private String info;
    private int code;
    private OtherBean other;
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

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {

        private String gold;

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }
    }

    public static class DataBean {

        private int id;
        private String name;
        private String logo;
        private int gold;
        private int num;
        private int exchange;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
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
    }
}
