package xm.ppq.papaquan.View.balance_detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.ExchangeHistoryData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.owncoin.ExchangeDetailActivity;

/**
 * Created by 交易明细 on 2017/3/21.
 */

public class ExchangeHistoryActivity extends BaseActivity {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.title_lin)
    LinearLayout title_lin;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.xrefresh)
    XRefreshView xrefresh;
    @BindView(R.id.bar)
    LinearLayout bar;

    private ArrayList<ExchangeHistoryData.DataBean> list = new ArrayList<>();
    private DetailAdapter detailAdapter;
    private SharedPreferencesPotting my_login;

    @Override
    protected int getLayout() {
        return R.layout.activity_exchange_history;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        my_login = new SharedPreferencesPotting(this, "my_login");
        center_text.setText("兑换记录");
        center_text.setTextColor(getResources().getColor(R.color.white));
        left_image.setImageResource(R.drawable.white_finish);
        left_image.setPadding(25, 25, 25, 25);
        detailAdapter = new DetailAdapter(this, list, R.layout.detail_item);
        listview.setAdapter(detailAdapter);
        xrefresh.setPullLoadEnable(true);
        xrefresh.setCustomHeaderView(new CustomHeader(this, 300));
        xrefresh.setCustomFooterView(new XRefreshViewFooter(this));
        xrefresh.setPinnedTime(1000);
        xrefresh.setAutoLoadMore(false);
        xrefresh.setMoveForHorizontal(true);
        xrefresh.setScrollBackDuration(300);
        xrefresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                getData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                getData();
            }
        });
        getData();
    }

    private int page;

    private void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1)
                    .put("page", page)
                    .put("length", 20);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.EXCHANGEHISTORY, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (o != null) {
                        String code = JsonUtil.getString(o, "code");
                        if (code != null && code.equals("0")) {
                            ExchangeHistoryData dataBean = (ExchangeHistoryData) JsonUtil.fromJson(o, ExchangeHistoryData.class);
                            List<ExchangeHistoryData.DataBean> data = dataBean.getData();
                            if (page == 0) {
                                list.clear();
                                list.addAll(data);
                                xrefresh.stopRefresh();
                            } else{
                                list.addAll(data);
                                xrefresh.stopLoadMore();
                            }
                            detailAdapter.notifyDataSetChanged();

                        }
                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {
        left_image.setOnClickListener(v -> finish());
        listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ExchangeHistoryActivity.this, ExchangeDetailActivity.class);
            intent.putExtra("gid",list.get(position).getGid());
            startActivity(intent);
        });
    }

    class DetailAdapter extends ConcreteAdapter<ExchangeHistoryData.DataBean> {

        public DetailAdapter(Context context, List<ExchangeHistoryData.DataBean> list, int itemLayout) {
            super(context, list, itemLayout);
        }

        @Override
        protected void convert(ViewHolder viewHolder, ExchangeHistoryData.DataBean item, int position) {
            viewHolder.setText(item.getTitle(), R.id.title);
            viewHolder.setText("积分: -" + item.getPaygold() + "金币", R.id.subtitle);
            viewHolder.setVisibility(View.INVISIBLE, R.id.earn);
            viewHolder.setText(item.getTime(), R.id.time);
        }
    }
}