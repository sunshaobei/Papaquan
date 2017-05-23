package xm.ppq.papaquan.View.Life.lifehome.searchfragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lib_sunshaobei2017.app.BaseActivity;
import com.example.lib_sunshaobei2017.widget.pagerslidingtabstrip.PagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.Life.scare_past.fragment.Sca_PasFragment;

public class SearchGoodsActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.idtor)
    Indicator idtor;
    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tabStrip;
    @BindView(R.id.edit)
    EditText search_edit;
    @BindView(R.id.hideedit_hint)
    View hideedit_hint;
    @BindView(R.id.bar)
    LinearLayout bar;

    private int position;

    @Override
    public int oncreate() {
        return R.layout.activity_search_goods;
    }

    @Override
    public void initView() {
        initStatusBar(bar);
       fragments.add(new SearchgoodsFragment1());
       fragments.add(new SearchgoodsFragment2());
       fragments.add(new SearchgoodsFragment3());
    }

    ArrayList<Fragment> fragments = new ArrayList<>();
    @Override
    public void initListener() {
        String[] str = {"折扣", "精选", "抢购"};
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return str[position];
            }
        });
        tabStrip.setTextColor(Color.BLACK);
        tabStrip.setTextSize(14);
        tabStrip.setViewPager(viewPager);
        idtor.setOffwidth(-30);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                SearchGoodsActivity.this.position = position;
                idtor.onMove(positionOffset, position);
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });


        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s))
                {
                    hideedit_hint.setVisibility(View.GONE);
                    EventBus.getDefault().post(position+s.toString());
                }else {
                    hideedit_hint.setVisibility(View.VISIBLE);
                    EventBus.getDefault().post(position+"");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }



    @Override
    public void onstart() {

    }

    @Override
    public void onresume() {

    }

    @Override
    public void onstop() {

    }

    @Override
    public void ondestroy() {

    }

    public void finish(View v)
    {
        finish();
    }


    public void share(View v)
    {
        search_edit.setText("");
        hideedit_hint.setVisibility(View.VISIBLE);
        EventBus.getDefault().post("");
    }
}
