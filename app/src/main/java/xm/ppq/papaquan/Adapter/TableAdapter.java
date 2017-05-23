package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.Level_Table;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/2/20.
 */

public class TableAdapter extends ConcreteAdapter<Level_Table> {

    private int width;

    public TableAdapter(Context context, List<Level_Table> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public TableAdapter(Context context, List<Level_Table> list, int itemLayout, int width) {
        super(context, list, itemLayout);
        this.width = width;
    }

    @Override
    protected void convert(ViewHolder viewHolder, Level_Table item, int position) {
        if (item.is_type() == true) {
            viewHolder.setText(item.getLevel(), R.id.level, width, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .setText(BaseUrl.STRINGS[item.getI()], R.id.empiric_value, width, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .setFrameLayout(R.id.mix, width, LinearLayout.LayoutParams.MATCH_PARENT)
                    .setLinearLayout(R.id.top_lin, 100, 100);
            if (item.is_io() == true) {
                viewHolder.setText(item.getIcon(), R.id.icon)
                        .setResources(R.drawable.level_0, R.id.resource, View.GONE);
            } else {
                viewHolder.setResources(BaseUrl.INTEGERS[item.getResource()], R.id.resource);
            }
        } else if (item.is_type() == false) {
            viewHolder.setText(BaseUrl.RULE[item.getI()], R.id.empiric_value, width, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .setText(BaseUrl.ROAD[item.getI()], R.id.level, width, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .setText(BaseUrl.REMAKE[item.getI()], R.id.icon)
                    .setFrameLayout(R.id.mix, width, LinearLayout.LayoutParams.MATCH_PARENT)
                    .setLinearLayout(R.id.top_lin, 100, 100);
        }
    }
}
