package xm.ppq.papaquan.View.scare_buying.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.MycouponData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.KtVipTextView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.applyrefunds.ApplyRefundActivity;
import xm.ppq.papaquan.View.Life.appraise.AppraiseActivity;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;
import xm.ppq.papaquan.View.order_pay.Order_PayActivity;
import xm.ppq.papaquan.View.waiting.WaitingActivity;

/**
 * Created by 我的精选 on 2017/5/3.
 */

public class MycouponAdapter extends ConcreteAdapter<MycouponData.DataBean> {

    private int type;

    public MycouponAdapter(Context context, List<MycouponData.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(ViewHolder viewHolder, MycouponData.DataBean item, int position) {
        switch (type) {
            case 4:/*"去评价":*/
                ((KtVipTextView) viewHolder.getView(R.id.make_user_choice)).setText("去评价");
                viewHolder.setKtClicked(R.id.make_user_choice, true)
                        .setVisibility(View.GONE, R.id.drawback_text)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), AppraiseActivity.class);
                            intent.putExtra("type", "精选");
                            intent.putExtra("sid", item.getSid());
                            intent.putExtra("pid", item.getId());
                            getContext().startActivity(intent);
                        }, R.id.make_user_choice);
                break;
//            case 1:/*"去付款":*/
//                ((KtVipTextView) viewHolder.getView(R.id.make_user_choice)).setText("去付款");
//                viewHolder.setKtClicked(R.id.make_user_choice, false)
//                        .setVisibility(View.GONE, R.id.drawback_text)
//                        .setOnClickListener(v -> {
//                            Intent intent = new Intent(getContext(), Order_PayActivity.class);
//                            getContext().startActivity(intent);
//                        }, R.id.make_user_choice);
//                break;
            case 2:/*"去付款":*/
                ((KtVipTextView) viewHolder.getView(R.id.make_user_choice)).setText("去付款");
                viewHolder.setKtClicked(R.id.make_user_choice, false)
                        .setVisibility(View.GONE, R.id.drawback_text)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), Order_PayActivity.class);
                            intent.putExtra("url", BaseUrl.ORDERDETAILS);
                            intent.putExtra("type", "精选");
                            intent.putExtra("oid", String.valueOf(item.getId()));
                            ((Activity) getContext()).startActivityForResult(intent, IntentCode.RequestCode.TOWAITUSE);
                        }, R.id.make_user_choice);
                break;
            case 3:/*"去使用":*/
                ((KtVipTextView) viewHolder.getView(R.id.make_user_choice)).setText("去使用");
                viewHolder.setKtClicked(R.id.make_user_choice, false)
                        .setOnClickListener(v -> {
                            Intent intent = new Intent(getContext(), WaitingActivity.class);
                            intent.putExtra("orderid", item.getId());
                            intent.putExtra("type", "精选");
                            intent.putExtra("id", item.getId());
                            intent.putExtra("url", BaseUrl.ORDERDETAILS);
                            ((Activity) getContext()).startActivityForResult(intent, IntentCode.RequestCode.TOAPPRAISE);
                        }, R.id.make_user_choice);
                break;
            case 5:/*"去退款":*/
                ((KtVipTextView) viewHolder.getView(R.id.make_user_choice)).setText("已退款");
                viewHolder.setKtClicked(R.id.make_user_choice, true)
                        .setVisibility(View.GONE, R.id.drawback_text);
                break;
        }
        viewHolder.setVisibility(View.GONE, R.id.scare_bestow)
                .setVisibility(View.VISIBLE, R.id.ccc)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), ApplyRefundActivity.class);
                    intent.putExtra("oid", item.getId());
                    ((Activity) getContext()).startActivityForResult(intent, IntentCode.RequestCode.TOAPPLYREFUND);
                }, R.id.drawback_text);
        TextView_Delete(viewHolder.getView(R.id.prime_cost));
        viewHolder.setText(item.getTitle(), R.id.title_scare)
                .setText(item.getSpectitle(), R.id.content_scare)
                .setVisibility(View.GONE, R.id.distance)
                .setText("￥" + item.getPrice(), R.id.red_money)
                .setText(item.getPrice() + "元", R.id.prime_cost)
                .setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                    intent.putExtra("cid", item.getCid());
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
}