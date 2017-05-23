package xm.ppq.papaquan.View.owncoin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alibaba.mobileim.ui.atmessage.adapter.FragmentAdapter;

import java.util.List;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class VPAdapter extends FragmentAdapter {
    private List<String> list;

    public VPAdapter(FragmentManager fragmentManager, List<String> list) {
        super(fragmentManager);
        this.list = list;
    }
    @Override
    public Fragment getItem(int position) {
        return new VpFragment(list,position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
