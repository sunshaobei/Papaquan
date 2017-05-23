package xm.ppq.papaquan.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class PapaTopicBean {

    /**
     * info : success
     * code : 0
     * data : [{"id":"66","title":"#LOL2017周末庆#","heat":"3","joinnum":"38","img":"","createtime":"1489402978"},{"id":"79","title":"#让红包飞#","heat":"1","joinnum":"1","img":"","createtime":"1489405380"},{"id":"78","title":"#走起来#","heat":"1","joinnum":"1","img":"","createtime":"1489405126"},{"id":"72","title":"##","heat":"3","joinnum":"0","img":"","createtime":"1489403299"},{"id":"71","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489403166"},{"id":"70","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489402998"},{"id":"69","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489402998"},{"id":"68","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489402998"},{"id":"67","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489402998"},{"id":"64","title":"","heat":"3","joinnum":"0","img":"","createtime":"1489401643"}]
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

    public static class DataBean implements Serializable {

        /**
         * id : 66
         * title : #LOL2017周末庆#
         * heat : 3
         * joinnum : 38
         * img :
         * createtime : 1489402978
         */

        private String top;
        private String id;
        private String title;
        private String heat;
        private String joinnum;
        private String img;
        private String createtime;

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

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

        public String getHeat() {
            return heat;
        }

        public void setHeat(String heat) {
            this.heat = heat;
        }

        public String getJoinnum() {
            return joinnum;
        }

        public void setJoinnum(String joinnum) {
            this.joinnum = joinnum;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
    }
}
