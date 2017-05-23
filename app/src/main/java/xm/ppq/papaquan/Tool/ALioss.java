package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ALioss {

    public String Url = "http://oss-cn-hangzhou.aliyuncs.com/";
    private Context context;
    private OSSCredentialProvider provider;
    private OSS oss;
    private ALi_ossInterface Ainterface;

    public ALioss(Context context) {
        this.context = context;
        Ainterface = (ALi_ossInterface) context;
        provider = new OSSPlainTextAKSKCredentialProvider(BaseUrl.OSS_KEY, BaseUrl.OSS_SELECT);
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConcurrentRequest(30 * 1000);
        conf.setSocketTimeout(30 * 1000);
        conf.setConnectionTimeout(10000);
        oss = new OSSClient(context.getApplicationContext(), Url, provider, conf);
    }

    public void setAinterface(ALi_ossInterface ainterface) {
        Ainterface = ainterface;
    }

    private String path;

    public void init(String filepath, String postfix, int type) {
        String Number = BaseActivity.Number();
        String atlas = Number + postfix;
        if (type == 3) {
            if (filepath.endsWith(".mp4")) {
                path = "video/";
                Ainterface.setVideoPic("cover/" + Number + ".jpg");
            } else {
                path = "cover/";
            }
        } else {
            path = "android/";
        }
        PutObjectRequest put = new PutObjectRequest("papaq", path + atlas, filepath);
        put.setProgressCallback((putObjectRequest, currentSize, totalSize) -> {
            if (type == 3) {
                if (filepath.endsWith(".mp4")) {
                    int i = (int) ((currentSize * 100) / totalSize);
                    Ainterface.upProgress(i);
                }
            }
        });
        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                String objectKey = putObjectRequest.getObjectKey();
                switch (type) {
                    case 3:
                        Ainterface.upVideoSuccess(objectKey);
                        break;
                    case 1:
                        Ainterface.upImageSuccess(objectKey);
                        break;
                    case 2:
                        Ainterface.upImageSuccess(BaseUrl.BITMAP + objectKey);
                        break;
                    case 4:
                        Ainterface.upImageSuccess(objectKey);
                        break;
                }
            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Ainterface.upImageError("上传失败");
            }
        });
//
//        task.waitUntilFinished();//等待至任务完成
    }

    private OSSAsyncTask task;

    public void Cancel() {
        if (task != null) {
            task.cancel();
        }
    }

}