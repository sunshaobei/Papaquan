package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/9.
 */

public class SearchSellerData {


    /**
     * info : success
     * code : 0
     * data : [{"id":"65","name":"我们都","logo":"iOS/img/ppq201705031142300452775_00.jpg","address":"厦门集美万达","tel":"123456","lng":"118.093124","lat":"24.573887","mark":{"coupon":0,"panic":0,"rebate":0}}]
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
         * id : 65
         * name : 我们都
         * logo : iOS/img/ppq201705031142300452775_00.jpg
         * address : 厦门集美万达
         * tel : 123456
         * lng : 118.093124
         * lat : 24.573887
         * mark : {"coupon":0,"panic":0,"rebate":0}
         */

        private String id;
        private String name;
        private String logo;
        private String address;
        private String tel;
        private String lng;
        private String lat;
        private MarkBean mark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
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

        public MarkBean getMark() {
            return mark;
        }

        public void setMark(MarkBean mark) {
            this.mark = mark;
        }

        public static class MarkBean {
            /**
             * coupon : 0
             * panic : 0
             * rebate : 0
             */

            private int coupon;
            private int panic;
            private int rebate;

            public int getCoupon() {
                return coupon;
            }

            public void setCoupon(int coupon) {
                this.coupon = coupon;
            }

            public int getPanic() {
                return panic;
            }

            public void setPanic(int panic) {
                this.panic = panic;
            }

            public int getRebate() {
                return rebate;
            }

            public void setRebate(int rebate) {
                this.rebate = rebate;
            }
        }
    }
}
