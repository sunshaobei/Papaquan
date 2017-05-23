package xm.ppq.papaquan.View.balance_detail;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 交易明细 on 2017/3/21.
 */

public class Balance_DetailActivity extends BaseActivity {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.title_lin)
    LinearLayout title_lin;
    @BindView(R.id.balance_list)
    ListView balance_list;

    private List<DetailBean> detailBeen;

    @Override
    protected int getLayout() {
        return R.layout.activity_balance_detail;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        title_lin.setBackgroundColor(getResources().getColor(R.color.white));
        center_text.setText("余额明细");
        center_text.setTextColor(getResources().getColor(R.color.black));
        left_image.setImageResource(R.drawable.black_finish);
        left_image.setPadding(25, 25, 25, 25);
    }

    @Override
    protected void setListener() {
        left_image.setOnClickListener(v -> finish());
    }

    private DetailAdapter adapter;

    class DetailAdapter extends ConcreteAdapter<DetailBean> {

        public DetailAdapter(Context context, List list, int itemLayout) {
            super(context, list, itemLayout);
        }

        @Override
        protected void convert(ViewHolder viewHolder, DetailBean item, int position) {

        }
    }

    class DetailBean {
        String content;
        String money_balance;
        String add_balance;
        String time_money;
    }

}