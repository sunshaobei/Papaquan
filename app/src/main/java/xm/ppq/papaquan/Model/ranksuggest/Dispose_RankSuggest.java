package xm.ppq.papaquan.Model.ranksuggest;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import xm.ppq.papaquan.Bean.Level_Table;

/**
 * Created by Administrator on 2017/2/20.
 */

public class Dispose_RankSuggest implements Rank_Model {

    private Handler handler;
    private Level_Table level_table;
    private List<Level_Table> level_list;
    private List<Level_Table> exper_list;

    public Dispose_RankSuggest(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void getLevel(final int key) {
        level_list = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                int length = 0;
                for (int i = 0; i < 22; i++) {
                    level_table = new Level_Table();
                    level_table.setIs_type(true);
                    level_table.setI(i);
                    if (i == 0) {
                        level_table.setLevel("等级");
                        level_table.setIcon("图标");
                        level_table.setIs_io(true);
                    } else {
                        level_table.setIs_io(false);
                        level_table.setLevel("" + (i - 1));
                        level_table.setResource(i - 1);
                    }
                    level_list.add(level_table);
                    level_table = null;
                }
                handler.obtainMessage(key, level_list).sendToTarget();
            }
        }.start();
    }

    @Override
    public void getExperience(final int key) {
        exper_list = new ArrayList<Level_Table>();
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 8; i++) {
                    level_table = new Level_Table();
                    level_table.setI(i);
                    level_table.setIs_type(false);
                    exper_list.add(level_table);
                    level_table = null;
                }
                handler.obtainMessage(key, exper_list).sendToTarget();
            }
        }.start();

    }
}