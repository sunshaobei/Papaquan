package xm.ppq.papaquan.View.main;

import android.support.v4.view.ViewPager;

import xm.ppq.papaquan.Adapter.HomePagerAdapter;

/**
 * Created by Administrator on 2017/2/17.
 */

public interface Round_home {
    void setFrameLayout(HomePagerAdapter adapter);

    void setCurrentItem(ViewPager viewPager, int position);

    void initChar();
}