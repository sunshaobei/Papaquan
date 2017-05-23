package xm.ppq.papaquan.View.discuss_reply;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.discuss_reply.fragment.DiscussFragment;
import xm.ppq.papaquan.View.discuss_reply.fragment.ReplyFragment;

/**
 * Created by 评论与回复 on 2017/3/2.
 */

public class Discuss_ReplyActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.discuss_reply_viewpager)
    ViewPager discuss_reply_viewpager;
    @BindView(R.id.discuss_text)
    TextView discuss_text;
    @BindView(R.id.reply_text)
    TextView reply_text;
    @BindView(R.id.discuss_back)
    TextView back;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.indicator)
    Indicator indicator;

    @BindColor(R.color.register_colors)
    int gray;
    @BindColor(R.color.red_d_r)
    int red;
    @BindColor(R.color.line_view)
    int white;
    @BindColor(R.color.black)
    int black;

    private List<Fragment> fragments;
    private HomePagerAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_discuss_reply;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        indicator.setCount(2);
        indicator.setOffwidth(-10);
        indicator.setBackgroundColor(Color.TRANSPARENT);
        fragments = new ArrayList<>();
        fragments.add(new DiscussFragment());
        fragments.add(new ReplyFragment());
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        discuss_reply_viewpager.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        discuss_reply_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    indicator.onMove(positionOffset,position);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        discuss_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_true));
                        discuss_text.setTextColor(Color.parseColor("#e60012"));
                        reply_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_false));
                        reply_text.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        discuss_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleleft_false));
                        discuss_text.setTextColor(Color.WHITE);
                        reply_text.setBackground(getResources().getDrawable(R.drawable.linearbg_titleright_true));
                        reply_text.setTextColor(Color.parseColor("#e60012"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        back.setOnClickListener(this);
    }

    @OnClick({R.id.reply_text, R.id.discuss_text})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.reply_text:
                if (discuss_reply_viewpager.getCurrentItem() != 1) {
                    discuss_reply_viewpager.setCurrentItem(1);
                }
                break;
            case R.id.discuss_text:
                if (discuss_reply_viewpager.getCurrentItem() != 0) {
                    discuss_reply_viewpager.setCurrentItem(0);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.discuss_back:
                finish();
                break;
        }

    }


}
