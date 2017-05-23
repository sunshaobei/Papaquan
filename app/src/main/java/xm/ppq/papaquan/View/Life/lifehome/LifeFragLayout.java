package xm.ppq.papaquan.View.Life.lifehome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.lib_sunshaobei2017.widget.ViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myview.mybanner.Mybanner;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.Presenter.life.lifehome.PofLifeHome;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.Tool.customview.TimeCountDownView.CountdownView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.Canvass_Business;
import xm.ppq.papaquan.View.Life.discountNchoose.DiscountAndChooseActivity;
import xm.ppq.papaquan.View.Life.lifehome.fragment.LifeChooseFragment;
import xm.ppq.papaquan.View.Life.lifehome.fragment.LifeDiscountFragment;
import xm.ppq.papaquan.View.Life.lifehome.searchfragment.SearchGoodsActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;
import xm.ppq.papaquan.View.Life.scare_past.Scare_PastActivity;
import xm.ppq.papaquan.View.city_search.CitySearchActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LifeFragLayout extends Fragment implements SetData {

    @BindView(R.id.locationicon)
    ImageView locationicon;
    @BindView(R.id.location)
    TextView location;
    private LayoutInflater inflater;
    private View fragment;
    @BindView(R.id.countdownview)
    CountdownView countdownView;
    @BindView(R.id.banner)
    Mybanner mybanner;
    @BindView(R.id.navigation_viewpager)
    ViewPager navigation_viewpager;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.share)
    View share;
    @BindView(R.id.more_text)
    TextView moreText;
    @BindView(R.id.scare_past_lin)
    View scareLin;
    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.today_content)
    TextView today_content;
    @BindView(R.id.today_icon)
    ImageView today_icon;
    @BindView(R.id.today_price)
    TextView today_price;
    @BindView(R.id.old_today_price)
    TextView old_today_price;
    @BindView(R.id.middle1)
    View middle1;
    @BindView(R.id.middle1_title)
    TextView middle1_title;
    @BindView(R.id.middle1_subtitle)
    TextView middle1_subtitle;
    @BindView(R.id.middle1_image)
    ImageView middle1_image;
    @BindView(R.id.middle2)
    View middle2;
    @BindView(R.id.middle2_title)
    TextView middle2_title;
    @BindView(R.id.middle2_subtitle)
    TextView middle2_subtitle;
    @BindView(R.id.middle2_image)
    ImageView middle2_image;
    @BindView(R.id.middle3)
    View middle3;
    @BindView(R.id.middle3_title)
    TextView middle3_title;
    @BindView(R.id.middle3_subtitle)
    TextView middle3_subtitle;
    @BindView(R.id.middle3_image)
    ImageView middle3_image;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.zhekou)
    RadioButton zhekou;
    @BindView(R.id.jingxuan)
    RadioButton jingxuan;
    @BindView(R.id.idcator)
    Indicator indicator;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.discount_text)
    TextView discount_text;
    @BindView(R.id.navigation_indicator)
    ViewPagerIndicator navigation_indicator;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.new_enter)
    LinearLayout new_enter;
    @BindView(R.id.title)
    FrameLayout title;
    @BindView(R.id.shade)
    View shade;

    private PofLifeHome pofLifeHome;
    private ArrayList<LifeHomeData.DataBean.NavigationIconBean> navigationIconList = new ArrayList<>();
    private int end_time;
    private SharedPreferencesPotting my_login;
    private HomePagerAdapter BottomAdapter;
    private ArrayList<Fragment> fragments;
    private NavigationAdapter navigationAdapter;
    private ShareDialog shareDialog;
    private List<LifeHomeData.DataBean.BannerBean> banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (fragment == null) {
            this.inflater = inflater;
            fragment = inflater.inflate(R.layout.fragment_life, container, false);
            ButterKnife.bind(this, fragment);
            my_login = new SharedPreferencesPotting(getActivity(), "my_login");
            shareDialog = new ShareDialog(getContext(), R.layout.deteil_share);
            shareDialog.setStatus("生活", url, BaseUrl.Image_url);
            initView();
            initListener();
            MainActivity activity = (MainActivity) getActivity();
            activity.initStatusBar(bar);
            pofLifeHome = new PofLifeHome(getActivity(), this);
            pofLifeHome.getData();
        } else {
            if (banner != null) {
                mybanner.setBanner(getChildFragmentManager(), list);
                mybanner.setOnItemClickListener(position -> {
                    int link_type = banner.get(position).getLink_type();
                    String link = banner.get(position).getLink();
                    int link_val = banner.get(position).getLink_val();
                    if (link_type == 0) {
                        LinkSkip.Link(getContext(), link_val, banner.get(position).getSingleid());
                    } else {
                        LinkSkip.Go2Chrome(getActivity(), link);
                    }
                });
            }
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return fragment;
    }

    private ArrayList<String> list = new ArrayList<>();
    private LifeDiscountFragment discountFragment;
    private LifeChooseFragment chooseFragment;

    @Override
    public void onResume() {
        super.onResume();
        long nowtime = System.currentTimeMillis();
        long time = end_time * 1000l;
        time = time - nowtime;
        countdownView.start(time);
        if (fragments == null) {
            fragments = new ArrayList<>();
            discountFragment = new LifeDiscountFragment();
            chooseFragment = new LifeChooseFragment();
            fragments.add(discountFragment);
            fragments.add(chooseFragment);
        }
        if (BottomAdapter == null) {
            BottomAdapter = new HomePagerAdapter(getChildFragmentManager());
            BottomAdapter.setFragments(fragments);
            view_pager.setAdapter(BottomAdapter);
        } else {
            BottomAdapter.setFragments(fragments);
            BottomAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        String cityname = my_login.getItemString("cityname");
        if (!TextUtils.isEmpty(cityname))
            location.setText(cityname);
        indicator.setCount(2);
        indicator.setOffwidth(-60);
        for (int i = 0; i < 10; i++) {
            LifeHomeData.DataBean.NavigationIconBean navigationIconBean = new LifeHomeData.DataBean.NavigationIconBean();
            navigationIconList.add(navigationIconBean);
        }
        navigationAdapter = new NavigationAdapter(getChildFragmentManager());
        navigation_viewpager.setAdapter(navigationAdapter);
        navigation_indicator.setIndicatorColor(Color.parseColor("#cdcdcd"), Color.TRANSPARENT, Color.parseColor("#e60012"));
        navigation_indicator.setRadiusSize(8);
        navigation_indicator.setIndicatorCount(1);
        navigation_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                navigation_indicator.move(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class NavigationAdapter extends FragmentPagerAdapter {
        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new NavigationFragment(navigationIconList, position);
        }

        @Override
        public int getCount() {
            int i1 = navigationIconList.size() / 10;
            int i = navigationIconList.size() % 10;
            if (i > 0)
                i1 = i1 + 1;
            return i1;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

    private void initListener() {
        shade.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), Canvass_Business.class));
        });
        search.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SearchGoodsActivity.class));
        });
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        zhekou.setChecked(true);
                        jingxuan.setChecked(false);
                        break;
                    case 1:
                        zhekou.setChecked(false);
                        jingxuan.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        zhekou.setOnClickListener(v -> {
            if (view_pager.getCurrentItem() != 0) {
                view_pager.setCurrentItem(0);
            }
        });
        jingxuan.setOnClickListener(v -> {
            if (view_pager.getCurrentItem() != 1) {
                view_pager.setCurrentItem(1);
            }
        });
        moreText.setOnClickListener(v -> getActivity().startActivity(new Intent(getActivity(), DiscountAndChooseActivity.class)));
        scareLin.setOnClickListener(v -> startActivity(new Intent(getActivity(), Scare_PastActivity.class)));
        share.setOnClickListener(v -> {
            shareDialog.Show(v);
        });
        location.setOnClickListener(v -> getActivity().startActivityForResult(new Intent(getActivity(), CitySearchActivity.class), IntentCode.RequestCode.TOCITYSEARCH));
        locationicon.setOnClickListener(v -> getActivity().startActivityForResult(new Intent(getActivity(), CitySearchActivity.class), IntentCode.RequestCode.TOCITYSEARCH));
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
        });
    }

    private String url = "index/life/index?city=" + BaseUrl.citycode;

    @Override
    public void ShowInfo(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(LifeHomeData data) {
        //banner
        banner = data.getData().getBanner();
        list.clear();
        for (int i = 0; i < banner.size(); i++) {
            list.add(BaseUrl.BITMAP + banner.get(i).getImg());
        }
        mybanner.setBanner(getChildFragmentManager(), list);

        mybanner.setOnItemClickListener(position -> {
            int link_type = banner.get(position).getLink_type();
            String link = banner.get(position).getLink();
            int link_val = banner.get(position).getLink_val();
            if (link_type == 0) {
                LinkSkip.Link(getContext(), link_val, banner.get(position).getSingleid());
            } else {
                LinkSkip.Go2Chrome(getActivity(), link);
            }
        });
        //gridview
        if (data.getData().getNavigation_icon().size() > 0) {
            navigationIconList.clear();
            navigationIconList.addAll(data.getData().getNavigation_icon());
            navigationAdapter.notifyDataSetChanged();
            int i1 = navigationIconList.size() / 10;
            int i = navigationIconList.size() % 10;
            if (i > 0) {
                i1 = i1 + 1;
            }
            navigation_indicator.setIndicatorCount(i1);
            EventBus.getDefault().post("navigation");
        }
        if (data.getData().getNew_shop().size() == 0 && data.getData().getTodaypanic() == null) {
            new_enter.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            shade.setVisibility(View.VISIBLE);
            view_pager.setVisibility(View.GONE);
            scareLin.setVisibility(View.GONE);
        } else {
            shade.setVisibility(View.GONE);
            scareLin.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            view_pager.setVisibility(View.VISIBLE);
            new_enter.setVisibility(View.VISIBLE);
            //viewflipper
            List<LifeHomeData.DataBean.NewShopBean> new_shop = data.getData().getNew_shop();
            for (int i = 0; i < new_shop.size(); i++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.new_enter, null);
                TextView textView = (TextView) view.findViewById(R.id.sajdisao);
                textView.setText("恭喜【" + new_shop.get(i).getName() + "】入驻");
                viewFlipper.addView(view);
            }
            viewFlipper.setInAnimation(getActivity(), R.anim.viewflipper_in);
            viewFlipper.setOutAnimation(getActivity(), R.anim.viewflipper_out);
            viewFlipper.startFlipping();
            //中间三个item
            List<LifeHomeData.DataBean.MiddleIconBean> middle_icon = data.getData().getMiddle_icon();
            if (middle_icon.size() >= 3) {
                Glide.with(getActivity()).load(BaseUrl.BITMAP + middle_icon.get(0).getImg()).into(middle1_image);
                Glide.with(getActivity()).load(BaseUrl.BITMAP + middle_icon.get(1).getImg()).into(middle2_image);
                Glide.with(getActivity()).load(BaseUrl.BITMAP + middle_icon.get(2).getImg()).into(middle3_image);
                if (middle_icon.get(0).getLink_type() == 1) {//外链
                    url_1 = middle_icon.get(0).getLink();
                } else {//内链
                    middle_1 = middle_icon.get(0).getLink_val();
                    id_1 = middle_icon.get(0).getSingleid();
                }
                if (middle_icon.get(1).getLink_type() == 1) {//外链
                    url_2 = middle_icon.get(0).getLink();
                } else {//内链
                    middle_2 = middle_icon.get(1).getLink_val();
                    id_2 = middle_icon.get(1).getSingleid();
                }
                if (middle_icon.get(2).getLink_type() == 1) {//外链
                    url_3 = middle_icon.get(0).getLink();
                } else {//内链
                    middle_3 = middle_icon.get(2).getLink_val();
                    id_3 = middle_icon.get(2).getSingleid();
                }
                middle1_title.setText(middle_icon.get(0).getTitle());
                middle2_title.setText(middle_icon.get(1).getTitle());
                middle3_title.setText(middle_icon.get(2).getTitle());
                middle1_subtitle.setText(middle_icon.get(0).getStitle());
                middle2_subtitle.setText(middle_icon.get(1).getStitle());
                middle3_subtitle.setText(middle_icon.get(2).getStitle());
            }
        }
        if (data.getData().getTodaypanic() == null) {
            scareLin.setVisibility(View.GONE);
        } else {
            scareLin.setVisibility(View.VISIBLE);
            //今日抢购
            LifeHomeData.DataBean.TodaypanicBean todaypanic = data.getData().getTodaypanic();
            today_content.setText(todaypanic.getTitle());
            discount_text.setText(todaypanic.getDiscount() + "折");
            Glide.with(getActivity()).load(BaseUrl.BITMAP + todaypanic.getImg()).into(today_icon);
            today_price.setText("￥ " + todaypanic.getBuying_price());
            old_today_price.setText(todaypanic.getPrice() + "元");
            end_time = todaypanic.getEnd_time();
            long l = System.currentTimeMillis();
            long l1 = end_time * 1000l - l;
            countdownView.start(l1);
        }
    }

    private int middle_1;
    private int middle_2;
    private int middle_3;
    private String url_1;
    private String url_2;
    private String url_3;
    private Object id_1;
    private Object id_2;
    private Object id_3;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (s.equals("刷新")) {
            pofLifeHome.getData();
            location.setText(my_login.getItemString("cityname"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.sssssss)
    public void wxpay(View view) {
        Intent intent = new Intent(getContext(), TenantActivity.class);
        getActivity().startActivity(intent);
    }

    @OnClick({R.id.middle1, R.id.middle2, R.id.middle3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.middle1:
                if (url_1 != null) {
                    LinkSkip.Go2Chrome(getActivity(), url_1);
                } else {
                    LinkSkip.Link(getContext(), middle_1, id_1);
                }
                break;
            case R.id.middle2:
                if (url_2 != null) {
                    LinkSkip.Go2Chrome(getActivity(), url_2);
                } else {
                    LinkSkip.Link(getContext(), middle_2, id_2);
                }
                break;
            case R.id.middle3:
                if (url_3 != null) {
                    LinkSkip.Go2Chrome(getActivity(), url_3);
                } else {
                    LinkSkip.Link(getContext(), middle_3, id_3);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOMINE) {
            ((MainActivity) getActivity()).setCurrentItem(3);
        }
    }
}