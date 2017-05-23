package xm.ppq.papaquan.Adapter.life;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.BaseRecyclerAdapter;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Bean.life.RankingBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 19:59.
 */

public class RankingAdapter extends BaseRecyclerAdapter<RankingBean.DataBean> {

    public RankingAdapter(Context context, List<RankingBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void Evaluate(REViewHolder viewHolder, RankingBean.DataBean item, int position) {
        viewHolder.setText("NO." + (position + 1), R.id.re_time)
                .setText(item.getNickname(), R.id.re_name)
                .setText(item.getNum() + "人", R.id.re_money);
    }
}