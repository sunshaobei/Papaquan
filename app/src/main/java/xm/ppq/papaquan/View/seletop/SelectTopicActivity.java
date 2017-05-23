package xm.ppq.papaquan.View.seletop;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Adapter.TopicAdapter;
import xm.ppq.papaquan.Bean.TopicBean;
import xm.ppq.papaquan.Presenter.seletop.Mutual_Select_Topic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;

/**
 * Created by 发表话题 on 2017/2/21.
 */

public class SelectTopicActivity extends BaseActivity implements Select_Topic_Total {

    @BindView(R.id.topic_list)
    ListView topic_list;
    @BindView(R.id.selecct_cancel)
    TextView cancel;
    @BindView(R.id.bar)
    LinearLayout bar;

    private TopicAdapter adapter;
    private Mutual_Select_Topic select_topic;
    private SharedPreferencesPotting potting;
    private ArrayList<String> list;
    private Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.activity_select_topic;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        potting = new SharedPreferencesPotting(this, "my_login");
        intent = getIntent();
        list = (ArrayList<String>) intent.getSerializableExtra("list");

        select_topic = new Mutual_Select_Topic(this);
        select_topic.start();
    }

    @Override
    protected void setListener() {
        topic_list.setOnItemClickListener((parent, view, position, id) -> {
            if (!list.contains(aitelist.get(position).uid))
            {
                intent.putExtra("id", aitelist.get(position));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }


    ArrayList<TopicBean.Data> aitelist = new ArrayList<>();

    @Override
    public void setTopicList(List<TopicBean.Data> topicList) {
        aitelist.clear();
        aitelist.addAll(topicList);
        if (adapter == null) {
            adapter = new TopicAdapter(this, topicList, R.layout.topic_list_item);
            adapter.setType(1);
            topic_list.setAdapter(adapter);
        } else {
            adapter.setList(topicList);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.selecct_cancel})
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.selecct_cancel:
                finish();
                break;
        }
    }
}