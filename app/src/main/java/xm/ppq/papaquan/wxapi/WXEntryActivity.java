package xm.ppq.papaquan.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.LoginEevenbus;
import xm.ppq.papaquan.Bean.WxLoginBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.bindphone.BindPhoneActivity;

/**
 * WXEntryActivity 是微信固定的Activiy、 不要改名字、并且放到你对应的项目报名下面、
 * 例如： ....(package报名).wxapi.WXEntryActivity
 * 不然无法回调、切记...
 * Wx  回调接口 IWXAPIEventHandler
 * <p/>
 * 关于WXEntryActivity layout。 我们没给页面、而是把Activity  主题 android:theme="@android:style/Theme.Translucent" 透明、
 * <p/>
 * User: MoMo - Nen
 * Date: 2015-10-24
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    public static final String APP_ID = "wx5fe3291df5212a0b";
    public static final String APP_SECRET = "a95e0eda6770397bd2a275f18de2b7ad";
    private IWXAPI mApi;

    private SharedPreferencesPotting potting;
    private SharedPreferencesPotting logpotting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        potting = new SharedPreferencesPotting(this, "my_wx");
        logpotting = new SharedPreferencesPotting(this, "my_login");
        mApi = WXAPIFactory.createWXAPI(this, APP_ID, true);
        pushService = PushServiceFactory.getCloudPushService();
        mApi.handleIntent(this.getIntent(), this);
    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq baseReq) {
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (logpotting.getItemString("result").equals("分享")) {
                    logpotting.setItemString("result", "登录")
                            .build();
                    Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                    if (sendResp != null) {
                        String code = sendResp.code;
                        getAccess_token(code);
                    }
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:

                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:

                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }
        finish();
    }

    private String openid;
    private String access_token;
    private CloudPushService pushService;

    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APP_ID + "&secret=" + APP_SECRET + "&code=" + code + "&grant_type=authorization_code";
        OkPotting.getInstance("").ComAsk(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                openid = JsonUtil.getString(result, "openid");
                access_token = JsonUtil.getString(result, "access_token");
                OkPotting.getInstance("").ComAsk("https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String s = response.body().string();
                        String nickname = JsonUtil.getString(s, "nickname");
                        String openid = JsonUtil.getString(s, "openid");
                        String sex = JsonUtil.getString(s, "sex");
                        String headurl = JsonUtil.getString(s, "headimgurl");
                        headurl = headurl.substring(0, headurl.length() - 1);
                        headurl += "132";
                        String unionid = JsonUtil.getString(s, "unionid");
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("unionid", unionid);
                            String finalHeadurl = headurl;
                            OkPotting.getInstance("http://app.papaquan.com/index.php/index/").AskOne(BaseUrl.WXLOGIN, jsonObject.toString(), new Subscriber<String>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(String s) {
                                    if (s != null) {
                                        switch (JsonUtil.getInt(s, "code")) {
                                            case -1:
                                                potting.setItemString("nickname", nickname)
                                                        .setItemString("openid", openid)
                                                        .setItemString("sex", sex)
                                                        .setItemString("headurl", finalHeadurl)
                                                        .setItemString("unionid", unionid)
                                                        .build();
                                                Intent intent = new Intent(WXEntryActivity.this, BindPhoneActivity.class);
                                                startActivity(intent);
                                                break;
                                            case 0:
                                                WxLoginBean wxLoginBean = (WxLoginBean) JsonUtil.fromJson(s, WxLoginBean.class);
                                                if (wxLoginBean != null) {
                                                    pushService.bindAccount(String.valueOf(wxLoginBean.data.uid), new CommonCallback() {
                                                        @Override
                                                        public void onSuccess(String s) {
                                                            Log.e("TAG", "绑定成功----" + s);
                                                        }

                                                        @Override
                                                        public void onFailed(String s, String s1) {
                                                            Log.e("TAG", s + ":" + s1);
                                                        }
                                                    });
                                                    logpotting.setItemString("token", wxLoginBean.data.token)
                                                            .setItemInt("uid", wxLoginBean.data.uid)
                                                            .setItemString("status", "已登录")
                                                            .build();
                                                    EventBus.getDefault().post(new LoginEevenbus("成功"));
                                                }
                                                break;
                                        }
                                    }
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}