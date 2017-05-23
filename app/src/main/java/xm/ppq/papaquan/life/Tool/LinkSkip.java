package xm.ppq.papaquan.life.Tool;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.Toast;

import xm.ppq.papaquan.View.Life.allsortofseller.AllSortOfSellerActivity;
import xm.ppq.papaquan.View.Life.classification.ClassificationActivity;
import xm.ppq.papaquan.View.Life.discountNchoose.DiscountAndChooseActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;
import xm.ppq.papaquan.View.Life.panic_buying.Panic_BuyingActivity;
import xm.ppq.papaquan.View.Life.productdetail.ProductDetailActivity;
import xm.ppq.papaquan.View.Life.redcardfive.ReCardFiveActivity;
import xm.ppq.papaquan.View.Life.restaurant.RestaurantActivity;
import xm.ppq.papaquan.View.Life.scare_past.Scare_PastActivity;
import xm.ppq.papaquan.View.Life.selleroof.SellerOofActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.mine_balance.Mine_BalanceActivity;
import xm.ppq.papaquan.View.mine_integral.Mine_IntegralActivity;
import xm.ppq.papaquan.View.owncoin.ExchangeDetailActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;
import xm.ppq.papaquan.View.signed.SignedActivity;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;
import xm.ppq.papaquan.View.topic_deail.Topic_DetailActivity;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;

/**
 * Created by EdgeDi on 15:13.
 */

public class LinkSkip {

    public static void Link(Context context, int key, Object id) {
        Intent intent = null;
        switch (key) {
            case 1://商家主页
                intent = new Intent(context, SellerOofActivity.class);
                break;
            case 2://总话题列表
                intent = new Intent(context, MainActivity.class);
                break;
            case 3://个人中心
                intent = new Intent(context, MainActivity.class);
                break;
            case 4://商户后台(入驻)
                intent = new Intent(context, TenantActivity.class);
                break;
            case 5://我的红卡页面
                intent = new Intent(context, RedCardActivity.class);
                break;
            case 6://折扣列表
                intent = new Intent(context, DiscountAndChooseActivity.class);
                break;
            case 7://精选列表
                intent = new Intent(context, DiscountAndChooseActivity.class);
                break;
            case 8://抢购列表
                intent = new Intent(context, Scare_PastActivity.class);
                break;
            case 9://我的积分
                intent = new Intent(context, Mine_IntegralActivity.class);
                break;
            case 10://我的余额
                intent = new Intent(context, Mine_BalanceActivity.class);
                break;
            case 11://纯五折列表
                intent = new Intent(context, ReCardFiveActivity.class);
                break;
            case 12://积分商城
                intent = new Intent(context, Mine_IntegralActivity.class);
                break;
            case 13://签到页面
                intent = new Intent(context, SignedActivity.class);
                break;
            case 14://单个商家详情
                intent = new Intent(context, Merchant_HomepageActivity.class);
                intent.putExtra("sid", String.valueOf(id.toString()));
                break;
            case 15://单个折扣详情
                intent = new Intent(context, RestaurantActivity.class);
                intent.putExtra("sid", String.valueOf(id.toString()));
                break;
            case 16://单个抢购详情
                intent = new Intent(context, Panic_BuyingActivity.class);
                intent.putExtra("pid", Integer.valueOf(id.toString()));
                break;
            case 17://单个精选详情
                intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("cid", Integer.valueOf(id.toString()));
                break;
            case 18://单个动态详情
                intent = new Intent(context, TrendTopicDetailActivity.class);
                intent.putExtra("tid", String.valueOf(id.toString()));
                break;
            case 19://单个话题列表
                intent = new Intent(context, Topic_DetailActivity.class);
                break;
            case 20://单个商家分类
                intent = new Intent(context, AllSortOfSellerActivity.class);
                intent.putExtra("hotid", String.valueOf(id.toString()));
                break;
            case 21://单个折扣行业
                intent = new Intent(context, ClassificationActivity.class);
                break;
            case 22://单个精选行业
                intent = new Intent(context, ClassificationActivity.class);
                break;
            case 23://单个用户主页
                intent = new Intent(context, Ta_SheetActivity.class);
                intent.putExtra("Uuid", String.valueOf(id.toString()));
                break;
            case 24://单个兑换详情
                intent = new Intent(context, ExchangeDetailActivity.class);
                intent.putExtra("gid", Integer.valueOf(id.toString()));
                break;
            default:
                break;
        }
        if (intent != null) {
            context.startActivity(intent);
        }
    }

    public static void Phone(Activity activity, String mum) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mum));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = {Manifest.permission.CALL_PHONE};
                if (activity.checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, 0);
                }
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        activity.startActivity(intent);
    }

    public static void Go2Chrome(Activity activity, String link) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(link);
            intent.setData(content_url);
            activity.startActivity(intent);
        } catch (Exception e) {

        }
    }
}