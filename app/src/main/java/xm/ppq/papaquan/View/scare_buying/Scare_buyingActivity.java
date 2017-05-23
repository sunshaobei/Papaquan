package xm.ppq.papaquan.View.scare_buying;

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
 * Created by 我的抢购 on 2017/4/10.
 */

public class Scare_buyingActivity extends BaseActivity {

    @BindView(R.id.tab_strip)
    PagerSlidingTabStrip tab_strip;
    @BindView(R.id.scare_page)
    ViewPager scare_page;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private String[] title = new String[]{"全部抢购", "待付款", "待使用", "待评论"};
    private List<Fragment> list;

    private ScarePageAdapter adapter;
    private ScareFragMent scareFragMent;
    private Bundle bundle;

    @Override
    protected int getLayout() {
        return R.layout.activity_scare_buying;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("我的抢购");
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bundle = new Bundle();
            scareFragMent = new ScareFragMent(1);
            bundle.putString("number", title[i]);
            switch (i) {
                case 0:
                    bundle.putString("type1", "all");
                    break;
                case 1:
                    bundle.putString("type1", "waitpay");
                    break;
                case 2:
                    bundle.putString("type1", "waituse");
                    break;
                case 3:
                    bundle.putString("type1", "waitcomment");
                    break;
            }
            scareFragMent.setArguments(bundle);
            list.add(scareFragMent);
            bundle = null;
            scareFragMent = null;
        }
        if (adapter == null) {
            adapter = new ScarePageAdapter(getSupportFragmentManager());
            adapter.setFragments(list);
            adapter.setTitle(title);
            scare_page.setAdapter(adapter);
        } else {
            adapter.setFragments(list);
            adapter.notifyDataSetChanged();
        }
        tab_strip.setViewPager(scare_page);
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
