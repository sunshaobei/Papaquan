package xm.ppq.papaquan.View.tasheet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import myview.SmoothImageView;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Presenter.he_sheet.Mutual_HeSheet;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BlurUtil;
import xm.ppq.papaquan.Tool.IMUtils;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.ImageUtil;
import xm.ppq.papaquan.Tool.MyToast;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.Indicator;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.accusation.AccusationActivity;
import xm.ppq.papaquan.View.pic.PictureActivity;
import xm.ppq.papaquan.View.tasheet.frame.DataFragment;
import xm.ppq.papaquan.View.tasheet.frame.TrendFragment;

/**
 * Created by 他人主页 on 2017/2/21.
 */

public class Ta_SheetActivity extends BaseActivity implements View.OnClickListener, Round_HeSheet {

    @BindView(R.id.sheet_background)
    LinearLayout sheet_background;
    @BindView(R.id.ta_news_viewpager)
    ViewPager ta_news_viewpager;
    @BindView(R.id.incoorbar)
    LinearLayout incoorbar;
    @BindView(R.id.outcoorbar)
    LinearLayout outcoorbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.topbar)
    TextView topbar;
    @BindView(R.id.gzTA)
    TextView gzTA;
    @BindView(R.id.toolbar1)
    Toolbar toolbar;
    @BindView(R.id.trend_back)
    ImageView back;
    @BindView(R.id.trend_more)
    ImageView more;
    @BindView(R.id.indicator)
    Indicator indicator;
    @BindView(R.id.ta_name)
    TextView ta_name;
    @BindView(R.id.he_head_icon)
    ImageView he_head_icon;
    @BindView(R.id.he_follow)
    TextView he_follow;
    @BindView(R.id.he_fons)
    TextView he_fons;
    @BindView(R.id.he_topic)
    TextView he_topic;
    @BindView(R.id.he_signature)
    TextView he_signature;
    @BindView(R.id.sex_icon)
    ImageView sex_icon;
    @BindView(R.id.ta_level)
    ImageView ta_level;
    @BindView(R.id.titlebg)
    View titlebg;
    @BindView(R.id.chat)
    TextView chat;
    @BindView(R.id.smoothview)
    SmoothImageView smoothview;
    @BindView(R.id.layout)
    CoordinatorLayout layout;

    private HomePagerAdapter adapter;
    private List<Fragment> fragments;
    public static String Uuid = "0";
    private SharedPreferencesPotting potting;
    private Mutual_HeSheet mutual_heSheet;
    private ShareDialog dialog;
    private String url;

    @Override
    protected int getLayout() {
        return R.layout.activity_sheet;
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

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(incoorbar);
        initStatusBar(outcoorbar);
        Uuid = getData("Uuid");
        url = "index/user/otherhomepage?tauid=" + Uuid + "&city=" + BaseUrl.citycode;
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_heSheet = new Mutual_HeSheet(this);
        mutual_heSheet.start(Uuid, 1);
        indicator.setCount(2);
        indicator.setOffwidth(55);
        adapter = new HomePagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        fragments.add(new TrendFragment());
        fragments.add(new DataFragment());
        adapter.setFragments(fragments);
        ta_news_viewpager.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        more.setOnClickListener(v -> {
            if (dialog != null)
                dialog.Show(v);
        });
        ta_news_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onMove(positionOffset, position);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (verticalOffset > -300) {//置顶
                topbar.setVisibility(View.GONE);
                outcoorbar.setAlpha(Math.abs(verticalOffset) / (float) 100);
                titlebg.setAlpha(Math.abs(verticalOffset) / (float) 100);
                back.setImageResource(R.drawable.black_finish);
                more.setImageResource(R.drawable.three_drop_black);
            } else {
                topbar.setVisibility(View.VISIBLE);
                outcoorbar.setAlpha(1);
                titlebg.setAlpha(1);
                back.setImageResource(R.drawable.white_finish);
                more.setImageResource(R.drawable.three_drop);
            }
        });
        chat.setOnClickListener(v -> {
            Intent chattingActivityIntent = IMUtils.getIMKit().getChattingActivityIntent("u_" + Uuid, IMUtils.APP_KEY);
            startActivity(chattingActivityIntent);
        });

        he_head_icon.setOnClickListener(v -> {
            int[] location = new int[2];
            smoothview.setVisibility(View.VISIBLE);
            he_head_icon.getLocationOnScreen(location);
            Glide.with(this).load(str).asBitmap().into(smoothview);
            smoothview.setOriginalInfo(he_head_icon.getWidth(), he_head_icon.getHeight(), location[0], location[1]);
            smoothview.transformIn();
        });
        smoothview.setOnClickListener(v -> {
            smoothview.transformOut();
            smoothview.postDelayed(() -> {
                smoothview.setVisibility(View.GONE);
            }, 300);
        });
        smoothview.setOnLongClickListener(v -> {
            Drawable drawable = he_head_icon.getDrawable();
            SnackShow("是否保存图片", ImageUtil.drawableToBitmap(drawable));
            return true;
        });
    }

    public void SnackShow(String result, Bitmap bitmap) {
        Snackbar make = null;
        if (make == null) {
            make = Snackbar.make(layout, result, Snackbar.LENGTH_SHORT);
            make.setAction("保存", v -> {
                Toast.makeText(this, ImageUtil.saveBitmap(this, bitmap), Toast.LENGTH_SHORT).show();
            });
            make.setActionTextColor(Color.parseColor("#ffffff"));
            View view = make.getView();
            view.setBackgroundColor(getResources().getColor(R.color.Red));
        } else {
            make.setText(result);
        }
        make.show();
    }

    String str;

    @OnClick({R.id.dongtai, R.id.ziliao, R.id.gzTA, R.id.trend_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dongtai:
                if (ta_news_viewpager.getCurrentItem() != 0) ta_news_viewpager.setCurrentItem(0);
                break;
            case R.id.ziliao:
                if (ta_news_viewpager.getCurrentItem() != 1) ta_news_viewpager.setCurrentItem(1);
                break;
            case R.id.gzTA:
                if (gzTA.getText().toString().equals("+关注")) {
                    mutual_heSheet.Finish_F_F(BaseUrl.DOSUB, String.valueOf(Uuid));
                } else if (gzTA.getText().toString().equals("已关注")) {
                    mutual_heSheet.Finish_F_F(BaseUrl.DELSUB, String.valueOf(Uuid));
                }
                break;
            case R.id.trend_back:
                finish();
                break;
        }
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public void setNickName(String nickName) {
        ta_name.setText(nickName);
        topbar.setText(nickName);
        this.nickname = nickName;
    }

    private String nickname;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sheet_background.setBackground((Drawable) msg.obj);
        }
    };

    @Override
    public void setHeadUrl(String headUrl) {
        if (dialog == null) {
            dialog = new ShareDialog(this, R.layout.deteil_share);
        }
        dialog.setStatus("他详", url, headUrl, nickname);
        str = headUrl;
        ImageLoading.Circular(this, headUrl, R.drawable.default_icon, he_head_icon);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = null;
                try {
                    bitmap = ImageLoading.getBitMap(Ta_SheetActivity.this, headUrl);
                    Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, false));
                    handler.obtainMessage(4001, drawable).sendToTarget();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void setT_F_F(String topic, String Follow, String Follower) {
        he_topic.setText(topic + "  动态");
        he_follow.setText(Follow + "  关注");
        he_fons.setText(Follower + "  粉丝");
    }

    @Override
    public void setSignature(String signature) {
        if (signature.equals("") || signature == null) {
            he_signature.setText("简介：这家伙很懒，什么也没留下");
        } else {
            he_signature.setText(signature);
        }
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setVip_End(long end) {

    }

    @Override
    public void setSexIcon(String sexIcon) {
        if (sexIcon.equals("2")) {
            sex_icon.setImageResource(R.drawable.woman);
        } else if (sexIcon.equals("1")) {
            sex_icon.setImageResource(R.drawable.man);
        } else {

        }
    }

    @Override
    public void IsFollow(String follow) {
        if (follow.equals("0")) {
            gzTA.setText("+关注");
        } else if (follow.equals("1")) {
            gzTA.setText("已关注");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setToast(String result) {
        MyToast.makeText(this, Toast.LENGTH_SHORT, Gravity.CENTER).setTextView(result).show();
    }

    @Override
    public void setLevel(int level) {
        ta_level.setImageResource(BaseUrl.INTEGERS[level]);
    }

    @Override
    public void setData(ArrayList<ShowNewsBigBean.Data> list, int type) {

    }

    @Override
    public void setCreateTime(String createTime) {

    }
}