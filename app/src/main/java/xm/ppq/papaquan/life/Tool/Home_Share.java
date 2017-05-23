package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.base.PapanewsInterface;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.accusation.AccusationActivity;

/**
 * Created by EdgeDi on 16:46.
 */

public class Home_Share extends PopupWindowPotting implements View.OnClickListener {

    private LinearLayout cancel_attention, go_home, report, delect;
    private int uid;
    private int Tid;
    private int TopicUid;
    private String name;
    private SharedPreferencesPotting potting;
    private PapanewsInterface Interface;
    private int Visibility;
    private String head_url;
    private String title;

    public Home_Share(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayout() {
        return R.layout.home_share;
    }

    public void setInterface(PapanewsInterface anInterface) {
        Interface = anInterface;
    }

    public Home_Share setAll(int uid, int Tid, String name, int Visibility) {
        this.uid = uid;
        this.Tid = Tid;
        this.TopicUid = uid;
        this.name = name;
        this.Visibility = Visibility;
        if (Visibility == View.VISIBLE) {
            cancel_attention.setVisibility(View.GONE);
        } else {
            cancel_attention.setVisibility(View.VISIBLE);
        }
        if (uid == potting.getItemInt("uid")) {
            cancel_attention.setVisibility(View.GONE);
            delect.setVisibility(View.VISIBLE);
            report.setVisibility(View.GONE);
            go_home.setVisibility(View.GONE);
        } else {
            go_home.setVisibility(View.VISIBLE);
            delect.setVisibility(View.GONE);
            report.setVisibility(View.VISIBLE);
        }
        if (uid == potting.getItemInt("AdminId")) {
            delect.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public void setShare(String head_url, String title) {
        this.head_url = head_url;
        this.title = title;
    }

    private LinearLayout wx_share;
    private LinearLayout qf_share;
    private LinearLayout qq_share;
    private LinearLayout qzone_share;

    @Override
    protected void initUI() {
        cancel_attention = Bind(R.id.cancel_attention);
        go_home = Bind(R.id.go_home);
        report = Bind(R.id.report);
        delect = Bind(R.id.delect);
        wx_share = Bind(R.id.wx_share);
        qf_share = Bind(R.id.qf_share);
        qq_share = Bind(R.id.qq_share);
        qzone_share = Bind(R.id.qzone_share);
        wx_share.setOnClickListener(v -> {
            ShareAndPay.WxShare(0, getActivity(), BaseUrl.URL + "index/index/tpdetails?id=" + Tid + "&city=" + BaseUrl.citycode, title, head_url);
            potting.setItemString("result", "分享")
                    .build();
        });
        qf_share.setOnClickListener(v -> {
            ShareAndPay.WxShare(1, getActivity(), BaseUrl.URL + "index/index/tpdetails?id=" + Tid + "&city=" + BaseUrl.citycode, title, head_url);
            potting.setItemString("result", "分享")
                    .build();
        });
        qq_share.setOnClickListener(v -> {
            ShareAndPay.TXShare(Tencent.createInstance(ShareAndPay.APP_ID, getActivity()), getActivity(), BaseUrl.URL + "index/index/tpdetails?id=" + Tid + "&city=" + BaseUrl.citycode, title, head_url, 1);
        });
        qzone_share.setOnClickListener(v -> {
            ShareAndPay.TXShare(Tencent.createInstance(ShareAndPay.APP_ID, getActivity()), getActivity(), BaseUrl.URL + "index/index/tpdetails?id=" + Tid + "&city=" + BaseUrl.citycode, title, head_url, 0);
        });
        potting = new SharedPreferencesPotting(getActivity(), "my_login");
    }

    @Override
    protected void setListener() {
        cancel_attention.setOnClickListener(this);
        go_home.setOnClickListener(this);
        report.setOnClickListener(this);
        delect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_attention://取消关注
                if (Visibility == View.GONE) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("uidtwo", TopicUid);
                        jsonObject.put("uidone", potting.getItemInt("uid"));
                        jsonObject.put("type", "1");
                        jsonObject.put("token", potting.getItemString("token"));
                        jsonObject.put("uid", potting.getItemInt("uid"));
                        jsonObject.put("tokentype", 1);
                        start(BaseUrl.DELSUB, jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastShow("您尚未关注此人");
                }
                break;
            case R.id.go_home://屏蔽TA
                if (Visibility == View.GONE) {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("uidtwo", TopicUid);
                        jsonObject.put("uidone", potting.getItemInt("uid"));
                        jsonObject.put("type", "1");
                        jsonObject.put("token", potting.getItemString("token"));
                        jsonObject.put("uid", potting.getItemInt("uid"));
                        jsonObject.put("tokentype", 1);
                        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.DELSUB, jsonObject.toString(), new Subscriber<BaseBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(BaseBean baseBean) {
                                if (baseBean.getCode().equals("0")) {
                                    JsonTool jsonTool = new JsonTool();
                                    jsonTool.put_key("type", "uidone", "uidtwo", "token", "uid", "tokentype")
                                            .put_value(2, potting.getItemInt("uid"), uid, potting.getItemString("token"), potting.getItemInt("uid"), 1);
                                    start(BaseUrl.DOSUB, jsonTool.getJson());
                                } else {
                                    ToastShow(baseBean.getInfo());
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    JsonTool jsonTool = new JsonTool();
                    jsonTool.put_key("type", "uidone", "uidtwo", "token", "uid", "tokentype")
                            .put_value(2, potting.getItemInt("uid"), uid, potting.getItemString("token"), potting.getItemInt("uid"), 1);
                    start(BaseUrl.DOSUB, jsonTool.getJson());
                }
                break;
            case R.id.report://举报
                Intent intent = new Intent(getActivity(), AccusationActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("tid", String.valueOf(Tid));
                intent.putExtra("uid", String.valueOf(TopicUid));
                getActivity().startActivity(intent);
                Hide();
                break;
            case R.id.delect://删除
                if (uid == potting.getItemInt("uid")) {
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
                }
                break;
        }
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
                    switch (baseBean.getCode()) {
                        case "0":
                            ToastShow("成功");
                            if (Interface != null) {
                                Interface.notifyItem();
                            }
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
}