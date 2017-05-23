package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.accusation.AccusationActivity;

/**
 * Created by Administrator on 2017/3/1.
 */

public class UpMoreDialog implements View.OnClickListener {

    @BindView(R.id.up_finish)
    TextView up_finish;
    @BindView(R.id.accusation_text)
    TextView accusation_text;
    @BindView(R.id.share_up_more)
    TextView share_up_more;
    @BindView(R.id.cancel_delete)
    TextView cancel_delete;

    private View view;
    private PopupWindow window;
    private Context context;
    private String name;
    private OnUpMoreListener onUpMoreListener;
    private String Tid;
    private String TopicUid;
    private SharedPreferencesPotting potting;
    private String uid;
    private PapanewsInterface papanewsInterface;
    private View showview;

    public UpMoreDialog(Context context, int layout) {
        this.context = context;
        potting = new SharedPreferencesPotting(context, "my_login");
        uid = String.valueOf(potting.getItemInt("uid"));
        view = LayoutInflater.from(context).inflate(layout, null);
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ButterKnife.bind(this, view);
        initPopup();
        setListener();
    }

    public UpMoreDialog(Context context, int layout, View showview) {
        this.context = context;
        this.showview = showview;
        potting = new SharedPreferencesPotting(context, "my_login");
        uid = String.valueOf(potting.getItemInt("uid"));
        view = LayoutInflater.from(context).inflate(layout, null);
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        ButterKnife.bind(this, view);
        initPopup();
        setListener();
    }

    public UpMoreDialog setUid(String TopicUid) {
        this.TopicUid = TopicUid;
        return this;
    }

    public void setOnUpMoreListener(OnUpMoreListener onUpMoreListener) {
        this.onUpMoreListener = onUpMoreListener;
    }

    public void setPapanewsInterface(PapanewsInterface papanewsInterface) {
        this.papanewsInterface = papanewsInterface;
    }

    public UpMoreDialog setName(String name) {
        this.name = name;
        return this;
    }

    public UpMoreDialog setTid(String tid) {
        Tid = tid;
        return this;
    }

    private void setListener() {
        window.setOnDismissListener(() -> Hide());
        up_finish.setOnClickListener(v -> Hide());
        share_up_more.setOnClickListener(this);
        accusation_text.setOnClickListener(this);
        cancel_delete.setOnClickListener(v -> {
            if (uid.equals(TopicUid)) {//是本人发的id
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", uid);
                    jsonObject.put("tid", Tid);
                    jsonObject.put("token", potting.getItemString("token"));
                    jsonObject.put("tokentype", 1);
                    start(BaseUrl.DELECTTOPIC, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {//不是本人
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uidone", uid);
                    jsonObject.put("uidtwo", TopicUid);
                    jsonObject.put("type", "1");
                    jsonObject.put("token", potting.getItemString("token"));
                    jsonObject.put("uid", uid);
                    jsonObject.put("tokentype", 1);
                    start(BaseUrl.DELSUB, jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void start(String url, JSONObject jsonObject) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    Log.e("getcode", baseBean.getCode());
                    switch (baseBean.getCode()) {
                        case "0":
                            Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show();
                            papanewsInterface.notifyItem();
                            break;
                        case "-1":

                            break;
                        case "-2":

                            break;
                    }
                    Hide();
                }
            }
        });
    }

    private void initPopup() {
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
    }

    public void Show(View view) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 0.6f;
        ((Activity) context).getWindow().setAttributes(lp);
        window.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public void Hide() {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = 1f;
        ((Activity) context).getWindow().setAttributes(lp);
        window.dismiss();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.accusation_text) {
            Intent intent = new Intent(context, AccusationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("tid", Tid);
            intent.putExtra("uid", TopicUid);
            context.startActivity(intent);
            Hide();
        } else if (v.getId() == R.id.share_up_more) {
            if (showview != null)
                papanewsInterface.ShowShare(showview);
            else
                papanewsInterface.ShowShare(v);
//            new ShareDialog(context, R.layout.deteil_share).Show();
//            papanewsInterface.ShowShare(activityview);
//            Hide();
        }
    }

    public interface OnUpMoreListener {
        void UpMore(View v);
    }

}