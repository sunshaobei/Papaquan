package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 15:25.
 */

public class Order_PayBean {

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

    public static class DataBean {

        private int cid;
        private int sid;
        private int specid;
        private String money;
        private int num;
        private int waituse;
        private Object usecode;
        private Object qrcode;
        private int status;
        private String createtime;
        private int ordertime;
        private int nowtime;
        private int canbuy;
        private String spectitle;
        private String price;
        private String costprice;
        private String mymoney;
        private String img;
        private String title;
        private String uphour;
        private String name;
        private String lat;
        private String lng;
        private String endtime;

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getSpecid() {
            return specid;
        }

        public void setSpecid(int specid) {
            this.specid = specid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getWaituse() {
            return waituse;
        }

        public void setWaituse(int waituse) {
            this.waituse = waituse;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrdertime() {
            return ordertime;
        }

        public void setOrdertime(int ordertime) {
            this.ordertime = ordertime;
        }

        public int getNowtime() {
            return nowtime;
        }

        public void setNowtime(int nowtime) {
            this.nowtime = nowtime;
        }

        public int getCanbuy() {
            return canbuy;
        }

        public void setCanbuy(int canbuy) {
            this.canbuy = canbuy;
        }

        public String getSpectitle() {
            return spectitle;
        }

        public void setSpectitle(String spectitle) {
            this.spectitle = spectitle;
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

        public String getMymoney() {
            return mymoney;
        }

        public void setMymoney(String mymoney) {
            this.mymoney = mymoney;
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

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }

    public static class Other {

        private int uid;
        private String name;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
