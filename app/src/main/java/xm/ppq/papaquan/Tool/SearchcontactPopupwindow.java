package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.mobileim.channel.cloud.contact.YWProfileInfo;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWConversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.IMinit.UserProfileSampleHelper;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;

/**
 * Created by sunshaobei on 2017/4/8.
 */

public class SearchcontactPopupwindow{
    private Activity context;
    private View v;
    private EditText editText;
    private View hint;
    private View dismiss;
    private ListView listview;
    private PopupWindow popupWindow;
    private View clear;
    private boolean candismiss = true;
    private ArrayList<YWProfileInfo> mUserInfoList = new ArrayList<>();
    private ArrayList<YWProfileInfo> list = new ArrayList<>();
    private ContactListAdapter contactListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public SearchcontactPopupwindow(Activity context, View v)
    {
        this.context = context;
        this.v = v;
        this.mUserInfoList = UserProfileSampleHelper.mUserInfoList;
        initview();
    }

    private void initview() {
        View searchpopupwindow = LayoutInflater.from(context).inflate(R.layout.item_searchcontact_popupwindow, null);
        popupWindow = new PopupWindow(searchpopupwindow, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        editText = (EditText) searchpopupwindow.findViewById(R.id.search_edit);
        hint = searchpopupwindow.findViewById(R.id.edit_hint);
        dismiss = searchpopupwindow.findViewById(R.id.dismiss);
        listview = (ListView) searchpopupwindow.findViewById(R.id.listview);
        swipeRefreshLayout = (SwipeRefreshLayout) searchpopupwindow.findViewById(R.id.swipeview);
        contactListAdapter = new ContactListAdapter(context, list, R.layout.item_contactlistview);
        listview.setAdapter(contactListAdapter);
        clear = searchpopupwindow.findViewById(R.id.text);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        initListener();
    }

    public void show()
    {
        popupWindow.showAtLocation(v,Gravity.CENTER,0,0);
        editText.postDelayed(() -> new TypewritingUtil(context).ShowType(editText),200);
    }

    public PopupWindow getDefault()
    {
        return popupWindow;
    }

    private void initListener() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getContactData(editText.toString());
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s))
                {
                    candismiss = true;
                    swipeRefreshLayout.setVisibility(View.GONE);
                    hint.setVisibility(View.VISIBLE);
                    listview.setVisibility(View.GONE);
                    clear.setVisibility(View.GONE);
                    dismiss.setBackground(new ColorDrawable(Color.parseColor("#66000000")));
                }else {
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(true);
                    candismiss = false;
                    hint.setVisibility(View.GONE);
                    clear.setVisibility(View.VISIBLE);
                    dismiss.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                    listview.setVisibility(View.VISIBLE);
                    getContactData(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dismiss.setOnClickListener(v1 -> {
            if (candismiss)
            popupWindow.dismiss();
        });

        clear.setOnClickListener(v12 -> {
            editText.setText("");
            hint.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
            clear.setVisibility(View.GONE);
            dismiss.setBackground(new ColorDrawable(Color.parseColor("#66000000")));
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String userId = list.get(position).userId;
                Intent chattingActivityIntent = IMUtils.getIMKit().getChattingActivityIntent(userId, IMUtils.APP_KEY);
                context.startActivity(chattingActivityIntent);
            }
        });
    }

    private void getContactData(String s) {
        list.clear();
        for (int i = 0; i < mUserInfoList.size(); i++) {
            YWProfileInfo ywProfileInfo = mUserInfoList.get(i);
            String s1 = ywProfileInfo.nick.toUpperCase();
            String s2 = s.toUpperCase();
            if (s1.contains(s2))
            {
                list.add(ywProfileInfo);
            }
        }
        swipeRefreshLayout.setRefreshing(false);
        contactListAdapter.notifyDataSetChanged();
    }

    class ContactListAdapter extends ConcreteAdapter<YWProfileInfo> {
        public ContactListAdapter(Context context, List<YWProfileInfo> list, int itemLayout) {
            super(context, list, itemLayout);
        }

        @Override
        protected void convert(ViewHolder viewHolder, YWProfileInfo item, int position) {
            ImageView image = viewHolder.getView(R.id.headicon);
            ImageLoading.Circular(context,item.icon,R.drawable.default_icon,image);
            viewHolder.setText(item.nick,R.id.nickname);
            String userId = item.userId;
            YWConversation conversationByConversationId = IMUtils.getIMKit().getIMCore().getConversationService().getConversationByConversationId("itunvawh" + item.userId);
            if (conversationByConversationId!=null)
            viewHolder.setText(conversationByConversationId.getLatestContent(),R.id.content);
        }
    }
}
