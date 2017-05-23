package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.MoneyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MoneyMineAdapter extends ConcreteAdapter<MoneyBean.Data> implements PapanewsInterface {

    private EmotionPopupWindow emotionPopupWindow;

    public MoneyMineAdapter(Context context, List<MoneyBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
        emotionPopupWindow = new EmotionPopupWindow((Activity) context, this, "评论");
    }

    @Override
    protected void convert(ViewHolder viewHolder, MoneyBean.Data item, int position) {
        viewHolder.setText(item.nickname, R.id.money_name)
                .setText(item.time, R.id.money_time)
                .setText(item.content, R.id.money_content)
                .setOnClickListener(v -> {
                    emotionPopupWindow.setId(item.id, 0);
                    emotionPopupWindow.Show(v);
                }, R.id.money_rep);
        ImageLoading.Circular((Activity) getContext(), item.headurl, R.drawable.default_icon, viewHolder.getView(R.id.money_icon));
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {

    }

    @Override
    public void ShowShare(View view) {

    }
}