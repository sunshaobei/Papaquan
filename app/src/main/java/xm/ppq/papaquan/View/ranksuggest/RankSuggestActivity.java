package xm.ppq.papaquan.View.ranksuggest;

import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.TableAdapter;
import xm.ppq.papaquan.Bean.Level_Table;
import xm.ppq.papaquan.Presenter.ranksuggest.Mutual_Rank;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 用户等级介绍 on 2017/2/20.
 */

public class RankSuggestActivity extends BaseActivity implements Rank_Total {

    @BindView(R.id.level_list)
    SlideListView level_list;
    @BindView(R.id.experience_list)
    SlideListView experience_list;

    private TableAdapter level_adapter;
    private TableAdapter tad_adapter;
    private Mutual_Rank mutual_rank;
    private int width;

    @Override
    protected int getLayout() {
        return R.layout.activity_ranksuggest;
    }

    @Override
    protected void initData() {
        width = getWindowManager().getDefaultDisplay().getWidth();
        ButterKnife.bind(this);
        mutual_rank = new Mutual_Rank(this);
        mutual_rank.initUI();
    }

    @Override
    protected void setListener() {
    }

    @Override
    public void setLevel(List<Level_Table> level) {
        if (level_adapter == null) {
            level_adapter = new TableAdapter(this, level, R.layout.table_item, (width - 212) / 3);
            level_list.setAdapter(level_adapter);
        } else {
            level_adapter.setList(level);
            level_adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setExperience(List<Level_Table> level) {
        if (tad_adapter == null) {
            tad_adapter = new TableAdapter(this, level, R.layout.table_item, (width - 212) / 3);
            experience_list.setAdapter(tad_adapter);
        } else {
            tad_adapter.setList(level);
            tad_adapter.notifyDataSetChanged();
        }
    }

    public void finish(View view) {
        finish();
    }
}
