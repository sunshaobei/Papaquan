package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.REViewHolder;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.Scare_PastBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.life.Tool.TimeTextView;

/**
 * Created by Administrator on 2017/4/17.
 */

public class ScarePastAdapter extends BaseRecyclerAdapter<Scare_PastBean.Data> {

    private SharedPreferencesPotting my_login;
    private Context context;

    public ScarePastAdapter(Context context, List<Scare_PastBean.Data> list, int layout) {
        super(context, list, layout);
        my_login = new SharedPreferencesPotting(context, "my_login");
        this.context = context;
    }

    @Override
    protected void Evaluate(REViewHolder viewHolder, Scare_PastBean.Data item, int position) {
        viewHolder.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Panic_BuyingActivity.class);
            intent.putExtra("pid", item.getId());
            ((Activity) (getContext())).startActivityForResult(intent, IntentCode.RequestCode.TOPANICBUY);
        }, R.id.scare_all);
        ImageLoading.common(getContext(), BaseUrl.BITMAP + item.getImg(), viewHolder.getView(R.id.icon_scare_past), R.mipmap.food);
        if (item.getUphour().equals("0")) {
            viewHolder.setTextVisi(R.id.left_top, View.VISIBLE);
        } else {
            viewHolder.setTextVisi(R.id.left_top, View.GONE);
        }
        switch (item.getStatus()) {
            case 1:
                viewHolder.setTextBackGround("提醒我", getContext().getResources().getDrawable(R.drawable.pass_make_green), R.id.make_use_of_btn)
                        .setText(Color.parseColor("#33cc99"), R.id.follow_scare)
                        .setTimeColor(Color.parseColor("#33cc99"), R.id.count_down, TimeTextView.START)
                        .setTextTime(item.getStart_time(), R.id.count_down)
                        .setOnClickener(v -> Remind(item.getId()), R.id.make_use_of_btn);
                break;
            case 2:
                viewHolder.setTextBackGround("抢购中", getContext().getResources().getDrawable(R.drawable.pass_make), R.id.make_use_of_btn)
                        .setText(Color.parseColor("#FF6666"), R.id.follow_scare)
                        .setTimeColor(Color.parseColor("#FF6666"), R.id.count_down, TimeTextView.STOP)
                        .setTextTime(item.getEnd_time(), R.id.count_down);
                break;
            case 3:
                viewHolder.setTextBackGround("抢光了", getContext().getResources().getDrawable(R.drawable.pass_make_gray), R.id.make_use_of_btn)
                        .setText(Color.parseColor("#B6B6B6"), R.id.follow_scare)
                        .setTimeColor(Color.parseColor("#B6B6B6"), R.id.count_down, TimeTextView.STOP)
                        .setTextTime(item.getEnd_time(), R.id.count_down);
                break;
            case 4:
                viewHolder.setTextBackGround("已结束", getContext().getResources().getDrawable(R.drawable.pass_make_gray), R.id.make_use_of_btn)
                        .setText(Color.parseColor("#B6B6B6"), R.id.follow_scare)
                        .setTimeColor(Color.parseColor("#B6B6B6"), R.id.count_down, TimeTextView.STOP)
                        .setTextTime(item.getStart_time(), R.id.count_down);
                break;
            case 5:
                viewHolder.setTextBackGround("过期了", getContext().getResources().getDrawable(R.drawable.pass_make_gray), R.id.make_use_of_btn)
                        .setText(Color.parseColor("#B6B6B6"), R.id.follow_scare)
                        .setTimeColor(Color.parseColor("#B6B6B6"), R.id.count_down, TimeTextView.STOP)
                        .setTextTime(item.getStart_time(), R.id.count_down);
                break;
        }
        viewHolder.setText(item.getShopname(), R.id.title_scare)
                .setText(item.getTitle(), R.id.content_scare1)
                .setText("￥" + item.getBuying_price(), R.id.red_scare)
                .setText(item.getPrice() + "元", R.id.money_yuan)
                .setText(item.getBrowse() + "人关注", R.id.follow_scare);
        TextView_Delete(viewHolder.getView(R.id.money_yuan));
    }

    private void TextView_Delete(TextView... textView) {
        for (TextView aTextView : textView) {
            aTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    private void Remind(int pid) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "pid", "agent")
                .put_value(my_login.getItemInt("uid"), my_login.getItemString("token"), 1, pid, "app");
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.REMIND, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    if (baseBean.getCode().equals("0")) {
                        Toast.makeText(context, "预约成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}