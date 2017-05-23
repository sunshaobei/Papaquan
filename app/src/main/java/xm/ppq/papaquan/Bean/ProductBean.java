package xm.ppq.papaquan.Bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EdgeDi on 10:10.
 */

public class ProductBean  {

    /**
     * info : success
     * code : 0
     * data : {"title":"打开的开发商","dnuse":"10月1日不可用","buynum":"0","spec":"1","browse":"510","appnum":"1","sort":"0","price":"10","specarr":[{"id":32,"title":"a","price":"10","costprice":"5","num":180},{"id":33,"title":"b","price":"15","costprice":"7","num":196}],"num":"-1","img":["ppqpc/3biRN86eT7E6TK3HfenHke.jpg","ppqpc/WkTGn4XricxR8G3Z647nEr.jpg","ppqpc/RAHKHQ6fHyY82K3WWkdyiA.jpg"],"usenum":"0","sid":"58","retnum":"0","refund":"1","gold":"","citycode":"350211","stime":"1493348558","etime":"1496113361","costprice":"5","arrival":"0","uphour":"0","id":"81","kfinfo":{"kuid":"3","kfcode":"ppqpc/yhs8AAJ6k635BRCZbfGzG2.png","phone":"123123"},"name":"森林精灵","lat":"24.57388","lng":"118.09313","tel":"354568","address":"厦门市集美万达","business_hours":"10：00","content":null}
     * other : null
     */

    private String info;
    private int code;
    private DataBean data;
    private ArrayList<OtherBean> other;

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

    public ArrayList<OtherBean> getOther() {
        return other;
    }

    public void setOther(ArrayList<OtherBean> other) {
        this.other = other;
    }

    public static class OtherBean {

        private int id;
        private String title;
        private String price;
        private String costprice;
        private int uphour;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCostprice() {
            return costprice;
        }

        public void setCostprice(String costprice) {
            this.costprice = costprice;
        }

        public int getUphour() {
            return uphour;
        }

        public void setUphour(int uphour) {
            this.uphour = uphour;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }



    public static class DataBean {
        /**
         * title : 打开的开发商
         * dnuse : 10月1日不可用
         * buynum : 0
         * spec : 1
         * browse : 510
         * appnum : 1
         * sort : 0
         * price : 10
         * specarr : [{"id":32,"title":"a","price":"10","costprice":"5","num":180},{"id":33,"title":"b","price":"15","costprice":"7","num":196}]
         * num : -1
         * img : ["ppqpc/3biRN86eT7E6TK3HfenHke.jpg","ppqpc/WkTGn4XricxR8G3Z647nEr.jpg","ppqpc/RAHKHQ6fHyY82K3WWkdyiA.jpg"]
         * usenum : 0
         * sid : 58
         * retnum : 0
         * refund : 1
         * gold :
         * citycode : 350211
         * stime : 1493348558
         * etime : 1496113361
         * costprice : 5
         * arrival : 0
         * uphour : 0
         * id : 81
         * kfinfo : {"kuid":"3","kfcode":"ppqpc/yhs8AAJ6k635BRCZbfGzG2.png","phone":"123123"}
         * name : 森林精灵
         * lat : 24.57388
         * lng : 118.09313
         * tel : 354568
         * address : 厦门市集美万达
         * business_hours : 10：00
         * content : null
         */

        private String title;
        private String dnuse;
        private String buynum;
        private String spec;
        private String browse;
        private String appnum;
        private String sort;
        private String price;
        private String num;
        private String usenum;
        private String sid;
        private String retnum;
        private String refund;
        private String gold;
        private String citycode;
        private String stime;
        private String etime;
        private String costprice;
        private String arrival;
        private String uphour;
        private String id;
        private KfinfoBean kfinfo;
        private String name;
        private String lat;
        private String lng;
        private String tel;
        private String address;
        private String business_hours;
        private Object content;
        private List<SpecarrBean> specarr;
        private List<String> img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDnuse() {
            return dnuse;
        }

        public void setDnuse(String dnuse) {
            this.dnuse = dnuse;
        }

        public String getBuynum() {
            return buynum;
        }

        public void setBuynum(String buynum) {
            this.buynum = buynum;
        }

        public String getSpec() {
            return spec;
        }

        public void setSpec(String spec) {
            this.spec = spec;
        }

        public String getBrowse() {
            return browse;
        }

        public void setBrowse(String browse) {
            this.browse = browse;
        }

        public String getAppnum() {
            return appnum;
        }

        public void setAppnum(String appnum) {
            this.appnum = appnum;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getRetnum() {
            return retnum;
        }

        public void setRetnum(String retnum) {
            this.retnum = retnum;
        }

        public String getRefund() {
            return refund;
        }

        public void setRefund(String refund) {
            this.refund = refund;
        }

        public String getGold() {
            return gold;
        }

        public void setGold(String gold) {
            this.gold = gold;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getCostprice() {
            return costprice;
        }

        public void setCostprice(String costprice) {
            this.costprice = costprice;
        }

        public String getArrival() {
            return arrival;
        }

        public void setArrival(String arrival) {
            this.arrival = arrival;
        }

        public String getUphour() {
            return uphour;
        }

        public void setUphour(String uphour) {
            this.uphour = uphour;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public KfinfoBean getKfinfo() {
            return kfinfo;
        }

        public void setKfinfo(KfinfoBean kfinfo) {
            this.kfinfo = kfinfo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusiness_hours() {
            return business_hours;
        }

        public void setBusiness_hours(String business_hours) {
            this.business_hours = business_hours;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public List<SpecarrBean> getSpecarr() {
            return specarr;
        }

        public void setSpecarr(List<SpecarrBean> specarr) {
            this.specarr = specarr;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public static class KfinfoBean {
            /**
             * kuid : 3
             * kfcode : ppqpc/yhs8AAJ6k635BRCZbfGzG2.png
             * phone : 123123
             */

            private String kuid;
            private String kfcode;
            private String phone;

            public String getKuid() {
                return kuid;
            }

            public void setKuid(String kuid) {
                this.kuid = kuid;
            }

            public String getKfcode() {
                return kfcode;
            }

            public void setKfcode(String kfcode) {
                this.kfcode = kfcode;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class SpecarrBean implements Serializable{
            /**
             * id : 32
             * title : a
             * price : 10
             * costprice : 5
             * num : 180
             */

            private int id;
            private String title;
            private String price;
            private String costprice;
            private int num;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCostprice() {
                return costprice;
            }

            public void setCostprice(String costprice) {
                this.costprice = costprice;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}