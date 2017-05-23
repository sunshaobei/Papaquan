package xm.ppq.papaquan.View.trendtopicdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.DiscussShowAdapter;
import xm.ppq.papaquan.Adapter.StringGridView;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.GetComBean;
import xm.ppq.papaquan.Bean.PicBean;
import xm.ppq.papaquan.Presenter.trendtopic.Mutual_Trend;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.A_reward_Dialog;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.DownloadImageTaskUtil;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.TextView.MovementMethod;
import xm.ppq.papaquan.Tool.TextView.SpanTextView;
import xm.ppq.papaquan.Tool.Util;
import xm.ppq.papaquan.Tool.customview.CustomHeader;
import xm.ppq.papaquan.Tool.customview.CustomImageView;
import xm.ppq.papaquan.Tool.shownews.NoScrollGridView;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.accusation.AccusationActivity;
import xm.ppq.papaquan.View.pic.PictureActivity;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by 动态/话题详情 on 2017/3/2.
 */

public class TrendTopicDetailActivity extends BaseActivity implements OnClickListener, PapanewsInterface, Round_Trend, ALi_ossInterface, ShareDialog.ShareListener {

    @BindView(R.id.center_text)
    TextView center_text;
    @BindView(R.id.left_image)
    ImageView left_image;
    @BindView(R.id.right_image)
    ImageView right_image;
    @BindView(R.id.text_text)
    EditText text_text;
    @BindView(R.id.discuss_list_show)
    ListView discuss_list_show;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.love_image)
    ImageView love_image;
    @BindView(R.id.love_text)
    TextView love_text;
    @BindView(R.id.trend_love_lin)
    LinearLayout trend_love_lin;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;
    @BindView(R.id.all_trend)
    LinearLayout all_trend;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.top_delete)
    ImageView top_delete;

    private Mutual_Trend mutual_trend;
    private DiscussShowAdapter adapter;
    private ShareDialog dialog;
    private SharedPreferencesPotting potting;
    private StringGridView wadapter;
    private EmotionPopupWindow emotionPopupWindow;
    private View view;
    private CustomImageView customImageView;

    private String share_url;

    @Override
    protected int getLayout() {
        return R.layout.activity_trendtopicdetail;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(String s) {
        if (s.equals("fresh")) {
            page = 0;
            mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        trend_switch.setText("最新");
        list.clear();
        mutual_trend.start();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        share_url = "index/index/tpdetails?id=" + getData("tid") + "&city=" + BaseUrl.citycode;
        statusBar.setBackgroundColor(Color.parseColor("#e60012"));
        initStatusBar(statusBar);
        aLioss = new ALioss(this);
        aLioss.setAinterface(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        if (view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.trend_head, null);
            discuss_list_show.addHeaderView(view);
            initHeadView();
            adapter = new DiscussShowAdapter(this, list, R.layout.discuss_show_item);
            discuss_list_show.setAdapter(adapter);
        }
        mutual_trend = new Mutual_Trend(this);
        center_text.setText("动态/话题详情");
        left_image.setPadding(25, 25, 25, 25);
        right_image.setPadding(25, 25, 25, 25);
        discuss_list_show.setFocusable(false);
        emotionPopupWindow = new EmotionPopupWindow(this, this, "评论");
        left_image.setImageResource(R.drawable.white_finish);
        right_image.setImageResource(R.drawable.white_three);
    }

    private int page;
    private int type = 1;
    private ImageView trend_head;
    private TextView follow_show;
    private TextView trend_nick_name;
    private TextView trend_time;
    private SpanTextView topic_title;
    private TextView money_trend;
    private TextView trend_admire_num;
    private TextView trend_read_num;
    private TextView trend_report;
    private TextView trend_like;
    private TextView trend_like_num;
    private TextView trend_switch;
    private NoScrollGridView discuss_icon_grid;
    private LinearLayout like_num_lin;
    private ImageView trend_like_image;
    private ImageView trend_level;
    private LinearLayout advert_lin;
    private ImageView advert_image;
    private ImageView trend_start_video;
    private TextView discount_num;
    private GridView nine_grid;
    private FrameLayout frameLayout;
    private LinearLayout error;

    private void initHeadView() {
        trend_start_video = $(R.id.trend_start_video);
        trend_head = $(R.id.trend_head);
        follow_show = $(R.id.follow_show);
        trend_nick_name = $(R.id.trend_nick_name);
        trend_time = $(R.id.trend_time);
        topic_title = $(R.id.topic_title);
        money_trend = $(R.id.money_trend);
        trend_admire_num = $(R.id.trend_admire_num);
        discuss_icon_grid = $(R.id.discuss_icon_grid);
        trend_read_num = $(R.id.trend_read_num);
        trend_report = $(R.id.trend_report);
        trend_like = $(R.id.trend_like);
        trend_like_num = $(R.id.trend_like_num);
        trend_switch = $(R.id.trend_switch);
        like_num_lin = $(R.id.like_num_lin);
        trend_like_image = $(R.id.trend_like_image);
        trend_level = $(R.id.trend_level);
        advert_lin = $(R.id.advert_lin);
        advert_image = $(R.id.advert_image);
        discount_num = $(R.id.discount_num);
        nine_grid = $(R.id.gd);
        customImageView = $(R.id.custom_image);
        frameLayout = $(R.id.frameLayout1);
        error = $(R.id.error);
    }

    private SparseArray<View> sparseArray = new SparseArray<>();

    protected <T extends View> T $(int rid) {
        if (sparseArray.get(rid) == null) {
            sparseArray.append(rid, view.findViewById(rid));
            return (T) sparseArray.get(rid);
        } else {
            return (T) sparseArray.get(rid);
        }
    }

    ArrayList<String> str = new ArrayList<>();

    @Override
    protected void setListener() {
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setCustomHeaderView(new CustomHeader(this, 300));
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(this));
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setScrollBackDuration(300);
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 0;
                mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
            }
        });

        right_image.setOnClickListener(this);
        text_text.setOnClickListener(this);
        money_trend.setOnClickListener(this);
        left_image.setOnClickListener(this);
        trend_report.setOnClickListener(v -> {
            Intent intent = new Intent(this, AccusationActivity.class);
            intent.putExtra("name", mutual_trend.getNickname());
            intent.putExtra("uid", mutual_trend.getUid());
            intent.putExtra("tid", mutual_trend.getId());
            startActivity(intent);
        });
        trend_switch.setOnClickListener(this);
        trend_love_lin.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tid", mutual_trend.getId());
                jsonObject.put("uid", potting.getItemInt("uid"));
                jsonObject.put("token", potting.getItemString("token"));
                jsonObject.put("tokentype", 1);
                mutual_trend.Love(BaseUrl.LIKETOPIC, jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
        follow_show.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "1");
                jsonObject.put("uidone", getUid());
                jsonObject.put("uidtwo", mutual_trend.getUid());
                jsonObject.put("uid", getUid());
                jsonObject.put("token", getToken());
                jsonObject.put("tokentype", 1);
                if (follow_show.getText().equals("+关注")) {
                    mutual_trend.Follow_Finish(BaseUrl.DOSUB, jsonObject, "已关注");
                } else {
                    mutual_trend.Follow_Finish(BaseUrl.DELSUB, jsonObject, "+关注");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    private ALioss aLioss;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0008) {
            String path;
            if (data != null) {
                Uri uri = data.getData();
                path = Util.getPath(TrendTopicDetailActivity.this, uri);
                String s = OwnUtil.compressImage(path, path + "compress", 30);
                aLioss.init(s, ".jpg", 4);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_image:
                if (dialog != null) {
                    dialog.Show(v);
                }
                break;
            case R.id.text_text:
                emotionPopupWindow.setId(mutual_trend.getId(), 0);
                emotionPopupWindow.setView();
                emotionPopupWindow.Show(money_trend);
                break;
            case R.id.money_trend:
                if (potting.isLogin()) {
                    ToastShow("您尚未登录");
                } else {
                    Intent intent = new Intent(this, A_reward_Dialog.class);
                    intent.putExtra("url", mutual_trend.getUrl());
                    intent.putExtra("name", mutual_trend.getNickname());
                    intent.putExtra("tid", getTid());
                    startActivity(intent);
                }
                break;
            case R.id.left_image:
                finish();
                break;
            case R.id.trend_switch:
                if (trend_switch.getText().equals("最新")) {
                    trend_switch.setText("热门");
                    type = 2;
                    page = 0;
                    mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
                } else if (trend_switch.getText().equals("热门")) {
                    trend_switch.setText("最新");
                    type = 1;
                    page = 0;
                    mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
                }
                break;
        }
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        setCri();
    }

    @Override
    public void ShowShare(View view) {
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getTid() {
        return getData("tid");
    }

    @Override
    public void setAdvert(String url) {
        if (!url.equals("")) {
            ImageLoading.common(this, BaseUrl.BITMAP + url, advert_image, R.drawable.white);
            advert_lin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void setVideo(String video, String url) {
        customImageView.setVisibility(View.VISIBLE);
        trend_start_video.setVisibility(View.VISIBLE);
        trend_start_video.setOnClickListener(v -> {
            JCVideoPlayer.startFullscreen(this, JCVideoPlayerStandard.class, "http://ppqappout.oss-cn-hangzhou.aliyuncs.com/" + video, "");
        });
        new DownloadImageTaskUtil(customImageView, BaseUrl.OUTURL + url, getActivity(), 1).execute(BaseUrl.OUTURL + url);
    }

    @Override
    public void setLevel(int Level) {
        trend_level.setImageResource(BaseUrl.INTEGERS[Level]);
    }

    @Override
    public void IsShow(String uid) {
        String id = String.valueOf(getUid());
        if (id.equals(uid)) {
            follow_show.setVisibility(View.GONE);
        } else {
            follow_show.setVisibility(View.VISIBLE);
            trend_head.setOnClickListener(v -> Skip(Ta_SheetActivity.class, "Uuid", uid));
            trend_nick_name.setOnClickListener(v -> Skip(Ta_SheetActivity.class, "Uuid", uid));
        }
    }

    @Override
    public void ShowToast(String result) {
        ToastShow(result);
    }


    private ArrayList<GetComBean.Data> list = new ArrayList<>();

    @Override
    public void setList(List<GetComBean.Data> data) {
        if (page == 0) {
            list.clear();
        }
        list.addAll(data);
        if (list.size() == 0) {
            error.setVisibility(View.VISIBLE);
            discount_num.setText("评论");
        } else {
            error.setVisibility(View.GONE);
            discount_num.setText("评论(" + list.size() + ")");
            adapter.setTid(mutual_trend.getId());
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public void setFollow(String result) {
        follow_show.setText(result);
    }

    @Override
    public void setLove(int resource, int colors) {
        love_image.setImageResource(resource);
        love_text.setTextColor(colors);
    }


    @Override
    public void setPicList(List<String> list) {
        if (list.size() == 1) {
            str.clear();
            str.addAll(list);
            trend_start_video.setVisibility(View.GONE);
            customImageView.setVisibility(View.VISIBLE);
            new DownloadImageTaskUtil(customImageView, list.get(0) + "/dantu", getActivity()).execute(list.get(0));
            customImageView.setOnClickListener(v -> {
                Intent intent = new Intent(this, PictureActivity.class);
                int[] location = new int[2];
                customImageView.getLocationOnScreen(location);
                intent.putExtra("position", 0);
                intent.putExtra("width", customImageView.getWidth());
                intent.putExtra("height", customImageView.getHeight());
                intent.putExtra("locationX", location[0]);
                intent.putExtra("locationY", location[1]);
                intent.putExtra("list", str);
                startActivity(intent);
            });
        } else if (list.size() > 1) {
            nine_grid.setVisibility(View.VISIBLE);
            int width = frameLayout.getWidth();
            Nineadapter nineadapter = new Nineadapter(TrendTopicDetailActivity.this, list, R.layout.item_pic);
            nine_grid.setAdapter(nineadapter);
        }
    }

    class Nineadapter extends ConcreteAdapter<String> {
        List list;

        public Nineadapter(Context context, List list, int itemLayout) {
            super(context, list, itemLayout);
            this.list = list;
        }


        @Override
        protected void convert(ViewHolder viewHolder, String item, int position) {
            int width = frameLayout.getWidth();
            int i = width - 20;
            int i1 = i / 3;
            ImageView image = viewHolder.getView(R.id.imageView);
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            layoutParams.height = i1;
            layoutParams.width = i1;
            image.setLayoutParams(layoutParams);
            Glide.with(getActivity()).load(BaseUrl.BITMAP + item + "/200x200").placeholder(R.drawable.default_icon_zheng).into(image);
            image.setOnClickListener(v -> {
                Intent intent1 = new Intent(getActivity(), PictureActivity.class);
                intent1.putExtra("height", image.getHeight());
                intent1.putExtra("width", image.getWidth());
                int[] location = new int[2];
                image.getLocationOnScreen(location);
                intent1.putExtra("locationX", location[0]);
                intent1.putExtra("locationY", location[1]);
                intent1.putStringArrayListExtra("list", (ArrayList<String>) list);
                intent1.putExtra("position", position);
                startActivity(intent1);
            });
        }
    }

    private MovementMethod movementMethod;
    private String name;

    @Override
    public void setComment_Reward_Like_Read_TopicUserdata_Createtime_Content(int comment, String reward, int like, int read, String topic, String createtime, String content, String url) {
        trend_read_num.setText(read + "人阅读");
        name = topic;
        trend_nick_name.setText(topic);
        movementMethod = new MovementMethod(this);
        topic_title.setText(content);
        topic_title.setMovementMethod(movementMethod);
        trend_time.setText(DateUtil.formatData(Long.valueOf(createtime != null ? createtime + "000" : System.currentTimeMillis() + "")));
        ImageLoading.Circular(this, url, R.drawable.default_icon, trend_head);
        if (like > 0) {
            trend_like_num.setText(like + "人喜欢");
        } else {
            trend_like_image.setVisibility(View.GONE);
            like_num_lin.setVisibility(View.GONE);
            trend_like_num.setVisibility(View.GONE);
        }
    }

    @Override
    public void setTrend(String like) {
        trend_like.setText(like);
    }

    @Override
    public void setCri() {
        if (trend_switch.getText().equals("最新")) {
            type = 1;
            page = 0;
            mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
        } else if (trend_switch.getText().equals("热门")) {
            type = 2;
            page = 0;
            mutual_trend.Criticism_num(type, mutual_trend.getId(), page);
        }
    }

    @Override
    public void setRewareList(List<String> list) {
        if (wadapter == null) {
            wadapter = new StringGridView(list, this);
            wadapter.setI(2);
            discuss_icon_grid.setAdapter(wadapter);
        } else {
            wadapter.setGridList(list);
            wadapter.notifyDataSetChanged();
        }
        xRefreshView.stopLoadMore();
        xRefreshView.stopRefresh();
    }

    @Override
    public void setReawrenum(int num) {
        if (num > 0) {
            trend_admire_num.setText(num + "人赞赏");
        } else {
            trend_admire_num.setVisibility(View.GONE);
        }
    }

    private int com_id;

    @Override
    public void setShare(String result, String head, int uid) {
        com_id = uid;
        if (dialog == null) {
            dialog = new ShareDialog(this, R.layout.deteil_share);
            dialog.setShareListener(this);
        }
        dialog.setUid(uid);
        dialog.setStatus("动详", share_url, head, result);
    }

    @Override
    public void setError() {
        all_trend.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
        linear.setVisibility(View.GONE);
        top_delete.setVisibility(View.VISIBLE);
    }

    @Override
    public void upImageSuccess(String url) {
        EventBus.getDefault().post(new PicBean(url));
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
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void setVideoPic(String url) {

    }

    @Override
    public void share(View v, String type) {
        if (type.equals("删除")) {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "tid", "token", "tokentype")
                    .put_value(getUid(), getTid(), getToken(), 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.DELECTTOPIC, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean != null) {
                        if (baseBean.getCode().equals("0")) {
                            finish();
                        }
                        ToastShow(baseBean.getInfo());
                    }
                }
            });
        } else if (type.equals("举报")) {
            Intent intent = new Intent(getActivity(), AccusationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("uid", com_id);
            intent.putExtra("tid", getTid());
            startActivity(intent);
        }
    }
}