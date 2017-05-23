package xm.ppq.papaquan.View.make_address;

/**
 * Created by sunshaobei on 2017/3/21.
 */

public interface Round_MakeAddress {
    String getName();
    String getPhone();
    String getProvince();
    String getCity();
    String getArea();
    String getStreet();
    int getPosition();
    String getPostcode();
    void setSuccess(String s);
    void setError(String s);
}
