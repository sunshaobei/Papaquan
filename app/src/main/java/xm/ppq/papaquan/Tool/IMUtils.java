package xm.ppq.papaquan.Tool;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.aop.AdviceBinder;
import com.alibaba.mobileim.aop.PointCutEnum;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWCustomConversationUpdateModel;
import com.alibaba.mobileim.conversation.YWMessageType;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.media.MediaService;
import com.alibaba.wxlib.util.SysUtil;

import java.util.Date;
import java.util.List;

import xm.ppq.papaquan.Tool.IMinit.ChattingOperation;
import xm.ppq.papaquan.Tool.IMinit.ChattingUI;
import xm.ppq.papaquan.Tool.IMinit.ConversationOperation;
import xm.ppq.papaquan.Tool.IMinit.ConversationUI;
import xm.ppq.papaquan.Tool.IMinit.UserProfileSampleHelper;
import xm.ppq.papaquan.Tool.IMinit.YWSDKGlobalConfigSample;

/**
 * Created by sunshaobei on 2017/3/25.
 */

public class IMUtils {

    public static final String APP_KEY = "23695090";

    public static void initIM(Application application){
        //必须首先执行这部分代码, 如果在":TCMSSevice"进程中，无需进行云旺（OpenIM）和app业务的初始化，以节省内存;
        SysUtil.setApplication(application);
        if(SysUtil.isTCMSServiceProcess(application)){
            return;
        }
        AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_UI_POINTCUT, ConversationUI.class);
        AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_OPERATION_POINTCUT, ConversationOperation.class);
        AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_UI_POINTCUT, ChattingUI.class);
        AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_OPERATION_POINTCUT, ChattingOperation.class);
        //全局配置修改
        AdviceBinder.bindAdvice(PointCutEnum.YWSDK_GLOBAL_CONFIG_POINTCUT, YWSDKGlobalConfigSample.class);
        if(SysUtil.isMainProcess()) {
            YWAPI.init(application, APP_KEY);
        }
    }

    public static String userid = "0";

    public static void setUserId(String id)
    {
        userid = id;
    }
    public static String password = "ppqa1b2c3";
    public static void login(){
        UserProfileSampleHelper userProfileSampleHelper = new UserProfileSampleHelper();
        userProfileSampleHelper.initProfileCallback();
        IYWLoginService loginService = getIMKit().getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new com.alibaba.mobileim.channel.event.IWxCallback() {
            @Override
            public void onSuccess(Object... objects) {
                List<YWConversation> conversationList = getIMKit().getConversationService().getConversationList();
                for (int i = 0; i < conversationList.size(); i++) {
                        if (conversationList.get(i).getConversationType()!=YWConversationType.P2P)
                        getIMKit().getIMCore().getConversationService().deleteConversation(conversationList.get(i));
                }
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i) {

            }
        });
    }
    public static void loginout(){
        IYWLoginService loginService = getIMKit().getLoginService();
        loginService.logout(new IWxCallback() {
            @Override
            public void onSuccess(Object... objects) {

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i) {

            }
        });
    }

    public static Fragment IMfragment(){
        return getIMKit().getConversationFragment();
    }

    public static YWIMKit getIMKit()
    {
        if (mIMKit==null)
        {
            mIMKit = YWAPI.getIMKitInstance(userid, APP_KEY);
        }
        return mIMKit;
    }
    public  static YWIMKit mIMKit;
}
