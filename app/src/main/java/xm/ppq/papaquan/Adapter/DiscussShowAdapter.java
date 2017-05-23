package xm.ppq.papaquan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.GetComBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.DownloadImageTaskUtil;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.LvHeightUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.Tool.typewriting.SpanStringUtils;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.review_details.Review_detailsActivity;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;
import xm.ppq.papaquan.View.trendtopicdetail.Round_Trend;
import xm.ppq.papaquan.life.Tool.TrendTopicPotting;
import xm.ppq.papaquan.life.Tool.TrendTopicPotting.OnReplyListener;

/**
 * Created by Administrator on 2017/3/3.
 */

public class DiscussShowAdapter extends ConcreteAdapter<GetComBean.Data> implements PapanewsInterface, OnReplyListener {

    private EmotionPopupWindow emotionPopupWindow;
    private String tid;
    private Round_Trend round_trend;
    private DoubleDiscussAdapter adapter;
    private SharedPreferencesPotting my_login;
    private TrendTopicPotting trendTopicPotting;

    public DiscussShowAdapter(Context context, List<GetComBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
        round_trend = (Round_Trend) context;
        my_login = new SharedPreferencesPotting(context, "my_login");
        emotionPopupWindow = new EmotionPopupWindow((Activity) getContext(), this, "回复评论");
        trendTopicPotting = new TrendTopicPotting((Activity) context);
        trendTopicPotting.setOnReplyListener(this);
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    protected void convert(ViewHolder viewHolder, GetComBean.Data item, int position) {
        viewHolder.setOnClickListener(v -> {
            trendTopicPotting.setPosition(position);
            trendTopicPotting.setIdAll(item.id, item.com_uid, tid, item.commentUserdata.nickname).Trend_Show(viewHolder.getView(R.id.more_image));
        }, R.id.more_image);
        if (item.commentUserdata.uid.equals(String.valueOf(round_trend.getUid()))) {
            viewHolder.setVisibility(View.GONE, R.id.dis_follow);
        } else {
            viewHolder.setVisibility(View.GONE, R.id.dis_follow);
            viewHolder.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), Ta_SheetActivity.class);
                intent.putExtra("Uuid", item.commentUserdata.uid);
                getContext().startActivity(intent);
            }, R.id.dis_icon);
        }
        viewHolder.setResources(BaseUrl.INTEGERS[item.commentUserdata.level], R.id.dis_level);
        if (item.reply != null) {
            adapter = new DoubleDiscussAdapter(getContext(), item.reply, R.layout.double_discuss_item);
            adapter.setCid(item.id);
            adapter.setTid(item.tid);
            ((SlideListView) viewHolder.getView(R.id.double_discuss_list)).setAdapter(adapter);
            LvHeightUtil.setListViewHeightBasedOnChildren(viewHolder.getView(R.id.double_discuss_list));
        }
        if (item.reply_num > 0) {
            viewHolder.setVisibility(View.VISIBLE, R.id.num_lin);
            viewHolder.setText("共" + item.reply_num + "条评论", R.id.discuss_num);
        } else if (item.reply_num == 0) {
            viewHolder.setVisibility(View.GONE, R.id.num_lin);
        }
        viewHolder.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Review_detailsActivity.class);
            intent.putExtra("cid", item.id);
            getContext().startActivity(intent);
        }, R.id.dis_all_item);
        //变换islke图片
        if (item.isLike == 1) {
            viewHolder.setResources(R.drawable.love_true, R.id.loveimage);
        } else if (item.isLike == 0) {
            viewHolder.setResources(R.drawable.love_false, R.id.loveimage);
        }
        //评论点赞
        viewHolder.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("cid", item.id)
                        .put("uid", my_login.getItemInt("uid"))
                        .put("token", my_login.getItemString("token"))
                        .put("tokentype", 1);
                OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.LIKECOMMENT, jsonObject.toString(), new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean o) {
                        switch (o.getCode()) {
                            case "0":
                                notifyItem();
                                break;
                            case "1":
                                notifyItem();
                                break;
                            case "-1":
                                ToastShow("您尚未登录，无法使用此功能");
                                break;
                            case "-2":
                                ToastShow("您的登录已失效");
                                break;
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, R.id.loveimage);
        if (item.focus.equals("0")) {
            viewHolder.setText("关注TA", R.id.dis_follow);
        } else {
            viewHolder.setText("已关注", R.id.dis_follow);
        }
        viewHolder.setOnClickListener(v -> {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", "1");
                jsonObject.put("uidone", round_trend.getUid());
                jsonObject.put("uidtwo", item.commentUserdata.uid);
                jsonObject.put("uid", round_trend.getUid());
                jsonObject.put("token", round_trend.getToken());
                jsonObject.put("tokentype", 1);
                if (((TextView) viewHolder.getView(R.id.dis_follow)).getText().toString().equals("关注TA")) {
                    Follow_Finish(BaseUrl.DOSUB, jsonObject, "已关注", item.topic_uid, item.commentUserdata.uid);
                } else {
                    Follow_Finish(BaseUrl.DELSUB, jsonObject, "关注TA", item.topic_uid, item.commentUserdata.uid);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, R.id.dis_follow);
        ImageLoading.Circular((Activity) getContext(), item.commentUserdata.headurl, R.drawable.default_icon, viewHolder.getView(R.id.dis_icon));
        viewHolder.setText(item.commentUserdata.nickname, R.id.dis_name)
                .setSpanned(SpanStringUtils.getEmotionContent(getContext(), item.content), R.id.dis_content)
                .setText(item.time, R.id.dis_time)
                .setText(item.likes, R.id.dis_love_number)
                .setOnClickListener(v -> {
                    emotionPopupWindow.setCid(item.id, item.com_uid, tid);
                    emotionPopupWindow.Show(v);
                }, R.id.common_double)
                .setOnClickListener(v -> {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("tid", tid);
                        jsonObject.put("uid", round_trend.getUid());
                        jsonObject.put("cid", item.id);
                        jsonObject.put("token", round_trend.getToken());
                        jsonObject.put("tokentype", 1);
                        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.LIKETOPIC, jsonObject.toString(), new Subscriber<BaseBean>() {
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
                                        ToastShow("已点赞");
                                        break;
                                    case "1":
                                        notifyItem();
                                        break;
                                    case "-1":
                                        ToastShow("您尚未登录，无法使用此功能");
                                        break;
                                    case "-2":
                                        ToastShow("您的登录已失效");
                                        break;
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, R.id.dis_love_number);
        if (item.img != null) {
            if (!item.img.equals("")) {
                viewHolder.setVisibility(View.VISIBLE, R.id.dis_content_icon);
                new DownloadImageTaskUtil(viewHolder.getView(R.id.dis_content_icon), item.img, (Activity) getContext()).execute(item.img);
            } else {
                viewHolder.setVisibility(View.GONE, R.id.dis_content_icon);
            }
        } else {
            viewHolder.setVisibility(View.GONE, R.id.dis_content_icon);
        }
    }

    @Override
    public void notifyItem(int position) {

    }

    @Override
    public void notifyItem() {
        round_trend.setCri();
    }

    @Override
    public void ShowShare(View view) {

    }

    public void Follow_Finish(String url, JSONObject jsonObject, String result, String tuid, String duid) {
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
                        notifyItem();
                        if (tuid.equals(duid)) {
                            round_trend.setFollow(result);
                        }
                        break;
                    case "-1":
                        round_trend.ShowToast("您尚未登录，无法关注");
                        break;
                    case "-2":
                        round_trend.ShowToast("您的账号登录已失效");
                        break;
                }
            }
        });
    }

    @Override
    public void reply(String id, String com_id, String tid, View v) {
        emotionPopupWindow.setCid(id, com_id, tid);
        emotionPopupWindow.Show(v);
    }

    @Override
    public void Renovate(int position) {
        getList().remove(position);
        notifyDataSetChanged();
    }
}
