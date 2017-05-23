package xm.ppq.papaquan.Tool.IMinit;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.mobileim.channel.cloud.contact.YWProfileInfo;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.YWContactFactory;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.example.lib_sunshaobei2017.app.BaseActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;

public class ContackListActivity extends BaseActivity {

    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.edit_hint)
    View edit_hint;

    private ArrayList<YWProfileInfo> mUserInfoList = UserProfileSampleHelper.mUserInfoList;
    private ContactListAdapter contactListAdapter;
    private YWMessage msg;
    private SharedPreferencesPotting my_login;

    @Override
    public int oncreate() {
        return R.layout.activity_contack_list;
    }

    @Override
    public void initView() {
        initStatusBar(bar);
        my_login = new SharedPreferencesPotting(this, "my_login");
        list.addAll(mUserInfoList);
        contactListAdapter = new ContactListAdapter(this, list, R.layout.item_contactlistview);
        listView.setAdapter(contactListAdapter);
        msg = (YWMessage) getIntent().getSerializableExtra("msg");
    }

    @Override
    public void initListener() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    list.clear();
                    list.addAll(mUserInfoList);
                    contactListAdapter.notifyDataSetChanged();
                    edit_hint.setVisibility(View.VISIBLE);
                } else {
                    edit_hint.setVisibility(View.GONE);
                    getContactData(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            YWProfileInfo ywProfileInfo = list.get(position);
            String userId = ywProfileInfo.userId;
            //TODO 转发给某人
            //转发给个人示例
            IYWContact appContact = YWContactFactory.createAPPContact( userId,IMUtils.getIMKit().getIMCore().getAppKey());
            if (appContact.getUserId().equals("u_"+my_login.getItemInt("uid")))
                ShortToast("不能转发给自己");
            else
            IMUtils.getIMKit().getConversationService().forwardMsgToContact(appContact,msg, forwardCallBack);
        });
    }

    final IWxCallback forwardCallBack = new IWxCallback() {

        @Override
        public void onSuccess(Object... result) {
            ShortToast("转发成功");
            finish();
        }

        @Override
        public void onError(int code, String info) {

        }

        @Override
        public void onProgress(int progress) {

        }
    };


    private ArrayList<YWProfileInfo> list = new ArrayList<>();

    private void getContactData(String s) {
        list.clear();
        for (int i = 0; i < mUserInfoList.size(); i++) {
            YWProfileInfo ywProfileInfo = mUserInfoList.get(i);
            String s1 = ywProfileInfo.nick.toUpperCase();
            String s2 = s.toUpperCase();
            if (s1.contains(s2)) {
                list.add(ywProfileInfo);
            }
        }
        contactListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onstart() {

    }

    @Override
    public void onresume() {

    }

    @Override
    public void onstop() {

    }

    @Override
    public void ondestroy() {

    }

    class ContactListAdapter extends ConcreteAdapter<YWProfileInfo> {
        public ContactListAdapter(Context context, List<YWProfileInfo> list, int itemLayout) {
            super(context, list, itemLayout);
        }

        @Override
        protected void convert(ViewHolder viewHolder, YWProfileInfo item, int position) {
            ImageView image = viewHolder.getView(R.id.headicon);
            ImageLoading.Circular(ContackListActivity.this, item.icon, R.drawable.default_icon, image);
            viewHolder.setText(item.nick, R.id.nickname);
            String userId = item.userId;
            YWConversation conversationByConversationId = IMUtils.getIMKit().getIMCore().getConversationService().getConversationByConversationId("itunvawh" + item.userId);
            if (conversationByConversationId != null)
                viewHolder.setText(conversationByConversationId.getLatestContent(), R.id.content);
        }
    }

}
