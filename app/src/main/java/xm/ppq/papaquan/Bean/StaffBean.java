package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by sunshaobei on 2017/4/21.
 */

public class StaffBean {

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
         * master : 1
         */

        private int master;

        public int getMaster() {
            return master;
        }

        public void setMaster(int master) {
            this.master = master;
        }
    }

    public static class DataBean {
        /**
         * id : 71
         * uid : 26
         * sid : 23
         * name : 店员1
         * latetime : null
         * status : 1
         * nickname : EdgeDi
         */

        private int id;
        private int uid;
        private int sid;
        private String name;
        private String latetime;
        private int status;
        private String nickname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

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

        public String getLatetime() {
            return latetime;
        }

        public void setLatetime(String latetime) {
            this.latetime = latetime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
