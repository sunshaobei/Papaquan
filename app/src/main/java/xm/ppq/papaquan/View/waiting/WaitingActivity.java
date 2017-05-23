package xm.ppq.papaquan.View.waiting;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Bean.life.Order_PayBean;
import xm.ppq.papaquan.Bean.life.WaittingData;
import xm.ppq.papaquan.Presenter.life.waittingforsue.PofWaitting;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.Image;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.tecenguide.TenCentGuideActivity;
import xm.ppq.papaquan.View.waiting.fragment.PassWordFragment;
import xm.ppq.papaquan.View.waiting.fragment.ScanCodeFragment;

/**
 * Created by 等待使用 on 2017/4/11.
 */

public class WaitingActivity extends BaseActivity implements DataUtil {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.right_image)
    ImageView right_image;
    @BindView(R.id.waiting_view_pager)
    ViewPager waiting_view_pager;
    @BindView(R.id.indicaor)
    Indicator indicaor;
    @BindView(R.id.pass_word)
    TextView pass_word;
    @BindView(R.id.scan_code)
    TextView scan_code;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.shopname)
    TextView shopname;
    @BindView(R.id.content_waiting)
    TextView contentWaiting;
    @BindView(R.id.price_now)
    TextView priceNow;
    @BindView(R.id.price_past)
    TextView pricePast;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.usetime)
    TextView usetime;
    @BindView(R.id.state)
    TextView state;
    @BindView(R.id.usesave)
    TextView usesave;
    @BindView(R.id.relative)
    RelativeLayout relative;

    private HomePagerAdapter adapter;
    private List<Fragment> fragments;
    private int orderid;
    private String url = BaseUrl.WAITTINGFORUSEINFO;
    private PofWaitting pofWaitting;
    private String type;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_waiting;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        type = getData("type");
        id = getIntent().getIntExtra("id", 0);
        orderid = getIntent().getIntExtra("orderid", 0);
        if (getData("url") != null) {
            url = getData("url");
        }
        right_image.setImageResource(R.mipmap.home);
        finish_result.setText("");
        center_result.setText("等待使用");
        indicaor.setCount(2);
        indicaor.setOffwidth(-15);
        indicaor.setBackcolor(Color.parseColor("#e4e4e4"));
        indicaor.setBackColors(Color.parseColor("#e4e4e4"));
        indicaor.setSelectColors(getResources().getColor(R.color.Red));
        pofWaitting = new PofWaitting(this);
        pofWaitting.getData(orderid);
    }

    @Override
    protected void setListener() {
        pass_word.setOnClickListener(v -> waiting_view_pager.setCurrentItem(0));
        scan_code.setOnClickListener(v -> waiting_view_pager.setCurrentItem(1));
        finish_result.setOnClickListener(v -> finish());
        waiting_view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicaor.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        pass_word.setTextColor(getResources().getColor(R.color.Red));
                        scan_code.setTextColor(Color.parseColor("#555555"));
                        break;
                    case 1:
                        scan_code.setTextColor(getResources().getColor(R.color.Red));
                        pass_word.setTextColor(Color.parseColor("#555555"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        right_image.setOnClickListener(v -> {
            setResult(IntentCode.ResultCode.BACKTOLIFE);
            finish();
        });
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setData(WaittingData.DataBean data) {
        shopname.setText(data.getShop_name());
        contentWaiting.setText(data.getPanic_info().getTitle());
        Glide.with(this).load(BaseUrl.BITMAP + data.getPanic_info().getImg()).into(icon);
        int is_vip = data.getIs_vip();
        if (is_vip == 1) {
            relative.setVisibility(View.VISIBLE);
            String buying_price = data.getPanic_info().getBuying_price();
            String vip_price = data.getPanic_info().getVip_price();
            usesave.setText("再省 ￥" + (Float.valueOf(buying_price) - Float.valueOf(vip_price)) + "");
            priceNow.setText(data.getPanic_info().getVip_price());
            money.setText("￥ " + data.getPanic_info().getVip_price());
        } else {
            priceNow.setText(data.getPanic_info().getBuying_price());
            money.setText("￥ " + data.getPanic_info().getBuying_price());
        }
        pricePast.setText(data.getPanic_info().getPrice());
        fragments = new ArrayList<>();
        fragments.add(new PassWordFragment<>(data.getClerk_list(),
                data.getPid(), data.getSid(), type, 0));
        fragments.add(new ScanCodeFragment(data.getQrcode(), data.getUsecode()));
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        waiting_view_pager.setAdapter(adapter);
        usetime.setText("请在" + data.getPanic_info().getConsumption_deadline() + "前使用");
        String uphour = data.getPanic_info().getUphour();
        Log.e(uphour, uphour);
        if (uphour.equals("0")) {
            state.setVisibility(View.VISIBLE);
        }
        lat = data.getLat();
        lng = data.getLng();
        address = data.getAddress();

    }

    @BindView(R.id.make_of_num)
    TextView make_of_num;
    @BindView(R.id.spec_num)
    TextView spec_num;
    @BindView(R.id.buy_num)
    TextView buy_num;
    @BindView(R.id.make_of_relative)
    RelativeLayout make_of_relative;
    @BindView(R.id.buy_relative)
    RelativeLayout buy_relative;
    @BindView(R.id.spec_relative)
    RelativeLayout spec_relative;

    @Override
    public void setBean(Order_PayBean bean) {
        make_of_relative.setVisibility(View.VISIBLE);
        buy_relative.setVisibility(View.VISIBLE);
        spec_relative.setVisibility(View.VISIBLE);
        Order_PayBean.DataBean dataBean = bean.getData();
        ImageLoading.common(this, BaseUrl.BITMAP + dataBean.getImg(), icon, R.mipmap.food);
        if (dataBean.getUphour().equals("0")) {
            state.setVisibility(View.VISIBLE);
        } else {
            state.setVisibility(View.GONE);
        }
        shopname.setText(dataBean.getName());
        contentWaiting.setText(dataBean.getTitle());
        make_of_num.setText(dataBean.getWaituse() + "份");
        spec_num.setText(dataBean.getSpectitle());
        buy_num.setText(String.valueOf(dataBean.getNum()));
        priceNow.setText("￥" + dataBean.getPrice());
        pricePast.setText(dataBean.getCostprice() + "元");
        money.setText("￥" + dataBean.getMoney());
        fragments = new ArrayList<>();
        fragments.add(new PassWordFragment<>(bean.getOther(), orderid, dataBean.getSid(), type, dataBean.getWaituse()));
        fragments.add(new ScanCodeFragment(bean.getData().getQrcode(), bean.getData().getUsecode()));
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        waiting_view_pager.setAdapter(adapter);
    }

    String lat;
    String lng;
    String address;

    public void guide(View v)
    {
        if (lat==null)
        {
            ToastShow("商家地址有误，请电话联系商家询问详细地址");
        }else {
            Intent intent = new Intent(this, TenCentGuideActivity.class);
            intent.putExtra("lat",lat);
            intent.putExtra("lng",lng);
            intent.putExtra("address",address);
            startActivity(intent);
        }

    }



}