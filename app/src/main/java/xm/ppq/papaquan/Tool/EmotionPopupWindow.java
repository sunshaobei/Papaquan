package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.PapanewsDiscussData;
import xm.ppq.papaquan.Bean.PicBean;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.typewriting.EmotionGridView;
import xm.ppq.papaquan.Tool.typewriting.LookPagerAdapter;
import xm.ppq.papaquan.Tool.typewriting.TypewritingUtil;
import xm.ppq.papaquan.View.BaseUrl;

import static xm.ppq.papaquan.Bean.ShowNewsBigBean.*;

/**
 * Created by Administrator on 2017/3/21.
 */

public class EmotionPopupWindow extends PopupWindowPotting implements View.OnClickListener {

    private RelativeLayout all;
    private ImageView pic;
    private ImageView em_kt;
    private EditText content_edit;
    private TextView dispatch_news;
    private ViewPager viewPager;
    private LinearLayout drop_lin;
    private EmotionGridView emotionGridView;
    private LookPagerAdapter pagerAdapter;
    private ImageView map_depot;
    private SharedPreferencesPotting potting;
    private PapanewsInterface papanewsInterface;
    private String type;
    private TypewritingUtil typewritingUtil;
    private TextView dispatch_finish;

    public EmotionPopupWindow(Activity activity, PapanewsInterface papanewsInterface, String type) {
        super(activity);
        potting = new SharedPreferencesPotting(activity, "my_login");
        this.papanewsInterface = papanewsInterface;
        this.type = type;
    }

    public void setView() {
        map_depot.setVisibility(View.VISIBLE);
    }

    private List<ShowNewsBigBean.Data.Comments> comments;
    public void show(View view,List<ShowNewsBigBean.Data.Comments> comments)
    {
        this.comments = comments;
        Show(view);
    }

    @Override
    public void Hide() {
        super.Hide();
        pic.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void PicEvenbus(PicBean bean) {
        pic.setVisibility(View.VISIBLE);
        ImageLoading.common(getActivity(), BaseUrl.BITMAP + bean.getUrl() + "/dantu", pic, R.drawable.news_pic);
        biturl = bean.getUrl();
    }

    private String biturl;

    @Override
    protected int getLayout() {
        return R.layout.fragment_look;
    }

    public EditText getEditText() {
        return content_edit;
    }

    @Override
    protected void initUI() {
        dispatch_finish = $(R.id.dispatch_finish);
        dispatch_finish.setOnClickListener(v -> Hide());
        all = $(R.id.all);
        all.setOnClickListener(v -> Hide());
        map_depot = $(R.id.map_depot);
        pic = $(R.id.pic);
        drop_lin = $(R.id.drop_lin);
        em_kt = $(R.id.em_kt);
        content_edit = $(R.id.content_edit);
        dispatch_news = $(R.id.dispatch_news);
        viewPager = $(R.id.viewpager);
        map_depot.setVisibility(View.GONE);
        pagerAdapter = new LookPagerAdapter(getActivity());
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        emotionGridView = new EmotionGridView(getActivity(), content_edit, height, pagerAdapter, viewPager);
        emotionGridView.a1(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 5));
        initRound();
    }

    /**
     * true则为显示表情
     * false则为显示键盘
     */
    private boolean isType = true;

    @Override
    protected void setListener() {
        map_depot.setOnClickListener(this);
        em_kt.setOnClickListener(this);
        dispatch_news.setOnClickListener(v -> {
            if (!content_edit.getText().toString().equals("") && content_edit.getText().toString() != null) {
                if (type.equals("评论")) {
                    Reply_Criticism();
                } else if (type.equals("回复评论")) {
                    Criticism();
                }
                dispatch_news.setFocusable(false);
            } else {
                ToastShow("内容不能为空");
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < drop_lin.getChildCount(); i++) {
                    if (position == i) {
                        ((ImageView) drop_lin.getChildAt(i)).setImageResource(R.drawable.round_dot_true);
                    } else {
                        ((ImageView) drop_lin.getChildAt(i)).setImageResource(R.drawable.round_dot_false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        content_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!isType) {
                        showInputMethod(getActivity(), content_edit);
                        viewPager.setVisibility(View.GONE);
                        drop_lin.setVisibility(View.GONE);
                        em_kt.setImageResource(R.drawable.expression);
                        return true;
                    }

                }
                return false;
            }
        });
        typewritingUtil = new TypewritingUtil(getActivity());
        typewritingUtil.addOnSoftKeyBoardVisibleListener(getActivity(), new TypewritingUtil.IKeyBoardVisibleListener() {
            @Override
            public void onSoftKeyBoardVisible(boolean visible, int keyboardHeight) {
                isType = visible;
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                viewPager.setVisibility(View.GONE);
                drop_lin.setVisibility(View.GONE);
                em_kt.setImageResource(R.drawable.expression);
            }
        });
    }

    private void initRound() {
        for (int i = 0; i < 6; i++) {
            ImageView imageview = new ImageView(getActivity());
            imageview.setPadding(5, 5, 5, 5);
            imageview.setLayoutParams(new LinearLayout.LayoutParams(30, 30));
            if (i == 0) {
                imageview.setImageResource(R.drawable.round_dot_true);
            } else {
                imageview.setImageResource(R.drawable.round_dot_false);
            }
            drop_lin.addView(imageview);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.em_kt:
                if (isType) {
                    HideKeyboard(content_edit);
                    viewPager.setVisibility(View.VISIBLE);
                    drop_lin.setVisibility(View.VISIBLE);
                    em_kt.setImageResource(R.drawable.keyset);
                } else {
                    viewPager.setVisibility(View.GONE);
                    drop_lin.setVisibility(View.GONE);
                    showInputMethod(getActivity(), content_edit);
                    em_kt.setImageResource(R.drawable.expression);
                }
                break;
            case R.id.map_depot:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                getActivity().startActivityForResult(intent, 0x0008);
                break;
        }
    }

    public static void HideKeyboard(EditText v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public static void showInputMethod(Context context, EditText view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
    }

    private String id;
    private int position;

    public void setId(String id, int postition) {
        this.id = id;
        this.position = postition;
    }

    private void Reply_Criticism() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tid", id);
            if (biturl != null) {
                jsonObject.put("img", biturl);
            }
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("content", content_edit.getText().toString());
            jsonObject.put("token", potting.getItemString("token"));
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.COMMENT, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String baseBean) {
                    if (baseBean != null) {
                        switch (JsonUtil.getString(baseBean,"code")) {
                            case "0":
                                if (comments!=null)
                                {
                                    PapanewsDiscussData data = (PapanewsDiscussData) JsonUtil.fromJson(baseBean, PapanewsDiscussData.class);
                                    PapanewsDiscussData.DataBean dataBean = data.getData();
                                    ShowNewsBigBean.Data.Comments data1 = new ShowNewsBigBean.Data.Comments(dataBean.getId(),dataBean.getUid()+"",dataBean.getNickname(),dataBean.getContent(),dataBean.getImg());
                                    comments.add(0,data1);
                                    papanewsInterface.notifyItem(position);
                                }else {
                                    EventBus.getDefault().post("fresh");
                                }
                                ToastShow("评论成功");
                                content_edit.setText("");
                                break;
                            case "-1":
                                ToastShow("您尚未登录，无法评论");
                                break;
                            case "-2":
                                ToastShow("您的账号已失效，请重新登录");
                                break;
                        }
                        dispatch_news.setFocusable(true);
                        Hide();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String cid;
    private String touid;
    private String tid;
    private String rid;

    public void setCid(String cid, String touid, String tid) {
        this.cid = cid;
        this.touid = touid;
        this.tid = tid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    private void Criticism() {
        try {
            JSONObject jsonObject = new JSONObject();
            if (rid != null) {
                jsonObject.put("rid", rid);
            }
            jsonObject.put("cid", cid);
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("touid", touid);
            jsonObject.put("tid", tid);
            jsonObject.put("content", content_edit.getText().toString());
            jsonObject.put("token", potting.getItemString("token"));
            jsonObject.put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.REPLY, jsonObject.toString(), new Subscriber<BaseBean>() {
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
                            content_edit.setText("");
                            papanewsInterface.notifyItem();
                            ToastShow("评论成功");
                            break;
                        case "-1":
                            ToastShow("您尚未登录，无法评论");
                            break;
                        case "-2":
                            ToastShow("您的登录已失效");
                            break;
                    }
                    dispatch_news.setFocusable(true);
                    Hide();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}