package xm.ppq.papaquan.View.Life.upsellerimage;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import xm.ppq.papaquan.Adapter.UpSellerTagAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.TypeThreeBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;

public class UpSellerImageActivity extends BaseActivity implements ALi_ossInterface {

    @BindView(R.id.bar)
    LinearLayout statusbar;
    @BindView(R.id.news_title)
    TextView news_title;
    @BindView(R.id.news_content)
    TextView news_content;
    @BindView(R.id.select_photo)
    FrameLayout select_photo;
    @BindView(R.id.on_flow_view)
    TagFlowLayout on_flow_view;
    @BindView(R.id.lin_photo)
    LinearLayout lin_photo;
    @BindView(R.id.photo_view)
    PhotoView photo_view;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.progress_lin)
    LinearLayout progress_lin;

    private ArrayList<String> one;
    private ArrayList<String> three;

    private UpSellerTagAdapter adapter;
    private int max;
    private ALioss aLioss;
    private int type;

    @Override
    protected int getLayout() {
        return R.layout.activity_up_seller_image;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusbar);
        photo = new ArrayList<>();
        aLioss = new ALioss(this);
        urls = new ArrayList<>();
        aLioss.setAinterface(this);
        switch (getData("type")) {
            case "经营许可证":
                max = 1;
                type = 2;
                news_title.setVisibility(View.GONE);
                news_content.setText("中国工商局规定，不同的经营品类，商品需要不同的许可证，如餐饮点需要卫生许可证，烟酒需要烟酒销售许可证等等");
                three = getIntent().getStringArrayListExtra("three");
                if (three != null) {
                    photo.clear();
                    for (int i = 0; i < three.size(); i++) {
                        photo.add(BaseUrl.BITMAP + three.get(i));
                    }
                    setFlowAdapter(photo);
                }
                break;
            case "商户":
                max = 3;
                type = 0;
                news_title.setVisibility(View.VISIBLE);
                news_content.setText("第一张图尽量选择门头照片，图片不能带水印，不得上传装修效果图。建议尺寸640x640px");
                one = getIntent().getStringArrayListExtra("one");
                if (one != null) {
                    photo.clear();
                    for (int i = 0; i < one.size(); i++) {
                        photo.add(BaseUrl.BITMAP + one.get(i));
                    }
                    setFlowAdapter(photo);
                }
                break;
        }
    }

    private ArrayList<String> photo;
    private ArrayList<String> select_photo_list;

    @Override
    protected void setListener() {
        select_photo.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectPictureActivity.class);
            intent.putExtra("intent_max_num", max);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("allSelectedPicture", photo);
            intent.putExtras(bundle);
            startActivityForResult(intent, IntentCode.RequestCode.TOPIC);
        });
        on_flow_view.setOnTagClickListener((view, position, parent) -> {
            lin_photo.setVisibility(View.VISIBLE);
            photo_view.setImageURI(Uri.parse(photo.get(position)));
            return false;
        });
        lin_photo.setOnClickListener(v -> lin_photo.setVisibility(View.GONE));
    }

    @Override
    public void onBackPressed() {
        if (progress_lin.getVisibility() == View.VISIBLE) {
            progress_lin.setVisibility(View.GONE);
            aLioss.Cancel();
            ToastShow("上传已取消");
            return;
        }
        if (lin_photo.getVisibility() == View.VISIBLE) {
            lin_photo.setVisibility(View.GONE);
            return;
        }
        if (photo.size() > 0) {
            snackbar = Snackbar.make(coordinator, "您已编辑内容，是否退出!", Snackbar.LENGTH_SHORT).setAction("退出", v -> finish());
            View view = snackbar.getView();
            view.setBackgroundColor(Color.parseColor("#E60012"));
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#ffffff"));
            ((TextView) view.findViewById(R.id.snackbar_action)).setTextColor(Color.parseColor("#ffffff"));
            snackbar.show();
            return;
        }
        super.onBackPressed();
    }

    private Snackbar snackbar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TOPIC) {
            if (data != null) {
                select_photo_list = data.getStringArrayListExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
                for (String s : select_photo_list) {
                    photo.add(s);
                }
                if (photo != null) {
                    add();
                    setFlowAdapter(photo);
                }
            }
        }
    }

    private void add() {
        if (photo.size() == max) select_photo.setVisibility(View.GONE);
        else select_photo.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.confirm_upload)
    public void UpLoad() {
        urls.clear();
        progress_lin.setVisibility(View.VISIBLE);
        for (int i = 0; i < photo.size(); i++) {
            aLioss.init(photo.get(i), ".jpg", 1);
        }
    }

    protected void setFlowAdapter(ArrayList<String> list) {
        if (adapter == null) {
            adapter = new UpSellerTagAdapter<String>(list, this, R.layout.item_dynamic_girdview) {
                @Override
                protected void Initialize(ViewHolder viewHolder, String item, int position) {
                    ImageLoading.common(getContext(), item, viewHolder.getView(R.id.item_dynamic_image), R.drawable.default_icon_zheng);
                    viewHolder.setOnClickListener(v -> {
                        photo.remove(position);
                        notifyDataChanged();
                        add();
                    }, R.id.item_dynagrid_delete);
                }
            };
            on_flow_view.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataChanged();
        }
    }

    /***********************
     * onClick
     *********************/
    public void finish(View v) {
        finish();
    }

    private ArrayList<String> urls;

    @Override
    public void upImageSuccess(String url) {
        urls.add(url);
        if (urls.size() == photo.size()) {
            runOnUiThread(() -> {
                progress_lin.setVisibility(View.GONE);
                ToastShow("上传完成");
                TypeThreeBean bean = new TypeThreeBean();
                bean.setList(urls);
                bean.setType(type);
                EventBus.getDefault().post(bean);
                finish();
            });
        }
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
