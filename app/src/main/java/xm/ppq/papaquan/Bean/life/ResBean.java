package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 10:20.
 */

public class ResBean {

    private int code;
    private Data data;
    private String info;
    private Object other;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public static class Data {

        private int use;

        public int getUse() {
            return use;
        }

        public void setUse(int use) {
            this.use = use;
        }
    }

}