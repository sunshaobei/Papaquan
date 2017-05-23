package xm.ppq.papaquan.Bean.life;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public class PicNTextDetailData {

    /**
     * info : success
     * code : 0
     * data : {"picdetails":"<p>桑德环境<img src=\"http://ppqapp.oss-cn-hangzhou.aliyuncs.com/ppqpc/1493209262352.png\" title=\"ppqpc/1493209262352.png\" alt=\"2-关注栏-1.png\"/><\/p>"}
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
         * picdetails : <p>桑德环境<img src="http://ppqapp.oss-cn-hangzhou.aliyuncs.com/ppqpc/1493209262352.png" title="ppqpc/1493209262352.png" alt="2-关注栏-1.png"/></p>
         */

        private String picdetails;

        public String getPicdetails() {
            return picdetails;
        }

        public void setPicdetails(String picdetails) {
            this.picdetails = picdetails;
        }
    }
}
