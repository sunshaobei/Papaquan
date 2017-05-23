package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by fcw on 2017/4/24.
 */

public class IntergalHeadBean {


    /**
     * info : success
     * code : 0
     * data : [{"id":100,"img":"ppqpc/wB7FnpdrsEMnAZyDQrAF3x.jpg","link":"http://www.iqiyi.com/v_19rrm981e8.html?vfm=2008_aldbd","link_type":0,"link_val":1,"singleid":0},{"id":101,"img":"ppqpc/RDGXrQYDHFP3zWXiByC7hn.jpeg","link":null,"link_type":0,"link_val":4,"singleid":0}]
     * other : {"gold":"76","exchangeNum":4}
     */

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
        /**
         * gold : 76
         * exchangeNum : 4
         */

        private String gold;
        private int exchangeNum;

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public int getExchangeNum() {
            return exchangeNum;
        }

        public void setExchangeNum(int exchangeNum) {
            this.exchangeNum = exchangeNum;
        }
    }

    public static class DataBean {
        /**
         * id : 100
         * img : ppqpc/wB7FnpdrsEMnAZyDQrAF3x.jpg
         * link : http://www.iqiyi.com/v_19rrm981e8.html?vfm=2008_aldbd
         * link_type : 0
         * link_val : 1
         * singleid : 0
         */

        private int id;
        private String img;
        private String link;
        private int link_type;
        private int link_val;
        private int singleid;

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
    }
}
