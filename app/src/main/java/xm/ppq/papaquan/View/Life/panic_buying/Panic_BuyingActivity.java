package xm.ppq.papaquan.View.Life.panic_buying;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XScrollView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.lib_sunshaobei2017.app.SunActivity;
import com.example.lib_sunshaobei2017.widget.ListView4ScrollView;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ScareNumberAdapter;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.Panic_BuyingBean;
import xm.ppq.papaquan.Bean.life.Panic_buyUserData;
import xm.ppq.papaquan.Presenter.panic_buying.Mutual_Panic_Buying;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.appraise.AppraiseActivity;
import xm.ppq.papaquan.View.Life.goodscommet.GoodsCommentActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.panic_pay_waiting.Panic_Pay_WaitingActivity;
import xm.ppq.papaquan.View.Life.shop_lodge.ShopLodgeActivity;
import xm.ppq.papaquan.View.Life.tecenguide.TenCentGuideActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;
import xm.ppq.papaquan.View.waiting.WaitingActivity;
import xm.ppq.papaquan.life.Tool.BannitHolder;
import xm.ppq.papaquan.life.Tool.ErrorDialog;
import xm.ppq.papaquan.life.Tool.LinkSkip;
import xm.ppq.papaquan.life.Tool.TimeTextView;

/**
 * Created by Administrator on 2017/4/18.
 */

public class Panic_BuyingActivity extends SunActivity implements Round_PanicBuying, TencentLocationListener {

    @BindView(R.id.listview)
    ListView4ScrollView listview;
    @BindView(R.id.scare_number_list)
    SlideListView scareNumberList;
    private int pid;
    private SharedPreferencesPotting potting;
    private Mutual_Panic_Buying mutual_panic_buying;

    private ScareNumberAdapter scareNumberAdapter;

    @BindView(R.id.web_view_detail)
    WebView webViewDetail;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.scrollview)
    XScrollView scrollview;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.titlebg)
    View titlebg;
    @BindView(R.id.time_down)
    TimeTextView timeDown;
    @BindView(R.id.situation)
    TextView situation;
    @BindView(R.id.two_top_rela)
    View twotoprela;
    @BindView(R.id.web_view_codex)
    WebView webViewCodex;
    @BindView(R.id.number_discuss)
    TextView numberDiscuss;
    @BindView(R.id.title_panic)
    TextView titlePanic;
    @BindView(R.id.money_panic)
    TextView moneyPanic;
    @BindView(R.id.prime_cost)
    TextView primeCost;
    @BindView(R.id.follow_number)
    TextView followNumber;
    @BindView(R.id.convenient)
    ConvenientBanner convenient;
    @BindView(R.id.red_money_pb)
    TextView redMoneyPb;
    @BindView(R.id.number_panic)
    TextView numberPanic;
    @BindView(R.id.hotelname)
    TextView hotelname;
    @BindView(R.id.hotellocation)
    TextView hotellocation;
    @BindView(R.id.scare_number)
    TextView scareNumber;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.purchasing)
    TextView purchasing;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.call)
    View call;
    @BindView(R.id.share)
    ImageView share;

    private int status;
    private int orderid;
    private BuyUserListAdapter buyUserListAdapter;
    private int sid = -1;
    private ErrorDialog errorDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic_buying);
        ButterKnife.bind(this);
        webViewDetail.setVisibility(View.GONE);
        potting = new SharedPreferencesPotting(this, "my_login");
        errorDialog = new ErrorDialog(this);
        initStatusBar(bar);
        OnSetListener();
    }

    private ShareDialog shareDialog;

    @Override
    protected void onResume() {
        super.onResume();
        pid = getIntent().getIntExtra("pid", 0);
        url = "index/Panicbuy/details?city=" + BaseUrl.citycode + "&pid=" + pid;
        mutual_panic_buying = new Mutual_Panic_Buying(this);
        mutual_panic_buying.start();
        mutual_panic_buying.getBuyUserList(0);
        mutual_panic_buying.getPicNTextInfo(pid);
    }

    private void OnSetListener() {
        scrollview.setOnScrollListener(new XScrollView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {
                if (arriveBottom) {
                    bar.setAlpha(1);
                    titlebg.setAlpha(1);
                    share.setImageResource(R.mipmap.share);
                    finish.setImageResource(R.drawable.white_finish);
                    home.setImageResource(R.drawable.white_home);
                    title.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if ((float) t < 200) {
                    bar.setAlpha((float) t / 200);
                    titlebg.setAlpha((float) t / 200);
//                    finish.setPadding(20, 20, 20, 20);
                    share.setImageResource(R.mipmap.share_2);
//                    share.setPadding(20,20,20,20);
                    finish.setImageResource(R.mipmap.finish_2);
//                    home.setPadding(20, 20, 20, 20);
                    home.setImageResource(R.mipmap.home_2);
                    title.setVisibility(View.GONE);
                } else {
                    bar.setAlpha(1);
                    titlebg.setAlpha(1);
//                    finish.setPadding(10, 10, 10, 10);
                    share.setImageResource(R.mipmap.share);
//                    share.setPadding(10,10,10,10);
                    finish.setImageResource(R.drawable.white_finish);
//                    home.setPadding(10, 10, 10, 10);
                    home.setImageResource(R.drawable.white_home);
                    title.setVisibility(View.VISIBLE);
                }
            }
        });
        share.setOnClickListener(v -> {
            if (shareDialog != null) {
                shareDialog.Show(v);
            }
        });
        numberDiscuss.setOnClickListener(v -> {
            Go2(GoodsCommentActivity.class, "pid", pid, IntentCode.RequestCode.TOGOODSCOMMENT, "url", BaseUrl.PANICBUYAPPRAISE);
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
                mutual_panic_buying.getBuyUserList(page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_panic_buying.getBuyUserList(page);
            }
        });
        buyUserListAdapter = new BuyUserListAdapter(this, buyuserlist, R.layout.item_panic_buyuserlist);
        scareNumberList.setAdapter(buyUserListAdapter);
    }

    private String url;
    private int page;
    private int Error;
    private TencentLocationManager locationManager;
    private ArrayList<Panic_buyUserData.DataBean> buyuserlist = new ArrayList<>();

    @Override
    public void JudgeStatus(int TimeStatus, long time, String text, String color) {
        timeDown.Judge(TimeStatus);
        timeDown.setTime(time);
        situation.setText(text);
        twotoprela.setBackgroundColor(Color.parseColor(color));
    }

    private LatLng end;
    private String address;
    private Panic_BuyingBean.DataBean.KFInfo kfInfo;

    @Override
    public void setBean(Panic_BuyingBean.DataBean dataBean) {
        kfInfo = dataBean.getKfInfo();
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        if (dataBean.getImg() != null) {
            shareDialog.setStatus("抢详", url, BaseUrl.BITMAP + dataBean.getImg().get(0), dataBean.getTitle() + "-" + dataBean.getShop_info().getName());
        } else {
            shareDialog.setStatus("抢详", url, BaseUrl.Image_url, dataBean.getTitle() + "-" + dataBean.getShop_info().getName());
        }
        end = new LatLng(dataBean.getShop_info().getLat(), dataBean.getShop_info().getLng());
        address = dataBean.getShop_info().getAddress();
        sid = Integer.valueOf(dataBean.getSid());
        webViewCodex.setWebViewClient(new WebViewClient());
        webViewCodex.loadDataWithBaseURL(null, dataBean.getContent(), "text/html", "UTF-8", null);
        webViewDetail.setWebViewClient(new WebViewClient());
        webViewDetail.getSettings().setUseWideViewPort(true);
//        webViewDetail.getSettings().setLoadWithOverviewMode(true);
        numberDiscuss.setText(dataBean.getAppraise() + "人评论");
        titlePanic.setText(dataBean.getTitle());
        title.setText(dataBean.getTitle());
        moneyPanic.setText(dataBean.getBuying_price());
        primeCost.setText(dataBean.getPrice() + "元");
        primeCost.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        followNumber.setText(dataBean.getBrowse() + "人关注");
        convenient.setPages(new CBViewHolderCreator<BannitHolder>() {
            @Override
            public BannitHolder createHolder() {
                return new BannitHolder();
            }
        }, dataBean.getImg())
                .stopTurning();
        if (!dataBean.getVip_price().equals("0")) {
            redMoneyPb.setText("红卡用户仅￥" + dataBean.getVip_price());
        } else {
            redMoneyPb.setText("无红卡价格");
        }
        orderid = dataBean.getOrderid();
        numberPanic.setText("总" + dataBean.getQuantity() + dataBean.getUnit() + "/剩" + dataBean.getStock() + dataBean.getUnit());
        hotelname.setText(dataBean.getShop_info().getName());
        hotellocation.setText(dataBean.getShop_info().getAddress() + "\n" + dataBean.getShop_info().getTel());
        me_lat = dataBean.getShop_info().getLat();
        me_lng = dataBean.getShop_info().getLng();
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setAllowCache(true);
        request.setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_GEO);
        locationManager = TencentLocationManager.getInstance(this);
        Error = locationManager.requestLocationUpdates(request, this);
        int i = Integer.valueOf(dataBean.getQuantity()) - Integer.valueOf(dataBean.getStock() != "false" ? dataBean.getStock() : "0");
        if (i > 0) {
            scareNumber.setText("已有" + i + "个人抢到");
            mutual_panic_buying.HumanList();
        } else {
            scareNumber.setVisibility(View.GONE);
        }
        status = dataBean.getStatus();
        switch (status) {
            case 0://未开始
                purchasing.setText("提醒我");
                purchasing.setBackgroundColor(Color.parseColor("#339966"));
                twotoprela.setBackgroundColor(Color.parseColor("#339966"));
                break;
            case 1://抢购中
                purchasing.setText("点击抢购");
                break;
            case 2://抢光了 没过期
                purchasing.setText("抢完了");
                purchasing.setBackgroundColor(Color.parseColor("#999999"));
                break;
            case 3://已结束 没过期
                purchasing.setText("已结束");
                purchasing.setBackgroundColor(Color.parseColor("#999999"));
                break;
            case 4://过期
                purchasing.setText("已过期");
                purchasing.setBackgroundColor(Color.parseColor("#999999"));
                break;
            case 5://抢到了 没付款
                purchasing.setText("点击付款");
                break;
            case 6://抢到 已经付款 没用评价
                purchasing.setText("点击使用");
                break;
            case 7:// 用了没评价
                purchasing.setText("待评价");
                break;
            case 8://已评价
                purchasing.setText("感谢惠顾");
                purchasing.setBackgroundColor(Color.parseColor("#999999"));
                break;
        }
        List<Panic_BuyingBean.DataBean.CouponListBean> coupon_list = dataBean.getCoupon_list();
        if (coupon_list != null && coupon_list.size() > 0)
            listview.setAdapter(new CouponAdapter(this, coupon_list, R.layout.item_coupon, dataBean.getShop_info().getName()));

        call.setOnClickListener(v -> {
            LinkSkip.Phone(Panic_BuyingActivity.this, dataBean.getShop_info().getTel());
        });
    }

    private double me_lat;
    private double me_lng;

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void RushError() {
        errorDialog.show();
    }

    @Override
    public void Toast(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == Error) {
            double lat = tencentLocation.getLatitude();//纬度
            double lng = tencentLocation.getLongitude();//经度
            locationManager = TencentLocationManager.getInstance(this);
            locationManager.removeUpdates(this);
            LatLng end = new LatLng(lat, lng);
            LatLng start = new LatLng(me_lat, me_lng);
            distance.setText(df.format(mutual_panic_buying.Calculated_distance(start, end)) + "km");
        }
    }

    //TODO 抢购成功
    @Override
    public void purchasingSuccess(String oid) {
        orderid = Integer.parseInt(oid);
        purchasing.setText("点击付款");
        Intent intent = new Intent(this, Panic_Pay_WaitingActivity.class);
        intent.putExtra("action", 1);
        intent.putExtra("oid", oid);
        startActivityForResult(intent, IntentCode.RequestCode.TOPAY);
    }

    //TODO 抢购用户列表
    @Override
    public void setBuyUserlist(List<Panic_buyUserData.DataBean> list) {
        if (page == 0) {
            buyuserlist.clear();
        }
        buyuserlist.addAll(list);
        buyUserListAdapter.notifyDataSetChanged();
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public void setPicNTextData(String s) {
        webViewDetail.getSettings().setUseWideViewPort(true);
        webViewDetail.getSettings().setLoadWithOverviewMode(true);
        webViewDetail.loadDataWithBaseURL(null, s, "text/html", "UTF-8", null);
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    /************
     * onClick
     *******************************/
    public void finish(View v) {
        finish();
    }

    public void backtohome(View v) {
        //TODO
        setResult(IntentCode.ResultCode.BACKTOLIFE);
        finish();
    }

    //点击抢购等按钮
    public void purchasing(View v) {
        if (!potting.isLogin()) {
            Toast("您尚未登录，无法抢购");
        } else {
            String text = purchasing.getText().toString();
            switch (text) {
                case "点击抢购":
                    mutual_panic_buying.purchasing();
                    CustomsfullscreamToast(R.layout.item_toast_purchasing);
                    break;
                case "点击付款":
                    Intent intent = new Intent(this, Panic_Pay_WaitingActivity.class);
                    intent.putExtra("action", 1);
                    intent.putExtra("oid", String.valueOf(orderid));
                    startActivityForResult(intent, IntentCode.RequestCode.TOPAY);
                    break;
                case "待评价":
                    Intent intent1 = new Intent(this, AppraiseActivity.class);
                    intent1.putExtra("pid", pid);
                    intent1.putExtra("type", "抢购");
                    intent1.putExtra("sid", sid);
                    startActivityForResult(intent1, IntentCode.RequestCode.TOAPPRAISE);
                    break;
                case "点击使用":
                    Intent skip = new Intent(this, WaitingActivity.class);
                    skip.putExtra("orderid", orderid);
                    skip.putExtra("type", "抢购");
                    startActivityForResult(skip, IntentCode.RequestCode.TOWAITUSE);
                    break;
                case "提醒我":
                    Remind(pid);
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case IntentCode.ResultCode.BACKTOAPPRAISE:
                mutual_panic_buying.start();
                break;
            case IntentCode.ResultCode.BACKTOLIFE:
                setResult(IntentCode.ResultCode.BACKTOLIFE);
                finish();
                break;
        }
    }

    public void navigation(View view) {
        Intent intent = new Intent(this, TenCentGuideActivity.class);
        intent.putExtra("lat", String.valueOf(end.getLatitude()));
        intent.putExtra("lng", String.valueOf(end.getLongitude()));
        intent.putExtra("address", address);
        startActivity(intent);
    }

    private void Remind(int pid) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "pid", "agent")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, pid, "app");
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.REMIND, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                        Toast.makeText(Panic_BuyingActivity.this, "预约成功", Toast.LENGTH_SHORT).show();
                    } else if (baseBean.getCode().equals("3")) {
                        Toast.makeText(Panic_BuyingActivity.this, "抢购即将开始", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Panic_BuyingActivity.this, baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void tousu(View view) {
        Intent intent = new Intent(this, ShopLodgeActivity.class);
        intent.putExtra("type", "1");
        intent.putExtra("typeid", String.valueOf(sid));
        startActivity(intent);
    }

    public void remove_red_card(View view) {
        startActivity(new Intent(this, RedCardActivity.class));
    }

    @OnClick({R.id.service, R.id.shop, R.id.mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service:
                if (kfInfo != null) {
                    String kuid = kfInfo.getKuid();
                    Intent chattingActivityIntent = IMUtils.getIMKit().getChattingActivityIntent("u_" + kuid, IMUtils.APP_KEY);
                    startActivity(chattingActivityIntent);
                }
                break;
            case R.id.shop:
                if (sid != -1) {
                    Intent intent1 = new Intent(this, Merchant_HomepageActivity.class);
                    intent1.putExtra("sid", String.valueOf(sid));
                    startActivity(intent1);
                }
                break;
            case R.id.mine:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                EventBus.getDefault().post(new String("抢购"));
                break;
        }
    }

//    public final static String CSS_STYLE = "<style>* {font-size:34px;line-height:20px;} p {color:#000000;} a {color:#3E62A6;}pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;}</style>";
}