package xm.ppq.papaquan.Bean.life;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class RedcardData {

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

        private String invitationNum;
        private String invitationMoney;
        private String myinvitation;
        private String headurl;
        private String cardnum;
        private String vipmsg;
        private int isopen;
        private List<RedBean> red;

        public String getInvitationNum() {
            return invitationNum;
        }

        public void setInvitationNum(String invitationNum) {
            this.invitationNum = invitationNum;
        }

        public String getInvitationMoney() {
            return invitationMoney;
        }

        public void setInvitationMoney(String invitationMoney) {
            this.invitationMoney = invitationMoney;
        }

        public String getMyinvitation() {
            return myinvitation;
        }

        public void setMyinvitation(String myinvitation) {
            this.myinvitation = myinvitation;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }

        public String getCardnum() {
            return cardnum;
        }

        public void setCardnum(String cardnum) {
            this.cardnum = cardnum;
        }

        public String getVipmsg() {
            return vipmsg;
        }

        public void setVipmsg(String vipmsg) {
            this.vipmsg = vipmsg;
        }

        public int getIsopen() {
            return isopen;
        }

        public void setIsopen(int isopen) {
            this.isopen = isopen;
        }

        public List<RedBean> getRed() {
            return red;
        }

        public void setRed(List<RedBean> red) {
            this.red = red;
        }

        public static class RedBean {

            private String name;
            private String money;
            private double price;
            private int status;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
