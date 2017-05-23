package xm.ppq.papaquan.View.Life.upbusinesslicence;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myview.SmoothImageView;
import xm.ppq.papaquan.Bean.TypeThreeBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.Image;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;

import static xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity.INTENT_MAX_NUM;

public class UpBusinessLicenceActivity extends BaseActivity implements ALi_ossInterface {

    @BindView(R.id.image)
    SmoothImageView image;
    @BindView(R.id.deleteimage)
    ImageView deleteimage;
    @BindView(R.id.bar)
    LinearLayout statusbar;
    @BindView(R.id.activity_up_business_licence)
    FrameLayout frameLayout;

    private ArrayList<String> piclist = new ArrayList<>();
    private SmoothImageView imageView;
    private ALioss aLioss;
    private ArrayList<String> two;

    @Override
    protected int getLayout() {
        return R.layout.activity_up_business_licence;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusbar);
        aLioss = new ALioss(this);
        aLioss.setAinterface(this);
        two = getIntent().getStringArrayListExtra("two");
        if (two != null) {
            image.setVisibility(View.VISIBLE);
            deleteimage.setVisibility(View.VISIBLE);
            ImageLoading.common(this, BaseUrl.BITMAP + two.get(0), image, R.mipmap.food);
            piclist.clear();
            piclist.add(BaseUrl.BITMAP + two.get(0));
        }
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TOSELECTPIC && resultCode == RESULT_OK) {
            deleteimage.setVisibility(View.VISIBLE);
            image.setVisibility(View.VISIBLE);
            piclist.clear();
            piclist = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            Picasso.with(this).load("file://" + piclist.get(0)).placeholder(new ColorDrawable(Color.parseColor("#f8f8f8"))).into(image);
        }
    }

    /**********************
     * onClick
     **********************/
    public void addpic(View v) {
        Intent intent = new Intent(this, SelectPictureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("allSelectedPicture", piclist);
        intent.putExtras(bundle);
        intent.putExtra(INTENT_MAX_NUM, 1);
        startActivityForResult(intent, IntentCode.RequestCode.TOSELECTPIC);
    }

    public void deleteimage(View v) {
        piclist.clear();
        image.setVisibility(View.GONE);
        deleteimage.setVisibility(View.GONE);
    }

    public void up(View view) {
        if (piclist != null) {
            if (piclist.size() > 0) {
                if (piclist.get(0).startsWith(BaseUrl.BITMAP)) {
                    String url = piclist.get(0).substring(43, piclist.get(0).length());
                    ArrayList<String> list = new ArrayList<>();
                    list.add(piclist.get(0));
                    TypeThreeBean bean = new TypeThreeBean();
                    bean.setList(list);
                    bean.setType(1);
                    EventBus.getDefault().post(bean);
                    finish();
                } else {
                    aLioss.init(piclist.get(0), ".jpg", 1);
                }
            } else {
                ToastShow("您尚未选择图片");
            }
        } else {
            ToastShow("您尚未选择图片");
        }
    }

    public void showimage(View v) {
        int[] location = new int[2];
        image.getLocationOnScreen(location);
        overridePendingTransition(0, 0);
        if (imageView == null) {
            imageView = new SmoothImageView(this);
            imageView.setOriginalInfo(image.getWidth(), image.getHeight(), location[0], location[1] + statusbar.getHeight());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            frameLayout.addView(imageView);
        }
        imageView.setVisibility(View.VISIBLE);
        imageView.transformIn();
        imageView.postDelayed(() -> isshow = true, 300);
        Picasso.with(this).load("file://" + piclist.get(0)).into(imageView);
        imageView.setOnClickListener(v1 -> {
            if (isshow) {
                isshow = false;
                imageView.transformOut();
                imageView.postDelayed(() -> imageView.setVisibility(View.GONE), 300);
            }
        });

    }

    private boolean isshow = false;

    public void finish(View v) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isshow) {
                imageView.transformOut();
                imageView.postDelayed(() -> imageView.setVisibility(View.GONE), 300);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void upImageSuccess(String url) {
        runOnUiThread(() -> {
            ArrayList<String> list = new ArrayList<>();
            list.add(url);
            TypeThreeBean bean = new TypeThreeBean();
            bean.setList(list);
            bean.setType(1);
            EventBus.getDefault().post(bean);
            finish();
        });
    }

    @Override
    public void upImageError(String s) {

    }

    @Override
    public void upVideoSuccess(String s) {

    }

    @Override
    public void upProgress(int progress) {

    }

    @Override
    public void setVideoPic(String url) {

    }
}
