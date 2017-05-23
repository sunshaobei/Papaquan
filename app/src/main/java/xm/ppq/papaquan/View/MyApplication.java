package xm.ppq.papaquan.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.mobileim.utility.CrashHandler;
import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.bumptech.glide.Glide;
import com.lzy.ninegrid.NineGridView;
import com.umeng.analytics.MobclickAgent;
import com.yixia.camera.VCamera;

import java.io.File;

import xm.ppq.papaquan.Tool.IMUtils;

/**
 * EdgeDi
 * Created by Application on 2017/2/16.
 */

public class MyApplication extends MultiDexApplication {

    public static String VIDEO_PATH = "/sdcard/Papaquan/";

    @Override
    public void onCreate() {
        super.onCreate();
        NineGridView.setImageLoader(new ImageGlide());
        IMUtils.initIM(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        initCloudChannel(getApplicationContext());
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        VIDEO_PATH += String.valueOf(System.currentTimeMillis());
        File file = new File(VIDEO_PATH);
        if (!file.exists()) file.mkdirs();

        //设置视频缓存路径
        VCamera.setVideoCachePath(VIDEO_PATH);

        // 开启log输出,ffmpeg输出到logcat
        VCamera.setDebugMode(true);

        // 初始化拍摄SDK，必须
        VCamera.initialize(this);

    }

    /**
     * 初始化云推送通道
     *
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                Log.e("TAG", "init cloudchannel success");
            }

            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.e("TAG", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
        BasicCustomPushNotification notification = new BasicCustomPushNotification();
        notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private class ImageGlide implements NineGridView.ImageLoader {

        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url).into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}