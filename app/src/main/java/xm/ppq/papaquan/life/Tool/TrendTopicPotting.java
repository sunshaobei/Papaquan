package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.accusation.AccusationActivity;

/**
 * Created by EdgeDi on 18:42.
 */

public class TrendTopicPotting extends PopupWindowPotting {

    private TextView trend_reply;
    private TextView trend_tip;
    private TextView trend_delete;

    private SharedPreferencesPotting potting;

    private String id;
    private String com_id;
    private String tid;
    private String name;

    private OnReplyListener onReplyListener;
    private int position;

    public TrendTopicPotting(Activity activity) {
        super(activity);
        potting = new SharedPreferencesPotting(activity, "my_login");
        setStyle(R.style.wxpopupwindowanim);
    }

    public TrendTopicPotting setIdAll(String id, String com_id, String tid, String name) {
        this.id = id;
        this.com_id = com_id;
        this.name = name;
        this.tid = tid;
        if (!com_id.equals(String.valueOf(potting.getItemInt("uid"))))
            trend_delete.setVisibility(View.GONE);
        return this;
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        this.onReplyListener = onReplyListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.trend_topic;
    }

    @Override
    protected void initUI() {
        trend_reply = Bind(R.id.trend_reply);
        trend_tip = Bind(R.id.trend_tip);
        trend_delete = Bind(R.id.trend_delete);
    }

    @Override
    protected void setListener() {
        trend_reply.setOnClickListener(v -> {
            if (onReplyListener != null) {
                onReplyListener.reply(id, com_id, tid, view);
            }
        });
        trend_tip.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccusationActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("uid", com_id);
            intent.putExtra("tid", tid);
            intent.putExtra("commid", id);
            getActivity().startActivity(intent);
            Hide();
        });
        trend_delete.setOnClickListener(v -> {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "token", "tokentype", "tid", "commentid")
                    .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, tid, id);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.DELETECOMMIT, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                            ToastShow("删除成功");
                            Hide();
                            if (onReplyListener != null) onReplyListener.Renovate(position);
                        } else {
                            ToastShow(baseBean.getInfo());
                        }
                    }
                }
            });
        });
    }

    private View view;

    @Override
    public void Trend_Show(View view) {
        super.Trend_Show(view);
        this.view = view;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public interface OnReplyListener {
        void reply(String id, String com_id, String tid, View view);

        void Renovate(int position);
    }
}