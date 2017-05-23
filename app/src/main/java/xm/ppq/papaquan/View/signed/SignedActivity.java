package xm.ppq.papaquan.View.signed;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.lib_sunshaobei2017.widget.ListView4ScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.ShowNewsBigBean;
import xm.ppq.papaquan.Bean.life.CalendarData;
import xm.ppq.papaquan.Bean.life.SignedGiftInfo;
import xm.ppq.papaquan.Bean.life.SignedInfo;
import xm.ppq.papaquan.Bean.life.SignedViewFlipperData;
import xm.ppq.papaquan.Presenter.he_sheet.Mutual_HeSheet;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.customview.CalendarView.customview.CustomCalendarView;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.tasheet.Round_HeSheet;

public class SignedActivity extends BaseActivity implements Round_HeSheet {

    @BindView(R.id.bar)
    LinearLayout statusbar;
    @BindView(R.id.finish)
    ImageView finish;
    @BindView(R.id.backtohome)
    ImageView backtohome;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.tv_signed)
    TextView signed;
    @BindView(R.id.showrule)
    TextView showrule;
    @BindView(R.id.viewflipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.firstsigned)
    TextView firstsigned;
    @BindView(R.id.followhim)
    TextView followhim;
    @BindView(R.id.serialsigned)
    TextView serialsigned;
    @BindView(R.id.totalsigned)
    TextView totalsigned;
    @BindView(R.id.listview)
    ListView4ScrollView listview;
    @BindView(R.id.calendarView)
    CustomCalendarView calendarView;

    private SuccessDialog successDialog;
    private SharedPreferencesPotting my_login;
    private Mutual_HeSheet mutual_heSheet;
    private GiftAdapter giftAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_signed;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusbar);
        initListener();
        successDialog = new SuccessDialog();
        my_login = new SharedPreferencesPotting(this, "my_login");
        mutual_heSheet = new Mutual_HeSheet(this);
        inidfirstSigned();//签到的信息
        initViewflipper();
        initGiftData();
        initCalendarview();
    }

    private void initListener() {
        calendarView.setOnChangYearMonthListener(() -> {
            initCalendarview();
        });
    }

    private String strday;
    private String strmonth;
    private ArrayList<String> selectList = new ArrayList<>();

    private void initCalendarview() {
        int currentYear = calendarView.getCurrentYear();
        int currentMonth = calendarView.getCurrentMonth() + 1;
        JSONObject json = new JsonTool().put_key("uid", "token", "tokentype", "yearmonth").put_value(my_login.getItemInt("uid"), my_login.getItemString("token"), 1, currentYear + "-" + currentMonth).getJson();
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("User/getSignmonth", json.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String o) {
                if (JsonUtil.getString(o, "code").equals("0")) {
                    CalendarData data = (CalendarData) JsonUtil.fromJson(o, CalendarData.class);
                    List<CalendarData.DataBean> calendarList = data.getData();
                    selectList.clear();
                    for (int i = 0; i < calendarList.size(); i++) {
                        CalendarData.DataBean dataBean = calendarList.get(i);
                        int day = dataBean.getDay();

                        if (dataBean.getSign() == 1) {
                            if (day < 10)
                                strday = "-0" + day;
                            else strday = "-" + day;
                            if (currentMonth < 10)
                                strmonth = "-0" + currentMonth;
                            else strmonth = "-" + currentMonth;
                            selectList.add(currentYear + strmonth + strday);
                        }
                    }
                    calendarView.setSelect(selectList);
                }
            }
        });

    }

    private ArrayList<SignedGiftInfo.DataBean> list = new ArrayList<>();

    private void initGiftData() {
        giftAdapter = new GiftAdapter(this, list, R.layout.item_signedgit);
        listview.setAdapter(giftAdapter);
        getGifData();
    }

    private void getGifData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", my_login.getItemString("tokentype"));
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("User/getprize", jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        SignedGiftInfo data = (SignedGiftInfo) JsonUtil.fromJson(o, SignedGiftInfo.class);
                        List<SignedGiftInfo.DataBean> data1 = data.getData();
                        list.clear();
                        list.addAll(data1);
                        giftAdapter.notifyDataSetChanged();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initViewflipper() {
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("User/newsign", "", new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String o) {
                if (JsonUtil.getString(o, "code").equals("0")) {
                    SignedViewFlipperData data = (SignedViewFlipperData) JsonUtil.fromJson(o, SignedViewFlipperData.class);
                    List<SignedViewFlipperData.DataBean> data1 = data.getData();
                    if (data1 != null && data1.size() > 0) {
                        for (int i = 0; i < data1.size(); i++) {
                            TextView tv = new TextView(SignedActivity.this);
                            tv.setText(data1.get(i).getNickname() + "  刚刚签到已累计" + data1.get(i).getTotalSign() + "天");
                            tv.setTextColor(Color.parseColor("#e60012"));
                            tv.setTextSize(16);
                            tv.setWidth(700);
                            tv.setMaxLines(1);
                            tv.setEllipsize(TextUtils.TruncateAt.END);
                            viewFlipper.addView(tv);
                        }
                        viewFlipper.setInAnimation(SignedActivity.this, R.anim.viewflipper_in);
                        viewFlipper.setOutAnimation(SignedActivity.this, R.anim.viewflipper_out);
                        viewFlipper.startFlipping();
                    }
                }
            }
        });
    }

    private int isSign;

    private void inidfirstSigned() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne("User/getCountsign", jsonObject.toString(), new Subscriber<String>() {


                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        SignedInfo data = (SignedInfo) JsonUtil.fromJson(o, SignedInfo.class);
                        SignedInfo.DataBean data1 = data.getData();
                        totalsigned.setText("已累计签到" + data1.getTotalSign() + "天");
                        serialsigned.setText("已连续签到" + data1.getContinueSign() + "天");
                        firstsigned.setText("今日首位签到：" + data1.getFirstSign().getNickname());
                        isSign = data1.getIsSign();
                        if (isSign == 1)
                            signed.setText("\t已签到\t");
                        else signed.setText("签到+5金币");
                        if (data1.getFirstSign().getFocus() == 0)
                            followhim.setText("关注");
                        else followhim.setText("已关注");
                        followhim.setOnClickListener(v -> {
                            if (followhim.getText().toString().equals("关注")) {
                                mutual_heSheet.Finish_F_F(BaseUrl.DOSUB, String.valueOf(data1.getFirstSign().getUid()));
                            } else if (followhim.getText().toString().equals("已关注")) {
                                mutual_heSheet.Finish_F_F(BaseUrl.DELSUB, String.valueOf(data1.getFirstSign().getUid()));
                            }
                        });
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setListener() {
        finish.setOnClickListener(v -> {
            finish();
        });
        share.setOnClickListener(v -> {
            new ShareDialog(SignedActivity.this, R.layout.deteil_share).Show(share);
        });
        backtohome.setOnClickListener(v -> {
            setResult(IntentCode.ResultCode.BACKTOMINE);
            finish();
        });
        signed.setOnClickListener(v -> {
            if (isSign == 0) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", my_login.getItemInt("uid"));
                    jsonObject.put("token", my_login.getItemString("token"));
                    jsonObject.put("tokentype", "1");
                    jsonObject.put("citycode", my_login.getItemString("citycode"));
                    OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SGIN_HOME, jsonObject.toString(), new Subscriber<String>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(String s) {
                            String code = JsonUtil.getString(s, "code");
                            switch (code) {
                                case "0":
                                    signed.setText("\t已签到\t");
                                    isSign = 1;
                                    if (!successDialog.isAdded()) {
                                        successDialog.show(getFragmentManager(), "signed");
                                        initCalendarview();
                                    }
                                    break;
                                case "2":
                                    ToastShow(JsonUtil.getString(s, "info"));
                                    break;
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                signed.setText("\t已签到\t");
            }
        });
        //TODO forward
        successDialog.setOnSureClickListener(() -> {

        });

        showrule.setOnClickListener(v -> {
            if (!successDialog.isAdded())
                successDialog.show(getFragmentManager(), "rule");
        });
    }


    public void Success() {
        ToastShow("领取成功");
        getGifData();
        if (!successDialog.isAdded())
            successDialog.show(getFragmentManager(), "receive");
    }


    @Override
    public int getUid() {
        return my_login.getItemInt("uid");
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public String getToken() {
        return my_login.getItemString("token");
    }

    @Override
    public void setNickName(String nickName) {

    }

    @Override
    public void setHeadUrl(String headUrl) {

    }

    @Override
    public void setT_F_F(String topic, String Follow, String Follower) {

    }

    @Override
    public void setSignature(String signature) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setVip_End(long end) {

    }

    @Override
    public void setSexIcon(String sexIcon) {

    }

    @Override
    public void IsFollow(String follow) {
        if (follow.equals("0")) {
            followhim.setText("关注");
            ToastShow("已取消关注");
        } else if (follow.equals("1")) {
            followhim.setText("已关注");
            ToastShow("关注成功");
        }
    }
    @Override
    public void setToast(String result) {
    }

    @Override
    public void setLevel(int level) {

    }

    @Override
    public void setData(ArrayList<ShowNewsBigBean.Data> list, int type) {

    }

    @Override
    public void setCreateTime(String createTime) {

    }
}
