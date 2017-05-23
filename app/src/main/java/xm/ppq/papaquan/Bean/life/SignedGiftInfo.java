package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/5.
 */

public class SignedGiftInfo {

    /**
     * info : success
     * code : 0
     * data : [{"id":1,"logo":"android/1490669237931.jpg","type":1,"day":7,"prizetype":1,"num":10,"isget":0,"signday":1},{"id":2,"logo":"android/1490711559560.jpg","type":1,"day":15,"prizetype":2,"num":7,"isget":0,"signday":1},{"id":3,"logo":"android/1490670006665.jpg","type":2,"day":40,"prizetype":1,"num":50,"isget":0,"signday":1},{"id":4,"logo":"android/1490688562926.jpg","type":2,"day":80,"prizetype":2,"num":15,"isget":0,"signday":1}]
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
         * id : 1
         * logo : android/1490669237931.jpg
         * type : 1
         * day : 7
         * prizetype : 1
         * num : 10
         * isget : 0
         * signday : 1
         */

        private int id;
        private String logo;
        private int type;
        private int day;
        private int prizetype;
        private int num;
        private int isget;
        private int signday;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getPrizetype() {
            return prizetype;
        }

        public void setPrizetype(int prizetype) {
            this.prizetype = prizetype;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getIsget() {
            return isget;
        }

        public void setIsget(int isget) {
            this.isget = isget;
        }

        public int getSignday() {
            return signday;
        }

        public void setSignday(int signday) {
            this.signday = signday;
        }
    }
}
