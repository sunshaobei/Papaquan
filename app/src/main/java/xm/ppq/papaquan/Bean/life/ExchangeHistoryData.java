package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class ExchangeHistoryData {

    /**
     * info : success
     * code : 0
     * data : [{"id":49,"gid":3,"time":"16分钟前","title":"123","paygold":1},{"id":48,"gid":4,"time":"21分钟前","title":"123","paygold":1}]
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
         * id : 49
         * gid : 3
         * time : 16分钟前
         * title : 123
         * paygold : 1
         */

        private int id;
        private int gid;
        private String time;
        private String title;
        private int paygold;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPaygold() {
            return paygold;
        }

        public void setPaygold(int paygold) {
            this.paygold = paygold;
        }

    }



}
