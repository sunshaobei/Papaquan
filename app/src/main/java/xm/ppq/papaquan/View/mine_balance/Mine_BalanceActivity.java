package xm.ppq.papaquan.View.mine_balance;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.balance.BalanceActivity;
import xm.ppq.papaquan.View.withdraw_cash.Withdraw_CashActivity;

/**
 * Created by 我的余额  on 2017/3/21.
 */

public class Mine_BalanceActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.mine_left)
    ImageView mine_left;
    @BindView(R.id.mine_right)
    TextView mine_right;
    @BindView(R.id.cash_text)
    TextView cash_text;
    @BindView(R.id.my_money)
    TextView money_view;

    private double my_money;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_balance;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        my_money = getIntent().getDoubleExtra("$", 0.0);
        money_view.setText("￥" + my_money);
    }

    @Override
    protected void setListener() {
        mine_left.setOnClickListener(v -> finish());
        mine_right.setOnClickListener(this);
        cash_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_right:
                Skip(BalanceActivity.class);
                break;
            case R.id.cash_text:
                Intent intent = new Intent(this, Withdraw_CashActivity.class);
                intent.putExtra("my_money", my_money);
                startActivity(intent);
                break;
        }
    }
}