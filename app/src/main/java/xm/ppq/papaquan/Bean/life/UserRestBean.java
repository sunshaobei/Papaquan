package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EdgeDi on 18:40.
 */

public class UserRestBean {

    private String info;
    private int code;
    private DataBean data;
    private ArrayList<OtherBean> other;

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

    public ArrayList<OtherBean> getOther() {
        return other;
    }

    public void setOther(ArrayList<OtherBean> other) {
        this.other = other;
    }

    public static class DataBean {

        private String usecode;
        private double rebate;
        private String qrimg;

        public String getUsecode() {
            return usecode;
        }

        public void setUsecode(String usecode) {
            this.usecode = usecode;
        }

        public double getRebate() {
            return rebate;
        }

        public void setRebate(double rebate) {
            this.rebate = rebate;
        }

        public String getQrimg() {
            return qrimg;
        }

        public void setQrimg(String qrimg) {
            this.qrimg = qrimg;
        }
    }

    public static class OtherBean {

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