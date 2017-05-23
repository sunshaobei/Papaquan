package xm.ppq.papaquan.View.topic_deail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.topic_detail.Mutual_TopicDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BlurUtil;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.topic_deail.topic.NewsFragMent;
import xm.ppq.papaquan.View.topic_deail.topic.TopicFragMent;

/**
 * Created by 话题详情 on 2017/2/23.
 */
public class Topic_DetailActivity extends BaseActivity implements View.OnClickListener, Round_Topicdetail {

    @BindView(R.id.topic_background)
    FrameLayout topic_background;
    @BindView(R.id.news_topic)
    TextView news_topic;
    @BindView(R.id.hot_topic)
    TextView hot_topic;
    @BindView(R.id.topic_detail_cancel)
    TextView cancel;
    @BindView(R.id.topic_detail_share)
    ImageView share;
    @BindView(R.id.topic_viewpager)
    ViewPager topic_viewpager;
    @BindView(R.id.topic_title_background)
    ImageView topic_title_background;
    @BindView(R.id.topic_title)
    TextView topic_title;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.topic_name)
    TextView topic_name;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.topic_appbar)
    AppBarLayout topic_appbar;
    @BindView(R.id.toolbar1)
    Toolbar toolbar1;
    @BindView(R.id.top_topic_title)
    TextView top_topic_title;
    @BindView(R.id.titlebg)
    View titlebg;

    private ShareDialog dialog;
    private String Hotid;
    private HomePagerAdapter homePagerAdapter;
    private List<Fragment> fragments;
    private Mutual_TopicDetail mutual_topicDetail;
    private SharedPreferencesPotting potting;
    private String hottitle;

    @Override
    protected int getLayout() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        fragments = new ArrayList<>();
        fragments.add(new NewsFragMent());
        fragments.add(new TopicFragMent());
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        homePagerAdapter.setFragments(fragments);
        topic_viewpager.setAdapter(homePagerAdapter);
        Hotid = getData("hotid");
        url = "index/index/topic?city=" + getCitycode() + "&id=" + Hotid;
        mutual_topicDetail = new Mutual_TopicDetail(this);
        mutual_topicDetail.start();
        initState();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    private void initState() {
        cancel.setPadding(20, 20 + getStatusBarHeight(), 20, 20);
        share.setPadding(20, 20 + getStatusBarHeight(), 20, 20);
    }

    @Override
    protected void setListener() {
        topic_appbar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset < -100) {
                top_topic_title.setText(hottitle);
                topic_title.setVisibility(View.GONE);
                detail.setVisibility(View.GONE);
                topic_name.setVisibility(View.GONE);
                imageView2.setVisibility(View.GONE);
                titlebg.setAlpha(1);
            } else {
                titlebg.setAlpha(Math.abs((float) verticalOffset / 100));
                top_topic_title.setText("");
                topic_title.setVisibility(View.VISIBLE);
                detail.setVisibility(View.VISIBLE);
                topic_name.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
            }

        });
        news_topic.setOnClickListener(this);
        hot_topic.setOnClickListener(this);
        cancel.setOnClickListener(this);
        share.setOnClickListener(this);
        topic_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    news_topic.setTextColor(getResources().getColor(R.color.topic_red));
                    hot_topic.setTextColor(getResources().getColor(R.color.topic_false));
                } else if (position == 1) {
                    news_topic.setTextColor(getResources().getColor(R.color.topic_false));
                    hot_topic.setTextColor(getResources().getColor(R.color.topic_red));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_topic:
                if (topic_viewpager.getCurrentItem() != 0) {
                    topic_viewpager.setCurrentItem(0);
                }
                break;
            case R.id.hot_topic:
                if (topic_viewpager.getCurrentItem() != 1) {
                    topic_viewpager.setCurrentItem(1);
                }
                break;
            case R.id.topic_detail_cancel:
                finish();
                break;
            case R.id.topic_detail_share:
                if (dialog != null) {
                    dialog.Show(news_topic);
                }
                break;

        }
    }

    @Override
    public String getHotid() {
        return Hotid;
    }

    @Override
    public String getPager() {
        return "0";
    }

    @Override
    public String getCitycode() {
        return potting.getItemString("citycode");
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public void setHotTitle(String Hottitle) {
        hottitle = Hottitle;
        topic_title.setText(Hottitle);
        top_topic_title.setText(Hottitle);
    }

    @Override
    public void setHot_Num(String hot_num) {
        detail.setText(hot_num);
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setIcon(String icon) {
        ImageLoading.common(this, icon, imageView2, R.drawable.topic_icon);
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void setBackground(String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = null;
                try {
                    if (!url.equals(BaseUrl.BITMAP)) {
                        bitmap = ImageLoading.getBitMap(Topic_DetailActivity.this, url);
                        Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, false));
                        runOnUiThread(() -> topic_title_background.setBackground(drawable));
                    } else {
                        Drawable drawable = getResources().getDrawable(R.drawable.topic_item_2);
                        runOnUiThread(() -> topic_title_background.setBackground(drawable));
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void setList(ArrayList<ShowNewsBigBean.Data> datas) {

    }

    private String url;

    @Override
    public void setShare(String title, String url) {
        if (dialog == null) {
            dialog = new ShareDialog(this, R.layout.deteil_share);
        }
        dialog.setStatus("话详", this.url, url, title + "-" + BaseUrl.publicname);
    }
}