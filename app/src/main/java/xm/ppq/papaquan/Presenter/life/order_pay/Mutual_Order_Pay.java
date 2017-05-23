package xm.ppq.papaquan.Presenter.life.order_pay;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.PaincPayBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.order_pay.Round_Order_Pay;

/**
 * Created by EdgeDi on 15:20.
 */

public class Mutual_Order_Pay implements OrderPayPresenter {

    private Round_Order_Pay round_order_pay;

    public Mutual_Order_Pay(Round_Order_Pay round_order_pay) {
        this.round_order_pay = round_order_pay;
    }

    @Override
    public void getInfo(String url) {
        switch (round_order_pay.getType()) {
            case "精选":
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("oid", round_order_pay.getOid())
                            .put("uid", round_order_pay.getUid())
                            .put("token", round_order_pay.getToken())
                            .put("tokentype", 1);
                    OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            if (s != null) {
                                Order_PayBean bean = (Order_PayBean) JsonUtil.fromJson(s, Order_PayBean.class);
                                if (bean.getCode() == 0) {
                                    round_order_pay.setBean(bean.getData());
                                }
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "抢购":
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("orderid", round_order_pay.getOid())
                            .put("uid", round_order_pay.getUid())
                            .put("token", round_order_pay.getToken())
                            .put("tokentype", 1);
                    OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            if (s != null) {
                                PaincPayBean paincPayBean = (PaincPayBean) JsonUtil.fromJson(s, PaincPayBean.class);
                                if (paincPayBean.getCode() == 0) {
                                    round_order_pay.setPaincBean(paincPayBean.getData());
                                }
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void Pay() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "oid", "token", "tokentype")
                .put_value(round_order_pay.getUid(), round_order_pay.getOid(), round_order_pay.getToken(), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.MONRYPAY, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    if (baseBean.getCode().equals("0")) {
                        round_order_pay.Skip();
                    } else {
                        round_order_pay.Toast(baseBean.getInfo());
                    }
                }
            }
        });
    }
}