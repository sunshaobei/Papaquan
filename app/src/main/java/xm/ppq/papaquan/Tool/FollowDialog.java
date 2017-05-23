package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.FollowGridAdapter;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.FollowGridBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 快速关注dialog on 2017/3/1.
 */

public class FollowDialog extends Dialog implements View.OnClickListener {

    @BindView(R.id.follow_grid)
    GridView follow_grid;
    @BindView(R.id.follow_ta)
    TextView follow_ta;
    @BindView(R.id.for_a_batch)
    TextView for_a_batch;
    @BindView(R.id.follow_finish)
    ImageView follow_finish;

    private OkPotting okPotting;
    private List<FollowGridBean.Data> list;
    private FollowGridAdapter adapter;

    private Activity activity;

    public FollowDialog(Context context) {
        super(context);
    }

    public FollowDialog(Context context, int themeResId, Activity activity) {
        super(context, themeResId);
        okPotting = OkPotting.getInstance(BaseUrl.PAPA_URL);
        list = new ArrayList<>();
        this.activity = activity;
    }

    private String uid;
    private String token;

    public void setData(String uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_attention);
        ButterKnife.bind(this);
        setListener();
        Follow();
    }

    private void setListener() {
        follow_ta.setOnClickListener(v -> {
            String follow = "";
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect == true) {
                        follow = follow + list.get(i).uid + ",";
                    }
                }
                if (!follow.equals(""))
                    follow = follow.substring(0, follow.length() - 1);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("alluid", follow);
                    jsonObject.put("uid", uid);
                    jsonObject.put("token", token);
                    jsonObject.put("tokentype", "1");
                    okPotting.Ask(BaseUrl.SUBFOLLOW, jsonObject.toString(), new Subscriber<BaseBean>() {
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
                                    ToastShow("关注成功");
                                    EventBus.getDefault().post("关注");
                                    break;
                                case "-1":
                                    ToastShow("您尚未登录，无法使用此功能");
                                    break;
                                case "-2":
                                    ToastShow("您的登录已失效，请重新登录");
                                    break;
                                case "1":
                                    ToastShow("你已关注，请勿重复操作");
                                    break;
                            }
                            hide();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        for_a_batch.setOnClickListener(v -> Follow());
        follow_finish.setOnClickListener(v -> hide());
        follow_grid.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).isSelect) {
                list.get(position).isSelect = false;
                adapter.notifyDataSetChanged();
            } else {
                list.get(position).isSelect = true;
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void Follow() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", uid);
            jsonObject.put("token", token);
            jsonObject.put("tokentype", 1);
            okPotting.AskOne(BaseUrl.RANDSUB, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (list.size() > 0) {
                        if (adapter == null) {
                            adapter = new FollowGridAdapter(activity, list, R.layout.follow_grid_view_item);
                            follow_grid.setAdapter(adapter);
                        } else {
                            adapter.setList(list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    FollowGridBean bean = (FollowGridBean) JsonUtil.fromJson(s, FollowGridBean.class);
                    switch (bean.code) {
                        case "0":
                            list.clear();
                            list.addAll(bean.data);
                            break;
                        case "-1":
                            Toast.makeText(getContext(), "您尚未登录，无法操作", Toast.LENGTH_SHORT).show();
                            break;
                        case "-2":
                            Toast.makeText(getContext(), "账号登录已失效，请重新登录", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void ToastShow(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
    }
}
