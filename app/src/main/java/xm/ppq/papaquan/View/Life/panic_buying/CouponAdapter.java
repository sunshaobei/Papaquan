package xm.ppq.papaquan.View.Life.panic_buying;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.Panic_BuyingBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/4/27.
 */

public class CouponAdapter extends ConcreteAdapter<Panic_BuyingBean.DataBean.CouponListBean> {
    private Context context;
    private String couponname;

    public CouponAdapter(Context context, List<Panic_BuyingBean.DataBean.CouponListBean> list, int itemLayout,String couponname) {
        super(context, list, itemLayout);
        this.context = context;
        this.couponname = couponname;
    }


    @Override
    protected void convert(ViewHolder viewHolder, Panic_BuyingBean.DataBean.CouponListBean item, int position) {
        ImageView imageView = viewHolder.getView(R.id.icon);
        Glide.with(context).load(BaseUrl.BITMAP+item.getImg()).into(imageView);
        viewHolder.setText(couponname,R.id.title)
                .setText(item.getTitle(),R.id.content);
        viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        },R.id.purchasingnow);
    }
}
