package xm.ppq.papaquan.View.pic;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lib_sunshaobei2017.app.SunActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.SmoothImageView;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageUtil;
import xm.ppq.papaquan.Tool.PicViewpager;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/3/31.
 */

public class PictureActivity extends SunActivity implements KeepPicTure {

    @BindView(R.id.viewpager)
    PicViewpager pic_viewpager;
    @BindView(R.id.pic_num)
    TextView pic_num;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    @BindView(R.id.status_bar)
    LinearLayout bar;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.smoothview)
    SmoothImageView imageView;
    @BindView(R.id.layout)
    CoordinatorLayout layout;

    private Bundle bundle;

    int mPosition;
    int mLocationX;
    int mLocationY;
    int mWidth;
    int mHeight;
    private ArrayList<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ButterKnife.bind(this);
        initStatusBar(bar);
        overridePendingTransition(0, 0);
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 0);
        list = intent.getStringArrayListExtra("list");
        mLocationX = intent.getIntExtra("locationX", 0);
        mLocationY = intent.getIntExtra("locationY", 0);
        mWidth = intent.getIntExtra("width", 0);
        mHeight = intent.getIntExtra("height", 0);
        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
        imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String s = BaseUrl.BITMAP + list.get(mPosition);
        Glide.with(this).load(s + "/dantu").asBitmap().into(imageView);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(bar, "alpha", 0, 1);
        alpha.setDuration(300);
        alpha.start();
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        alpha1.setDuration(300);
        alpha1.start();
        setListener();
        pic_viewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                pic_viewpager.setVisibility(View.VISIBLE);
                initData();
            }
        }, 500);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        if (s.equals("quit")) {
            quit();
        }
        if (s.equals("hide")) {
            pic_viewpager.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }
    }


    protected void initData() {
        if (list != null) {
            pic_num.setText((mPosition + 1) + "/" + list.size());
            pic_viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    PicFragment picFragment = new PicFragment();
                    bundle = new Bundle();
                    bundle.putString("url", BaseUrl.BITMAP + list.get(position));
                    picFragment.setArguments(bundle);
                    return picFragment;
                }

                @Override
                public int getCount() {
                    return list.size();
                }
            });
            vpposition = mPosition;
            pic_viewpager.setCurrentItem(mPosition);
        }
    }

    int vpposition;

    protected void setListener() {
        pic_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                vpposition = position;
                if (list != null) {
                    pic_num.setText((position + 1) + "/" + list.size());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    int x;
    int y;


    private void quit() {

        SmoothImageView smoothImageView = new SmoothImageView(this);

        switch (mPosition) {
            case 0:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - mPosition);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY + mHeight + 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 3);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 6);
                }
                break;
            case 1:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - mPosition);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY + mHeight + 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 4);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 7);
                }

                break;
            case 2:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - mPosition);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY + mHeight + 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 5);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 8);
                }
                break;
            case 3:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 0);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 3);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10);
                    x = mLocationX + (mWidth + 10) * (vpposition - 6);
                }
                break;
            case 4:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 1);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 4);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10);
                    x = mLocationX + (mWidth + 10) * (vpposition - 7);
                }

                break;
            case 5:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 2);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 5);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY + (mHeight + 10);
                    x = mLocationX + (mWidth + 10) * (vpposition - 8);
                }
                break;
            case 6:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 0);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 3);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 6);
                }
                break;
            case 7:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 1);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 4);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 7);
                }
                break;
            case 8:
                if (vpposition == 1 || vpposition == 2 || vpposition == 0) {
                    y = mLocationY - (mHeight + 10) * 2;
                    x = mLocationX + (mWidth + 10) * (vpposition - 2);
                }
                if (vpposition >= 3 && vpposition <= 5) {
                    y = mLocationY - mHeight - 10;
                    x = mLocationX + (mWidth + 10) * (vpposition - 5);
                }
                if (vpposition >= 6 && vpposition <= 8) {
                    y = mLocationY;
                    x = mLocationX + (mWidth + 10) * (vpposition - 8);
                }
                break;
        }
        smoothImageView.setOriginalInfo(mWidth, mHeight, x, y);
        smoothImageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        smoothImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(this).load(BaseUrl.BITMAP + list.get(vpposition) + "/dantu").asBitmap().into(smoothImageView);
        pic_viewpager.setVisibility(View.GONE);
        fragment.addView(smoothImageView);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(bar, "alpha", 1, 0);
        alpha.setDuration(300);
        alpha.start();
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        alpha1.setDuration(300);
        alpha1.start();
        smoothImageView.transformOut();
        smoothImageView.postDelayed(() -> smoothImageView.setVisibility(View.GONE), 300);
        smoothImageView.postDelayed(() -> finish(), 500);
    }

    boolean exiting = false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(!exiting)
            {
                exiting = true;
                quit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
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
}