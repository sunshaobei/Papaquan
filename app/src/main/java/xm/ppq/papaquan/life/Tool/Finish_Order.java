package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.widget.TextView;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 13:46.
 */

public class Finish_Order extends PopupWindowPotting {

    private TextView shi;
    private TextView fou;
    private int oid;
    private SharedPreferencesPotting potting;

    public Finish_Order(Activity activity, int oid) {
        super(activity);
        this.oid = oid;
        potting = new SharedPreferencesPotting(activity, "my_login");
    }

    @Override
    protected int getLayout() {
        return R.layout.finish_order;
    }

    @Override
    protected void initUI() {
        shi = Bind(R.id.shi);
        fou = Bind(R.id.fou);
    }

    @Override
    protected void setListener() {
        fou.setOnClickListener(v -> Hide());
        shi.setOnClickListener(v -> {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "oid", "token", "tokentype")
                    .put_value(potting.getItemInt("uid"), oid, potting.getItemString("token"), 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.CLOSEORDER, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                            ToastShow("订单取消成功");
                            Hide();
                            getActivity().finish();
                        } else {
                            ToastShow(baseBean.getInfo());
                        }
                    }
                }
            });
        });
    }
}
