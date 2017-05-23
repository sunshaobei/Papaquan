package xm.ppq.papaquan.View.signed;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.mobileim.channel.message.template.ITemplateMsg;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.life.SignedGiftInfo;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by sunshaobei on 2017/5/5.
 */

public class GiftAdapter extends ConcreteAdapter <SignedGiftInfo.DataBean>{
    private SignedActivity context;
    private final SharedPreferencesPotting my_login;

    public static int Prizetype;
    public GiftAdapter(SignedActivity context, List<SignedGiftInfo.DataBean> list, int itemLayout) {
        super(context, list, itemLayout);
        this.context = context;
        my_login = new SharedPreferencesPotting(context, "my_login");
    }

    private String str;
    private String s;
    @Override
    protected void convert(ViewHolder viewHolder, SignedGiftInfo.DataBean item, int position) {

        if (item.getType() == 1)
            str= "连续签到";
        else str = "累计签到";
        if (item.getPrizetype()==1)
            s = "金币";
        else s = "天红卡";
        if (item.getIsget()==1)
        {
            viewHolder.setVisibility(View.GONE,R.id.lingqucountdown)
                    .setText("已领取",R.id.lingqustate)
                    .setTextColor(Color.parseColor("#999999"),R.id.lingqustate)
                    .setBackGround(context.getResources().getDrawable(R.drawable.tvbg_signed_cccccc),R.id.lingqu);
        }else {
            int signday = item.getSignday();
            int day = item.getDay();
            if (signday>=day){
                viewHolder.setVisibility(View.GONE,R.id.lingqucountdown)
                        .setText("领取",R.id.lingqustate)
                        .setTextColor(Color.parseColor("#ffffff"),R.id.lingqustate)
                        .setBackGround(context.getResources().getDrawable(R.drawable.tvbg_signed_e60012),R.id.lingqu);
                viewHolder.setOnClickListener(v -> {
                    Prizetype = item.getPrizetype();
                    getGift(item.getId());
                },R.id.lingqu);
            }else {
                viewHolder.setVisibility(View.VISIBLE,R.id.lingqucountdown)
                        .setText("还需"+(day-signday)+"天",R.id.lingqucountdown)
                        .setText("领取",R.id.lingqustate)
                        .setTextColor(Color.parseColor("#999999"),R.id.lingqustate)
                        .setBackGround(context.getResources().getDrawable(R.drawable.tvbg_signed_cccccc),R.id.lingqu);
            }


        }
        viewHolder.setText(str+item.getDay()+"天,领取"+item.getNum()+s, R.id.text);
        ImageView image = viewHolder.getView(R.id.image);
        Glide.with(context).load(BaseUrl.BITMAP+item.getLogo()).into(image);
    }

    private void getGift(int pid) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pid",pid)
                    .put("uid",my_login.getItemInt("uid"))
                    .put("token",my_login.getItemString("token"))
                    .put("tokentype",1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("User/getgift", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {}
                @Override
                public void onError(Throwable e) {}
                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o,"code").equals("0"))
                    {
                        context.Success();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
