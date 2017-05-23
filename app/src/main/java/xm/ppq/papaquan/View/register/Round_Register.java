package xm.ppq.papaquan.View.register;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface Round_Register {

    String getPhone();

    String getPassword();

    String getCode();

    String getCityCode();

    void setSuccess(String result);

    void Error(String cause);

    void ToastError(String cause);
}