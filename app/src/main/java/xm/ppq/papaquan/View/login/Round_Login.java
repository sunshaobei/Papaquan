package xm.ppq.papaquan.View.login;

import xm.ppq.papaquan.Bean.LoginBean;

/**
 * Created by Administrator on 2017/3/7.
 */

public interface Round_Login {

    String getPhone();

    String getPassWord();

    String getType();

    void OnSuccess(String result);

    void setLoginBean(LoginBean.DataBean loginbean);

}
