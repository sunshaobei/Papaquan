package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.mine_choice.Mine_ChoiceActivity;
import xm.ppq.papaquan.View.order_pay.Order_PayActivity;

/**
 * Created by EdgeDi on 9:29.
 */

public class MoreDialog extends BaseDialog {

    private ImageView finish;
    private TextView cancel_order;
    private TextView go_pay;
    private TextView more;
    private SharedPreferencesPotting potting;

    private String oid;

    public MoreDialog(Context context) {
        super(context, 0);
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    protected int getLayout() {
        return R.layout.more_dialog;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    protected void initUI() {
        finish = bind(R.id.finish);
        cancel_order = bind(R.id.cancel_order);
        go_pay = bind(R.id.go_pay);
        more = bind(R.id.more);
        finish.setOnClickListener(v -> hide());
        go_pay.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Order_PayActivity.class);
            intent.putExtra("url", BaseUrl.ORDERDETAILS);
            intent.putExtra("type", "精选");
            intent.putExtra("oid", oid);
            getContext().startActivity(intent);
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
        cancel_order.setOnClickListener(v -> {
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
                            Toast.makeText(getContext(), "订单取消成功", Toast.LENGTH_SHORT).show();
                            hide();
                            if (getOnDialogListener() != null) getOnDialogListener().Dialog(v);
                        } else {
                            Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        });
        more.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Mine_ChoiceActivity.class);
            getContext().startActivity(intent);
            if (getOnDialogListener() != null) getOnDialogListener().Dialog(v);
        });
    }
}