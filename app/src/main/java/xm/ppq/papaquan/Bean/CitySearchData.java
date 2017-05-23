package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/10.
 */

public class CitySearchData {


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
        private int code;
        private String name;
        private String fullname;
        private int pcode;
        private int pointcode;
        private int children;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getPcode() {
            return pcode;
        }

        public void setPcode(int pcode) {
            this.pcode = pcode;
        }

        public int getPointcode() {
            return pointcode;
        }

        public void setPointcode(int pointcode) {
            this.pointcode = pointcode;
        }

        public int getChildren() {
            return children;
        }

        public void setChildren(int children) {
            this.children = children;
        }
    }
}
