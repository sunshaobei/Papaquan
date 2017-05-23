package xm.ppq.papaquan.View.published_dynamic;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_sunshaobei2017.utils.FileSizeUtil;
import com.example.zhaoshuang.weixinrecordeddemo.TakeVideoActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.DynamciGridAdapter;
import xm.ppq.papaquan.Bean.PapaTopicBean;
import xm.ppq.papaquan.Bean.TopicBean;
import xm.ppq.papaquan.Presenter.dynamic.Mutual_Dynamic;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.ChooseVideoDialog;
import xm.ppq.papaquan.Tool.DynamicDialog;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.MyToast;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.REEditText.REditText;
import xm.ppq.papaquan.Tool.REEditText.RObject;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.RefreshView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.Tool.typewriting.EmotionGridView;
import xm.ppq.papaquan.Tool.typewriting.LookPagerAdapter;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;
import xm.ppq.papaquan.View.seletop.SelectTopicActivity;

import static xm.ppq.papaquan.Tool.OwnUtil.getPathByUri4kitkat;
import static xm.ppq.papaquan.Tool.OwnUtil.getVideoThumbnail;

public class Dynamic extends BaseActivity implements Round_Dynamic, ALi_ossInterface {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.keyliner2)
    LinearLayout keyliner2;
    @BindView(R.id.keyboadlayout)
    LinearLayout keyboadLayout;
    @BindView(R.id.expression)
    ImageView imageExpression;
    @BindView(R.id.aitesomeone)
    ImageView imageAtsomeoone;
    @BindView(R.id.sm_edit)
    REditText edit_dynamic;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.sm_ll_addpic)
    LinearLayout addpic;
    @BindView(R.id.sm_ll_addvideo)
    LinearLayout addvideo;
    @BindView(R.id.hideflowlayout)
    TextView hideflowlayout;
    @BindView(R.id.dynamic_gridview)
    GridView gridview;
    @BindView(R.id.chooselayout)
    RelativeLayout chooselayout;
    @BindView(R.id.showvideo)
    RelativeLayout showvideoicon;
    @BindView(R.id.deletevideo)
    ImageView deletevideo;
    @BindView(R.id.showvideoimage)
    ImageView showvideoimage;
    @BindView(R.id.hidewindow)
    View hidewindow;
    @BindView(R.id.refreshview)
    RefreshView refreshView;
    @BindView(R.id.status_bar)
    LinearLayout status_bar;
    @BindView(R.id.upprogress)
    TextView upprogress;
    @BindView(R.id.playvideo)
    ImageView playvide;
    @BindView(R.id.sm_hyp)
    TextView sm_hyp;

    private String hottitle;
    private int keyboardHeight;
    private LookPagerAdapter pageradapter;
    private EmotionGridView emotionGridView;
    private TypewritingUtil typewritingUtil;
    private int height;
    private boolean isshowEmotion = false;
    private String realPathFromURI = "";
    private Uri uri;
    private Mutual_Dynamic mutual_dynamic;
    private SharedPreferencesPotting potting;
    private ALioss aLioss;
    private DynamciGridAdapter gridAdapter;
    private List<String> UpImageCount = new ArrayList<>();
    //存放所有选择的照片
    private ArrayList<String> allSelectedPicture = new ArrayList<>();
    //存放从选择界面选择的照片
    private ArrayList<String> selectedPicture = new ArrayList<>();
    private String videoPath = "";
    private static final int SENDVIDEO = 3;
    private static final int SENDPIC = 1;
    private ChooseVideoDialog chooseVideoDialog;
    private String videoPicPath;
    private DynamicDialog dynamicDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        dynamicDialog = new DynamicDialog(this, R.style.dialog);
        dynamicDialog.setActivity(this);
        hottitle = getData("hottitle");
        if (hottitle != null) {
            initEdittext(hottitle, 1);
        }
        initStatusBar(status_bar);
        text();
        initPicLog();
        mutual_dynamic = new Mutual_Dynamic(this);
        gridAdapter = new DynamciGridAdapter(allSelectedPicture, this, gridview, chooselayout, tv_send);
        gridview.setAdapter(gridAdapter);
        aLioss = new ALioss(this);
        aLioss.setAinterface(this);
        chooseVideoDialog = new ChooseVideoDialog();
        chooseVideoDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
        initdialogListener();
    }

    private void initdialogListener() {
        chooseVideoDialog.setOnChooseLocalListener(() -> {
            Intent intenttovideo = new Intent();
            intenttovideo.setType("video/*");
            intenttovideo.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intenttovideo, IntentCode.RequestCode.TOVIDEO);
        });
        chooseVideoDialog.setOnChooseTakeVideoListener(() -> {
            Intent intentvid = new Intent(Dynamic.this, TakeVideoActivity.class);
            startActivityForResult(intentvid, IntentCode.RequestCode.TOVIDEO);
        });
        edit_dynamic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    tv_send.setTextColor(Color.parseColor("#ffffff"));
                    tv_send.setBackground(new ColorDrawable(Color.parseColor("#e60012")));
                    mFlowLayout.setVisibility(View.VISIBLE);
                } else {
                    tv_send.setTextColor(Color.parseColor("#e60012"));
                    tv_send.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    //表情
    private void initPicLog() {
        height = getWindowManager().getDefaultDisplay().getHeight();
        pageradapter = new LookPagerAdapter(this);
        emotionGridView = new EmotionGridView(this, edit_dynamic, height, pageradapter, viewpager);
        emotionGridView.a1(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 5));
    }


    ArrayList<String> aitelist = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0x52 && resultCode == RESULT_OK) {
            TopicBean.Data data1 = (TopicBean.Data) data.getSerializableExtra("id");
            initEdittext(data1.nickname, 0);
            uids.add(data1);
            aitelist.add(data1.uid);
        }
        switch (requestCode) {
            case IntentCode.RequestCode.TOPIC:
                if (resultCode == RESULT_OK) {
                    selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
                    if (selectedPicture.size() != 0) {
                        gridview.setVisibility(View.VISIBLE);
                        chooselayout.setVisibility(View.GONE);
                        for (String str : selectedPicture) {
                            if (!allSelectedPicture.contains(str)) {
                                allSelectedPicture.add(str);
                            }
                        }
                        gridAdapter.notifyDataSetChanged();
                    }
                    if (allSelectedPicture.size() != 0) {
                        tv_send.setTextColor(Color.parseColor("#e60012"));
                        tv_send.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                    } else {
                        tv_send.setTextColor(Color.parseColor("#ffffff"));
                        tv_send.setBackground(new ColorDrawable(Color.parseColor("#e60012")));
                    }
                }
                break;
            case IntentCode.RequestCode.TOVIDEO:
                if (resultCode != 0) {
                    if (resultCode == IntentCode.ResultCode.BACKTODYNAMIC) {
                        //视频路径
                        realPathFromURI = data.getStringExtra("videoPath");
                        //图片路径

//                        videoPicPath = data.getStringExtra("framePicPath");
//                        //录制时间
//                        String videoDuration = data.getStringExtra("videoDuration");
//                        //录制大小
//                        String videoSize = data.getStringExtra("videoSize");
                        chooselayout.setVisibility(View.GONE);
                        showvideoicon.setVisibility(View.VISIBLE);
                        //TODO 根据视频路径获取第一帧
                        Bitmap bitmap = getVideoThumbnail(realPathFromURI);
                        showvideoimage.setImageBitmap(bitmap);
                        showvideoicon.setOnClickListener(v -> {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.fromFile(new File(realPathFromURI)), "video/mp4");
                            startActivity(intent);
                        });
                    } else {
                        uri = data.getData();
                        if (uri != null) {

                            realPathFromURI = getPathByUri4kitkat(this, uri);
                            double fileOrFilesSize = FileSizeUtil.getFileOrFilesSize(realPathFromURI, FileSizeUtil.SIZETYPE_MB);
                            if (fileOrFilesSize > 5) {
                                ToastShow("请选择小于5M的视频");
                            } else {
                                Bitmap bitmap = getVideoThumbnail(realPathFromURI);
                                //解析封面图
//                            videoPicPath = OwnUtil.saveBitmapFile(bitmap);
                                chooselayout.setVisibility(View.GONE);
                                showvideoicon.setVisibility(View.VISIBLE);
                                showvideoimage.setImageBitmap(bitmap);
                                showvideoicon.setOnClickListener(v -> {
                                    Intent intent1 = new Intent(Intent.ACTION_VIEW);
                                    intent1.setDataAndType(uri, "video/mp4");
                                    startActivity(intent1);
                                });
                            }
                        }
                    }
                    //删除视频
                    deletevideo.setOnClickListener(v -> {
                        showvideoicon.setVisibility(View.GONE);
                        chooselayout.setVisibility(View.VISIBLE);
                        realPathFromURI = "";
                        tv_send.setTextColor(Color.parseColor("#e60012"));
                        tv_send.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                    });
                    if (realPathFromURI.isEmpty()) {
                        tv_send.setTextColor(Color.parseColor("#ffffff"));
                        tv_send.setBackground(new ColorDrawable(Color.parseColor("#e60012")));
                    } else {
                        tv_send.setTextColor(Color.parseColor("#e60012"));
                        tv_send.setBackground(new ColorDrawable(Color.parseColor("#ffffff")));
                    }

                }
                break;
        }
    }

    private ArrayList<TopicBean.Data> uids = new ArrayList<>();

    //不同颜色的字体
    private void initEdittext(String s, int type) {
        RObject rObject = new RObject();
        switch (type) {
            case 0:
                rObject.setObjectRule("@");
                break;
            case 1:
                s = s.replaceAll("#", "");
                rObject.setObjectRule("#");
                break;
        }
        rObject.setObjectText(s);
        edit_dynamic.setObject(rObject);
    }

    @Override
    protected void setListener() {
        sm_hyp.setOnClickListener(v -> {
            i++;
            start();
        });
    }

    private List<PapaTopicBean.DataBean> dataBeen = new ArrayList<>();
    private int i = 0;

    private void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("page", i);
            jsonObject.put("citycode", potting.getItemString("citycode"));
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.HOTSLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeen.size() > 0) {
                        mFlowLayout.setAdapter(new TagAdapter<PapaTopicBean.DataBean>(dataBeen) {
                            @Override
                            public View getView(FlowLayout parent, int position, PapaTopicBean.DataBean s) {
                                TextView tv = (TextView) getLayoutInflater().inflate(R.layout.tv_flowlayout,
                                        mFlowLayout, false);
                                tv.setText(s.getTitle());
                                tv.setOnClickListener(v -> {
                                    initEdittext(s.getTitle(), 1);
                                    hottitle = s.getTitle();
                                    mFlowLayout.setVisibility(View.GONE);
                                });
                                return tv;
                            }
                        });
                    } else {
                        i = 0;
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            PapaTopicBean bean = (PapaTopicBean) JsonUtil.fromJson(s, PapaTopicBean.class);
                            dataBeen.clear();
                            dataBeen.addAll(bean.getData());
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void text() {
        start();
        /*****监听软键盘弹出与否获取高度******/
        typewritingUtil = new TypewritingUtil(this);
        typewritingUtil.addOnSoftKeyBoardVisibleListener(this, (visible, windowBottom) -> {
            if (visible) {
                keyboadLayout.setVisibility(View.VISIBLE);
                if (isshowEmotion) {
                    viewpager.setVisibility(View.GONE);
                    isshowEmotion = false;
                    imageExpression.setImageResource(R.drawable.smileface);
                }
            } else {
                if (!isshowEmotion) {
                    keyboadLayout.setVisibility(View.GONE);
                } else {
                    viewpager.setVisibility(View.VISIBLE);
                }
            }
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) keyliner2.getLayoutParams();
            keyboardHeight = windowBottom - getStatusBarHeight();
            params.height = keyboardHeight;
            keyliner2.setLayoutParams(params);
        });
    }

    /**
     * 点击事件
     *
     * @param v
     */

    @OnClick({R.id.expression, R.id.aitesomeone, R.id.tv_cancel, R.id.sm_ll_addvideo, R.id.sm_ll_addpic, R.id.tv_send})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.expression:
                if (!isshowEmotion) {
                    keyboadLayout.setVisibility(View.VISIBLE);
                    isshowEmotion = true;
                    typewritingUtil.Hide(v);
                    imageExpression.setImageResource(R.drawable.keyset);
                } else {
                    viewpager.setVisibility(View.GONE);
                    typewritingUtil.ShowType(edit_dynamic);
                    isshowEmotion = false;
                    imageExpression.setImageResource(R.drawable.smileface);
                }

                break;
            case R.id.aitesomeone:
                Intent intent1 = new Intent(this, SelectTopicActivity.class);
                intent1.putStringArrayListExtra("list",aitelist);
                startActivityForResult(intent1, 0x52);
                break;
            case R.id.tv_cancel:
                //结束当前页面
                if (edit_dynamic.getText().toString().equals("") && selectedPicture.size() == 0 && realPathFromURI.equals("")) {
                    finish();
                } else {
                    dynamicDialog.show();
                }
                break;
            case R.id.tv_send:
                refreshView.setVisibility(View.VISIBLE);
                tv_send.setVisibility(View.GONE);
                if (allSelectedPicture.size() == 0 && realPathFromURI.equals("")) {
                    if (getContent() != null && !getContent().equals("")) {
                        hidewindow.setVisibility(View.VISIBLE);
                        mutual_dynamic.send();
                    } else {
                        ToastShow("内容不能为空");
                        refreshView.setVisibility(View.GONE);
                        tv_send.setVisibility(View.VISIBLE);
                    }
                } else {
                    hidewindow.setVisibility(View.VISIBLE);
                    if (allSelectedPicture.size() > 0) {
                        //单一线程
                        new Thread(() -> {
                            for (int i = 0; i < allSelectedPicture.size(); i++) {
                                String s = OwnUtil.compressImage(allSelectedPicture.get(i), allSelectedPicture.get(i) + "compress", 30);
                                aLioss.init(s, "pid" + i + ".jpg", SENDPIC);
                            }
                        }).start();
                    } else if (realPathFromURI != null) {
                        new Thread(() -> {
                            aLioss.init(realPathFromURI, ".mp4", SENDVIDEO);
                        }).start();
                    }
                }
                break;
            case R.id.sm_ll_addpic:
                Intent intent = new Intent(Dynamic.this, SelectPictureActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("allSelectedPicture", allSelectedPicture);
                intent.putExtras(bundle);
                if (allSelectedPicture.size() < 9) {
                    startActivityForResult(intent, IntentCode.RequestCode.TOPIC);
                }
                break;
            case R.id.sm_ll_addvideo:
                if (!chooseVideoDialog.isAdded()) {
                    chooseVideoDialog.show(getFragmentManager(), "choose");
                }
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!edit_dynamic.getText().toString().equals("") || selectedPicture.size() > 0 || !realPathFromURI.equals("")) {
                dynamicDialog.show();
                return true;
            }
            if (isshowEmotion) {
                viewpager.setVisibility(View.GONE);
                keyboadLayout.setVisibility(View.GONE);
                imageExpression.setImageResource(R.drawable.smileface);
                isshowEmotion = false;
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void sendSuccess() {
        refreshView.setVisibility(View.GONE);
        tv_send.setVisibility(View.VISIBLE);
        MyToast.makeText(this, Toast.LENGTH_LONG).setTextView("发布成功").show();
        setResult(IntentCode.ResultCode.DYNAMICTOHOME);
        finish();
    }

    @Override
    public void sendError(String error) {
        ToastShow(error);
        refreshView.setVisibility(View.GONE);
        tv_send.setVisibility(View.VISIBLE);
    }

    @Override
    public void upImageError(String s) {
        runOnUiThread(() -> {
            ToastShow(s);
            hidewindow.setVisibility(View.GONE);
            refreshView.setVisibility(View.GONE);
            tv_send.setVisibility(View.VISIBLE);
        });
    }

    private int sendvideoCountdown;

    @Override
    public void upVideoSuccess(String s) {
        if (s.endsWith(".jpg")) {
            sendvideoCountdown++;

        } else {
            sendvideoCountdown++;
            this.videoPath = s;
            mutual_dynamic.send();
        }
        if (sendvideoCountdown == 2)
            mutual_dynamic.send();
    }

    @Override
    public void upProgress(int progress) {
        handler.obtainMessage(progress).sendToTarget();
    }

    @Override
    public void setVideoPic(String url) {
        videoPicPath = url;
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            playvide.setVisibility(View.GONE);
            upprogress.setVisibility(View.VISIBLE);
            upprogress.setText(msg.what + "%");
        }
    };

    @Override
    public void upImageSuccess(String url) {
        UpImageCount.add(url);
        if (allSelectedPicture.size() == UpImageCount.size()) {
            mutual_dynamic.send();
        }
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getContent() {
        return edit_dynamic.getText().toString();
    }

    @Override
    public List<String> getPicture() {
        return UpImageCount;
    }

    @Override
    public String getCityCode() {
        if (potting.getItemString("citycode").equals("")) {
            return "";
        } else {
            return potting.getItemString("citycode");
        }
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }

    @Override
    public String getVideoPic() {
        return videoPicPath;
    }

}
