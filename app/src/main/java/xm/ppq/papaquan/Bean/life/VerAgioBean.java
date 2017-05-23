package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by EdgeDi on 19:48.
 */

public class VerAgioBean {

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

        private List<AdminBean> admin;
        private List<RebateBean> rebate;

        public List<AdminBean> getAdmin() {
            return admin;
        }

        public void setAdmin(List<AdminBean> admin) {
            this.admin = admin;
        }

        public List<RebateBean> getRebate() {
            return rebate;
        }

        public void setRebate(List<RebateBean> rebate) {
            this.rebate = rebate;
        }

        public static class AdminBean {

            private int id;
            private String name;

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
        }

        public static class RebateBean {

            private int value;
            private String name;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
