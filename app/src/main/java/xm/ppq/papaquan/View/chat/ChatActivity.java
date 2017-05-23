package xm.ppq.papaquan.View.chat;

import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ChatAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ChatActivity extends BaseActivity {

    @BindView(R.id.chat_list)
    ListView chat_list;
    @BindView(R.id.finish_result)
    TextView finish_result;

    private ChatAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_chat;
    }

    private List<String> list;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("12.5" + i);
        }
        if (adapter == null) {
            adapter = new ChatAdapter(this, list, R.layout.chat_item);
            chat_list.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
        chat_list.setSelection(Gravity.BOTTOM);
        finish_result.setText("");
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }
}
