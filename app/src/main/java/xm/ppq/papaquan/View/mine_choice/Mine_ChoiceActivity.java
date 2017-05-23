package xm.ppq.papaquan.View.mine_choice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.widget.pagerslidingtabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.ScarePageAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.scare_buying.fragment.ScareFragMent;


/**
 * Created by 我的精选 on 2017/4/10.
 */

public class Mine_ChoiceActivity extends BaseActivity {

    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tab_strip;
    @BindView(R.id.choice_page)
    ViewPager choice_page;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private String[] title = new String[]{"待付款", "待使用", "待评论", "退款"};
    private List<Fragment> list;

    private ScarePageAdapter adapter;
    private ScareFragMent scareFragMent;
    private Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_choice;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("我的精选");
        list = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            scareFragMent = new ScareFragMent(2);
            bundle = new Bundle();
            bundle.putInt("type", i + 2);
            bundle.putString("number", title[i]);
            scareFragMent.setArguments(bundle);
            list.add(scareFragMent);
            bundle = null;
            scareFragMent = null;
        }
        if (adapter == null) {
            adapter = new ScarePageAdapter(getSupportFragmentManager());
            adapter.setFragments(list);
            adapter.setTitle(title);
            choice_page.setAdapter(adapter);
        } else {
            adapter.setFragments(list);
            adapter.notifyDataSetChanged();
        }
        tab_strip.setViewPager(choice_page);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            setResult(IntentCode.ResultCode.BACKTOLIFE);
            finish();
        }
    }
}