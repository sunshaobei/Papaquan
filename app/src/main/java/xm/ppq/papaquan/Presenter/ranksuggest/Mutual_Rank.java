package xm.ppq.papaquan.Presenter.ranksuggest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.List;

import xm.ppq.papaquan.Bean.Level_Table;
import xm.ppq.papaquan.Model.ranksuggest.Dispose_RankSuggest;
import xm.ppq.papaquan.Model.ranksuggest.Rank_Model;
import xm.ppq.papaquan.View.ranksuggest.Rank_Total;

/**
 * Created by Administrator on 2017/2/20.
 */

public class Mutual_Rank implements RankPresenter {

    private Rank_Total total;
    private Rank_Model model;
    private static final int key1 = 4001;
    private static final int key2 = 4002;

    public Mutual_Rank(Rank_Total total) {
        this.total = total;
        model = new Dispose_RankSuggest(handler);
    }

    @Override
    public void initUI() {
        model.getLevel(key1);
        model.getExperience(key2);
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case key1:
                    total.setLevel((List<Level_Table>) msg.obj);
                    break;
                case key2:
                    total.setExperience((List<Level_Table>) msg.obj);
                    break;
            }
        }
    };
}
