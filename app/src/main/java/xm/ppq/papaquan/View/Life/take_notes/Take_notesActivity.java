package xm.ppq.papaquan.View.Life.take_notes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.take_notes.fragment.RecordFragment;

/**
 * Created by 核销记录:精选/抢购 on 2017/4/12.
 */

public class Take_notesActivity extends BaseActivity {

    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.take_notes_view)
    ViewPager take_notes_view;
    @BindView(R.id.take_1)
    TextView take_1;
    @BindView(R.id.take_2)
    TextView take_2;

    private HomePagerAdapter adapter;
    private List<Fragment> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_take_notes;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("核销记录");
        indicator.setCount(2);
        indicator.setOffwidth(-15);
        indicator.setBackcolor(Color.parseColor("#e4e4e4"));
        indicator.setBackColors(Color.parseColor("#e4e4e4"));
        indicator.setSelectColors(getResources().getColor(R.color.Red));
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        list = new ArrayList<>();
        RecordFragment recordFragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("sid", getData("sid"));
        bundle.putInt("type", 1);
        recordFragment.setArguments(bundle);
        list.add(recordFragment);
        RecordFragment recordFragment1 = new RecordFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("sid", getData("sid"));
        bundle1.putInt("type", 2);
        recordFragment1.setArguments(bundle1);
        list.add(recordFragment1);
        adapter.setFragments(list);
        take_notes_view.setAdapter(adapter);
        TextSelect(take_notes_view.getCurrentItem(), take_1, take_2);
    }

    @Override
    protected void setListener() {
        take_1.setOnClickListener(v -> take_notes_view.setCurrentItem(0));
        take_2.setOnClickListener(v -> take_notes_view.setCurrentItem(1));
        finish_result.setOnClickListener(v -> finish());
        take_notes_view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                TextSelect(position, take_1, take_2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}