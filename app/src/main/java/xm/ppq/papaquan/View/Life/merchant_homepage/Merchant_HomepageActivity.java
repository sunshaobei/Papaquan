package xm.ppq.papaquan.View.Life.merchant_homepage;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.widget.ScrollViewExtend;
import com.tencent.mapsdk.raster.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.mybanner.Mybanner;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.life.HomePagerChooseAdapter;
import xm.ppq.papaquan.Adapter.life.HomePagerRushAdapter;
import xm.ppq.papaquan.Bean.life.HomePagerOtherBean;
import xm.ppq.papaquan.Bean.life.MerChat_HomePageBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.enter.EnterActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Merchant_HomepageActivity extends BaseActivity {

    @BindView(R.id.convenientBanner)
    Mybanner convenientBanner;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.trade)
    TextView trade;
    @BindView(R.id.consume)
    TextView consume;
    @BindView(R.id.business)
    TextView business;
    @BindView(R.id.number_merchant)
    TextView number_merchant;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.wifi)
    TextView wifi;
    @BindView(R.id.car)
    TextView car;
    @BindView(R.id.air)
    TextView air;
    @BindView(R.id.room)
    TextView room;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.phone_image)
    ImageView phone_image;

    @BindView(R.id.enter)
    TextView enter;

    private String sid;
    private MerChat_HomePageBean.DataBean dataBean;
    private LatLng start;
    private SharedPreferencesPotting potting;
    private ShareDialog shareDialog;
    private String url;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant_homepage;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        sid = getData("sid");
        url = "index/shop/details?city=" + BaseUrl.citycode + "&sid=" + sid;
        potting = new SharedPreferencesPotting(this, "my_login");
        try {
            start = new LatLng(Double.valueOf(potting.getItemString("lat")), Double.valueOf(potting.getItemString("lng")));
        } catch (Exception e) {
            start = new LatLng(0, 0);
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOP_DETAILS, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBean != null) {
                        shareDialog = new ShareDialog(getActivity(), R.layout.deteil_share);
                        shareDialog.setStatus("商主", url, BaseUrl.BITMAP + dataBean.getLogo().get(0), dataBean.getName());
                        web_view.getSettings().setUseWideViewPort(true);
                        web_view.getSettings().setLoadWithOverviewMode(true);
                        web_view.loadDataWithBaseURL(null, dataBean.getContent(), "text/html", "utf-8", null);
                        if (dataBean.getWifi() == 1) {
                            wifi.setVisibility(View.VISIBLE);
                        } else {
                            wifi.setVisibility(View.GONE);
                        }
                        if (dataBean.getCar() == 1) {
                            car.setVisibility(View.VISIBLE);
                        } else {
                            car.setVisibility(View.GONE);
                        }
                        if (dataBean.getAir() == 1) {
                            air.setVisibility(View.VISIBLE);
                        } else {
                            air.setVisibility(View.GONE);
                        }
                        if (dataBean.getRoom() == 1) {
                            room.setVisibility(View.VISIBLE);
                        } else {
                            room.setVisibility(View.GONE);
                        }
                        LatLng end;
                        try {
                            end = new LatLng(Double.valueOf(dataBean.getLat()), Double.valueOf(dataBean.getLng()));
                        } catch (Exception e) {
                            end = new LatLng(0, 0);
                        }
                        distance.setText(df.format(Calculated_distance(start, end)) + "km");
                        address.setText(dataBean.getAddress());
                        number_merchant.setText(dataBean.getBrowse() + "");
                        business.setText(Stringutil.ThreeString(dataBean.getBusiness_hours(), ""));
                        consume.setText("人均" + dataBean.getConsume() + "元");
                        trade.setText(dataBean.getIndustry());
                        title.setText(dataBean.getName());

                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < dataBean.getLogo().size(); i++) {
                            list.add(BaseUrl.BITMAP + dataBean.getLogo().get(i));
                        }
                        convenientBanner.setBanner(getSupportFragmentManager(), list);
                        phone_image.setOnClickListener(v -> {
                            String tel = dataBean.getTel();
                            LinkSkip.Phone(getActivity(), tel);
                        });
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getInt(s, "code") == 0) {
                            MerChat_HomePageBean bean = (MerChat_HomePageBean) JsonUtil.fromJson(s, MerChat_HomePageBean.class);
                            dataBean = bean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", sid)
                    .put("uid", potting.getItemInt("uid"))
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPOHER, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        HomePagerOtherBean otherBean = (HomePagerOtherBean) JsonUtil.fromJson(s, HomePagerOtherBean.class);
                        if (otherBean.getCode() == 0) {
                            if (otherBean.getData().getRebate() != null) {
                                zhekou_lin.setVisibility(View.VISIBLE);
                                HomePagerOtherBean.DataBean.RebateBean rebateBean = otherBean.getData().getRebate();
                                zk_name.setText(rebateBean.getTitle());
                                zhekou.setText(rebateBean.getDiscount() + "");
                                shiy.setText(rebateBean.getUsenum() + "人使用");
                            } else {
                                zhekou_lin.setVisibility(View.GONE);
                            }
                            if (chooseAdapter == null) {
                                chooseAdapter = new HomePagerChooseAdapter(getActivity(), otherBean.getData().getCou(), R.layout.rush_item_homepage);
                                choose_list.setAdapter(chooseAdapter);
                            } else {
                                chooseAdapter.setList(otherBean.getData().getCou());
                                chooseAdapter.notifyDataSetChanged();
                            }
                            if (rushAdapter == null) {
                                rushAdapter = new HomePagerRushAdapter(getActivity(), otherBean.getData().getPanic(), R.layout.scare_past_item);
                                rush_list.setAdapter(rushAdapter);
                            } else {
                                rushAdapter.setList(otherBean.getData().getPanic());
                                rushAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private HomePagerChooseAdapter chooseAdapter;
    private HomePagerRushAdapter rushAdapter;

    @BindView(R.id.rush_list)
    SlideListView rush_list;
    @BindView(R.id.choose_list)
    SlideListView choose_list;
    @BindView(R.id.shiy)
    TextView shiy;
    @BindView(R.id.zhekou)
    TextView zhekou;
    @BindView(R.id.zhekou_lin)
    LinearLayout zhekou_lin;
    @BindView(R.id.zk_name)
    TextView zk_name;
    @BindView(R.id.scroll)
    ScrollViewExtend scroll;
    @BindView(R.id.bar)
    RelativeLayout bar;

    @Override
    protected void setListener() {
        enter.setOnClickListener(v -> {
            startActivity(new Intent(Merchant_HomepageActivity.this, TenantActivity.class));
        });
    }

    public void share(View view) {
        if (shareDialog != null) {
            shareDialog.Show(view);
        }
    }

    public void go_home(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void finish(View view) {
        finish();
    }

    public void shiyong(View view) {
        Skip(RestaurantActivity.class, "sid", sid);
    }

    public void enter(View view) {
        Skip(EnterActivity.class);
    }

}