package xm.ppq.papaquan.Tool.IMinit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListOperation;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWCustomConversationBody;

import java.util.List;

import xm.ppq.papaquan.Tool.ConversationDialog;
import xm.ppq.papaquan.Tool.IMUtils;


/**
 * Created by sunshaobei on 2017/3/25.
 */

public class ConversationOperation extends IMConversationListOperation {
    public ConversationOperation(Pointcut pointcut) {
        super(pointcut);
    }


    private boolean istop;
    public static String text = "";
    @Override
    public boolean onConversationItemLongClick(Fragment fragment, YWConversation conversation) {
         istop = conversation.isTop();
        YWConversationType type = conversation.getConversationType();
        if (type == YWConversationType.P2P) {
            ConversationDialog conversationDialog = new ConversationDialog();
            conversationDialog.show(fragment.getActivity().getFragmentManager(),"conversationDialog");
            if (istop)
            {
                text = "取消置顶";
            }else {
                text = "置顶聊天";
            }
            conversationDialog.setOnSelectListener(new ConversationDialog.OnSelectListener() {
                @Override
                public void onDelete() {
                    IMUtils.getIMKit().getIMCore().getConversationService().deleteConversationAndKeepMsg(conversation);
                    conversationDialog.dismiss();
                }
                @Override
                public void onSettop() {
                    YWIMCore imCore = IMUtils.getIMKit().getIMCore();
                    if (istop)
                    {
                        imCore.getConversationService().removeTopConversation(conversation);
                    }else {
                        imCore.getConversationService().setTopConversation(conversation);
                    }
                    conversationDialog.dismiss();
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean onItemClick(Fragment fragment, YWConversation conversation) {
        YWConversationType type = conversation.getConversationType();
        if (type == YWConversationType.P2P){

            return false;
        } else if (type == YWConversationType.Tribe){
            return true;
        } else if (type == YWConversationType.Custom){
            return true;
        }
        return false;
    }
    /**
     * 返回自定义会话和群会话的默认头像 如返回本地的 R.drawable.test
     * 该方法只适用设置自定义会话和群会话的头像，设置单聊会话头像请参考{@link com.taobao.openimui.sample.UserProfileSampleHelper}
     *
     * @param fragment
     * @param conversation
     * @return
     */
    @Override
    public int getConversationDefaultHead(Fragment fragment, YWConversation conversation) {
        return 0;
    }

}
