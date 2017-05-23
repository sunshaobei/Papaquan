package xm.ppq.papaquan.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class AddressBean {

    public String info;
    public String code;
    public List<Data> data;
    public String other;

    public class Data {
        public String id;
        public String uid;
        public String province;
        public String city;
        public String area;
        public String street;
        public String username;
        public String deliverytel;
        public String zipcode;
        public String isdefault;
    }

}