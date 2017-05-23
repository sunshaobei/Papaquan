package xm.ppq.papaquan.View.retrieve;

/**
 * Created by sunshaobei on 2017/3/7.
 */

public interface Round_Retrieve {
    String getPhone();
    String getCode();
    void getCodeSuccess(String result);
    void Error(String error);
    void setCodeSuccess(String result,String random);

}
