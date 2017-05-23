package xm.ppq.papaquan.View.Life.verification;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.take_notes.Take_AgioActivity;
import xm.ppq.papaquan.View.Life.take_notes.Take_notesActivity;
import xm.ppq.papaquan.View.Life.verification.fragment.VerAgioFragment;
import xm.ppq.papaquan.View.Life.verification.fragment.VerQJFragment;
import xm.ppq.papaquan.qr_code.Qr_CodeActivity;

/**
 * Created by 核销验证 on 2017/4/12.
 */

public class VerificationActivity extends BaseActivity implements ObtainInfo {

    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.verification_view)
    ViewPager verification_view;
    @BindView(R.id.code_1)
    TextView code_1;
    @BindView(R.id.code_2)
    TextView code_2;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.right_title)
    TextView right_title;

    private HomePagerAdapter adapter;
    private List<Fragment> list;
    private String sid;
    private VerAgioFragment verAgioFragment;
    private VerQJFragment verQJFragment;

    @Override
    protected int getLayout() {
        return R.layout.activity_verification;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("核销验证");
        right_title.setText("核销记录");
        sid = getData("sid");
        right_title.setTextColor(getResources().getColor(R.color.white));
        indicator.setCount(2);
        indicator.setOffwidth(-15);
        indicator.setBackcolor(Color.parseColor("#e4e4e4"));
        indicator.setBackColors(Color.parseColor("#e4e4e4"));
        indicator.setSelectColors(getResources().getColor(R.color.Red));
        list = new ArrayList<>();
        verAgioFragment = new VerAgioFragment();
        verQJFragment = new VerQJFragment();
        list.add(verAgioFragment);
        list.add(verQJFragment);
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(list);
        verification_view.setAdapter(adapter);
        TextSelect(verification_view.getCurrentItem(), code_1, code_2);
    }

    @Override
    protected void setListener() {
        right_title.setOnClickListener(v -> {
            if (verification_view.getCurrentItem() == 0) {
                Skip(Take_AgioActivity.class, "sid", sid);
            } else {
                Skip(Take_notesActivity.class, "sid", sid);
            }
        });
        finish_result.setOnClickListener(v -> finish());
        code_1.setOnClickListener(v -> verification_view.setCurrentItem(0));
        code_2.setOnClickListener(v -> verification_view.setCurrentItem(1));
        verification_view.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
                TextSelect(position, code_1, code_2);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x89) {
            if (resultCode == Qr_CodeActivity.QR_CODE) {
                String code = data.getStringExtra("code");
                if (code != null) {
                    verAgioFragment.setCode(code);
                    verQJFragment.setCode(code);
                } else {
                    Toast.makeText(this, "解析出错", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}