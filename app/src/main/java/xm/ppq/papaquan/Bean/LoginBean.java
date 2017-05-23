package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/8.
 */

public class LoginBean {

    /**
     * info : 登录成功
     * code : 0
     * data : {"token":"e822aa39b399fab26555fef0c6b1a149adee46e0","uid":27,"tel":"13515973596","exper":{"errorcode":2,"msg":"已达到上限"},"nickname":"iamyourdady","headurl":"http://ppqapp.oss-cn-hangzhou.aliyuncs.com/android/1490614597885.jpg"}
     * other : null
     */

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
        /**
         * token : e822aa39b399fab26555fef0c6b1a149adee46e0
         * uid : 27
         * tel : 13515973596
         * exper : {"errorcode":2,"msg":"已达到上限"}
         * nickname : iamyourdady
         * headurl : http://ppqapp.oss-cn-hangzhou.aliyuncs.com/android/1490614597885.jpg
         */

        private String token;
        private int uid;
        private String tel;
        private ExperBean exper;
        private String nickname;
        private String headurl;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public ExperBean getExper() {
            return exper;
        }

        public void setExper(ExperBean exper) {
            this.exper = exper;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public static class ExperBean {
            /**
             * errorcode : 2
             * msg : 已达到上限
             */

            private int errorcode;
            private String msg;

            public int getErrorcode() {
                return errorcode;
            }

            public void setErrorcode(int errorcode) {
                this.errorcode = errorcode;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }
}
