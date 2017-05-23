package xm.ppq.papaquan.View.main.frame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.HomeMineBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BlurUtil;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.mine_tenant.TenantActivity;
import xm.ppq.papaquan.View.balance_detail.ExchangeHistoryActivity;
import xm.ppq.papaquan.View.follower_follow.Follower_FollowActivity;
import xm.ppq.papaquan.View.login.LoginActivity;
import xm.ppq.papaquan.View.main.MainActivity;
import xm.ppq.papaquan.View.mine_balance.Mine_BalanceActivity;
import xm.ppq.papaquan.View.mine_choice.Mine_ChoiceActivity;
import xm.ppq.papaquan.View.mine_integral.Mine_IntegralActivity;
import xm.ppq.papaquan.View.my_topic.MyTopicActivity;
import xm.ppq.papaquan.View.perinfo.PerInfoActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;
import xm.ppq.papaquan.View.scare_buying.Scare_buyingActivity;
import xm.ppq.papaquan.View.setup.SetUpActivity;
import xm.ppq.papaquan.View.signed.SignedActivity;


/**
 * Created by Administrator on 2017/2/17.
 */

public class MineFragLayout extends Fragment implements View.OnClickListener {

    @BindView(R.id.setting)
    LinearLayout setting;
    @BindView(R.id.icon_background)
    LinearLayout icon_background;
    @BindView(R.id.head_portrait)
    ImageView head_portrait;
    @BindView(R.id.vip_lin)
    LinearLayout vip_lin;
    @BindView(R.id.follow_text)
    TextView follow_text;
    @BindView(R.id.follower_text)
    TextView follower_text;
    @BindView(R.id.mask_text)
    TextView mask_text;
    @BindView(R.id.mine_bar)
    LinearLayout statusBar;
    @BindView(R.id.sign_in)
    TextView sign_in;
    @BindView(R.id.home_nickname)
    TextView home_nickname;
    @BindView(R.id.home_abstract)
    TextView home_abstract;
    @BindView(R.id.topic_home)
    TextView topic_home;
    @BindView(R.id.jifen)
    TextView jifen;
    @BindView(R.id.jinbi)
    TextView jinbi;
    @BindView(R.id.mine_level)
    ImageView mine_level;
    @BindView(R.id.mine_money_back)
    LinearLayout mine_money_back;
    @BindView(R.id.mine_red_card)
    LinearLayout mine_red_card;
    @BindView(R.id.scare_buying_lin)
    LinearLayout scare_buying_lin;
    @BindView(R.id.mine_choice)
    LinearLayout mine_choice;
    @BindView(R.id.tenant_lin)
    LinearLayout tenant_lin;
    @BindView(R.id.redcarddeadday)
    TextView redcarddeadday;
    @BindView(R.id.at_count)
    TextView atCount;
    @BindView(R.id.minepurachcount)
    FrameLayout minepurachcount;
    @BindView(R.id.minechoisecount)
    TextView minechoisecount;
    @BindView(R.id.minechoisecountlayout)
    FrameLayout minechoisecountlayout;
    @BindView(R.id.myexchangehistory)
    View myexchangehistory;

    private View view;
    private MainActivity activity;
    private OkPotting okPotting;
    private SharedPreferencesPotting potting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
            ButterKnife.bind(this, view);
            activity = (MainActivity) getActivity();
            activity.initStatusBar(statusBar);
            setListener();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        okPotting = OkPotting.getInstance(BaseUrl.PAPA_URL);
        if (!potting.getItemString("token").isEmpty()) {
            sign_in.setVisibility(View.VISIBLE);
            Gain();
        } else {
            sign_in.setVisibility(View.GONE);
            jifen.setText("");
            jinbi.setText("");
            topic_home.setText("0\n动态");
            follow_text.setText("0\n关注");
            follower_text.setText("0\n粉丝");
            mask_text.setText("0\n屏蔽");
            home_nickname.setText("请登陆");
            mine_level.setVisibility(View.GONE);
            home_abstract.setText("");
            head_portrait.setImageResource(R.drawable.default_icon);
        }
    }

    public void setListener() {
        tenant_lin.setOnClickListener(v -> {
            if (potting.isLogin()) {
                startActivityForResult(new Intent(getActivity(), TenantActivity.class), IntentCode.RequestCode.TOTENANT);
            } else {
                Toast.makeText(getContext(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        });
        mine_red_card.setOnClickListener(v -> {
            if (potting.isLogin()) {
                startActivityForResult(new Intent(getActivity(), RedCardActivity.class), IntentCode.RequestCode.TOREDCARD);
            } else {
                Toast.makeText(getContext(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        });
        mine_choice.setOnClickListener(v -> {
            if (potting.isLogin()) {
                startActivityForResult(new Intent(getActivity(), Mine_ChoiceActivity.class), IntentCode.RequestCode.TOMINECHOISE);
            } else {
                Toast.makeText(getContext(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        });
        scare_buying_lin.setOnClickListener(v -> {
            if (potting.isLogin()) {
                startActivityForResult(new Intent(getActivity(), Scare_buyingActivity.class), IntentCode.RequestCode.TOSCAREBUYING);
            } else {
                Toast.makeText(getContext(), "您尚未登录", Toast.LENGTH_SHORT).show();
            }
        });
        //我的兑换
        myexchangehistory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ExchangeHistoryActivity.class));
        });
        setting.setOnClickListener(this);
        vip_lin.setOnClickListener(this);
        follow_text.setOnClickListener(this);
        follower_text.setOnClickListener(this);
        mask_text.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        head_portrait.setOnClickListener(this);
        topic_home.setOnClickListener(this);
        mine_money_back.setOnClickListener(this);
    }

    public void Skip(Class s) {
        startActivity(new Intent(getActivity(), s));
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.setting:
                intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
                break;
            case R.id.vip_lin:
                if (potting.isLogin()) {
                    intent = new Intent(getActivity(), Mine_IntegralActivity.class);
                    if (homeMineBean != null) {
                        if (homeMineBean.getData().getInfo().getGold() == 0) {
                            intent.putExtra("integer", 0);
                        } else {
                            intent.putExtra("integer", homeMineBean.getData().getInfo().getGold());
                        }
                        startActivityForResult(intent, IntentCode.RequestCode.TOINTEGEAL);
                        return;
                    }
                } else {
                    Toast.makeText(getContext(), "您尚未登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.follow_text:
                intent = new Intent(getActivity(), Follower_FollowActivity.class);
                intent.putExtra("type", "1");
                if (potting.isLogin())
                    startActivity(intent);
                else Toast.makeText(getActivity(), "您还未登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.follower_text:
                intent = new Intent(getActivity(), Follower_FollowActivity.class);
                intent.putExtra("type", "2");
                if (potting.isLogin())
                    startActivity(intent);
                else Toast.makeText(getActivity(), "您还未登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mask_text:
                intent = new Intent(getActivity(), Follower_FollowActivity.class);
                intent.putExtra("type", "3");
                if (potting.isLogin())
                    startActivity(intent);
                else Toast.makeText(getActivity(), "您还未登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.topic_home:
                intent = new Intent(getActivity(), MyTopicActivity.class);
                if (potting.isLogin())
                    startActivity(intent);
                else Toast.makeText(getActivity(), "您还未登陆", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_in:
                Intent intent1 = new Intent(getActivity(), SignedActivity.class);
                startActivity(intent1);
                break;
            case R.id.head_portrait:
                if (potting.isLogin()) {
                    intent = new Intent(getActivity(), PerInfoActivity.class);
                } else {
                    intent = new Intent(getActivity(), LoginActivity.class);
                }
                startActivity(intent);
                break;
            case R.id.mine_money_back:
                intent = new Intent(getActivity(), Mine_BalanceActivity.class);
                intent.putExtra("$", my_money);
                if (potting.isLogin())
                    startActivity(intent);
                else Toast.makeText(getActivity(), "您还未登陆", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean isSign = false;

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 4001) {
                icon_background.setBackground((Drawable) msg.obj);
            }
        }
    };

    private HomeMineBean homeMineBean;
    private double my_money = 0;

    private void Gain() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", potting.getItemInt("uid"));
            jsonObject.put("token", potting.getItemString("token"));
            jsonObject.put("tokentype", "1");
            okPotting.AskOne(BaseUrl.HOME_MINE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (homeMineBean != null) {
                        if (homeMineBean.getData().getInfo().getGold() == 0) {
                            jifen.setText("0金币");
                        } else {
                            jifen.setText(homeMineBean.getData().getInfo().getGold() + "金币");
                        }
                        my_money = Double.parseDouble(homeMineBean.getData().getInfo().getMoney());
                        jinbi.setText(homeMineBean.getData().getInfo().getMoney() + "元");
                        topic_home.setText(homeMineBean.getData().getTopic_num() + "\n动态");
                        mine_level.setVisibility(View.VISIBLE);
                        follow_text.setText(homeMineBean.getData().getFollow_num() + "\n关注");
                        follower_text.setText(homeMineBean.getData().getFans_num() + "\n粉丝");
                        mask_text.setText(homeMineBean.getData().getBlack_num() + "\n屏蔽");
                        mine_level.setImageResource(BaseUrl.INTEGERS[homeMineBean.getData().getInfo().getLevel()]);
                        home_nickname.setText(homeMineBean.getData().getInfo().getNickname());
                        if (homeMineBean.getData().getCoupon_num() == 0) {
                            minechoisecountlayout.setVisibility(View.INVISIBLE);
                        } else {
                            minechoisecountlayout.setVisibility(View.VISIBLE);
                            minechoisecount.setText(String.valueOf(homeMineBean.getData().getCoupon_num()));
                        }

                        long vip_end = homeMineBean.getData().getInfo().getVip_end();
                        int panic_num = homeMineBean.getData().getPanic_num();
                        if (panic_num == 0) {
                            minepurachcount.setVisibility(View.GONE);
                        } else {
                            atCount.setText(panic_num + "");
                        }
                        if (vip_end == 0) {
                            redcarddeadday.setVisibility(View.INVISIBLE);
                        } else {
                            long l = System.currentTimeMillis();
                            long l1 = vip_end * 1000l - l;
                            long l2 = l1 / 1000l / 24l / 3600l;
                            int day = (int) l2;
                            redcarddeadday.setText("剩余" + day + "天");
                        }
                        potting.setItemString("nickname", homeMineBean.getData().getInfo().getNickname())
                                .build();
                        ImageLoading.Circular(getActivity(), homeMineBean.getData().getInfo().getHeadurl(), R.drawable.default_icon, head_portrait);
                        if (homeMineBean.getData().getInfo().getSignature() != null) {
                            home_abstract.setText(homeMineBean.getData().getInfo().getSignature());
                        } else {
                            home_abstract.setText("简介:这家伙很懒，什么都没有留下!");
                        }
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                Bitmap bitmap = null;
                                try {
                                    bitmap = ImageLoading.getBitMap(getActivity(), homeMineBean.getData().getInfo().getHeadurl());
                                    Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(bitmap, 20, false));
                                    handler.obtainMessage(4001, drawable).sendToTarget();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        if (homeMineBean.getData().getIs_sign() == 1) {
                            isSign = true;
                            sign_in.setText("\t已签到\t");
                        } else {
                            isSign = false;
                            sign_in.setText("签到+5金币");
                        }
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        String s1 = JsonUtil.getString(s, "code");
                        switch (s1) {
                            case "0":
                                homeMineBean = (HomeMineBean) JsonUtil.fromJson(s, HomeMineBean.class);
                                break;
                            default:
                                String citycode = potting.getItemString("citycode");
                                String cityname = potting.getItemString("cityname");
                                potting.getClear();
                                potting.setItemString("citycode", citycode).build();
                                potting.setItemString("cityname", cityname).build();
                                break;
                        }

                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOMINE) {
            if (requestCode == IntentCode.RequestCode.TOSIGNED) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setCurrentItem(1);
            }
        } else if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            MainActivity activity = (MainActivity) getActivity();
            activity.setCurrentItem(1);
        }
    }
}
