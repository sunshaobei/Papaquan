/*type为0时是我的抢购，其他值为精选*/
package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.ScareFBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.KtVipTextView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.appraise.AppraiseActivity;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.View.Life.panic_pay_waiting.Panic_Pay_WaitingActivity;
import xm.ppq.papaquan.View.waiting.WaitingActivity;

/**
 * Created by Administrator on 2017/4/10.
 */

public class ScareFAdapter extends ConcreteAdapter<ScareFBean.DataBean> {

    public ScareFAdapter(Context context, List<ScareFBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ScareFBean.DataBean item, int position) {
        switch (item.getStatus()) {
            case 4:/*"已完成":*/
                ((KtVipTextView) viewHolder.getView(R.id.scare_bestow)).setText("已完成");
                viewHolder.setKtClicked(R.id.scare_bestow, true);
                break;
            case 1:/*"去付款":*/
                ((KtVipTextView) viewHolder.getView(R.id.scare_bestow)).setText("去付款");
                viewHolder.setKtClicked(R.id.scare_bestow, false)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), Panic_Pay_WaitingActivity.class);
                            intent.putExtra("oid", String.valueOf(item.getOrderid()));
                            getContext().startActivity(intent);
                        }, R.id.scare_bestow);
                break;
            case 2:/*"去使用":*/
                ((KtVipTextView) viewHolder.getView(R.id.scare_bestow)).setText("去使用");
                viewHolder.setKtClicked(R.id.scare_bestow, false)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), WaitingActivity.class);
                            intent.putExtra("orderid", item.getOrderid());
                            intent.putExtra("type", "抢购");
                            ((Activity) getContext()).startActivityForResult(intent, IntentCode.RequestCode.TOWAITUSE);
                        }, R.id.scare_bestow);
                break;
            case 3:/*"去评论":*/
                ((KtVipTextView) viewHolder.getView(R.id.scare_bestow)).setText("去评论");
                viewHolder.setKtClicked(R.id.scare_bestow, false)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), AppraiseActivity.class);
                            intent.putExtra("type", "抢购");
                            intent.putExtra("sid", item.getSid());
                            intent.putExtra("pid", item.getPid());
                            ((Activity) getContext()).startActivityForResult(intent, IntentCode.RequestCode.TOAPPRAISE);
                        }, R.id.scare_bestow);
                break;
//                case "马上抢":
//                    viewHolder.setKtClicked(R.id.scare_bestow, false).setOnClickListener(v -> {
//                        Intent intent = new Intent(getContext(), Place_OrderActivity.class);
//                        getContext().startActivity(intent);
//                    }, R.id.scare_bestow);
//                    break;
        }
        viewHolder.setVisibility(View.VISIBLE, R.id.scare_bestow)
                .setVisibility(View.GONE, R.id.ccc);
        TextView_Delete(viewHolder.getView(R.id.prime_cost));
        viewHolder.setText(item.getShopname(), R.id.title_scare)
                .setText(item.getTitle(), R.id.content_scare)
                .setVisibility(View.GONE, R.id.distance)
                .setText("￥" + item.getPay_money(), R.id.red_money)
                .setText(item.getPrice() + "元", R.id.prime_cost)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), Panic_BuyingActivity.class);
                    intent.putExtra("pid", item.getPid());
                    getContext().startActivity(intent);
                }, R.id.scare_relative);
        String uphour = item.getUphour();
        if (uphour.equals("0")) {
            viewHolder.setVisibility(View.VISIBLE, R.id.myy);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.myy);
        }
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getImg(), viewHolder.getView(R.id.scare_icon), R.mipmap.food);
    }

    protected void TextView_Delete(TextView... textView) {
        for (int i = 0; i < textView.length; i++) {
            textView[i].getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}