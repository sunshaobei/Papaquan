package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

import xm.ppq.papaquan.Bean.AiteBean;
import xm.ppq.papaquan.Bean.life.RemindBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.news_mine_money.Mine_MoneyActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by EdgeDi on 18:58.
 */

public class TuiSongService extends MessageReceiver {

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
//        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
//        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Intent intent = null;
        switch (JsonUtil.getString(extraMap, "type")) {
            case "reward"://打赏
                intent = new Intent(context, TrendTopicDetailActivity.class);
                intent.putExtra("tid", JsonUtil.getString(extraMap, "tid"));
                break;
            case "panicuse"://抢购
                intent = new Intent(context, Panic_BuyingActivity.class);
                intent.putExtra("pid", JsonUtil.getInt(extraMap, "pid"));
                break;
            case "couponuse"://精选
                intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("cid", JsonUtil.getInt(extraMap, "cid"));
                break;
            case "cardend"://红卡
                intent = new Intent(context, RedCardActivity.class);
                break;
            case "comment"://回复
                intent = new Intent(context, TrendTopicDetailActivity.class);
                intent.putExtra("tid", JsonUtil.getString(extraMap, "tid"));
                break;
            case "at"://艾特
                intent = new Intent(context, Mine_MoneyActivity.class);
                intent.putExtra("mom", "@我的");
                break;
            case "reply"://评论
                intent = new Intent(context, TrendTopicDetailActivity.class);
                intent.putExtra("tid", JsonUtil.getString(extraMap, "tid"));
                break;
            case "panic"://抢购
                RemindBean remindBean = (RemindBean) JsonUtil.fromJson(extraMap, RemindBean.class);
                if (remindBean != null) {
                    intent = new Intent(context, Panic_BuyingActivity.class);
                    intent.putExtra("pid", Integer.valueOf(remindBean.getPid()));
                }
                break;
            default:
                intent = new Intent(context, MainActivity.class);
                break;
        }
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
//        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
//        Log.e("MyMessageReceiver", "onNotificationRemoved");
    }
}