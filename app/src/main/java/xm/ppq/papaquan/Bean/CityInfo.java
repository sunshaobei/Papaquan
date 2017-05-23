package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by sunshaobei on 2017/3/9.
 */

public class CityInfo {

    /**
     * info : success
     * code : 0
     * data : {"code":350211,"fullname":"集美区","pcode":350200,"pointcode":0,"children":0,"otherCity":[{"code":350203,"fullname":"思明区","condition":1,"pointcode":0,"children":0},{"code":350205,"fullname":"海沧区","condition":1,"pointcode":0,"children":0},{"code":350206,"fullname":"湖里区","condition":1,"pointcode":0,"children":0},{"code":350211,"fullname":"集美区","condition":1,"pointcode":0,"children":0},{"code":350212,"fullname":"同安区","condition":1,"pointcode":0,"children":0},{"code":350213,"fullname":"翔安区","condition":1,"pointcode":0,"children":0}]}
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
         * code : 350211
         * fullname : 集美区
         * pcode : 350200
         * pointcode : 0
         * children : 0
         * otherCity : [{"code":350203,"fullname":"思明区","condition":1,"pointcode":0,"children":0},{"code":350205,"fullname":"海沧区","condition":1,"pointcode":0,"children":0},{"code":350206,"fullname":"湖里区","condition":1,"pointcode":0,"children":0},{"code":350211,"fullname":"集美区","condition":1,"pointcode":0,"children":0},{"code":350212,"fullname":"同安区","condition":1,"pointcode":0,"children":0},{"code":350213,"fullname":"翔安区","condition":1,"pointcode":0,"children":0}]
         */

        private int code;
        private String fullname;
        private int pcode;
        private int pointcode;
        private int children;
        private List<OtherCityBean> otherCity;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getPcode() {
            return pcode;
        }

        public void setPcode(int pcode) {
            this.pcode = pcode;
        }

        public int getPointcode() {
            return pointcode;
        }

        public void setPointcode(int pointcode) {
            this.pointcode = pointcode;
        }

        public int getChildren() {
            return children;
        }

        public void setChildren(int children) {
            this.children = children;
        }

        public List<OtherCityBean> getOtherCity() {
            return otherCity;
        }

        public void setOtherCity(List<OtherCityBean> otherCity) {
            this.otherCity = otherCity;
        }

        public static class OtherCityBean {
            /**
             * code : 350203
             * fullname : 思明区
             * condition : 1
             * pointcode : 0
             * children : 0
             */

            private int code;
            private String fullname;
            private int condition;
            private int pointcode;
            private int children;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public int getCondition() {
                return condition;
            }

            public void setCondition(int condition) {
                this.condition = condition;
            }

            public int getPointcode() {
                return pointcode;
            }

            public void setPointcode(int pointcode) {
                this.pointcode = pointcode;
            }

            public int getChildren() {
                return children;
            }

            public void setChildren(int children) {
                this.children = children;
            }
        }
    }
}
