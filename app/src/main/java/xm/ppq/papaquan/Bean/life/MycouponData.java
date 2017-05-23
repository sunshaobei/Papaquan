package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/3.
 */

public class MycouponData {

    /**
     * info : success
     * code : 0
     * data : [{"id":51,"cid":5,"sid":13,"specid":0,"num":1,"waituse":1,"title":"反反复复","img":null,"price":"12","uphour":"0"},{"id":49,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":48,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":44,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":43,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":42,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":41,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":40,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":39,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"},{"id":38,"cid":81,"sid":58,"specid":32,"num":1,"waituse":1,"spectitle":"a","specprice":"10","title":"打开的开发商","img":"ppqpc/3biRN86eT7E6TK3HfenHke.jpg","price":"","uphour":"0"}]
     * other : null
     */

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
        /**
         * id : 51
         * cid : 5
         * sid : 13
         * specid : 0
         * num : 1
         * waituse : 1
         * title : 反反复复
         * img : null
         * price : 12
         * uphour : 0
         * spectitle : a
         * specprice : 10
         */

        private int id;
        private int cid;
        private int sid;
        private int specid;
        private int num;
        private int waituse;
        private String title;
        private Object img;
        private String price;
        private String uphour;
        private String spectitle;
        private String specprice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getSpectitle() {
            return spectitle;
        }

        public void setSpectitle(String spectitle) {
            this.spectitle = spectitle;
        }

        public String getSpecprice() {
            return specprice;
        }

        public void setSpecprice(String specprice) {
            this.specprice = specprice;
        }
    }
}
