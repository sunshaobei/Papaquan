package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.AiteBean;
import xm.ppq.papaquan.Bean.DisRepBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.shownews.DiscussUtil;
import xm.ppq.papaquan.View.news_mine_money.Mine_MoneyActivity;

/**
 * Created by Administrator on 2017/3/2.
 */

public class AiteMineAdapter extends ConcreteAdapter<AiteBean.Data> {

    public AiteMineAdapter(Context context, List<AiteBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, AiteBean.Data item, int position) {
        viewHolder.setText(item.rewarduser.nickname, R.id.aite_name)
                .setText(item.createtime, R.id.aite_time)
                .setText("￥" + item.money + ".00", R.id.aite_reply)
                .setText(item.myuser.nickname, R.id.aite_nickname)
                .setText(item.topiclist.content, R.id.aite_content);
        ImageLoading.Circular((Activity) getContext(), item.rewarduser.headurl, R.drawable.default_icon, viewHolder.getView(R.id.aite_icon));
        ImageLoading.common((Activity) getContext(), item.myuser.headurl, viewHolder.getView(R.id.aite_figure), R.drawable.default_icon_zheng);
    }
}
