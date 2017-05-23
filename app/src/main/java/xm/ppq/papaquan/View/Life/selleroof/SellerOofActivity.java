package xm.ppq.papaquan.View.Life.selleroof;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.example.lib_sunshaobei2017.widget.ViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.mybanner.Mybanner;
import myview.mybanner.OnItemClickListener;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.NewSellerOofBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.SearchSellerData;
import xm.ppq.papaquan.Bean.life.SeventhBean;
import xm.ppq.papaquan.Bean.life.TopSelleOofBean;
import xm.ppq.papaquan.Presenter.life.sller_oof.Mutual_Seller_Oof;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;
import xm.ppq.papaquan.View.Life.selleroof.fragment.PaiHangFragment;
import xm.ppq.papaquan.life.Tool.LinkSkip;

/**
 * Created by 商家114 on 2017/4/13.
 */

public class SellerOofActivity extends BaseActivity implements Round_SellerOof {

    private HomePagerAdapter adapter;
    private List<Fragment> fragments;
    private PaiHangFragment paiHangFragment;
    private ArrayList<String> top;

    @BindView(R.id.banner)
    Mybanner banner;
    @BindView(R.id.idcator)
    Indicator indicator;
    @BindView(R.id.seller_oof_view)
    ViewPager sellerOofView;
    @BindView(R.id.week_paihang)
    TextView weekPaihang;
    @BindView(R.id.moon_paihang)
    TextView moonPaihang;
    @BindView(R.id.year_paihang)
    TextView yearPaihang;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.new_enter)
    ViewFlipper new_enter;

    @BindView(R.id.faceview)
    View searchview;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.hideedit_hint)
    View hideedit_hint;
    @BindView(R.id.dismiss)
    View dismiss;
    @BindView(R.id.hidetext)
    TextView hidetext;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.backtotop)
    View backtotop;

    private Mutual_Seller_Oof mutual_seller_oof;
    private SharedPreferencesPotting potting;
    private SharedPreferencesPotting my_login;
    private SearchAdapter searchAdapter;
    private SellersortAdapter sellersortAdapter;
    private ShareDialog shareDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_selleroof;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("114", url, BaseUrl.Image_url);
        mutual_seller_oof = new Mutual_Seller_Oof(this);
        mutual_seller_oof.getCarousel_Figure();
        mutual_seller_oof.getNewEnter();
        mutual_seller_oof.getTradeClassify();
        indicator.setCount(3);
        indicator.setOffwidth(-15);
        indicator.setBackcolor(Color.parseColor("#e4e4e4"));
        indicator.setBackColors(Color.parseColor("#e4e4e4"));
        indicator.setSelectColors(getResources().getColor(R.color.Red));
        my_login = new SharedPreferencesPotting(this, "my_login");
        fragments = new ArrayList<>();
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 3; i++) {
            Bundle bundle = null;
            switch (i) {
                case 0:
                    bundle = new Bundle();
                    bundle.putString("title", "周排行");
                    break;
                case 1:
                    bundle = new Bundle();
                    bundle.putString("title", "月排行");
                    break;
                case 2:
                    bundle = new Bundle();
                    bundle.putString("title", "年排行");
                    break;
            }
            paiHangFragment = new PaiHangFragment();
            paiHangFragment.setArguments(bundle);
            fragments.add(paiHangFragment);
        }
        adapter.setFragments(fragments);
        sellerOofView.setAdapter(adapter);
        TextViewColor(sellerOofView.getCurrentItem(), weekPaihang, moonPaihang, yearPaihang);

        navigation_indicator.setIndicatorColor(Color.parseColor("#cdcdcd"), Color.TRANSPARENT, Color.parseColor("#e60012"));
        navigation_indicator.setRadiusSize(8);
        navigation_indicator.setIndicatorCount(1);

        searchAdapter = new SearchAdapter(SellerOofActivity.this, searchlist, R.layout.paihang_item);
        listview.setAdapter(searchAdapter);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(getActivity(), 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(getActivity()));
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                searchSellerData(s);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                searchSellerData(s);
            }
        });
        backtotop.setOnClickListener(v -> {
            listview.smoothScrollToPosition(0);
            backtotop.setVisibility(View.GONE);
        });

        xRefreshView.setOnAbsListViewScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 3) {
                    backtotop.setVisibility(View.VISIBLE);
                } else backtotop.setVisibility(View.GONE);
            }
        });

        for (int i = 0; i < 10; i++) {
            list.add(new TypeClassfiyBean.DataBean());
        }
        sellersortAdapter = new SellersortAdapter(getSupportFragmentManager(), list);
        trade_viewpager.setAdapter(sellersortAdapter);

    }

    private String s;
    private ArrayList<SearchSellerData.DataBean> searchlist = new ArrayList<>();

    @Override
    protected void setListener() {
        trade_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        weekPaihang.setOnClickListener(v -> sellerOofView.setCurrentItem(0));
        moonPaihang.setOnClickListener(v -> sellerOofView.setCurrentItem(1));
        yearPaihang.setOnClickListener(v -> sellerOofView.setCurrentItem(2));
        finish.setOnClickListener(v -> finish());
        sellerOofView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                TextViewColor(sellerOofView.getCurrentItem(), weekPaihang, moonPaihang, yearPaihang);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //搜索
        search.setOnClickListener(v -> {
            isshow = true;
            searchview.setVisibility(View.VISIBLE);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alpha = ObjectAnimator.ofFloat(searchview, "alpha", 0, 0.5f, 0.7f, 1f);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(searchview, "translationY", -3000, 0);
            animatorSet.setDuration(300);
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.play(alpha).with(translationY);//两个动画同时开始
            animatorSet.start();
        });
        dismiss.setOnClickListener(v -> {
            dismissSearchview();
        });
        hidetext.setOnClickListener(v -> {
            search_edit.setText("");
            hideedit_hint.setVisibility(View.VISIBLE);
            hidetext.setVisibility(View.GONE);
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    xRefreshView.setVisibility(View.VISIBLE);
                    hidetext.setVisibility(View.VISIBLE);
                    hideedit_hint.setVisibility(View.GONE);
                    SellerOofActivity.this.s = s.toString();
                    searchSellerData(s.toString());
                } else {
                    xRefreshView.setVisibility(View.GONE);
                    hideedit_hint.setVisibility(View.VISIBLE);
                    searchSellerData("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent1 = new Intent(SellerOofActivity.this, Merchant_HomepageActivity.class);
            intent1.putExtra("sid", String.valueOf(searchlist.get(position).getId()));
            startActivity(intent1);
        });


    }

    private boolean isshow = false;

    private void dismissSearchview() {
        isshow = false;
        EventBus.getDefault().post("dismisstrue");
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(searchview, "alpha", 1, 0.5f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(searchview, "translationY", 0, -3000);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(alpha).with(translationY);//两个动画同时开始
        animatorSet.start();
        EmotionPopupWindow.HideKeyboard(search_edit);
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    /**
     * 获得顶部轮播图
     *
     * @param dataBean
     */
    @Override
    public void setTop(ArrayList<TopSelleOofBean.DataBean> dataBean) {
        top = new ArrayList<>();
        for (TopSelleOofBean.DataBean bean : dataBean) {
            top.add(BaseUrl.BITMAP + bean.getImg());
        }
        banner.setBanner(getSupportFragmentManager(), top);
        banner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                int link_type = dataBean.get(position).getLink_type();
                String link = dataBean.get(position).getLink();
                int link_val = dataBean.get(position).getLink_val();
                if (link_type == 0) {
                    LinkSkip.Link(SellerOofActivity.this, link_val, dataBean.get(position).getSingleid());
                } else {
                    LinkSkip.Go2Chrome(getActivity(), link);
                }
            }
        });
    }

    /**
     * 获得商家入驻
     *
     * @param dataBeen
     */
    @Override
    public void setNesEnter(ArrayList<NewSellerOofBean.DataBean> dataBeen) {
        for (NewSellerOofBean.DataBean dataBean : dataBeen) {
            View view = LayoutInflater.from(this).inflate(R.layout.new_enter, null);
            TextView textView = (TextView) view.findViewById(R.id.sajdisao);
            textView.setText("恭喜【" + dataBean.getName() + "】成功入驻");
            new_enter.addView(view);
        }
        new_enter.setInAnimation(this, R.anim.viewflipper_in);
        new_enter.setOutAnimation(this, R.anim.viewflipper_out);
        new_enter.startFlipping();
    }

    @BindView(R.id.trade_viewpager)
    ViewPager trade_viewpager;
    @BindView(R.id.navigation_indicator)
    ViewPagerIndicator navigation_indicator;


    private ArrayList<TypeClassfiyBean.DataBean> list = new ArrayList<>();

    @Override
    public void setClassify(ArrayList<TypeClassfiyBean.DataBean> dataBeen) {
        if (dataBeen != null) {
            int size = dataBeen.size() / 10;
            if (dataBeen.size() % 10 > 0)
                size = size + 1;
            navigation_indicator.setIndicatorCount(size);
            list.clear();
            list.addAll(dataBeen);
            //TODO
            sellersortAdapter.notifyDataSetChanged();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post("114");
                }
            });
        }
    }

    @Override
    public void setSeventh(ArrayList<SeventhBean.DataBean> dataBeen) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isshow) {
                dismissSearchview();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    int page;

    public void searchSellerData(String s) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", my_login.getItemString("citycode"))
                    .put("page", page)
                    .put("length", 10)
                    .put("search", s);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("shop/search", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        SearchSellerData data = (SearchSellerData) JsonUtil.fromJson(o, SearchSellerData.class);
                        if (data != null && data.getData().size() > 0) {
                            if (page == 0)
                                searchlist.clear();
                            searchlist.addAll(data.getData());
                            searchAdapter.notifyDataSetChanged();
                            xRefreshView.stopLoadMore();
                            xRefreshView.stopRefresh();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void go_home(View view) {
        finish();
    }

    private String url = "index/shop/index?city=" + BaseUrl.citycode;

    public void share(View view) {
        shareDialog.Show(view);
    }

    public void enter(View view) {
        Skip(TenantActivity.class);
    }
}