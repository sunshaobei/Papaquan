package xm.ppq.papaquan.Presenter.home;

import android.support.v4.app.FragmentManager;

import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Model.home.Dispose_Home;
import xm.ppq.papaquan.View.main.Round_home;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Mutual_Home implements HomePresenter {

    private HomePagerAdapter adapter;
    private Round_home round_home;
    private Dispose_Home dispose_home;

    public Mutual_Home(Round_home round_home) {
        this.round_home = round_home;
        dispose_home = new Dispose_Home();
    }

    @Override
    public void Initialise(FragmentManager fm) {
        adapter = new HomePagerAdapter(fm);
        adapter.setFragments(dispose_home.getList());
        round_home.setFrameLayout(adapter);
        round_home.initChar();
    }

}
