package xm.ppq.papaquan.Adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.lib_sunshaobei2017.widget.GridView4ScrollView;
import com.lzy.ninegrid.NineGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.LikeBean;
import xm.ppq.papaquan.Bean.LoveBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.A_reward_Dialog;
import xm.ppq.papaquan.Tool.DateUtil;
import xm.ppq.papaquan.Tool.EmotionPopupWindow;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.More_TextView;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.Tool.UpMoreDialog;
import xm.ppq.papaquan.Tool.UpMoreInterface;
import xm.ppq.papaquan.Tool.shownews.ShowNewAdapter;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.main.frame.Round_papa_news;
import xm.ppq.papaquan.View.pic.PictureActivity;
import xm.ppq.papaquan.View.review_details.Review_detailsActivity;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;
import xm.ppq.papaquan.View.trendtopicdetail.TrendTopicDetailActivity;
import xm.ppq.papaquan.life.Tool.Home_Share;

import static xm.ppq.papaquan.View.main.frame.PapaFragLayout.WAIT;

/**
 * Created by sunshaobei on 2017/3/17.
 */


public class PapanewsFragmentAdapter extends RecyclerView.Adapter<PapanewsFragmentAdapter.MyViewHold> implements UpMoreDialog.OnUpMoreListener
        , PapanewsInterface {

    private Activity context;
    private LayoutInflater inflater;
    private ArrayList<ShowNewsBigBean.Data> dataList;
    private Home_Share home_share;
    private OkPotting okpotting;
    private Round_papa_news papa_news;
    private View mHeaderView;
    private ShareDialog dialog;
    private EmotionPopupWindow popupWindow;
    private SharedPreferencesPotting potting;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL1 = 1;
    public static final int TYPE_NORMAL2 = 2;
    public static final int TYPE_NORMAL3 = 3;
    public static final int TYPE_NORMAL4 = 4;
    private UpMoreInterface moreInterface;
    private EditText editText;
    private int windowwidth;

    public PapanewsFragmentAdapter(Activity context, ArrayList<ShowNewsBigBean.Data> dataList, Round_papa_news papa_news, UpMoreInterface moreInterface) {
        NineGridView.setImageLoader(new GlideImageLoader());
        windowwidth = context.getWindowManager().getDefaultDisplay().getWidth() - 230;
        this.context = context;
        this.dataList = dataList;
        this.papa_news = papa_news;
        this.moreInterface = moreInterface;
        home_share = new Home_Share(context);
        home_share.setInterface(this);
        okpotting = OkPotting.getInstance(BaseUrl.PAPA_URL);
        dialog = new ShareDialog(context, R.layout.deteil_share);
        potting = new SharedPreferencesPotting(context, "my_login");
        inflater = LayoutInflater.from(context);
        popupWindow = new EmotionPopupWindow(context, this, "评论");
    }

    public PapanewsFragmentAdapter(Activity context, ArrayList<ShowNewsBigBean.Data> dataList, Round_papa_news papa_news, UpMoreInterface moreInterface, EditText editText) {
        NineGridView.setImageLoader(new GlideImageLoader());
        windowwidth = context.getWindowManager().getDefaultDisplay().getWidth() - 230;
        this.context = context;
        this.dataList = dataList;
        this.papa_news = papa_news;
        this.moreInterface = moreInterface;
        home_share = new Home_Share(context);
        home_share.setInterface(this);
        okpotting = OkPotting.getInstance(BaseUrl.PAPA_URL);
        dialog = new ShareDialog(context, R.layout.deteil_share);
        potting = new SharedPreferencesPotting(context, "my_login");
        inflater = LayoutInflater.from(context);
        popupWindow = new EmotionPopupWindow(context, this, "评论");
        this.editText = editText;
    }

    public void addHeaderView(View headerView, int v) {
        if (v == View.GONE) {
            mHeaderView = null;
        } else {
            mHeaderView = headerView;
        }
        notifyItemInserted(0);
    }

    @Override
    public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new MyViewHold(mHeaderView, TYPE_HEADER);
        MyViewHold myViewHold = null;
        switch (viewType) {
            case TYPE_NORMAL1:
                myViewHold = new MyViewHold(inflater.inflate(R.layout.show_news_item1, null), viewType);
                break;
            case TYPE_NORMAL2:
                myViewHold = new MyViewHold(inflater.inflate(R.layout.show_news_item2, null), viewType);
                break;
            case TYPE_NORMAL3:
                myViewHold = new MyViewHold(inflater.inflate(R.layout.show_news_item3, null), viewType);
                break;
            case TYPE_NORMAL4:
                myViewHold = new MyViewHold(inflater.inflate(R.layout.show_news_item4, null), viewType);
                break;
        }

        return myViewHold;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            if (dataList.get(position).picture != null && dataList.get(position).picture.size() > 0) {
                if (dataList.get(position).picture.size() == 1)
                    return TYPE_NORMAL1;//单图片
                else return TYPE_NORMAL2;//多图
            } else {
                if (dataList.get(position).video != null && dataList.get(position).videopic != null && !dataList.get(position).video.equals("") && !dataList.get(position).videopic.equals("")) {
                    return TYPE_NORMAL3;//有视频
                } else {
                    return TYPE_NORMAL4;//纯文本
                }
            }
        }
        if (position == 0)
            return TYPE_HEADER;
        else {
            if (dataList.get(position).picture != null && dataList.get(position).picture.size() > 0) {
                if (dataList.get(position).picture.size() == 1)
                    return TYPE_NORMAL1;//单图片
                else return TYPE_NORMAL2;//多图
            } else {
                if (dataList.get(position).video != null && dataList.get(position).videopic != null && !dataList.get(position).video.equals("") && !dataList.get(position).videopic.equals("")) {
                    return TYPE_NORMAL3;//有视频
                } else {
                    return TYPE_NORMAL4;//纯文本
                }
            }
        }
    }

    public void setDataList(ArrayList<ShowNewsBigBean.Data> dataList) {
        this.dataList = dataList;
    }

    Handler mHandle = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    public void onBindViewHolder(MyViewHold holder, int position) {

        if (dataList.size() > 0 && mHeaderView == null) {
            ShowNewsBigBean.Data data = dataList.get(position);

            switch (getItemViewType(position)) {
                case TYPE_NORMAL1://单图
                    holder.oneimage1.setImageResource(R.drawable.default_icon_zheng);
                    holder.oneimage1.setTag(data.picture.get(0));
                    holder.oneimage1.setMaxWidth(holder.frameLayout1.getWidth());
                    Glide.with(context).load(BaseUrl.BITMAP + data.picture.get(0) + "/dantu").asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (holder.oneimage1.getTag().equals(data.picture.get(0))) {
                                ViewGroup.LayoutParams layoutParams = holder.oneimage1.getLayoutParams();
                                double aspectRatio = (double) (resource.getHeight() * 2) / (double) (resource.getWidth() * 2);
                                if (resource.getWidth() * 2 <= holder.frameLayout1.getWidth()) {
                                    if (resource.getHeight() * 2 < 500) {
                                        layoutParams.width = resource.getWidth() * 2;
                                        layoutParams.height = resource.getHeight() * 2;
                                    } else {
                                        layoutParams.height = 500;
                                        double v = 500d / aspectRatio;
                                        layoutParams.width = (int) v;
                                    }
                                } else {
                                    if (resource.getHeight() < 500) {
                                        layoutParams.width = resource.getWidth() * 2;
                                        layoutParams.height = (int) (resource.getWidth() * 2 * aspectRatio);
                                    } else {
                                        layoutParams.height = 500;
                                        double v = 500d / aspectRatio;
                                        layoutParams.width = (int) v;
                                    }
                                }
                                context.runOnUiThread(() -> {
                                    holder.oneimage1.setLayoutParams(layoutParams);
                                    holder.oneimage1.setImageBitmap(resource);
                                });
                            } else {
                                holder.oneimage1.setImageResource(R.drawable.default_icon_zheng);
                            }
                        }
                    });
                    holder.oneimage1.setOnClickListener(v -> {
                        Intent intent = new Intent(context, PictureActivity.class);
                        intent.putExtra("height", holder.oneimage1.getHeight());
                        intent.putExtra("width", holder.oneimage1.getWidth());
                        int[] location = new int[2];
                        holder.oneimage1.getLocationOnScreen(location);
                        intent.putExtra("locationX", location[0]);
                        intent.putExtra("locationY", location[1]);
                        intent.putStringArrayListExtra("list", (ArrayList<String>) data.picture);
                        intent.putExtra("position", 0);
                        context.startActivity(intent);
                    });
                    break;
                case TYPE_NORMAL2://多图:
                    //TODO
                    holder.setNineGrid((ArrayList<String>) data.picture, holder.gridView);
                    break;
                case TYPE_NORMAL3://视频

                    holder.video_start.setOnClickListener(v -> JCVideoPlayerStandard.startFullscreen(context, JCVideoPlayerStandard.class, BaseUrl.OUTURL + data.video, ""));
                    holder.oneimage.setImageResource(R.drawable.default_icon_zheng);
                    holder.oneimage.setTag(data.videopic);

                    if (position == 0 && WAIT == 1) {
                        WAIT = 0;
                        holder.video_start.postDelayed(() -> {
                            Glide.with(context).load(BaseUrl.OUTURL + data.videopic).asBitmap().into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    if (holder.oneimage.getTag().equals(data.videopic)) {
                                        ViewGroup.LayoutParams layoutParams = holder.oneimage.getLayoutParams();
                                        double aspectRatio = (double) resource.getHeight() / (double) resource.getWidth();
                                        if (resource.getWidth() <= holder.frameLayout3.getWidth()) {
                                            if (resource.getHeight() < 500) {
                                                layoutParams.width = resource.getWidth();
                                                layoutParams.height = resource.getHeight();
                                            } else {
                                                layoutParams.height = 500;
                                                double v = 500d / aspectRatio;
                                                layoutParams.width = (int) v;
                                            }
                                        } else {
                                            if (resource.getHeight() < 500) {
                                                layoutParams.width = resource.getWidth();
                                                layoutParams.height = (int) (resource.getWidth() * aspectRatio);
                                            } else {
                                                layoutParams.height = 500;
                                                double v = 500d / aspectRatio;
                                                layoutParams.width = (int) v;
                                            }
                                        }
                                        context.runOnUiThread(() -> {
                                            holder.oneimage.setLayoutParams(layoutParams);
                                        });
                                        holder.oneimage.setImageBitmap(resource);
                                    } else {
                                        holder.oneimage.setImageResource(R.drawable.default_icon_zheng);
                                    }
                                }
                            });
                        }, 2000);
                    } else {
                        Glide.with(context).load(BaseUrl.OUTURL + data.videopic).asBitmap().into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                if (holder.oneimage.getTag().equals(data.videopic)) {
                                    ViewGroup.LayoutParams layoutParams = holder.oneimage.getLayoutParams();
                                    double aspectRatio = (double) resource.getHeight() / (double) resource.getWidth();
                                    if (resource.getWidth() <= holder.frameLayout3.getWidth()) {
                                        if (resource.getHeight() < 500) {
                                            layoutParams.width = resource.getWidth();
                                            layoutParams.height = resource.getHeight();
                                        } else {
                                            layoutParams.height = 500;
                                            double v = 500d / aspectRatio;
                                            layoutParams.width = (int) v;
                                        }
                                    } else {
                                        if (resource.getHeight() < 500) {
                                            layoutParams.width = resource.getWidth();
                                            layoutParams.height = (int) (resource.getWidth() * aspectRatio);
                                        } else {
                                            layoutParams.height = 500;
                                            double v = 500d / aspectRatio;
                                            layoutParams.width = (int) v;
                                        }
                                    }
                                    context.runOnUiThread(() -> {
                                        holder.oneimage.setLayoutParams(layoutParams);
                                    });
                                    holder.oneimage.setImageBitmap(resource);
                                } else {
                                    holder.oneimage.setImageResource(R.drawable.default_icon_zheng);
                                }
                            }
                        });
                    }
                    break;
                case TYPE_NORMAL4://纯文本
                    break;


            }

            if (data.likes_num > 0) {
                holder.set_view.setVisibility(View.VISIBLE);
            } else {
                holder.set_view.setVisibility(View.GONE);
                if (data.reward_num > 0) {
                    holder.set_view.setVisibility(View.VISIBLE);
                } else {
                    holder.set_view.setVisibility(View.GONE);
                }
                if (data.comments.size() > 0) {
                    holder.set_view.setVisibility(View.VISIBLE);
                } else {
                    holder.set_view.setVisibility(View.GONE);
                }
            }
            holder.nick_name_show.setOnClickListener(v -> {
                if (data.uid.equals(String.valueOf(potting.getItemInt("uid")))) {
                    //TODO
                } else {
                    Intent intent = new Intent(context, Ta_SheetActivity.class);
                    intent.putExtra("Uuid", data.uid);
                    context.startActivity(intent);
                }
            });
            holder.level_show.setImageResource(BaseUrl.INTEGERS[data.level]);
            holder.head_url_show.setOnClickListener(v -> {
                if (data.uid.equals(String.valueOf(potting.getItemInt("uid")))) {
                    //TODO
                } else {
                    Intent intent = new Intent(context, Ta_SheetActivity.class);
                    intent.putExtra("Uuid", data.uid);
                    context.startActivity(intent);
                }
            });
            holder.setImageView(data.headurl, holder.head_url_show)
                    .setText(data.nickname, holder.nick_name_show)
                    .setText("共" + data.comment_num + "人评论", holder.discuss_show);
            holder.setText(DateUtil.formatData(Long.valueOf(data.createtime + "000")), holder.read_number_show);
            if (data.video != null && !data.video.equals("")) {
                //TODO
            }
            if (papa_news.getUid() == 0) {
                holder.follow_show.setVisibility(View.GONE);
            } else {
                if (data.uid.equals(String.valueOf(papa_news.getUid()))) {
                    holder.follow_show.setVisibility(View.GONE);
                } else {
                    if (data.sub == 0) {
                        holder.setText("+关注", holder.follow_show);
                        holder.follow_show.setVisibility(View.VISIBLE);
                    } else {
                        holder.follow_show.setVisibility(View.GONE);
                        holder.setText("已关注", holder.follow_show);
                    }
                }
            }

            if (data.like == 0) {
                holder.love_image_show.setImageResource(R.mipmap.love_white);
                holder.love_text_show.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                holder.love_image_show.setImageResource(R.drawable.love_true);
                holder.love_text_show.setTextColor(context.getResources().getColor(R.color.red_d_r));
            }
            if (potting.isLogin()) {
                ToastShow("您尚未登录");
            } else {
                holder.love_lin.setOnClickListener(v -> {
                    try {
                        JSONObject json = new JSONObject();
                        json.put("tid", data.id);
                        json.put("uid", papa_news.getUid());
                        json.put("token", papa_news.getToken());
                        json.put("tokentype", 1);
                        okpotting.AskOne(BaseUrl.LIKETOPIC, json.toString(), new Subscriber<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                if (s != null) {
                                    switch (JsonUtil.getString(s, "code")) {
                                        case "0"://取消
                                            ToastShow("已点赞");
                                            break;
                                        case "1"://点赞
                                            ToastShow("喜欢成功");
                                            data.like = 1;
                                            data.likes_num++;
                                            LoveBean loveBeen = (LoveBean) JsonUtil.fromJson(JsonUtil.getString(s, "data"), LoveBean.class);
                                            if (loveBeen != null) {
                                                LikeBean likeBean = new LikeBean();
                                                likeBean.uid = loveBeen.getUid();
                                                likeBean.headurl = loveBeen.getHeadurl();
                                                data.likeusers.add(0, likeBean);
                                                notifyItemChanged(position);
                                            }
                                            break;
                                        case "-2":
                                            ToastShow("您的用户凭证已失效，请重新登录");
                                            break;
                                        case "-1":
                                            ToastShow("您尚未登录，无法操作");
                                            break;
                                    }
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }
            if (data.comments != null) {
                if (data.comment_num > 3) {
                    holder.dicss_num.setVisibility(View.VISIBLE);
                } else {
                    holder.dicss_num.setVisibility(View.GONE);
                }
            } else {
                holder.dicss_num.setVisibility(View.GONE);
            }
            holder.setContent_show(Stringutil.DisplaceTitle(data.content, context), holder.content_show, data.id);
            holder.more_show.setOnClickListener(v -> {
                home_share.setAll(Integer.valueOf(data.uid), Integer.valueOf(data.id), data.nickname, holder.follow_show.getVisibility()).Show(v);
//                up_more.setName(data.nickname).setUid(data.uid).setTid(data.id).Show(v);
                if (editText != null)
                    EmotionPopupWindow.HideKeyboard(editText);
            });
            if (potting.isLogin()) {
                ToastShow("您尚未登录");
            } else {
                holder.money_lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, A_reward_Dialog.class);
                        intent.putExtra("url", data.headurl);
                        intent.putExtra("tid", data.id);
                        intent.putExtra("name", data.nickname);
                        context.startActivity(intent);
                    }
                });
            }
            holder.show_news_all.setOnClickListener(v -> {
                Intent intent = new Intent(context, TrendTopicDetailActivity.class);
                intent.putExtra("tid", data.id);
                context.startActivity(intent);
            });
            holder.discuss_lin.setOnClickListener(v -> {
                if (potting.getItemString("token") != null && !potting.getItemString("token").equals("")) {
                    popupWindow.setId(data.id, position);
                    popupWindow.setView();
                    popupWindow.show(v, data.comments);
                    holder.discuss_lin.postDelayed(() -> EmotionPopupWindow.showInputMethod(context, popupWindow.getEditText()), 30);
                } else ToastShow("您还未登陆");
            });
            if (data.likeusers != null) {
                if (data.likeusers.size() > 0) {
                    holder.like_lin.setVisibility(View.VISIBLE);
                    holder.love_number_show.setText(data.likes_num + "人喜欢");
                    holder.setGridView(data.likeusers, 2, holder.love_grid_show);
                } else {
                    holder.like_lin.setVisibility(View.GONE);
                }
            } else {
                holder.like_lin.setVisibility(View.GONE);
            }
            if (data.rewardusers != null) {
                if (data.rewardusers.size() > 0) {
                    holder.two_view.setVisibility(View.VISIBLE);
                    holder.money_list_lin.setVisibility(View.VISIBLE);
                    holder.money_grid_show.setVisibility(View.VISIBLE);
                    holder.money_number_show.setVisibility(View.VISIBLE);
                    holder.money_number_show.setText(data.reward_num + "人打赏");
                    holder.setGridView(data.rewardusers, 2, holder.money_grid_show);
                } else {
                    holder.two_view.setVisibility(View.GONE);
                    holder.money_list_lin.setVisibility(View.GONE);
                    holder.money_grid_show.setVisibility(View.GONE);
                    holder.money_number_show.setVisibility(View.GONE);
                }
            } else {
                holder.two_view.setVisibility(View.GONE);
                holder.money_list_lin.setVisibility(View.GONE);
                holder.money_grid_show.setVisibility(View.GONE);
                holder.money_number_show.setVisibility(View.GONE);
            }
            if (data.comments != null) {
                if (data.comments.size() > 0) {
                    if (data.likes_num > 0) {
                        holder.slid_view.setVisibility(View.VISIBLE);
                    }
                    if (data.reward_num > 0) {
                        holder.slid_view.setVisibility(View.VISIBLE);
                    }
                    holder.criticism_list.setVisibility(View.VISIBLE);
                    holder.setListView(data.comments, holder.criticism_list);
                } else {
                    holder.slid_view.setVisibility(View.GONE);
                    holder.criticism_list.setVisibility(View.GONE);
                }
            } else {
                holder.slid_view.setVisibility(View.GONE);
                holder.criticism_list.setVisibility(View.GONE);
            }
            holder.follow_show.setOnClickListener(v -> {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "1");
                    jsonObject.put("uidone", papa_news.getUid());
                    jsonObject.put("uidtwo", data.uid);
                    jsonObject.put("uid", papa_news.getUid());
                    jsonObject.put("token", papa_news.getToken());
                    jsonObject.put("tokentype", 1);
                    if (holder.follow_show.getText().equals("+关注")) {
                        Follow_Finish(BaseUrl.DOSUB, jsonObject, "已关注", holder.follow_show);
                    } else {
                        Follow_Finish(BaseUrl.DELSUB, jsonObject, "+关注", holder.follow_show);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });
        }
        if (holder.showlin != null) {
            holder.showlin.setOnClickListener(v -> {
                if (getView() != null && !lin.equals(holder.leftmoreview)) {
                    hide(lin, imageView);
                }
                if (!linshow) {
                    holder.showlin.setImageResource(R.mipmap.leftmore_hide);
                    show(holder.leftmoreview, holder.showlin);
                } else {
                    holder.showlin.setImageResource(R.mipmap.leftmore_show);
                    hide(holder.leftmoreview, holder.showlin);
                }
            });
        }
    }

    private boolean linshow = false;
    private View lin;
    private ImageView imageView;

    public void hide() {
        hide(lin, imageView);
    }

    public View getView() {
        return lin;
    }

    private void show(View v, ImageView image) {
        v.setVisibility(View.VISIBLE);
        lin = v;
        imageView = image;
        linshow = true;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 0, 0.3f, 0.5f, 0.7f, 1);
        ObjectAnimator translationx = ObjectAnimator.ofFloat(v, "translationX", 1000, 0);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(alpha).with(translationx);//两个动画同时开始
        animatorSet.start();
    }

    private void hide(View v, ImageView image) {
        lin = null;
        imageView = null;
        linshow = false;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alpha = ObjectAnimator.ofFloat(v, "alpha", 1, 0.7f, 0.5f, 0.3f, 0);
        ObjectAnimator translationx = ObjectAnimator.ofFloat(v, "translationX", 0, 1000);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(alpha).with(translationx);//两个动画同时开始
        animatorSet.start();
        image.setImageResource(R.mipmap.leftmore_show);
        v.postDelayed(() -> {
            v.setVisibility(View.INVISIBLE);
        }, 300);

    }

    private void Follow_Finish(String url, JSONObject jsonObject, String result, TextView follow_show) {
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
                        follow_show.setText(result);
                        if (result.equals("已关注")) {
                            follow_show.setVisibility(View.GONE);
                            Toast("关注成功");
                            notifyItem();
                        } else {
                            notifyItem();
                            follow_show.setVisibility(View.VISIBLE);
                        }
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

    private Toast toast;

    private void Toast(String result) {
        if (toast == null) {
            toast = Toast.makeText(context, result, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(result);
        }
        toast.show();
    }

    @Override
    public int getItemCount() {
        if (mHeaderView != null) {
            return dataList.size() + 1;
        }
        return dataList.size();
    }

    @Override
    public void UpMore(View v) {

    }

    @Override
    public void notifyItem(int position) {
        notifyItemChanged(position);
    }

    @Override
    public void notifyItem() {
        papa_news.startMutual();
    }

    @Override
    public void ShowShare(View view) {
//        up_more.Hide();
        moreInterface.Show(dialog);
    }


    class MyViewHold extends RecyclerView.ViewHolder {

        @BindView(R.id.set_view)
        View set_view;
        @BindView(R.id.slid_view)
        View slid_view;
        @BindView(R.id.money_list_lin)
        LinearLayout money_list_lin;

        ImageView video_start;

        @BindView(R.id.level_show)
        ImageView level_show;
        @BindView(R.id.head_url_show)
        ImageView head_url_show;

        GridView4ScrollView gridView;
        ImageView oneimage;
        ImageView oneimage1;
        FrameLayout frameLayout1;
        FrameLayout frameLayout3;

        @BindView(R.id.content_show)
        More_TextView content_show;
        @BindView(R.id.nick_name_show)
        TextView nick_name_show;
        @BindView(R.id.read_number_show)
        TextView read_number_show;
        @BindView(R.id.follow_show)
        TextView follow_show;
        @BindView(R.id.more_show)
        ImageView more_show;
        @BindView(R.id.money_lin)
        LinearLayout money_lin;
        @BindView(R.id.love_image_show)
        ImageView love_image_show;
        @BindView(R.id.love_text_show)
        TextView love_text_show;
        @BindView(R.id.love_lin)
        LinearLayout love_lin;
        @BindView(R.id.show_news_all)
        View show_news_all;
        @BindView(R.id.discuss_lin)
        LinearLayout discuss_lin;
        @BindView(R.id.like_lin)
        LinearLayout like_lin;
        @BindView(R.id.love_grid_show)
        GridView love_grid_show;
        @BindView(R.id.love_number_show)
        TextView love_number_show;
        @BindView(R.id.discuss_show)
        TextView discuss_show;
        @BindView(R.id.two_view)
        View two_view;
        @BindView(R.id.criticism_list)
        ListView criticism_list;
        @BindView(R.id.money_grid_show)
        GridView money_grid_show;
        @BindView(R.id.money_number_show)
        TextView money_number_show;
        @BindView(R.id.dicss_num)
        LinearLayout dicss_num;
        @BindView(R.id.showlin)
        ImageView showlin;
        @BindView(R.id.leftmoreview)
        View leftmoreview;

        private ArrayList<LikeBean> gird_list = new ArrayList<>();

        public MyViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public MyViewHold(View itemView, int type) {
            super(itemView);
            switch (type) {
                case TYPE_HEADER:
                    break;
                case TYPE_NORMAL1://单图
                    ButterKnife.bind(this, itemView);
                    oneimage1 = (ImageView) itemView.findViewById(R.id.oneimage);
                    frameLayout1 = (FrameLayout) itemView.findViewById(R.id.frameLayout1);
                    break;
                case TYPE_NORMAL2://多图
                    ButterKnife.bind(this, itemView);
                    gridView = (GridView4ScrollView) itemView.findViewById(R.id.gd);
                    break;
                case TYPE_NORMAL3://视频
                    ButterKnife.bind(this, itemView);
                    oneimage = (ImageView) itemView.findViewById(R.id.oneimage);
                    video_start = (ImageView) itemView.findViewById(R.id.video_start);
                    frameLayout3 = (FrameLayout) itemView.findViewById(R.id.frameLayout3);
                    break;
                case TYPE_NORMAL4://纯文本
                    ButterKnife.bind(this, itemView);
                    break;
            }

        }

        MyViewHold setContent_show(CharSequence s, More_TextView textView, String tid) {
            textView.setDesc(s, tid);
            return this;
        }

        MyViewHold setImageView(String s, ImageView imageView) {
            ImageLoading.Circular(context, s + "/200x200", R.drawable.default_icon, imageView);
            return this;
        }

        MyViewHold setText(CharSequence s, TextView textView) {
            textView.setText(s);
            return this;
        }

        MyViewHold setNineGrid(ArrayList<String> list, GridView4ScrollView grid_view) {

            boolean notify = false;
            NineAdapter nineAdapter = new NineAdapter(list, windowwidth, notify);
            grid_view.setAdapter(nineAdapter);
            nineAdapter.setNotify(true);
            nineAdapter.notifyDataSetChanged();
            return this;
        }


        class NineAdapter extends BaseAdapter {
            ArrayList<String> list;
            int width;
            boolean notify;

            public void setNotify(boolean b) {
                this.notify = b;
            }

            public NineAdapter(ArrayList<String> list, int width, boolean notify) {
                this.list = list;
                this.width = width;
                this.notify = notify;
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_pic, null);
                int width1 = width - 20;
                int width2 = width1 / 3;
                ImageView image = (ImageView) view.findViewById(R.id.imageView);
                ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
                layoutParams.width = width2;
                layoutParams.height = width2;
                image.setLayoutParams(layoutParams);
                if (notify) {
                    Glide.with(context).load(BaseUrl.BITMAP + list.get(position) + "/200x200").into(image);
                } else {
                    image.setImageResource(R.drawable.default_icon_zheng);
                }
                image.setOnClickListener(v -> {
                    Intent intent = new Intent(context, PictureActivity.class);
                    intent.putExtra("height", image.getHeight());
                    intent.putExtra("width", image.getWidth());
                    int[] location = new int[2];
                    image.getLocationOnScreen(location);
                    intent.putExtra("locationX", location[0]);
                    intent.putExtra("locationY", location[1]);
                    intent.putStringArrayListExtra("list", list);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                });
                return view;
            }
        }


        MyViewHold setGridView(List<LikeBean> list, int i, GridView grid_view) {
            gird_list.clear();
            gird_list.addAll(list);
            bAdapter badapter = new bAdapter(gird_list, context);
            badapter.setI(i);
            grid_view.setAdapter(badapter);
            return this;
        }

        MyViewHold setListView(List<ShowNewsBigBean.Data.Comments> list, ListView listView) {
            listView.setAdapter(new ShowNewAdapter(context, list, R.layout.criticism_item));
            listView.setOnItemClickListener((parent, view, position, id) -> {
                //TODO
                Intent intent = new Intent(context, Review_detailsActivity.class);
                intent.putExtra("cid", list.get(position).id);
                context.startActivity(intent);
            });
            return this;
        }
    }

    public void ToastShow(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }


    /**
     * Glide 加载
     */
    private class GlideImageLoader implements NineGridView.ImageLoader {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url) {
            Glide.with(context).load(url)//
                    .placeholder(R.drawable.default_icon_zheng)//0
                    .error(R.drawable.default_icon_zheng)//
                    .diskCacheStrategy(DiskCacheStrategy.ALL)//
                    .into(imageView);
        }

        @Override
        public Bitmap getCacheImage(String url) {
            return null;
        }
    }
}