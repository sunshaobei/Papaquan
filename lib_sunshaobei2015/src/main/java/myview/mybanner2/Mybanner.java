package myview.mybanner2;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.lib_sunshaobei2017.R;
import com.example.lib_sunshaobei2017.widget.ViewPagerIndicator;

import java.util.ArrayList;

/**
 * Created by sunshaobei on 2017/4/16.
 */

public class Mybanner extends FrameLayout {
    private Context context;
    private ViewPager viewPager;
    private ViewPagerIndicator indicator;
    private ArrayList<String> list  = new ArrayList<>();

    public Mybanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        indicator = (ViewPagerIndicator) view.findViewById(R.id.indicator);
        this.addView(view);
        indicator.setindicatorStyle(ViewPagerIndicator.Line);
    }

    public void setBanner(FragmentManager manager, final ArrayList<String> list)
    {
        this.list = list ;
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(manager);
        viewPager.setAdapter(myPagerAdapter);
        indicator.setIndicatorCount(list.size());
        indicator.setLineIndicator(Color.parseColor("#e60012"),Color.parseColor("#D0D0D0"),20,40,6);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position>=list.size())
                {
                    position = position % list.size();
                }
                indicator.linemove(positionOffset,position);
            }
            @Override
            public void onPageSelected(int position) {
                i = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state)
                {
                    case ViewPager.SCROLL_STATE_DRAGGING://手拽
                        byDraging = true;
                        break;
                    case ViewPager.SCROLL_STATE_IDLE://静止
                        byDraging = false;
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://设置
                        byDraging = false;
                        break;
                }
            }
        });
        i = list.size()*20000/2;
        viewPager.setCurrentItem(list.size()*20000/2);
        viewPager.postDelayed(action,3000);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(40);
        viewPager.setPageTransformer(true,new RotateDownPageTransformer());
    }

    private boolean byDraging = false;
    private int i;
    Runnable action = new Runnable() {
        @Override
        public void run() {
            if (!byDraging)
            {
                if (attachedwindow)
                {
                    i++;
                    viewPager.setCurrentItem(i);
                    postDelayed(this,3000);
                }else {
                    postDelayed(this,300);
                }
            }else {
                postDelayed(this,300);
            }
        }
    };


    class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position>=list.size())
            {
                position = position % list.size();
            }
            return new BannerFragment(list,position);
        }

        @Override
        public int getCount() {
            return list.size()*20000;
        }
    }

    private boolean attachedwindow = false;
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachedwindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        attachedwindow = false;
    }
}
