package xm.ppq.papaquan.Bean.life;

/**
 * Created by EdgeDi on 17:02.
 */

public class EvenRewardBean {

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

        private int sell;
        private int ofirst;
        private int onext;
        private int tfirst;
        private int tnext;
        private String myinvitation;

        public int getSell() {
            return sell;
        }

        public void setSell(int sell) {
            this.sell = sell;
        }

        public int getOfirst() {
            return ofirst;
        }

        public void setOfirst(int ofirst) {
            this.ofirst = ofirst;
        }

        public int getOnext() {
            return onext;
        }

        public void setOnext(int onext) {
            this.onext = onext;
        }

        public int getTfirst() {
            return tfirst;
        }

        public void setTfirst(int tfirst) {
            this.tfirst = tfirst;
        }

        public int getTnext() {
            return tnext;
        }

        public void setTnext(int tnext) {
            this.tnext = tnext;
        }

        public String getMyinvitation() {
            return myinvitation;
        }

        public void setMyinvitation(String myinvitation) {
            this.myinvitation = myinvitation;
        }
    }
}