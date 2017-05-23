package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;

public class Money_Dialog extends BaseDialog implements View.OnClickListener {

    private CheckBox wx_check;
    private CheckBox ali_check;
    private double money;

    private OnCheckListener onCheckListener;

    public Money_Dialog(Context context) {
        super(context, 0);
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.money_dialog;
    }

    @Override
    protected void initUI() {
        LinearLayout wx_pay = bind(R.id.wx_pay);
        LinearLayout ali_pay = bind(R.id.ali_pay);
        wx_check = bind(R.id.wx_check);
        TextView pay = bind(R.id.pay);
        ali_check = bind(R.id.ali_check);
        wx_pay.setOnClickListener(this);
        ali_pay.setOnClickListener(this);
        pay.setOnClickListener(v -> {
            if (onCheckListener != null) {
                onCheckListener.onCheck(position, money);
                hide();
            }
        });
    }

    private int position = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wx_pay:
                position = 0;
                break;
            case R.id.ali_pay:
                position = 1;
                break;
        }
        CheckStatus(position, wx_check, ali_check);
    }

    private void CheckStatus(int position, CheckBox... checkBoxes) {
        for (int i = 0; i < checkBoxes.length; i++) {
            if (i == position) {
                checkBoxes[i].setChecked(true);
            } else {
                checkBoxes[i].setChecked(false);
            }
        }
    }

    public interface OnCheckListener {
        void onCheck(int position, double money);
    }
}
