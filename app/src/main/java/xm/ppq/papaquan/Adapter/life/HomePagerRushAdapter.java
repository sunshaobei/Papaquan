package xm.ppq.papaquan.Adapter.life;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.HomePagerOtherBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.life.Tool.TimeTextView;

/**
 * Created by EdgeDi on 16:39.
 */

public class HomePagerRushAdapter extends ConcreteAdapter<HomePagerOtherBean.DataBean.PanicBean> {

    public HomePagerRushAdapter(Context context, List<HomePagerOtherBean.DataBean.PanicBean> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, HomePagerOtherBean.DataBean.PanicBean item, int position) {
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getImg(), viewHolder.getView(R.id.icon_scare_past), R.mipmap.food);
        if (item.getUphour().equals("0")) {
            viewHolder.setVisibility(View.VISIBLE, R.id.left_top);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.left_top);
        }
//        int start = item.getStart_time();
//        int now = item.getNowtime();
//        int end = item.getEnd_time();
//        if (start - now >= 0) {//尚未开始
//            setTextBackGround("提醒我", getContext().getResources().getDrawable(R.drawable.pass_make_green), viewHolder.getView(R.id.make_use_of_btn));
//            viewHolder.setText(Color.parseColor("#33cc99"), R.id.follow_scare);
//            setTimeColor(Color.parseColor("#33cc99"), viewHolder.getView(R.id.count_down), TimeTextView.START);
//            setTextTime(start, viewHolder.getView(R.id.count_down));
//        } else {//已经开始
//            if (start - end >= 0) {//进行中
//                setTextBackGround("抢购中", getContext().getResources().getDrawable(R.drawable.pass_make), viewHolder.getView(R.id.make_use_of_btn));
//                viewHolder.setText(Color.parseColor("#e60012"), R.id.follow_scare);
//                setTimeColor(Color.parseColor("#e60012"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
//                setTextTime(end, viewHolder.getView(R.id.count_down));
//            } else {//已结束
//                setTextBackGround("已结束", getContext().getResources().getDrawable(R.drawable.pass_make_gray), viewHolder.getView(R.id.make_use_of_btn));
//                viewHolder.setText(Color.parseColor("#B6B6B6"), R.id.follow_scare);
//                setTimeColor(Color.parseColor("#B6B6B6"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
//                setTextTime(end, viewHolder.getView(R.id.count_down));
//            }
//        }
        viewHolder.setText(item.getTitle(), R.id.title_scare)
                .setText("￥" + item.getBuying_price(), R.id.red_scare)
                .setText(item.getPrice() + "元", R.id.money_yuan)
                .setText(item.getBrowse() + "人关注", R.id.follow_scare);
        TextView_Delete(viewHolder.getView(R.id.money_yuan));
        if (item.getEnd_time() > item.getNowtime()) {
            setTextTime(item.getEnd_time(), viewHolder.getView(R.id.count_down));
            setTimeColor(Color.parseColor("#e60012"), viewHolder.getView(R.id.count_down), TimeTextView.STOP);
            viewHolder.setText("马上抢", R.id.make_use_of_btn)
                    .setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), Panic_BuyingActivity.class);
                        intent.putExtra("pid", item.getId());
                        getContext().startActivity(intent);
                    }, R.id.make_use_of_btn);
        } else {
            setTextTime(item.getEnd_time(), viewHolder.getView(R.id.count_down));
            setTimeColor(Color.parseColor("#b6b6b6"), viewHolder.getView(R.id.count_down), TimeTextView.EXPIRE);
            viewHolder.setText("已过期", R.id.make_use_of_btn);
        }
    }

    //    public void setTextBackGround(CharSequence result, Drawable drawable, TextView textView) {
//        textView.setText(result);
//        textView.setBackground(drawable);
//    }
//
    public void setTextTime(long time, TimeTextView view) {
        view.setTime(time);
    }

    public void setTimeColor(int color, TimeTextView view, int type) {
        view.Judge(type);
        view.setTextColor(color);
    }
}