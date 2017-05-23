package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.WxBean;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.life.tenxun.BaseUiListener;
import xm.ppq.papaquan.wxapi.WXEntryActivity;

/**
 * 分享和支付工具类
 * Created by EdgeDi on 9:42.
 */

public class ShareAndPay {

    /**
     * 微信分享
     *
     * @param flag       0--好友 1--朋友圈
     * @param context    上下文，用于注册wxapi
     * @param url        分享地址
     * @param title      分享标题
     * @param bitmap_url 分享图片
     */
    public static void WxShare(int flag, Activity context, String url, String title, String bitmap_url) {
        // 微信注册初始化
        IWXAPI api = WXAPIFactory.createWXAPI(context, WXEntryActivity.APP_ID, true);
        api.registerApp(WXEntryActivity.APP_ID);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        WXWebpageObject web_pageUrl = new WXWebpageObject();
        web_pageUrl.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(web_pageUrl);
        msg.title = title;
        msg.description = "点击查看详情";
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    msg.setThumbImage(ImageLoading.getBitMap(context, bitmap_url));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = flag;
                api.sendReq(req);
            }
        }.start();
    }

    public static void WxShare(int flag, Activity context, String url, String title, Bitmap bitmap) {
        // 微信注册初始化
        IWXAPI api = WXAPIFactory.createWXAPI(context, WXEntryActivity.APP_ID, true);
        api.registerApp(WXEntryActivity.APP_ID);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(context, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        WXWebpageObject web_pageUrl = new WXWebpageObject();
        web_pageUrl.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(web_pageUrl);
        msg.title = title;
        msg.description = "点击查看详情";
        msg.setThumbImage(bitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag;
        api.sendReq(req);
    }

    /**
     * 微信支付
     *
     * @param jsonObject 请求参数
     * @param activity
     * @param url        请求网址
     */
    public static void WxPay(Activity activity, String url, JSONObject jsonObject) {
        OkPotting.getInstance("").AskOne(url, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                WxBean wxBean = (WxBean) JsonUtil.fromJson(s, WxBean.class);
                if (wxBean.getCode() == 0) {
                    IWXAPI api = WXAPIFactory.createWXAPI(activity, WXEntryActivity.APP_ID);
                    api.registerApp(WXEntryActivity.APP_ID);
                    PayReq request = new PayReq();
                    request.appId = WXEntryActivity.APP_ID;
                    request.partnerId = wxBean.getData().getPartnerid();
                    request.prepayId = wxBean.getData().getPrepayid();
                    request.packageValue = wxBean.getData().getPackageX();
                    request.nonceStr = wxBean.getData().getNoncestr();
                    request.timeStamp = wxBean.getData().getTimestamp();
                    request.sign = wxBean.getData().getSign();
                    api.sendReq(request);
                } else {
                    Toast.makeText(activity, "支付错误，请重新下单付款", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 支付宝支付
     *
     * @param activity
     * @param url        请求参数网址
     * @param jsonObject 请求参数
     * @param mHandler   回掉入口 例：
     *                   private Handler mHandler = new Handler() {
     *                   public void handleMessage(Message msg) {
     *                   if (msg.what == 0x1) {
     *                   Log.e("ssss", msg.obj.toString());
     *                   }
     *                   }
     *                   };
     */
    public static void ZhiPay(Activity activity, String url, JSONObject jsonObject, Handler mHandler) {
        OkPotting.getInstance("").AskOne(url, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        PayTask payTask = new PayTask(activity);
                        mHandler.obtainMessage(0x1, payTask.payV2(s, true)).sendToTarget();
                    }
                }.start();
            }
        });
    }

    public static final String APP_ID = "1105593601";

    /**
     * 分享qq空间和好友
     *
     * @param tencent    必须在Create里实例化
     * @param activity   分享参数
     * @param url        分享网址
     * @param title      分享标题
     * @param bitmap_url 分享图片
     * @param type       0--空间 1--好友
     */
    public static void TXShare(Tencent tencent, Activity activity, String url, String title, String bitmap_url, int type) {
        if (tencent.isSessionValid() && tencent.getOpenId() == null) {
            Log.e("您还未安装QQ", "111");
        }
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "点击查看详情");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, bitmap_url);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "啪啪圈");
        if (type == 0) {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        } else {
            params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        }
        tencent.shareToQQ(activity, params, new BaseUiListener());
    }
}