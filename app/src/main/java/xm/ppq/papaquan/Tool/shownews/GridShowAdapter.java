package xm.ppq.papaquan.Tool.shownews;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;


/**
 * Created by Administrator on 2017/2/28.
 */

public class GridShowAdapter extends ConcreteAdapter<String> {

    private int i = 0;

    public GridShowAdapter(Context context, List<String> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        if (i == 1) {
//            viewHolder.setImageBack(item, R.id.picture_double);
        } else if (i == 2) {
//            Log.e("图片地址", item);
            ImageLoading.common((Activity) getContext(), item, viewHolder.getView(R.id.picture), R.drawable.news_pic);
        }
    }
}
