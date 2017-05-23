package xm.ppq.papaquan.View.reevesetup;

/**
 * Created by sunshaobei on 2017/3/8.
 */

public interface Round_Reeve {
    String getPsw1();

    String getPsw2();

    String getPhone();

    String getRandom();

    void reSetSuccess(String result);

    void reSetError(String error);
}
