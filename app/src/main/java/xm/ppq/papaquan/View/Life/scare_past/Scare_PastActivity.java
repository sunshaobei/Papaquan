package xm.ppq.papaquan.View.Life.scare_past;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.lib_sunshaobei2017.widget.ViewPagerIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.Scare_PastBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.scare_past.fragment.Sca_PasFragment;
import xm.ppq.papaquan.life.Tool.BannitHolder;

/**
 * Created by 抢购和往期抢购 on 2017/4/17.
 */

public class Scare_PastActivity extends BaseActivity {

    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.title_scare_past)
    LinearLayout title_scare_past;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.convenient_indicator)
    ViewPagerIndicator convenient_indicator;
    @BindView(R.id.scare_text)
    TextView scare_text;
    @BindView(R.id.past_text)
    TextView past_text;
    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.finish_head)
    ImageView finishhead;
    @BindView(R.id.share_head)
    ImageView sharehead;
    @BindView(R.id.home_head)
    ImageView homehead;
    @BindView(R.id.titlebg)
    View titlebg;

    private ArrayList<String> piclist = new ArrayList<>();
    private HomePagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private Sca_PasFragment sca_pasFragment;
    private Bundle bundle;
    private boolean appbarisshow = true;
    private ShareDialog shareDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_scare_past;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.setStatus("抢购", url, BaseUrl.Image_url);
        fragments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            bundle = new Bundle();
            sca_pasFragment = new Sca_PasFragment();
            if (i == 0) {
                bundle.putString("type", "now");
            } else {
                bundle.putString("type", "past");
            }
            sca_pasFragment.setArguments(bundle);
            fragments.add(sca_pasFragment);
        }
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        view_pager.setAdapter(adapter);
        TextSelect(view_pager.getCurrentItem(), scare_text, past_text);
        indicator.setCount(fragments.size());
        indicator.setOffwidth(-15);
        indicator.setBackcolor(Color.parseColor("#e4e4e4"));
        indicator.setBackColors(Color.parseColor("#e4e4e4"));
        indicator.setSelectColors(getResources().getColor(R.color.Red));
        convenient_indicator.setindicatorStyle(ViewPagerIndicator.Line);
    }

    private String url = "index/panicbuy/panicList?city=" + BaseUrl.citycode;

    @Override
    protected void setListener() {
        sharehead.setOnClickListener(v -> shareDialog.Show(v));
        homehead.setOnClickListener(v -> finish());
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                if (!appbarisshow) {
                    if (position == 0) {
                        scare_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_true));
                        scare_text.setTextColor(Color.parseColor("#e60012"));
                        past_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_false));
                        past_text.setTextColor(Color.WHITE);
                    } else {
                        scare_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_false));
                        scare_text.setTextColor(Color.WHITE);
                        past_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_true));
                        past_text.setTextColor(Color.parseColor("#e60012"));
                    }
                } else {
                    if (position == 0) {
                        scare_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                        scare_text.setTextColor(Color.parseColor("#e60012"));
                        past_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                        past_text.setTextColor(Color.parseColor("#555555"));
                    } else {
                        past_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                        past_text.setTextColor(Color.parseColor("#e60012"));
                        scare_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                        scare_text.setTextColor(Color.parseColor("#555555"));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        scare_text.setOnClickListener(v -> view_pager.setCurrentItem(0));
        past_text.setOnClickListener(v -> view_pager.setCurrentItem(1));
        appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (Math.abs(verticalOffset) < 280) {
                appbarisshow = true;
                bar.setAlpha((float) Math.abs(verticalOffset) / 280);
                finishhead.setPadding(0, 0, 0, 0);
                finishhead.setImageResource(R.mipmap.finish_2);
                sharehead.setPadding(0, 0, 0, 0);
                sharehead.setImageResource(R.mipmap.share_2);
                homehead.setPadding(0, 0, 0, 0);
                homehead.setImageResource(R.mipmap.home_2);
                titlebg.setAlpha((float) Math.abs(verticalOffset) / 280);
                indicator.setVisibility(View.VISIBLE);
                scare_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                past_text.setBackground(new ColorDrawable(Color.TRANSPARENT));
                if (view_pager.getCurrentItem() == 0) {
                    scare_text.setTextColor(Color.parseColor("#e60012"));
                    past_text.setTextColor(Color.parseColor("#555555"));
                } else {
                    past_text.setTextColor(Color.parseColor("#e60012"));
                    scare_text.setTextColor(Color.parseColor("#555555"));
                }
            } else {
                appbarisshow = false;
                bar.setAlpha(1);
                titlebg.setAlpha(1);
                finishhead.setPadding(10, 10, 10, 10);
                finishhead.setImageResource(R.drawable.white_finish);
                sharehead.setPadding(10, 10, 10, 10);
                sharehead.setImageResource(R.drawable.white_share);
                homehead.setPadding(10, 10, 10, 10);
                homehead.setImageResource(R.drawable.white_home);
                indicator.setVisibility(View.GONE);
                int currentItem = view_pager.getCurrentItem();
                if (currentItem == 0) {
                    scare_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_true));
                    scare_text.setTextColor(Color.parseColor("#e60012"));
                    past_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_false));
                    past_text.setTextColor(Color.WHITE);
                } else {
                    scare_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_false));
                    scare_text.setTextColor(Color.WHITE);
                    past_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_true));
                    past_text.setTextColor(Color.parseColor("#e60012"));
                }
            }
        });
        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                position = position % piclist.size();
                convenient_indicator.linemove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**********************************
     * onClick
     **************************************/
    public void finish(View v) {
        finish();
    }

    public void share(View v) {
        //TODO
    }

    public void backtohome(View v) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ArrayList<Scare_PastBean.Other> list) {
        if (list.size() >= 2) {
            convenient_indicator.setVisibility(View.VISIBLE);
        } else {
            convenient_indicator.setVisibility(View.GONE);
        }
        if (piclist.size() <= 0) {
            for (int i = 0; i < list.size(); i++) {
                piclist.add(list.get(i).getImg());
            }
            convenientBanner.setPages(new CBViewHolderCreator<BannitHolder>() {
                @Override
                public BannitHolder createHolder() {
                    return new BannitHolder();
                }
            }, piclist)
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                    .startTurning(3000);
            convenient_indicator.setIndicatorCount(list.size());
            convenient_indicator.setLaroutPosition(ViewPagerIndicator.CENTERPOSITION);
            convenient_indicator.setLineIndicator(Color.parseColor("#e60012"), Color.parseColor("#50000000"), 40, 40, 10);

            convenientBanner.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    int link_type = list.get(position).getLink_type();
                    String link = list.get(position).getLink();
                    switch (link_type) {
                        case 0://TODO 内链
                            break;
                        case 1://TODO 外链
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            Uri content_url = Uri.parse("http://" + link);
                            intent.setData(content_url);
                            startActivity(intent);
                            break;
                    }

                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}