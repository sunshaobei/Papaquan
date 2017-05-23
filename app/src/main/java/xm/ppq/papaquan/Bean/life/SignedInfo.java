package xm.ppq.papaquan.Bean.life;

/**
 * Created by sunshaobei on 2017/5/5.
 */

public class SignedInfo {

    /**
     * info : success
     * code : 0
     * data : {"isSign":1,"totalSign":1,"continueSign":1,"firstSign":{"uid":"29","nickname":"gajhjd","focus":0}}
     * other : null
     */

    private String info;
    private int code;
    private DataBean data;
    private String other;
    private int issigned;

    public int getIssigned() {
        return issigned;
    }

    public void setIssigned(int issigned) {
        this.issigned = issigned;
    }

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
        /**
         * isSign : 1
         * totalSign : 1
         * continueSign : 1
         * firstSign : {"uid":"29","nickname":"gajhjd","focus":0}
         */

        private int isSign;
        private int totalSign;
        private int continueSign;
        private FirstSignBean firstSign;

        public int getIsSign() {
            return isSign;
        }

        public void setIsSign(int isSign) {
            this.isSign = isSign;
        }

        public int getTotalSign() {
            return totalSign;
        }

        public void setTotalSign(int totalSign) {
            this.totalSign = totalSign;
        }

        public int getContinueSign() {
            return continueSign;
        }

        public void setContinueSign(int continueSign) {
            this.continueSign = continueSign;
        }

        public FirstSignBean getFirstSign() {
            return firstSign;
        }

        public void setFirstSign(FirstSignBean firstSign) {
            this.firstSign = firstSign;
        }

        public static class FirstSignBean {
            /**
             * uid : 29
             * nickname : gajhjd
             * focus : 0
             */

            private String uid;
            private String nickname;
            private int focus;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getFocus() {
                return focus;
            }

            public void setFocus(int focus) {
                this.focus = focus;
            }
        }
    }
}
