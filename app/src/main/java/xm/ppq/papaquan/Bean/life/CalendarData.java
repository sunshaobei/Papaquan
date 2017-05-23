package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/6.
 */

public class CalendarData {

    /**
     * info : success
     * code : 0
     * data : [{"day":1,"sign":0},{"day":2,"sign":0},{"day":3,"sign":0},{"day":4,"sign":0},{"day":5,"sign":0},{"day":6,"sign":0},{"day":7,"sign":0},{"day":8,"sign":0},{"day":9,"sign":0},{"day":10,"sign":0},{"day":11,"sign":0},{"day":12,"sign":0},{"day":13,"sign":0},{"day":14,"sign":0},{"day":15,"sign":0},{"day":16,"sign":0},{"day":17,"sign":0},{"day":18,"sign":0},{"day":19,"sign":0},{"day":20,"sign":0},{"day":21,"sign":0},{"day":22,"sign":0},{"day":23,"sign":0},{"day":24,"sign":0},{"day":25,"sign":0},{"day":26,"sign":0},{"day":27,"sign":0},{"day":28,"sign":0},{"day":29,"sign":0},{"day":30,"sign":0}]
     * other : 6
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
         * day : 1
         * sign : 0
         */

        private int day;
        private int sign;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getSign() {
            return sign;
        }

        public void setSign(int sign) {
            this.sign = sign;
        }
    }
}
