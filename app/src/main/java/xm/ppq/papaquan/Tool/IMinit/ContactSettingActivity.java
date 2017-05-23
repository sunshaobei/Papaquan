package xm.ppq.papaquan.Tool.IMinit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.mobileim.YWAccount;
import com.alibaba.mobileim.channel.constant.YWProfileSettingsConstants;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.YWContactManager;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.fundamental.widget.WXNetworkImageView;
import com.alibaba.mobileim.fundamental.widget.WxAlertDialog;
import com.alibaba.mobileim.utility.IMNotificationUtils;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BlurUtil;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.View.BaseActivity;

public class ContactSettingActivity extends BaseActivity {

    @BindView(R.id.bar)
    LinearLayout statubar;
    private ImageView contactHead;
    private TextView contactShowName;
    private ImageView msgRemindSwitch;
    private RelativeLayout clearMsgRecordLayout;
    private String appKey;
    private String userId;
    private int msgRecFlag = YWProfileSettingsConstants.RECEIVE_PEER_MSG;
    private YWAccount account;

    private YWContactManager contactManager;
    private IYWContact contact;

    private IYWConversationService conversationService;
    private YWConversation conversation;
    private Handler uiHandler;
    private String avatarPath;
    private RelativeLayout relative;

    @Override
    protected int getLayout() {
        return R.layout.demo_activity_contact_setting;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statubar);
        Intent intent = getIntent();
        if(intent != null) {
            appKey = intent.getStringExtra("AppKey");
            userId = intent.getStringExtra("UserId");
        }
        uiHandler = new Handler(Looper.getMainLooper());
        account = IMUtils.getIMKit().getIMCore();
        contactManager = (YWContactManager) IMUtils.getIMKit().getContactService();
        contact = contactManager.getContactProfileInfo(userId, appKey);
        avatarPath = contact.getAvatarPath();
        conversationService = IMUtils.getIMKit().getConversationService();
        conversation = conversationService.getConversationByUserId(userId);
        if(contactManager != null) {
            msgRecFlag = contactManager.getMsgRecFlagForContact(userId, appKey);
        }
        initViews();
    }

    @Override
    protected void setListener() {

    }

    public static Intent getContactSettingActivityIntent(Context context, String appKey, String userId) {
        Intent intent = new Intent(context, ContactSettingActivity.class);
        intent.putExtra("AppKey", appKey);
        intent.putExtra("UserId", userId);
        return intent;
    }

    private void initViews() {
        contactHead = (ImageView) findViewById(R.id.head);
        contactShowName = (TextView) findViewById(R.id.contact_show_name);
        if (contact != null){
            contactShowName.setText(contact.getShowName());
        }else{
            contactShowName.setText(userId);
        }

        msgRemindSwitch = (ImageView) findViewById(R.id.receive_msg_remind_switch);
        clearMsgRecordLayout = (RelativeLayout) findViewById(R.id.clear_msg_record);
        if(msgRecFlag != YWProfileSettingsConstants.RECEIVE_PEER_MSG_NOT_REMIND) {
            msgRemindSwitch.setImageResource(R.drawable.on_switch);
        } else {
            msgRemindSwitch.setImageResource(R.drawable.off_switch);
        }
        msgRemindSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMsgRecType();
            }
        });
        clearMsgRecordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearMsgRecord();
            }
        });
        initTitle();
    }

    private void initTitle() {
        View titleView = findViewById(R.id.title_bar);
        titleView.setVisibility(View.VISIBLE);
        titleView.setBackgroundColor(Color.parseColor("#e60012"));

        TextView leftButton = (TextView) findViewById(R.id.left_button);
        leftButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.demo_common_back_btn_white, 0, 0, 0);
        leftButton.setText("返回");
        leftButton.setTextColor(Color.WHITE);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView title = (TextView) findViewById(R.id.title_self_title);
        title.setText("聊天设置");
        title.setTextColor(Color.WHITE);

        WXNetworkImageView wxNetworkImageView = (WXNetworkImageView) findViewById(R.id.head);
        ImageLoading.Circular(this,avatarPath,R.drawable.default_icon,wxNetworkImageView);
        relative = (RelativeLayout) findViewById(R.id.contact_info);
        new Thread(()->{
            Bitmap bitmap = null;
            try {
                bitmap = ImageLoading.getBitMap(ContactSettingActivity.this, avatarPath);
                Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, false));
                handler.obtainMessage(0,drawable).sendToTarget();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            relative.setBackground((Drawable) msg.obj);
        }
    };

    private void setMsgRecType() {
        if (contact == null){
            return;
        }
        if(msgRecFlag != YWProfileSettingsConstants.RECEIVE_PEER_MSG_NOT_REMIND) {
            contactManager.setContactMsgRecType(contact, YWProfileSettingsConstants.RECEIVE_PEER_MSG_NOT_REMIND, 10, new SettingsCallback());
        } else {
            contactManager.setContactMsgRecType(contact, YWProfileSettingsConstants.RECEIVE_PEER_MSG, 10, new SettingsCallback());
        }
    }

    class SettingsCallback implements IWxCallback {

        @Override
        public void onError(int code, String info) {

        }

        @Override
        public void onProgress(int progress) {

        }

        @Override
        public void onSuccess(Object... result) {
            if(contact != null) {
                msgRecFlag = contactManager.getMsgRecFlagForContact(contact);
            } else {
                msgRecFlag = contactManager.getMsgRecFlagForContact(userId, appKey);
            }
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(msgRecFlag != YWProfileSettingsConstants.RECEIVE_PEER_MSG_NOT_REMIND) {
                        msgRemindSwitch.setImageResource(R.drawable.on_switch);
                    } else {
                        msgRemindSwitch.setImageResource(R.drawable.off_switch);
                    }
                }
            });
        }
    }

    protected void clearMsgRecord() {
        String message = "清空的消息再次漫游时不会出现。你确定要清空聊天消息吗？";
        AlertDialog.Builder builder = new WxAlertDialog.Builder(ContactSettingActivity.this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                conversation.getMessageLoader().deleteAllMessage();
                                IMNotificationUtils.getInstance().showToast("记录已清空",
                                        ContactSettingActivity.this);
                            }
                        })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
        builder.create().show();
    }

}
