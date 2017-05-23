package xm.ppq.papaquan.Adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<>();

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment) {
        list.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        list.remove(fragment);
    }

    public void setFragments(List<Fragment> list) {
        this.list = list;
    }

    public List<Fragment> getList() {
        return list;
    }

    public void clear() {
        for (Fragment fragment : list) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        list.clear();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setPagerTitle(String[] s)
    {
        this.str = s;
    }

    String[] str;

    @Override
    public CharSequence getPageTitle(int position) {
        if (str!=null)
        return str[position];
        else return super.getPageTitle(position) ;
    }

}
