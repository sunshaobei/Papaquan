package xm.ppq.papaquan.Bean;

/**
 * Created by sunshaobei on 2017/5/9.
 */

public class ShopIntrodutionData {

    /**
     * info : success
     * code : 0
     * data : {"content":"74634534534sdfg<i>sdfasdfasd<b>sdfasdfasdfasd<\/b><\/i>"}
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
         * content : 74634534534sdfg<i>sdfasdfasd<b>sdfasdfasdfasd</b></i>
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
