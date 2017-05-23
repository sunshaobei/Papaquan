package xm.ppq.papaquan.Presenter.life.waittingforsue;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.WaittingData;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.waiting.DataUtil;

/**
 * Created by sunshaobei on 2017/4/28.
 */

public class PofWaitting implements Presenter {

    private DataUtil dataUtil;
    private final SharedPreferencesPotting my_login;

    public PofWaitting(DataUtil dataUtil) {
        this.dataUtil = dataUtil;
        my_login = new SharedPreferencesPotting((Context) dataUtil, "my_login");
    }

    @Override
    public void getData(int orderid) {
        if (dataUtil.getType().equals("抢购")) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("uid", my_login.getItemInt("uid"))
                        .put("orderid", orderid)
                        .put("token", my_login.getItemString("token"))
                        .put("tokentype", 1);
                OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(dataUtil.getUrl(), jsonObject.toString(), new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(String o) {
                        String code = JsonUtil.getString(o, "code");
                        if (code.equals("0")) {
                            WaittingData data = (WaittingData) JsonUtil.fromJson(o, WaittingData.class);
                            dataUtil.setData(data.getData());
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            JsonTool jsontool = new JsonTool();
            jsontool.put_key("uid", "oid","token","tokentype")
                    .put_value(my_login.getItemInt("uid"), orderid,my_login.getItemString("token"),1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(dataUtil.getUrl(), jsontool.getJson().toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        Order_PayBean order_paybean = (Order_PayBean) JsonUtil.fromJson(s, Order_PayBean.class);
                        if (order_paybean.getCode() == 0) {
                            dataUtil.setBean(order_paybean);
                        }
                    }
                }
            });
        }
    }
}