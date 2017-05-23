package xm.ppq.papaquan.View.owncoin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_sunshaobei2017.app.SunActivity;
import com.example.lib_sunshaobei2017.widget.ScrollViewExtend;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ProductDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;

public class ExchangeDetailActivity extends SunActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.num_follow)
    TextView numFollow;
    @BindView(R.id.info)
    TextView info;

    private SharedPreferencesPotting potting;
    private int gid;
    private ArrayList<String> imagelist = new ArrayList<>();
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.scrollview)
    ScrollViewExtend scrollview;
    @BindView(R.id.home)
    ImageView home;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.titlebg)
    View titlebg;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.nomore)
    TextView nomore;
    @BindView(R.id.sytv)
    TextView sytv;
    @BindView(R.id.ydhtv)
    TextView ydhtv;

    private VPAdapter vpAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangedetail);
        ButterKnife.bind(this);
        initStatusBar(bar);
        initData();
        setListener();
    }

    public void initData() {
        potting = new SharedPreferencesPotting(this, "my_login");
        gid = getIntent().getIntExtra("gid", 0);
        web_view.setWebViewClient(new WebViewClient());
        vpAdapter = new VPAdapter(getSupportFragmentManager(), imagelist);
        viewpager.setAdapter(vpAdapter);
        getData();
    }

    private void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("gid", gid)
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GOLDDETAILS, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        ProductDetail productDetail = (ProductDetail) JsonUtil.fromJson(s, ProductDetail.class);
                        if (productDetail.getCode() == 0) {
                            ProductDetail.DataBean data = productDetail.getData();
                            name.setText(data.getName());
                            text2.setText(data.getGold() + "");
                            text4.setText(data.getPrice() + "");
                            numFollow.setText(data.getBrowse() + "");
                            int haschange = data.getHaschange();
                            switch (haschange) {
                                case 1://点击兑换
                                    info.setText("点击兑换");
                                    info.setBackground(getResources().getDrawable(R.drawable.tvbg_coinfcc00));
                                    info.setTextColor(Color.BLACK);
                                    info.setOnClickListener(v -> {
                                        exchange(data.getId());
                                    });
                                    break;
                                case -1://金币不足
                                    info.setText("金币不足");
                                    break;
                                case -2://已经兑换
                                    info.setText("已兑换");
                                    info.setBackground(getResources().getDrawable(R.drawable.tvbg_coin66000000));
                                    info.setTextColor(Color.WHITE);
                                    break;
                            }
                            ydhtv.setText(data.getExchange() + "");
                            sytv.setText(data.getNum() - data.getExchange() + "");
                            String content = data.getContent();
                            web_view.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
                            if (content == null || content.equals(""))
                                nomore.setVisibility(View.VISIBLE);
                            List<String> logo = data.getLogo();
                            imagelist.clear();
                            imagelist.addAll(logo);
                            vpAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void exchange(int id) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", potting.getItemInt("uid"))
                    .put("gid", id)
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.EXCHANGE, jsonObject.toString(), new Subscriber<String>() {
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
                        if (code.equals("0")) {
                            Intent intent = new Intent(ExchangeDetailActivity.this, Exchange.class);
                            intent.putExtra("id", JsonUtil.getString(o, "data"));
                            startActivityForResult(intent, IntentCode.RequestCode.TOEXCHANGERESULT);
                        } else {
                            Toast.makeText(ExchangeDetailActivity.this, JsonUtil.getString(o, "info"), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setListener() {
        scrollview.setOnScrollChangedListener(offset -> {
            if (offset < 300) {
                float i = (float) offset / 300;
                titlebg.setAlpha(i);
                finish.setPadding(0, 0, 0, 0);
                finish.setImageResource(R.mipmap.finish_2);
                home.setPadding(0, 0, 0, 0);
                home.setImageResource(R.mipmap.home_2);
            } else {
                titlebg.setAlpha(1);
                finish.setPadding(16, 16, 16, 16);
                finish.setImageResource(R.drawable.white_finish);
                home.setPadding(16, 16, 16, 16);
                home.setImageResource(R.drawable.white_home);

            }
        });
        finish.setOnClickListener(v -> finish());
        home.setOnClickListener(v -> {
            setResult(IntentCode.ResultCode.BACKTOLIFE);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOEXCHANGEDETAIL) {
            getData();
        }
    }
}