package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.graphics.Color;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.R;

/**
 * Created by EdgeDi on 17:38.
 */

public class FlowTabAdapter extends UpSellerTagAdapter<LevelBean> {

    public FlowTabAdapter(List<LevelBean> datas, Context context, int layout) {
        super(datas, context, layout);
    }

    @Override
    protected void Initialize(ViewHolder viewHolder, LevelBean item, int position) {
        if (item.isCheck() == false) {
            viewHolder.setTextColor(Color.parseColor("#555555"), R.id.fl_result)
                    .setBackGround(getContext().getResources().getDrawable(R.drawable.flow_view), R.id.flow_lin);
        } else {
            viewHolder.setTextColor(Color.parseColor("#ffffff"), R.id.fl_result)
                    .setBackGround(getContext().getResources().getDrawable(R.drawable.flow_view_ture), R.id.flow_lin);
        }
        viewHolder.setText(item.getResult(), R.id.fl_result);
    }
}
