package xm.ppq.papaquan.View.review_details;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ReviewAdapter;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.DetailsBean;
import xm.ppq.papaquan.Bean.ReplyListBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DownloadImageTaskUtil;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CustomImageView;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;

/**
 * Created by Administrator on 2017/3/22.
 */

public class Review_detailsActivity extends BaseActivity implements PapanewsInterface {

    @BindView(R.id.rede_list)
    ListView rede_list;
    @BindView(R.id.rede_lin)
    LinearLayout rede_lin;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;

    private ReviewAdapter adapter;
    private EmotionPopupWindow emotionPopupWindow;
    private String cid;
    private SharedPreferencesPotting potting;
    private DetailsBean.Data data;
    private View view;

    @Override
    protected int getLayout() {
        return R.layout.activity_review_details;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (view == null) {
            view = LayoutInflater.from(this).inflate(R.layout.rede_head, null);
            rede_list.addHeaderView(view);
            addHead(view);
            initHead();
        }
        cid = getData("cid");
        potting = new SharedPreferencesPotting(this, "my_login");
        center_result.setText("回复");
        emotionPopupWindow = new EmotionPopupWindow(this, this, "回复评论");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cid", cid);
            jsonObject.put("uid", potting.getItemInt("uid"));
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.COMMENTDETAILS, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (data != null) {
                        if (data.isLike == 0) {
                            rede_love_image.setImageResource(R.drawable.love_false);
                        } else {
                            rede_love_image.setImageResource(R.drawable.love_true);
                        }
                        rede_love_image.setOnClickListener(v -> {
                            try {
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("uid", potting.getItemInt("uid"));
                                jsonObject1.put("token", potting.getItemString("token"));
                                jsonObject1.put("tokentype", 1);
                                jsonObject1.put("cid", data.id);
                                OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.LIKECOMMENT, jsonObject1.toString(), new Subscriber<BaseBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(BaseBean baseBean) {
                                        switch (baseBean.getCode()) {
                                            case "1":
                                                rede_love_image.setImageResource(R.drawable.love_true);
                                                data.likes++;
                                                rede_love_num.setText(data.likes + "");
                                                break;
                                        }
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        });
                        level_review.setImageResource(BaseUrl.INTEGERS[data.level]);
                        emotionPopupWindow.setCid(data.id, data.com_uid, data.tid);
                        center_result.setText(data.reply_num + "条回复");
                        ImageLoading.Circular(Review_detailsActivity.this, data.headurl, R.drawable.default_icon, rede_head);
                        if (!data.com_uid.equals(String.valueOf(potting.getItemInt("uid")))) {
                            rede_head.setOnClickListener(v -> Skip(Ta_SheetActivity.class, "Uuid", data.com_uid));
                            rede_follow.setVisibility(View.VISIBLE);
                            rede_follow.setOnClickListener(v -> {
                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("type", "1");
                                    jsonObject.put("uidone", potting.getItemInt("uid"));
                                    jsonObject.put("uidtwo", data.com_uid);
                                    jsonObject.put("uid", potting.getItemInt("uid"));
                                    jsonObject.put("token", potting.getItemString("token"));
                                    jsonObject.put("tokentype", 1);
                                    if (rede_follow.getText().toString().equals("关注TA")) {
                                        Follow_Finish(BaseUrl.DOSUB, jsonObject, "已关注");
                                    } else {
                                        Follow_Finish(BaseUrl.DELSUB, jsonObject, "关注TA");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                        } else {
                            rede_follow.setVisibility(View.GONE);
                        }
                        trend_nick_name.setText(data.nickname);
                        rede_time.setText(data.time);
                        rede_content.setText(data.content);
                        rede_love_num.setText(data.likes + "");
                        if (data.img != null) {
                            if (!data.img.equals("")) {
                                new DownloadImageTaskUtil(rede_img, data.img, Review_detailsActivity.this).execute(data.img);
                            }
                        }
                        if (data.focus.equals("0")) {
                            rede_follow.setText("关注TA");
                        } else {
                            rede_follow.setText("已关注");
                        }
                        ReplyList(data.id);
                        adapter = new ReviewAdapter(Review_detailsActivity.this, reply, R.layout.rede_list_item);
                        adapter.setCid(data.id);
                        adapter.setTid(data.tid);
                        rede_list.setAdapter(adapter);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            DetailsBean bean = (DetailsBean) JsonUtil.fromJson(s, DetailsBean.class);
                            data = bean.data;
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ImageView rede_head;
    private ImageView rede_detail;
    private CustomImageView rede_img;
    private TextView rede_follow;
    private TextView trend_nick_name;
    private TextView rede_time;
    private TextView rede_content;
    private TextView rede_love_num;
    private ImageView level_review;
    private ImageView rede_love_image;

    private void initHead() {
        rede_love_image = $(R.id.rede_love_image);
        level_review = $(R.id.level_review);
        rede_head = $(R.id.rede_head);
        rede_follow = $(R.id.rede_follow);
        trend_nick_name = $(R.id.trend_nick_name);
        rede_time = $(R.id.rede_time);
        rede_content = $(R.id.rede_content);
        rede_love_num = $(R.id.rede_love_num);
        rede_img = $(R.id.rede_img);
        rede_detail = $(R.id.rede_detail);
        rede_detail.setOnClickListener(v -> emotionPopupWindow.Show(v));
    }

    @Override
    protected void setListener() {
        rede_lin.setOnClickListener(v -> emotionPopupWindow.Show(v));
        finish_result.setOnClickListener(v -> finish());
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        ReplyList(cid);
    }

    @Override
    public void ShowShare(View view) {

    }

    private List<ReplyListBean.Data> reply = new ArrayList<>();

    public void ReplyList(String cid) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cid", cid);
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("page", 0);
            jsonObject.put("type", 2);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.REPLE_LIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    adapter.setList(reply);
                    adapter.notifyDataSetChanged();
                    int size = reply.size();
                    center_result.setText(size+"条回复");
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            ReplyListBean bean = (ReplyListBean) JsonUtil.fromJson(s, ReplyListBean.class);
                            reply.clear();
                            reply.addAll(bean.data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Follow_Finish(String url, JSONObject jsonObject, String result) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                switch (baseBean.getCode()) {
                    case "0":
                        rede_follow.setText(result);
                        break;
                    case "-1":
                        ToastShow("您尚未登录，无法关注");
                        break;
                    case "-2":
                        ToastShow("您的账号登录已失效");
                        break;
                }
            }
        });
    }
}