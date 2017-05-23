package xm.ppq.papaquan.Adapter;

import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.IntergalListBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/4/11.
 */

public class IntegralAdapter extends ConcreteAdapter<IntergalListBean.DataBean> {

    private int gold;

    public IntegralAdapter(Context context, List<IntergalListBean.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    protected void convert(ViewHolder viewHolder, IntergalListBean.DataBean item, int position) {
        if (gold == -1) {
            viewHolder.setKtTextView(R.id.duihuan, "可以兑换", 10, 10);
        } else {
            if (item.getNum() - item.getExchange() > 0) {
                viewHolder.setText("剩" + (item.getNum() - item.getExchange()) + "份", R.id.gold_number);
                if (gold - item.getGold() > 0) {
                    viewHolder.setKtTextView(R.id.duihuan, "可以兑换", 10, 10);
                } else if (gold - item.getGold() < 0) {
                    viewHolder.setKtTextView(R.id.duihuan, "金币不足", 10, 10)
                            .setKtClicked(R.id.duihuan, true);
                }
            } else {
                viewHolder.setText("剩0份", R.id.gold_number);
                viewHolder.setKtTextView(R.id.duihuan, "抢完了", 20, 10)
                        .setKtClicked(R.id.duihuan, true);
            }
        }
        viewHolder.setText(item.getName(), R.id.gold_title)
                .setText(String.valueOf(item.getGold()), R.id.gold_money);
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getLogo(), viewHolder.getView(R.id.gold_icon), R.mipmap.food);

    }
}