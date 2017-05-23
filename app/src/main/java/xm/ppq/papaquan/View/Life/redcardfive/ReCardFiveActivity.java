package xm.ppq.papaquan.View.Life.redcardfive;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.example.lib_sunshaobei2017.app.SunActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.TabCustomsViewCircle;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.RedCradFiveAdapter;
import xm.ppq.papaquan.Bean.RedCardFiveBean;
import xm.ppq.papaquan.Bean.life.ProductDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;


public class ReCardFiveActivity extends SunActivity {

    private int Error = 8;
    private RedCradFiveAdapter adapter;

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.tab)
    TabCustomsViewCircle tab;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.headicon)
    ImageView headicon;
    @BindView(R.id.endtime)
    TextView endtime;
    @BindView(R.id.cardnum)
    TextView cardnum;
    @BindView(R.id.buy_card)
    TextView buy_card;
    @BindView(R.id.redcard)
    ImageView redcard;

    private SharedPreferencesPotting my_login;
    private long choosetime;
    private ShareDialog shareDialog;
    private String url = "index/rebate/fivelist?city=" + BaseUrl.citycode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_card_five);
        ButterKnife.bind(this);
        my_login = new SharedPreferencesPotting(this, "my_login");
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("五列", url, BaseUrl.Image_url);
        initStatusBar(bar);
        initView();
        initListener();
        getData(0);
    }

    private void initListener() {
        ViewGroup.LayoutParams layoutParams = tab.getLayoutParams();
        layoutParams.width = (getWindowManager().getDefaultDisplay().getWidth() - 60) * 5;
        tab.setLayoutParams(layoutParams);
        tab.setOnSelectListener(new TabCustomsViewCircle.OnSelectListener() {
            @Override
            public void onSelect(int Selectposition) {
                if (Selectposition == 0) {
                    choosetime = 0;
                } else {
                    choosetime = (System.currentTimeMillis() + (Selectposition - 1) * ondaytime) / 1000l;
                }
                page = 0;
                ReCardFiveActivity.this.getData(choosetime);
            }
        });
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(this, 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(this));
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                getData(choosetime);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                getData(choosetime);
            }
        });
        buy_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReCardFiveActivity.this, RedCardActivity.class));
            }
        });
    }


    private String s;
    private int ondaytime = 1000 * 60 * 60 * 24;

    public String getMonthDay(long offset) {
        long l = System.currentTimeMillis();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(l + offset * ondaytime);
        int month = instance.get(Calendar.MONTH);
        String months = intToString(month + 1);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        String days = intToString(day);
        return months + "-" + days;
    }

    public String intToString(int i) {
        if (i <= 9) {
            s = "0" + i;
        } else {
            s = "" + i;
        }
        return s;
    }

    private void initView() {
        adapter = new RedCradFiveAdapter(ReCardFiveActivity.this, datas, R.layout.red_card_five_item);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            if (datas != null) {
                if (datas.size() > 0) {
                    Intent intent = new Intent(this, RestaurantActivity.class);
                    intent.putExtra("sid", String.valueOf(datas.get(position).getSid()));
                    startActivity(intent);
                }
            }
        });
    }

    /*****************
     * 点击事件
     *************************/
    public void finish(View v) {
        finish();
    }

    public void share(View v) {
        shareDialog.Show(v);
    }

    public void backtohome(View v) {
        finish();
    }

    private ArrayList<RedCardFiveBean.DataBean> datas = new ArrayList<>();
    private int page;

    public void getData(long time) {
        String lat = "";
        String lats = my_login.getItemString("lat");
        if (lats != null && !lats.equals(""))
            lat = Double.valueOf(lats) + "";
        String lng = "";
        String lngs = my_login.getItemString("lng");
        if (lngs != null && !lngs.equals(""))
            lng = Double.valueOf(lngs) + "";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", my_login.getItemString("citycode"))
                    .put("page", page)
                    .put("time", time)
                    .put("lat", lat)
                    .put("lng", lng)
                    .put("length", 10)
                    .put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.FIVELIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        xRefreshView.stopLoadMore();
                        xRefreshView.stopRefresh();
                        RedCardFiveBean redCardFiveBean = (RedCardFiveBean) JsonUtil.fromJson(s, RedCardFiveBean.class);
                        if (redCardFiveBean.getCode() == 0) {
                            RedCardFiveBean.OtherBean other = redCardFiveBean.getOther();
                            ImageLoading.Circular(ReCardFiveActivity.this, other.getHeadurl(), headicon);
                            int isopen = other.getIsopen();
                            if (isopen == 1) {
                                redcard.setImageResource(R.drawable.red_card_double);
                                cardnum.setVisibility(View.VISIBLE);
                                cardnum.setText(other.getCardnum());
                                endtime.setVisibility(View.VISIBLE);
                                endtime.setText(other.getVip_end());
                            } else {
                                redcard.setImageResource(R.mipmap.redcardgray);
                                cardnum.setVisibility(View.INVISIBLE);
                                endtime.setVisibility(View.INVISIBLE);
                                buy_card.setVisibility(View.VISIBLE);
                            }
                            List<RedCardFiveBean.DataBean> data = redCardFiveBean.getData();
                            if (page == 0) {
                                datas.clear();
                            }
                            if (data != null)
                                datas.addAll(data);
                            runOnUiThread(() -> adapter.notifyDataSetChanged());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
