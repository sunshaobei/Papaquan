package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.View.Life.discountNchoose.DiscountAndChooseActivity;
import xm.ppq.papaquan.View.Life.scare_past.Scare_PastActivity;
import xm.ppq.papaquan.View.mine_balance.Mine_BalanceActivity;
import xm.ppq.papaquan.View.mine_choice.Mine_ChoiceActivity;
import xm.ppq.papaquan.View.scare_buying.Scare_buyingActivity;
import xm.ppq.papaquan.View.waiting.WaitingActivity;

/**
 * Created by EdgeDi on 19:34.
 */

public class PayDialog extends BaseDialog {

    private TextView money;
    private TextView more_btn;
    private TextView plug_leak;
    private TextView mine_order;
    private ImageView imageView8;

    private Double text_money;
    private int oid;

    public PayDialog(Context context, Double text_money, int oid) {
        super(context, 0);
        this.text_money = text_money;
        this.oid = oid;
    }

    @Override
    protected int getLayout() {
        return R.layout.rush_buy;
    }

    private String type;
    private String url;

    public void setType(String type, String url) {
        this.type = type;
        this.url = url;
    }

    @Override
    protected void initUI() {
        money = bind(R.id.money);
        more_btn = bind(R.id.more_btn);
        plug_leak = bind(R.id.plug_leak);
        mine_order = bind(R.id.mine_order);
        imageView8 = bind(R.id.imageView8);
        money.setText(text_money + "金币");
        imageView8.setOnClickListener(v -> {
            hide();
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
        mine_order.setOnClickListener(v -> {
            Intent intent = null;
            if (type.equals("精选")) {
                intent = new Intent(getContext(), Mine_ChoiceActivity.class);
            } else if (type.equals("抢购")) {
                intent = new Intent(getContext(), Scare_buyingActivity.class);
            }
            if (intent != null) getContext().startActivity(intent);
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
        more_btn.setOnClickListener(v -> {
            Intent intent = null;
            if (type.equals("精选")) {
                intent = new Intent(getContext(), DiscountAndChooseActivity.class);
            } else if (type.equals("抢购")) {
                intent = new Intent(getContext(), Scare_PastActivity.class);
            } else if (type.equals("折扣")) {
                intent = new Intent(getContext(), DiscountAndChooseActivity.class);
            }
            if (intent != null) getContext().startActivity(intent);
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
        plug_leak.setOnClickListener(v -> {
            Intent skip = new Intent(getContext(), WaitingActivity.class);
            skip.putExtra("orderid", oid);
            skip.putExtra("type", type);
            skip.putExtra("url", url);
            getContext().startActivity(skip);
            hide();
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
    }
}