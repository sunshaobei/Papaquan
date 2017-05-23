package myview.mybanner;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sunshaobei on 2017/4/16.
 */

public class Mybanner extends FrameLayout {
    private Context context;
    private ViewPager viewPager;
    private ViewPagerIndicator indicator;
    private ArrayList<String> list = new ArrayList<>();

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                if (!byDraging) {
                    i++;
                    if (i >= list.size() * 20000)
                        i = list.size() * 20000 / 2;
                    viewPager.setCurrentItem(i);
                }
            }
        }
    };
    private Timer timer;

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
        indicator.setIndicatorCount(2);
        indicator.setLineIndicator(Color.parseColor("#e60012"), Color.parseColor("#D0D0D0"), 20, 40, 6);
    }

    public void setBanner(FragmentManager manager, final ArrayList<String> list) {
        this.list = list;
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(manager);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(10);
        indicator.setIndicatorCount(list.size());
        indicator.setLineIndicator(Color.parseColor("#e60012"), Color.parseColor("#D0D0D0"), 20, 40, 6);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position >= list.size()) {
                    position = position % list.size();
                }
                indicator.linemove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                i = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
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
        i = list.size() * 20000 / 2;
        viewPager.setCurrentItem(list.size() * 20000 / 2);
    }

    private boolean byDraging = false;
    private int i;
    class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position >= list.size()) {
                position = position % list.size();
            }
            return new BannerFragment(Mybanner.this, list, position);
        }

        @Override
        public int getCount() {
            return list.size() * 20000;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (timer==null){
            timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    // 需要做的事:发送消息
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            };
            timer.schedule(task, 3000, 3000);
        }

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
        timer = null;
    }


    public void setOnItemClickListener(OnItemClickListener o) {
        this.onItemClickListener = o;
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
}
