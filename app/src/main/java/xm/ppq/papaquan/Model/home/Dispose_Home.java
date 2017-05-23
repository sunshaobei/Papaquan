package xm.ppq.papaquan.Model.home;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.View.Life.lifehome.LifeFragLayout;
import xm.ppq.papaquan.View.main.frame.MineFragLayout;
import xm.ppq.papaquan.View.main.frame.NewsFragLayout;
import xm.ppq.papaquan.View.main.frame.PapaFragLayout;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Dispose_Home implements Home_Model {

    private List<Fragment> fragments;

    @Override
    public List<Fragment> getList() {
        fragments = new ArrayList<>();
        fragments.add(new PapaFragLayout());
        fragments.add(new LifeFragLayout());
        fragments.add(new NewsFragLayout());
        fragments.add(new MineFragLayout());
        return fragments;
    }

}
