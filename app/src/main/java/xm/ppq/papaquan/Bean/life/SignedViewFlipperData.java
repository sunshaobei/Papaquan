package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/5.
 */

public class SignedViewFlipperData {


    /**
     * info : success
     * code : 0
     * data : [{"uid":41,"nickname":"上官辉","totalSign":2},{"uid":27,"nickname":"iamyourdady","totalSign":1},{"uid":30,"nickname":"000000","totalSign":3},{"uid":25,"nickname":"0000001","totalSign":6},{"uid":28,"nickname":"皮皮虾","totalSign":6},{"uid":29,"nickname":"gajhjd","totalSign":6},{"uid":30,"nickname":"000000","totalSign":3},{"uid":32,"nickname":"小时候很调皮","totalSign":5},{"uid":25,"nickname":"0000001","totalSign":6},{"uid":29,"nickname":"gajhjd","totalSign":6}]
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
         * uid : 41
         * nickname : 上官辉
         * totalSign : 2
         */

        private int uid;
        private String nickname;
        private int totalSign;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getTotalSign() {
            return totalSign;
        }

        public void setTotalSign(int totalSign) {
            this.totalSign = totalSign;
        }
    }
}
