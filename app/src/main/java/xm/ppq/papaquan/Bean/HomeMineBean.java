package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/3/13.
 */

public class HomeMineBean {

    private String info;
    private int code;
    private DataBean data;
    private Object other;

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

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public static class DataBean {

        private InfoBean info;
        private int topic_num;
        private int follow_num;
        private int fans_num;
        private int black_num;
        private int is_sign;
        private int panic_num;
        private int coupon_num;

        public int getCoupon_num() {
            return coupon_num;
        }

        public void setCoupon_num(int coupon_num) {
            this.coupon_num = coupon_num;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getTopic_num() {
            return topic_num;
        }

        public void setTopic_num(int topic_num) {
            this.topic_num = topic_num;
        }

        public int getFollow_num() {
            return follow_num;
        }

        public void setFollow_num(int follow_num) {
            this.follow_num = follow_num;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public int getBlack_num() {
            return black_num;
        }

        public void setBlack_num(int black_num) {
            this.black_num = black_num;
        }

        public int getIs_sign() {
            return is_sign;
        }

        public void setIs_sign(int is_sign) {
            this.is_sign = is_sign;
        }

        public int getPanic_num() {
            return panic_num;
        }

        public void setPanic_num(int panic_num) {
            this.panic_num = panic_num;
        }

        public static class InfoBean {

            private int uid;
            private String nickname;
            private String headurl;
            private int sex;
            private String signature;
            private String money;
            private int gold;
            private int exper;
            private long vip_end;
            private int level;

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

            public String getHeadurl() {
                return headurl;
            }

            public void setHeadurl(String headurl) {
                this.headurl = headurl;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getGold() {
                return gold;
            }

            public void setGold(int gold) {
                this.gold = gold;
            }

            public int getExper() {
                return exper;
            }

            public void setExper(int exper) {
                this.exper = exper;
            }

            public long getVip_end() {
                return vip_end;
            }

            public void setVip_end(long vip_end) {
                this.vip_end = vip_end;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }
        }
    }
}
