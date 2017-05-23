package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class RedCardFiveBean {

    /**
     * info : success
     * code : 0
     * data : [{"sid":3,"desc":"今天5折","uphour":"0","usenum":"0","title":"aaa","name":"老许馒头","logo":"ppqpc/iaZhb6YfepjA4yRkkYYeFx.png","lng":"118.040623","lat":"24.557784"},{"sid":64,"desc":"今天6.6折","uphour":"0","usenum":"0","title":"大打折扣","name":"核销密码123456","logo":"iOS/img/ppq201705021617101728804_00.jpg","lng":"118.093124","lat":"24.573887"},{"sid":23,"desc":"今天6.9折","uphour":"0","usenum":"0","title":"大优惠","name":"孙氏集团","logo":"ppqpc/ppE2acdz3MtcwaKiDy5AiM.png","lng":"117.540623","lat":"24.479514"},{"sid":62,"desc":"今天8折","uphour":"0","usenum":"0","title":"eeee","name":false,"logo":null,"lng":"118.08948","lat":"24.47951"},{"sid":70,"desc":"今天8折","uphour":"0","usenum":"0","title":"二滩","name":"小新理发","logo":"ppqpc/hA7nmmBrpSAHb6mrmmCsFZ.jpg","lng":"118.09313","lat":"24.57388"},{"sid":67,"desc":"今天8折","uphour":"0","usenum":"0","title":"aaaaaa","name":"小新咖啡","logo":"ppqpc/BNA7zRSYwbKaRFcRip8c6h.jpg","lng":"118.09313","lat":"24.57425"}]
     * other : {"nickname":"iamyourdady","headurl":"http://ppqapp.oss-cn-hangzhou.aliyuncs.com/android/1490614597885.jpg","vip_end":"","cardnum":"0","isopen":0}
     */

    private String info;
    private int code;
    private OtherBean other;
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

    public OtherBean getOther() {
        return other;
    }

    public void setOther(OtherBean other) {
        this.other = other;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class OtherBean {
        /**
         * nickname : iamyourdady
         * headurl : http://ppqapp.oss-cn-hangzhou.aliyuncs.com/android/1490614597885.jpg
         * vip_end :
         * cardnum : 0
         * isopen : 0
         */

        private String nickname;
        private String headurl;
        private String vip_end;
        private String cardnum;
        private int isopen;

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

        public String getVip_end() {
            return vip_end;
        }

        public void setVip_end(String vip_end) {
            this.vip_end = vip_end;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public int getIsopen() {
            return isopen;
        }

        public void setIsopen(int isopen) {
            this.isopen = isopen;
        }
    }

    public static class DataBean {
        /**
         * sid : 3
         * desc : 今天5折
         * uphour : 0
         * usenum : 0
         * title : aaa
         * name : 老许馒头
         * logo : ppqpc/iaZhb6YfepjA4yRkkYYeFx.png
         * lng : 118.040623
         * lat : 24.557784
         */

        private int sid;
        private String desc;
        private String uphour;
        private String usenum;
        private String title;
        private String name;
        private String logo;
        private String lng;
        private String km;
        private String lat;

        public String getKm() {
            return km;
        }

        public void setKm(String km) {
            this.km = km;
        }
        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }
    }
}
