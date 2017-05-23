package xm.ppq.papaquan.View.Life.selleroof;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.View.Life.selleroof.fragment.TradeClassify;

/**
 * Created by sunshaobei on 2017/5/16.
 */

public class SellersortAdapter extends FragmentPagerAdapter {
    private ArrayList<TypeClassfiyBean.DataBean> dataBeen = new ArrayList<>();

    public SellersortAdapter(FragmentManager fm, ArrayList<TypeClassfiyBean.DataBean> dataBeen) {
        super(fm);
        this.dataBeen = dataBeen;
    }

    @Override
    public Fragment getItem(int position) {
        return new TradeClassify(position,dataBeen);
    }

    @Override
    public int getCount() {
        int i = dataBeen.size() / 10;
        if (dataBeen.size()%10>0)
            i = i +1;
        return i;
    }
}
