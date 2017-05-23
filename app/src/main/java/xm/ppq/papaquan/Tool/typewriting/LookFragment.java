package xm.ppq.papaquan.Tool.typewriting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.DataBean;
import xm.ppq.papaquan.Bean.ReplyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/2/27.
 */

public class LookFragment extends Fragment implements View.OnClickListener {

    private LinearLayout drop_lin;
    private TextView dispatch_news;
    private ViewPager viewpager;
    private ImageView map_depot, em_kt;
    private EditText content_edit;
    private View view;
    private LookPagerAdapter pageradapter;
    private int height;
    private TypewritingUtil typeutil;
    private EmotionGridView emotionGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_look, container, false);
        }
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
        setListener();
    }

    private void initUI() {
        content_edit = (EditText) view.findViewById(R.id.content_edit);
        em_kt = (ImageView) view.findViewById(R.id.em_kt);
        map_depot = (ImageView) view.findViewById(R.id.map_depot);
        drop_lin = (LinearLayout) view.findViewById(R.id.drop_lin);
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        dispatch_news = (TextView) view.findViewById(R.id.dispatch_news);
        pageradapter = new LookPagerAdapter(getContext());
        height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        typeutil = new TypewritingUtil(getActivity());
        emotionGridView = new EmotionGridView(getContext(), content_edit, height, pageradapter, viewpager);
        emotionGridView.a1(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height / 5));
        initRound();
    }

    private void setListener() {
        em_kt.setOnClickListener(this);
        map_depot.setOnClickListener(this);
        dispatch_news.setOnClickListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        content_edit.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN && isType) {
                viewpager.setVisibility(View.VISIBLE);
                drop_lin.setVisibility(View.VISIBLE);
                typeutil.Hide(v);
                em_kt.setImageResource(R.drawable.keyset);
                isType = false;
            } else {
                viewpager.setVisibility(View.GONE);
                drop_lin.setVisibility(View.GONE);
                typeutil.ShowType(content_edit);
                em_kt.setImageResource(R.drawable.expression);
                isType = true;
            }
            return false;
        });
    }

    /**
     * true则为显示表情
     * false则为显示键盘
     */
    private boolean isType = true;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dispatch_news:
                if (dataBean != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("uid", dataBean.getUid());
                        jsonObject.put("token", dataBean.getToken());
                        jsonObject.put("content", content_edit.getText().toString());
                        jsonObject.put("tid", dataBean.getTid());
                        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.COMMENT, jsonObject.toString(), new Subscriber<BaseBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                String result = "";
                                switch (baseBean.getCode()) {
                                    case "0":
                                        result = "评论成功";
                                        content_edit.setText("");
                                        break;
                                    case "-1":
                                        result = "您尚未登录，无法操作";
                                        break;
                                    case "-2":
                                        result = "你的账号已失效，请重新登录";
                                        break;
                                }
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        });
                        dataBean = null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (replyBean != null) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("uid", replyBean.getUid());
                        jsonObject.put("cid", replyBean.getCid());
                        jsonObject.put("content", replyBean.getContent());
                        jsonObject.put("token", replyBean.getToken());
                        jsonObject.put("tid", replyBean.getTid());
                        jsonObject.put("touid", replyBean.getTouid());
                        jsonObject.put("pid", replyBean.getPid());
                        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.REPLY, jsonObject.toString(), new Subscriber<BaseBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                String result = "";
                                switch (baseBean.getCode()) {
                                    case "0":
                                        result = "评论成功";
                                        content_edit.setText("");
                                        break;
                                    case "-1":
                                        result = "您尚未登录，无法操作";
                                        break;
                                    case "-2":
                                        result = "你的账号已失效，请重新登录";
                                        break;
                                }
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            }
                        });
                        replyBean = null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.em_kt:
                if (isType) {
                    viewpager.setVisibility(View.VISIBLE);
                    drop_lin.setVisibility(View.VISIBLE);
                    typeutil.Hide(v);
                    em_kt.setImageResource(R.drawable.keyset);
                    isType = false;
                } else {
                    viewpager.setVisibility(View.GONE);
                    drop_lin.setVisibility(View.GONE);
                    typeutil.ShowType(content_edit);
                    em_kt.setImageResource(R.drawable.expression);
                    isType = true;
                }
                break;
            case R.id.map_depot:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (data != null) {
                Uri uri = data.getData();
                Log.e("uri", uri.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initRound() {
        for (int i = 0; i < 6; i++) {
            ImageView imageview = new ImageView(getContext());
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

    private DataBean dataBean;
    private ReplyBean replyBean;

    @Subscribe
    public void OnResult(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Subscribe
    public void OnReply(ReplyBean replyBean) {
        this.replyBean = replyBean;
        if (replyBean != null) {
            content_edit.setHint("回复:" + replyBean.getName());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}