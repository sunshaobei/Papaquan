package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.TenantData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ItemTextView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.merchant.MerchantActivity;

/**
 * Created by Administrator on 2017/4/12.
 */

public class TenantAdapter extends ConcreteAdapter<TenantData.DataBean> {

    private Context context;

    public TenantAdapter(Context context, List<TenantData.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder viewHolder, TenantData.DataBean item, int position) {
        ((ItemTextView) viewHolder.getView(R.id.manage_shenhe)).setColorText(item.getCheck().equals("1"));
        switch (((ItemTextView) viewHolder.getView(R.id.manage_shenhe)).getText().toString()) {
            case "已审核":
                viewHolder.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), MerchantActivity.class);
                    intent.putExtra("sid", item.getId())
                            .putExtra("type", "已审核");
                    ((Activity) context).startActivityForResult(intent, IntentCode.RequestCode.TOMANAGE);
                }, R.id.manage);
                break;
            case "待审核":
                viewHolder.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), MerchantActivity.class);
                    intent.putExtra("sid", item.getId())
                            .putExtra("type", "待审核");
                    ((Activity) context).startActivityForResult(intent, IntentCode.RequestCode.TOMANAGE);
                }, R.id.manage);
                break;
        }
        ImageView imageView = viewHolder.getView(R.id.logo);
        Picasso.with(getContext()).load(BaseUrl.BITMAP + item.getLogo()).placeholder(R.drawable.default_icon_zheng).into(imageView);
        viewHolder.setText(item.getName(), R.id.name);
    }
}
