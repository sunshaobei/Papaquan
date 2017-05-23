package xm.ppq.papaquan.View.Life.invite_friend;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.invite_friend.fragment.EventRewardFragment;
import xm.ppq.papaquan.View.Life.invite_friend.fragment.MyRewardFragment;
import xm.ppq.papaquan.View.Life.invite_friend.fragment.RankingListFragment;
import xm.ppq.papaquan.life.Tool.LifeViewPager;
import xm.ppq.papaquan.life.Tool.Red_Share;

/**
 * 好友邀请
 * Created by EdgeDi on 16:40.
 */

public class Invite_FriendActivity extends BaseActivity {

    @BindView(R.id.friend_radio)
    RadioGroup friend_radio;
    @BindView(R.id.friend_left)
    RadioButton friend_left;
    @BindView(R.id.friend_center)
    RadioButton friend_center;
    @BindView(R.id.friend_right)
    RadioButton friend_right;
    @BindView(R.id.hand_1)
    ImageView hand_1;
    @BindView(R.id.hand_2)
    ImageView hand_2;
    @BindView(R.id.hand_3)
    ImageView hand_3;
    @BindView(R.id.view_pager)
    ViewPager view_pager;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.right_image)
    ImageView right_image;

    private ArrayList<Fragment> fragments;
    private HomePagerAdapter adapter;
    private Red_Share red_share;

    @Override
    protected int getLayout() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        center_result.setText("我的邀请");
        finish_result.setText("");
        finish_result.setOnClickListener(v -> finish());
        right_image.setImageResource(R.mipmap.share);
        friend_left.setChecked(true);
        if (fragments == null) {
            fragments = new ArrayList<>();
            fragments.add(new EventRewardFragment());
            fragments.add(new MyRewardFragment());
            fragments.add(new RankingListFragment());
        }
        if (adapter == null) {
            adapter = new HomePagerAdapter(getSupportFragmentManager());
            adapter.setFragments(fragments);
            view_pager.setAdapter(adapter);
        } else {
            adapter.setFragments(fragments);
            adapter.notifyDataSetChanged();
        }
        red_share = new Red_Share(this);
    }

    @Override
    protected void setListener() {
        right_image.setOnClickListener(v -> {
            if (red_share != null) {
                red_share.Show(v);
            }
        });
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < friend_radio.getChildCount(); i++) {
                    RadioButton childAt = (RadioButton) friend_radio.getChildAt(i);
                    if (position == i) {
                        childAt.setChecked(true);
                    } else {
                        childAt.setChecked(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        friend_radio.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.friend_left:
                    hand_1.setVisibility(View.VISIBLE);
                    hand_2.setVisibility(View.INVISIBLE);
                    hand_3.setVisibility(View.INVISIBLE);
                    view_pager.setCurrentItem(0);
                    break;
                case R.id.friend_center:
                    hand_1.setVisibility(View.INVISIBLE);
                    hand_2.setVisibility(View.VISIBLE);
                    hand_3.setVisibility(View.INVISIBLE);
                    view_pager.setCurrentItem(1);
                    break;
                case R.id.friend_right:
                    hand_1.setVisibility(View.INVISIBLE);
                    hand_2.setVisibility(View.INVISIBLE);
                    hand_3.setVisibility(View.VISIBLE);
                    view_pager.setCurrentItem(2);
                    break;
            }
        });
    }
}