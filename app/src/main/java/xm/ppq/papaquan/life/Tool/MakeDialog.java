package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.View.Life.appraise.AppraiseActivity;
import xm.ppq.papaquan.View.Life.discountNchoose.DiscountAndChooseActivity;
import xm.ppq.papaquan.View.Life.scare_past.Scare_PastActivity;
import xm.ppq.papaquan.View.mine_integral.Mine_IntegralActivity;

/**
 * Created by EdgeDi on 20:09.
 */

public class MakeDialog extends BaseDialog {

    private int pid;
    private int sid;
    private String type;
    private TextView money;
    private String title;
    private String name;

    public MakeDialog(Context context) {
        super(context, 0);
    }

    @Override
    protected int getLayout() {
        return R.layout.rush_make;
    }

    public void setIdAll(int pid, int sid) {
        this.pid = pid;
        this.sid = sid;
    }

    public void setContent(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public void setMoney(String money) {
        this.money.setText(money + "金币");
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void initUI() {
        money = bind(R.id.money);
        TextView more_btn = bind(R.id.more_btn);
        TextView mine_order = bind(R.id.mine_order);
        ImageView imageView8 = bind(R.id.imageView8);
        TextView plug_leak = bind(R.id.plug_leak);
        imageView8.setOnClickListener(v -> hide());
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
        mine_order.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Mine_IntegralActivity.class);
            getContext().startActivity(intent);
            hide();
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
        plug_leak.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AppraiseActivity.class);
            intent.putExtra("pid", pid);
            intent.putExtra("type", type);
            intent.putExtra("sid", sid);
            if (type.equals("折扣")) {
                intent.putExtra("name", name);
                intent.putExtra("title", title);
            }
            getContext().startActivity(intent);
            hide();
            if (getOnDialogListener() != null) {
                getOnDialogListener().Dialog(v);
            }
        });
    }
}