package xm.ppq.papaquan.View.redcard;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.RedcardPartshopData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/5/2.
 */

public class RedCarePartShopAdapter extends ConcreteAdapter<RedcardPartshopData.DataBean> {

    public RedCarePartShopAdapter(Context context, List<RedcardPartshopData.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RedcardPartshopData.DataBean item, int position) {
        viewHolder.setText(item.getName(), R.id.name);
        ImageView imageView = viewHolder.getView(R.id.icon);
        Glide.with(getContext()).load(BaseUrl.BITMAP + item.getLogo()).into(imageView);
    }
}
