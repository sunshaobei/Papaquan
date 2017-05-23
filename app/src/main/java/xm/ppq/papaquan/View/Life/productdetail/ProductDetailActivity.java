package xm.ppq.papaquan.View.Life.productdetail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.lib_sunshaobei2017.app.SunActivity;
import com.example.lib_sunshaobei2017.widget.ListView4ScrollView;
import com.example.lib_sunshaobei2017.widget.ScrollViewExtend;
import com.tencent.mapsdk.raster.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ProdBottomAdapter;
import xm.ppq.papaquan.Bean.ProductBean;
import xm.ppq.papaquan.Bean.life.MinBean;
import xm.ppq.papaquan.Presenter.life.coupondetail.PofProductDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.goodscommet.GoodsCommentActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.shop_lodge.ShopLodgeActivity;
import xm.ppq.papaquan.View.Life.tecenguide.TenCentGuideActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.place_order.Place_OrderActivity;
import xm.ppq.papaquan.life.Tool.BannitHolder;
import xm.ppq.papaquan.life.Tool.LinkSkip;
import xm.ppq.papaquan.life.Tool.MoreDialog;

/**
 * 精选商品
 */
public class ProductDetailActivity extends SunActivity implements GetData {

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.scrollview)
    ScrollViewExtend scrollview;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.titlebg)
    View titlebg;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.call)
    View call;
    @BindView(R.id.judge_of_num)
    LinearLayout judge_of_num;

    @BindView(R.id.servicer)
    View goservicer;
    @BindView(R.id.shop)
    View goshop;
    @BindView(R.id.mineinfo)
    View gomineinfo;
    @BindView(R.id.imageView9)
    ImageView imageView9;

    private PofProductDetail pofProductDetail;
    private int cid;
    private SharedPreferencesPotting potting;
    private ProductBean.DataBean.KfinfoBean kfinfo;
    private String sid;
    private String url;
    private ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        cid = getIntent().getIntExtra("cid", 0);
        url = "index/coupon/details?city=" + BaseUrl.citycode + "&cid=" + cid;
        initStatusBar(bar);
        initData();
        setListener();
    }

    public void initData() {
        potting = new SharedPreferencesPotting(this, "my_login");
        pofProductDetail = new PofProductDetail(this);
        pofProductDetail.getData(String.valueOf(cid));
    }

    public void setListener() {
        imageView9.setOnClickListener(v -> {
            if (shareDialog != null) {
                shareDialog.Show(v);
            }
        });
        judge_of_num.setOnClickListener(v -> {
            Intent intent = new Intent(this, GoodsCommentActivity.class);
            intent.putExtra("url", BaseUrl.APPLIST);
            intent.putExtra("pid", cid);
            startActivity(intent);
        });
        scrollview.setOnScrollChangedListener(offset -> {
            if (offset < 300) {
                float i = (float) offset / 300;
                titlebg.setAlpha(i);
                finish.setImageResource(R.mipmap.finish_2);
                finish.setPadding(5, 5, 5, 5);
                home.setImageResource(R.mipmap.home_2);
                home.setPadding(5, 5, 5, 5);
                imageView9.setImageResource(R.mipmap.share_2);
                imageView9.setPadding(5, 5, 5, 5);
                bar.setAlpha(i);
                title.setVisibility(View.GONE);
            } else {
                titlebg.setAlpha(1);
                finish.setImageResource(R.drawable.white_finish);
                finish.setPadding(10, 10, 10, 10);
                home.setImageResource(R.drawable.white_home);
                home.setPadding(10, 10, 10, 10);
                imageView9.setImageResource(R.drawable.white_share);
                imageView9.setPadding(10, 10, 10, 10);
                bar.setAlpha(1);
                title.setVisibility(View.VISIBLE);
            }
        });
        scrollview.setOnScrollToBottomLintener(isBottom -> {
            if (isBottom) {
                titlebg.setAlpha(1);
                finish.setImageResource(R.drawable.white_finish);
                finish.setPadding(10, 10, 10, 10);
                home.setImageResource(R.drawable.white_home);
                home.setPadding(10, 10, 10, 10);
                imageView9.setImageResource(R.drawable.white_share);
                imageView9.setPadding(10, 10, 10, 10);
                bar.setAlpha(1);
                title.setVisibility(View.VISIBLE);
            }
        });

        goservicer.setOnClickListener(v -> {
            if (kfinfo != null) {
                String kuid = kfinfo.getKuid();
                Intent chattingActivityIntent = IMUtils.getIMKit().getChattingActivityIntent("u_" + kuid, IMUtils.APP_KEY);
                startActivity(chattingActivityIntent);
            }
        });
        goshop.setOnClickListener(v -> {
            if (sid != null) {
                Intent intent1 = new Intent(this, Merchant_HomepageActivity.class);
                intent1.putExtra("sid", sid);
                startActivity(intent1);
            }
        });
        gomineinfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            EventBus.getDefault().post(new String("抢购"));
        });
    }

    /*****************
     * 点击事件
     ******************/
    public void toOrder_Par(View v) {
        if (potting.isLogin()) {
            Toast.makeText(this, "您尚未登录", Toast.LENGTH_SHORT).show();
        } else {
            Y_N();
        }
    }

    private void Y_N() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "cid", "token", "tokentype")
                .put_value(potting.getItemInt("uid"), cid, potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.HASORDER, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    if (JsonUtil.getInt(s, "code") == -2) {
                        MinBean minBean = (MinBean) JsonUtil.fromJson(s, MinBean.class);
                        if (moreDialog == null) {
                            moreDialog = new MoreDialog(ProductDetailActivity.this);
                            moreDialog.setOnDialogListener(view -> moreDialog.hide());
                        }
                        moreDialog.setOid(minBean.getData().getOid());
                        moreDialog.show();

                    } else if (JsonUtil.getInt(s, "code") == 0) {
                        Intent intent = new Intent(ProductDetailActivity.this, Place_OrderActivity.class);
                        for (int i = 0; i < set_meal_group.getChildCount(); i++) {
                            RadioButton childAt = (RadioButton) set_meal_group.getChildAt(i);
                            if (childAt.isChecked()) {
                                intent.putExtra("bean", specarrBeen.get(i));
                            }
                        }
                        if (intent.getSerializableExtra("bean") == null) {
                            intent.putExtra("spid", spid);
                            intent.putExtra("price", price);
                        }
                        intent.putExtra("shop", shop);
                        intent.putExtra("cid", cid);
                        intent.putExtra("shopname", shopname);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private MoreDialog moreDialog;

    public void finish(View v) {
        finish();
    }

    public void backtolife(View v) {
        setResult(IntentCode.ResultCode.BACKTOLIFE);
        finish();
    }

    @BindView(R.id.con_banner)
    ConvenientBanner con_banner;
    @BindView(R.id.product_detail_title)
    TextView product_detail_title;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.retail_sales)
    TextView retail_sales;
    @BindView(R.id.pd_follow)
    TextView pd_follow;
    @BindView(R.id.sold_num)
    TextView sold_num;
    @BindView(R.id.term_text_1)
    TextView term_text_1;
    @BindView(R.id.term_image_1)
    ImageView term_image_1;
    @BindView(R.id.term_text_2)
    TextView term_text_2;
    @BindView(R.id.term_image_2)
    ImageView term_image_2;
    @BindView(R.id.term_text_3)
    TextView term_text_3;
    @BindView(R.id.term_image_3)
    ImageView term_image_3;
    @BindView(R.id.term_text_4)
    TextView term_text_4;
    @BindView(R.id.term_image_4)
    ImageView term_image_4;
    @BindView(R.id.set_meal_group)
    RadioGroup set_meal_group;
    @BindView(R.id.hotelname)
    TextView hotelname;
    @BindView(R.id.hotellocation)
    TextView hotellocation;
    @BindView(R.id.phone_location)
    TextView phone_location;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.discuss_num)
    TextView discuss_num;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.not_time)
    TextView not_time;
    @BindView(R.id.make_time)
    TextView make_time;

    private ArrayList<ProductBean.DataBean.SpecarrBean> specarrBeen = new ArrayList<>();
    private String shopname;
    private String shop;
    private String spid;
    private String price;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setData(ProductBean data) {

        title.setText(data.getData().getTitle());
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("精商", url, BaseUrl.BITMAP + data.getData().getImg().get(0), data.getData().getTitle() + "-" + data.getData().getName());
        price = data.getData().getPrice();
        spid = data.getData().getSpec();
        shop = data.getData().getTitle();
        kfinfo = data.getData().getKfinfo();
        sid = data.getData().getSid();
        shopname = data.getData().getName();
        pofProductDetail.ImageText(data.getData().getId());
        if (data.getData().getSpecarr() != null) {
            specarrBeen.clear();
            specarrBeen.addAll(data.getData().getSpecarr());
        }
        if (data.getData().getImg() != null) {
            try {
                con_banner.setPages(new CBViewHolderCreator<BannitHolder>() {
                    @Override
                    public BannitHolder createHolder() {
                        return new BannitHolder();
                    }
                }, data.getData().getImg())
                        .stopTurning();
            } catch (Exception e) {

            }
        }
        product_detail_title.setText(data.getData().getTitle());
        text1.setText("￥" + data.getData().getPrice());
        retail_sales.setText("门市价:" + data.getData().getCostprice());
        pd_follow.setText(data.getData().getBrowse() + "人关注");
        sold_num.setText("已售 " + data.getData().getBuynum());
        if (data.getData().getRefund().equals("1")) {
            term_text_1.setText("支持退款");
            term_image_1.setImageResource(R.mipmap.suresiged);
        } else {
            term_text_1.setText("支持退款");
            term_image_1.setImageResource(R.mipmap.not_select);
        }
        if (data.getData().getUphour().equals("2")) {
            term_text_2.setText("提前预约");
            term_image_2.setImageResource(R.mipmap.suresiged);
        } else {
            term_text_2.setText("免预约");
            term_image_2.setImageResource(R.mipmap.suresiged);
        }
        term_text_3.setText("返" + data.getData().getGold() + "金币");
        term_text_4.setText("限堂食");
        if (data.getData().getSpecarr() != null) {
            for (int i = 0; i < data.getData().getSpecarr().size(); i++) {
                set_meal_group.addView(pofProductDetail.CreateButton(this, data.getData().getSpecarr().get(i).getTitle()));
            }
        }
        hotelname.setText(data.getData().getName());
        hotellocation.setText(data.getData().getAddress());
        address = data.getData().getAddress();
        phone_location.setText(data.getData().getTel());
        try {
            end = new LatLng(Double.valueOf(data.getData().getLat()), Double.valueOf(data.getData().getLng()));
            LatLng start = new LatLng(Double.valueOf(potting.getItemString("lat")), Double.valueOf(potting.getItemString("lng")));
            distance.setText(df.format(Calculated_distance(start, end)) + "km");
            long st = Long.valueOf(data.getData().getStime()) * 1000;
            long et = Long.valueOf(data.getData().getEtime()) * 1000;
            String s = DateUtil.getStringByFormat(st, DateUtil.dateFormatYMDHM).toString();
            String e = DateUtil.getStringByFormat(et, DateUtil.dateFormatYMDHM).toString();
            time.setText(s + "至" + e);
        } catch (Exception e) {
            distance.setText("0km");
            time.setText("商家暂无规定期限");
        }
        if (Integer.valueOf(data.getData().getAppnum()) > 0) {
            discuss_num.setText(data.getData().getAppnum() + "评论");
        } else {
            discuss_num.setText("评论");
        }
        not_time.setText(data.getData().getDnuse());
        make_time.setText(data.getData().getBusiness_hours());
        web_view_rule.loadDataWithBaseURL(null, data.getData().getContent().toString(), "text/html", "utf-8", null);
        if (data.getOther() != null) {
            if (adapter == null) {
                adapter = new ProdBottomAdapter(this, data.getOther(), R.layout.scare_item_f);
                adapter.setShopname(data.getData().getName());
                listview.setAdapter(adapter);
            } else {
                adapter.setList(data.getOther());
                adapter.notifyDataSetChanged();
            }
        }
        set_meal_group.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < set_meal_group.getChildCount(); i++) {
                RadioButton childAt = (RadioButton) set_meal_group.getChildAt(i);
                if (childAt.isChecked()) {
                    retail_sales.setText("门市价:" + specarrBeen.get(i).getCostprice());
                    text1.setText("￥" + specarrBeen.get(i).getPrice());
                }
            }
        });
        call.setOnClickListener(v -> {
            LinkSkip.Phone(ProductDetailActivity.this, data.getData().getTel());
        });
    }

    @BindView(R.id.web_view_rule)
    WebView web_view_rule;
    @BindView(R.id.image_text_web)
    WebView image_text_web;
    @BindView(R.id.listview)
    ListView4ScrollView listview;

    private LatLng end;
    private String address;
    private ProdBottomAdapter adapter;

    @Override
    public void setImageText(String html) {
        html = html.replaceAll("<img", "<img width=100%");
        image_text_web.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    public void tousu(View view) {
        Intent intent = new Intent(this, ShopLodgeActivity.class);
        intent.putExtra("typeid", sid);
        intent.putExtra("type", "2");
        startActivity(intent);
    }

    public void navigation(View view) {
        Intent intent = new Intent(this, TenCentGuideActivity.class);
        intent.putExtra("lat", String.valueOf(end.getLatitude()));
        intent.putExtra("lng", String.valueOf(end.getLongitude()));
        intent.putExtra("address", address);
        startActivity(intent);
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    private double Calculated_distance(LatLng start, LatLng end) {
        if (start != null) {
            double lat1 = (Math.PI / 180) * start.getLatitude();
            double lat2 = (Math.PI / 180) * end.getLatitude();

            double lon1 = (Math.PI / 180) * start.getLongitude();
            double lon2 = (Math.PI / 180) * end.getLongitude();

            //地球半径
            double R = 6371;

            //两点间距离 km，如果想要米的话，结果*1000就可以了
            double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
            if (d > 500) {
                d = 500;
            }
            return d;
        } else {
            return 0;
        }
    }
}