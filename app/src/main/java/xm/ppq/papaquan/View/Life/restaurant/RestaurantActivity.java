package xm.ppq.papaquan.View.Life.restaurant;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.app.SunActivity;
import com.example.lib_sunshaobei2017.widget.ListView4ScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.CriticisnAdapter;
import xm.ppq.papaquan.Bean.life.RestaurantBean;
import xm.ppq.papaquan.Bean.life.UserRestBean;
import xm.ppq.papaquan.Presenter.life.restaurant.Mutual_Restaurant;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.appraise.AppraiseActivity;
import xm.ppq.papaquan.View.Life.goodscommet.GoodsCommentActivity;
import xm.ppq.papaquan.View.Life.restaurant.fragment.Dismiss;
import xm.ppq.papaquan.View.Life.restaurant.fragment.PswFragment;
import xm.ppq.papaquan.View.Life.shop_lodge.ShopLodgeActivity;
import xm.ppq.papaquan.View.Life.tecenguide.TenCentGuideActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;
import xm.ppq.papaquan.life.Tool.LinkSkip;

public class RestaurantActivity extends SunActivity implements Round_Restaurant, Dismiss {

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.downview)
    View downview;
    @BindView(R.id.pullup)
    View pullup;
    @BindView(R.id.mmhx)
    TextView mmhx;
    @BindView(R.id.smhx)
    TextView smhx;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.dialog)
    View dialog;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.criticism_list_view)
    ListView4ScrollView criticism_list_view;
    @BindView(R.id.scrollview)
    ScrollView scrollView;
    @BindView(R.id.lodge)
    TextView lodge;

    private String sid;
    private ShareDialog shareDialog;
    private Mutual_Restaurant mutual_restaurant;
    private SharedPreferencesPotting potting;
    private String pid;
    private String lat;
    private String lng;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restau_rant);
        ButterKnife.bind(this);
        sid = getIntent().getStringExtra("sid");
        url = "index/rebate/details?city=" + BaseUrl.citycode + "&sid=" + sid;
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_restaurant = new Mutual_Restaurant(this);
        initStatusBar(bar);
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mutual_restaurant.StartInfo();
    }

    private void initView() {
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new PswFragment(position, sid, pid);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        indicator.setCount(2);
        indicator.setSelectColors(Color.parseColor("#e60012"));
        indicator.setOffwidth(-70);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mmhx.setTextColor(Color.parseColor("#e60012"));
                    smhx.setTextColor(Color.parseColor("#000000"));
                } else {
                    smhx.setTextColor(Color.parseColor("#e60012"));
                    mmhx.setTextColor(Color.parseColor("#000000"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initListener() {
        lodge.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShopLodgeActivity.class);
            intent.putExtra("typeid", sid);
            intent.putExtra("type", "3");
            startActivity(intent);
        });
        look_all.setOnClickListener(v -> {
            Intent intent = new Intent(this, GoodsCommentActivity.class);
            intent.putExtra("url", BaseUrl.REBATE_APPLIST);
            intent.putExtra("pid", Integer.valueOf(sid));
            startActivity(intent);
        });
        TypewritingUtil typewritingUtil = new TypewritingUtil(this);
        typewritingUtil.addOnSoftKeyBoardVisibleListener(this, new TypewritingUtil.IKeyBoardVisibleListener() {
            @Override
            public void onSoftKeyBoardVisible(boolean visible, int keyboardHeight) {
                if (visible) {
                    if (keyboardHeight > downview.getHeight()) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pullup.getLayoutParams();
                        layoutParams.height = keyboardHeight;
                        pullup.setLayoutParams(layoutParams);
                    }
                } else {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pullup.getLayoutParams();
                    layoutParams.height = 280;
                    pullup.setLayoutParams(layoutParams);
                }
            }
        });
        mmhx.setOnClickListener(v -> {
            viewpager.setCurrentItem(0);
            mmhx.setTextColor(Color.parseColor("#e60012"));
            smhx.setTextColor(Color.parseColor("#000000"));
        });
        smhx.setOnClickListener(v -> {
            viewpager.setCurrentItem(1);
            smhx.setTextColor(Color.parseColor("#e60012"));
            mmhx.setTextColor(Color.parseColor("#000000"));
        });
        buy_card.setOnClickListener(v -> {
            mutual_restaurant.StartInfo();
            Intent intent = new Intent(this, RedCardActivity.class);
            startActivity(intent);
        });
    }

    /*************************
     * 点击事件
     *****************************/
    public void finish(View v) {
        finish();
    }

    public void share(View v) {
        //TODO
        if (shareDialog != null)
            shareDialog.Show(v);
    }

    private String url;

    public void backtohome(View v) {
        finish();
    }

    public void showdialog(View v) {
        if (potting.isLogin()) {
            ToastShow("您尚未登录");
        } else {
            if (show.getText().equals("核销使用")) {
                isshow = true;
                dialog.setVisibility(View.VISIBLE);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(dialog, "alpha", 0, 0.5f, 0.7f, 1);
                alpha.setDuration(500);
                alpha.start();
            } else if (show.getText().equals("开通红卡")) {
                Intent intent = new Intent(this, RedCardActivity.class);
                startActivity(intent);
            } else if (show.getText().equals("去评价")) {
                Intent intent = new Intent(this, AppraiseActivity.class);
                intent.putExtra("type", "折扣");
                intent.putExtra("pid", Integer.valueOf(pid));
                intent.putExtra("sid", Integer.valueOf(sid));
                intent.putExtra("title", app_title);
                intent.putExtra("name", app_name);
                startActivity(intent);
            } else if (show.getText().equals("已使用")) {

            } else {

            }
        }
    }

    public void dialogmiss(View v) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pullup.getLayoutParams();
        layoutParams.height = 280;
        pullup.setLayoutParams(layoutParams);
        EmotionPopupWindow.HideKeyboard(edit);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(dialog, "alpha", 1, 0.7f, 0.5f, 0);
        alpha.setDuration(500);
        alpha.start();
        dialog.postDelayed(() -> dialog.setVisibility(View.GONE), 500);
    }

    public void dismiss(View v) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pullup.getLayoutParams();
        layoutParams.height = 280;
        pullup.setLayoutParams(layoutParams);
        EmotionPopupWindow.HideKeyboard(edit);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(dialog, "alpha", 1, 0.7f, 0.5f, 0);
        alpha.setDuration(500);
        alpha.start();
        dialog.postDelayed(() -> dialog.setVisibility(View.GONE), 500);
    }

    boolean isshow = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isshow) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pullup.getLayoutParams();
                layoutParams.height = 280;
                pullup.setLayoutParams(layoutParams);
                EmotionPopupWindow.HideKeyboard(edit);
                ObjectAnimator alpha = ObjectAnimator.ofFloat(dialog, "alpha", 1, 0.7f, 0.5f, 0);
                alpha.setDuration(500);
                alpha.start();
                dialog.postDelayed(() -> dialog.setVisibility(View.GONE), 500);
                isshow = false;
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getApp_Title() {
        return app_title;
    }

    @Override
    public String getName() {
        return app_name;
    }

    @BindView(R.id.shop_name)
    TextView shop_name;
    @BindView(R.id.rr_icon)
    ImageView rr_icon;
    @BindView(R.id.time_at_term)
    TextView time_at_term;
    @BindView(R.id.make_user_of)
    TextView make_user_of;
    @BindView(R.id.store_name)
    TextView store_name;
    @BindView(R.id.store_address)
    TextView store_address;
    @BindView(R.id.office_hours)
    TextView office_hours;
    @BindView(R.id.follow_num)
    TextView follow_num;
    @BindView(R.id.comment_num)
    TextView comment_num;
    @BindView(R.id.restau_num)
    TextView restau_num;
    @BindView(R.id.card_num)
    TextView card_num;
    @BindView(R.id.web_view)
    WebView web_view;
    @BindView(R.id.redcard)
    ImageView redcard;
    @BindView(R.id.buy_card)
    TextView buy_card;
    @BindView(R.id.show)
    TextView show;
    @BindView(R.id.look_all)
    TextView look_all;
    @BindView(R.id.phone)
    View phone;
    @BindView(R.id.comment_rela)
    RelativeLayout comment_rela;

    private String app_name;
    private String app_title;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void setInfo(RestaurantBean.DataBean dataBean) {
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("折详", url, BaseUrl.BITMAP + dataBean.getLogo(), dataBean.getTitle() + "-" + dataBean.getName());
        app_name = dataBean.getName();
        app_title = dataBean.getTitle();
        lat = dataBean.getLat();
        lng = dataBean.getLng();
        address = dataBean.getAddress();
        pid = dataBean.getUse();
        card_num.setText(dataBean.getCardnum());
        if (dataBean.getStatus() == 1) {//红卡未到期
            buy_card.setVisibility(View.INVISIBLE);
            show.setText("核销使用");
            time_at_term.setVisibility(View.VISIBLE);
            time_at_term.setText(String.valueOf(dataBean.getVip_end()));
            redcard.setImageDrawable(getResources().getDrawable(R.drawable.red_card_double));
        } else if (dataBean.getStatus() == 0) {//红卡已到期或者不是红卡
            show.setText("开通红卡");
            time_at_term.setVisibility(View.INVISIBLE);
            buy_card.setVisibility(View.VISIBLE);
            redcard.setImageDrawable(getResources().getDrawable(R.mipmap.redcardgray));
        } else if (dataBean.getStatus() == 2) {//去评价
            show.setText("去评价");
        } else if (dataBean.getStatus() == 3) {//已使用
            show.setText("已使用");
        } else {//已过期
            show.setText("已过期");
        }
        title.setText(dataBean.getName());
        store_name.setText(dataBean.getName());
        shop_name.setText(dataBean.getTitle());
        ImageLoading.Circular(this, dataBean.getHeadurl(), R.drawable.default_icon, rr_icon);
        make_user_of.setText("使用:" + dataBean.getUsenum());
        store_address.setText(address);
        if (!dataBean.getBusiness_hours().equals("")) {
            office_hours.setText("营业时间:" + dataBean.getBusiness_hours());
        } else {
            office_hours.setText("营业时间:未知");
        }
        follow_num.setText("关注:" + dataBean.getBrowse());
        if (Integer.valueOf(dataBean.getAppnum()) > 0) {
            comment_num.setText("评论(" + dataBean.getAppnum() + ")");
        } else {
            comment_rela.setVisibility(View.GONE);
        }
        restau_num.setText(dataBean.getRebate() + "折");
        if (Integer.valueOf(dataBean.getAppnum()) > 3) {
            look_all.setText("全看全部（" + dataBean.getAppnum() + "条）");
        } else {
            look_all.setVisibility(View.GONE);
        }
        if (dataBean.getContent() != null) {
            String html = dataBean.getContent().replaceAll("<img", "<img width=100%");
            web_view.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        }
        phone.setOnClickListener(v -> {
            LinkSkip.Phone(RestaurantActivity.this, dataBean.getTel());
        });
    }

    @Override
    public void setList(ArrayList<UserRestBean.OtherBean> otherBeen) {

    }

    @Override
    public void setData(UserRestBean.DataBean data) {

    }

    private CriticisnAdapter adapter;

    @Override
    public void setOther(ArrayList<RestaurantBean.Other> other) {
        if (adapter == null) {
            adapter = new CriticisnAdapter(this, other, R.layout.item_goodscommentlistview);
            criticism_list_view.setAdapter(adapter);
        } else {
            adapter.setList(other);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void ToastShow(String result) {

    }

    @Override
    public void setUse(int i, String title) {
        pid = String.valueOf(i);
        show.setText(title);
    }

    @Override
    public void diss() {
        dialog.setVisibility(View.GONE);
        isshow = false;
    }

    public void guide(View view) {
        Intent intent = new Intent(this, TenCentGuideActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        intent.putExtra("address", address);
        startActivity(intent);
    }
}