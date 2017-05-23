package xm.ppq.papaquan.View.Life.selleroof;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/5/16.
 */

public class SellersortgridAdapter extends ConcreteAdapter<TypeClassfiyBean.DataBean> {
    public SellersortgridAdapter(Context context, List<TypeClassfiyBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, TypeClassfiyBean.DataBean item, int position) {
        if (item.getLogo()!=null)
        {
            ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getLogo(), viewHolder.getView(R.id.icon), R.mipmap.food);
            viewHolder.setText(item.getName(), R.id.title);
            TextView textView = viewHolder.getView(R.id.title);
            textView.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
        }else {
            ImageView imageView = viewHolder.getView(R.id.icon);
            imageView.setImageDrawable(new ColorDrawable(Color.parseColor("#cdcdcd")));
            TextView textView = viewHolder.getView(R.id.title);
            textView.setBackground(new ColorDrawable(Color.parseColor("#cdcdcd")));
            textView.setText("");
        }
    }
}
