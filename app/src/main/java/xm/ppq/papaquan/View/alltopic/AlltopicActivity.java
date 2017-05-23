package xm.ppq.papaquan.View.alltopic;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.widget.pagerslidingtabstrip.PagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;

public class AlltopicActivity extends BaseActivity {


    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.hint)
    View hint;
    @BindView(R.id.clean)
    View clean;
    private int position;

    private AlltopicPagerAdapter alltopicPagerAdapter;
    private SharedPreferencesPotting my_login;

    @Override
    protected int getLayout() {
        return R.layout.activity_alltopic;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        alltopicPagerAdapter = new AlltopicPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(alltopicPagerAdapter);
        tabStrip.setTextColor(Color.BLACK);
        tabStrip.setTextSize(12);
        tabStrip.setViewPager(viewpager);
        indicator.setOffwidth(-20);
        my_login = new SharedPreferencesPotting(this, "my_login");
    }

    @Override
    protected void setListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                AlltopicActivity.this.position = position;
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        finish.setOnClickListener(v -> finish());
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    EventBus.getDefault().post(position+s.toString());
                    hint.setVisibility(View.GONE);
                    clean.setVisibility(View.VISIBLE);
                } else {
                    clean.setVisibility(View.GONE);
                    hint.setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(position+"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        clean.setOnClickListener(v -> {
            search.setText("");
            clean.setVisibility(View.GONE);
            hint.setVisibility(View.VISIBLE);
            EventBus.getDefault().post("");
        });
    }

    class AlltopicPagerAdapter extends FragmentPagerAdapter {

        String[] str = {"热门", "最新", "我的"};

        public AlltopicPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new AlltopicFragment(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return str[position];
        }
    }
}
