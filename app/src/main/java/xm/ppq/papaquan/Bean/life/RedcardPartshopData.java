package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class RedcardPartshopData {

    /**
     * info : success
     * code : 0
     * data : [{"sid":6,"name":"老袁拉面","logo":"ppqpc/pRTNeJjtBbesbx8Q54McTY.png"},{"sid":61,"name":"天天","logo":"ppqpc/YZ7FcR6BBWiAarJTW5rdcS.jpg"},{"sid":45,"name":"LOL咯","logo":"android/1492753120969.jpg"},{"sid":44,"name":"1","logo":"["},{"sid":21,"name":"对对对","logo":"ppqpc/f8QSFzpE2d2JDpFEA824jA.png"},{"sid":59,"name":"老王包子铺","logo":"ppqpc/EZKpPQcsTDXJN5ETwyCFPh.png"},{"sid":60,"name":"扫雷","logo":"ppqpc/c7EJ4BNRSGscpiyZeBBEyK.jpg"},{"sid":58,"name":"森林精灵","logo":"ppqpc/KJfGP3aPcb6rSdaZJfcXbm.jpg"},{"sid":43,"name":"镂空","logo":"["},{"sid":36,"name":"测试通过","logo":"iOSImage/dynamic/ppq201704201534353252938_00.jpg"},{"sid":50,"name":"嗯啊哦","logo":"ppqpc/3T8D5WYh45yXrPrS3NmmMx.jpg"},{"sid":23,"name":"孙氏集团","logo":"ppqpc/ppE2acdz3MtcwaKiDy5AiM.png"},{"sid":25,"name":"lin","logo":"ppqpc/pAxNkyZj3Rnayb5YD6YFDP.png"},{"sid":62,"name":"oooooo","logo":"ppqpc/HKmTNekJA3pZxesPhMTT2T.jpg"}]
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
         * sid : 6
         * name : 老袁拉面
         * logo : ppqpc/pRTNeJjtBbesbx8Q54McTY.png
         */

        private int sid;
        private String name;
        private String logo;

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
