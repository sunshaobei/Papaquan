package xm.ppq.papaquan.View.main.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.example.lib_sunshaobei2017.widget.ViewPagerIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.SplashActivity;

public class HomePageActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.ind)
    ViewPagerIndicator ind;

    private HomePagerAdapter adapter;
    private ArrayList<Fragment> fragments;
    private SharedPreferencesPotting potting;
    private ItemFragment itemFragment;
    private Bundle bundle;

    @Override
    protected int getLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_homepage;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "login");
        if (potting.getItemBoolean("one") == false) {
            potting.setItemBoolean("one", true)
                    .build();
            fragments = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                itemFragment = new ItemFragment();
                bundle = new Bundle();
                bundle.putInt("num", i);
                itemFragment.setArguments(bundle);
                fragments.add(itemFragment);
                itemFragment = null;
                bundle = null;
            }
            ind.setIndicatorColor(Color.parseColor("#cdcdcd"), Color.TRANSPARENT, Color.parseColor("#e60012"));
            ind.setRadiusSize(10);
            ind.setIndicatorCount(3);
            adapter = new HomePagerAdapter(getSupportFragmentManager());
            adapter.setFragments(fragments);
            view_pager.setAdapter(adapter);
        } else {
            Skip(SplashActivity.class);
            finish();
        }
    }

    @Override
    protected void setListener() {
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ind.move(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}