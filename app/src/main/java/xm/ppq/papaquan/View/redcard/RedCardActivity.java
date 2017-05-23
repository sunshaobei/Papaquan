package xm.ppq.papaquan.View.redcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.widget.ScrollViewExtend;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.RedCardAdapter;
import xm.ppq.papaquan.Bean.RedCardVipBean;
import xm.ppq.papaquan.Bean.life.RedcardData;
import xm.ppq.papaquan.Bean.life.RedcardPartshopData;
import xm.ppq.papaquan.Presenter.redcard.Mutual_RedCard;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.KtVipTextView;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.Tool.shownews.NoScrollGridView;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.invite_friend.Invite_FriendActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.selleroof.SellerOofActivity;
import xm.ppq.papaquan.View.purchase_red.PurchaseRedActivity;

/**
 * Created by 我的红卡 on 2017/4/10.
 */

public class RedCardActivity extends BaseActivity implements Round_RedCard {

    @BindView(R.id.red_grid_vip)
    NoScrollGridView red_grid_vip;
    @BindView(R.id.finish_image)
    ImageView finish_image;
    @BindView(R.id.shop_show_grid)
    NoScrollGridView shop_show_grid;
    @BindView(R.id.cardinfo)
    LinearLayout cardinfo;
    @BindView(R.id.buy_card)
    TextView buyCard;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.year_kt_text)
    KtVipTextView yearkttext;
    @BindView(R.id.year_half_kt_text)
    KtVipTextView yearHalfKtText;
    @BindView(R.id.year_moon_text)
    KtVipTextView yearMoonText;
    @BindView(R.id.year_circuit_text)
    KtVipTextView yearCircuitText;
    @BindView(R.id.year_money)
    TextView yearMoney;
    @BindView(R.id.year_red)
    TextView yearRed;
    @BindView(R.id.year_half_red)
    TextView yearHalfRed;
    @BindView(R.id.year_half_money)
    TextView yearHalfMoney;
    @BindView(R.id.moon_money)
    TextView moonMoney;
    @BindView(R.id.moon_red)
    TextView moonRed;
    @BindView(R.id.circuit_money)
    TextView circuitMoney;
    @BindView(R.id.cardnum)
    TextView cardnum;
    @BindView(R.id.deadtime)
    TextView deadtime;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.scrollview)
    ScrollView scrollView;
    @BindView(R.id.red_background)
    ImageView red_background;
    @BindView(R.id.red_code_edit)
    EditText red_code_edit;

    private RedCardAdapter adapter;
    private Mutual_RedCard mutual_redCard;
    private RedCarePartShopAdapter redCarePartShopAdapter;
    private ShareDialog shareDialog;
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_redcard;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        shareDialog = new ShareDialog(this, R.layout.deteil_share);
        url = "index/user/myredcard?city=" + BaseUrl.citycode + "&uid=" + potting.getItemInt("uid");
        shareDialog.setStatus("我五", url, BaseUrl.Image_url);
        redCarePartShopAdapter = new RedCarePartShopAdapter(this, list, R.layout.shop_show_item);
        shop_show_grid.setAdapter(redCarePartShopAdapter);
        mutual_redCard = new Mutual_RedCard(this);
        mutual_redCard.startVip();
        mutual_redCard.startShop();
        mutual_redCard.getData();
        TextView_Delete(yearRed, yearHalfRed, moonRed);
    }

    @Override
    protected void setListener() {
        finish_image.setOnClickListener(v -> finish());
        buyCard.setOnClickListener(v -> {
            scrollView.scrollTo(0, view1.getHeight() + view2.getHeight());
        });
        shop_show_grid.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent1 = new Intent(RedCardActivity.this, Merchant_HomepageActivity.class);
            intent1.putExtra("sid", String.valueOf(list.get(position).getSid()));
            startActivity(intent1);
        });
    }

    @Override
    public void setList(ArrayList<RedCardVipBean> list) {
        if (adapter == null) {
            adapter = new RedCardAdapter(this, list, R.layout.red_vip_item);
            adapter.setType(0);
            red_grid_vip.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    private ArrayList<RedcardPartshopData.DataBean> list = new ArrayList<>();

    @Override
    public void setShowList(RedcardPartshopData data) {
        list.clear();
        list.addAll(data.getData());
        redCarePartShopAdapter.notifyDataSetChanged();
    }

    @BindView(R.id.have_num)
    TextView have_num;
    @BindView(R.id.add_money)
    TextView add_money;
    @BindView(R.id.invite_code)
    TextView invite_code;

    @Override
    public void setData(RedcardData dataBean) {
        invite_code.setText(dataBean.getData().getMyinvitation());
        have_num.setText(dataBean.getData().getInvitationNum());
        add_money.setText(dataBean.getData().getInvitationMoney() != null ? dataBean.getData().getInvitationMoney() : "0");
        RedcardData.DataBean data = dataBean.getData();
        if (data.getIsopen() == 0) {
            red_background.setImageResource(R.mipmap.redcardgray);
            cardnum.setText(data.getCardnum());
            buyCard.setVisibility(View.VISIBLE);
        } else {
            red_background.setImageResource(R.drawable.red_card_double);
            buyCard.setVisibility(View.INVISIBLE);
            cardnum.setText(data.getCardnum());
            deadtime.setText("有效期至 " + data.getVipmsg());
        }
        ImageLoading.Circular(this, data.getHeadurl(), R.drawable.default_icon, icon);
        List<RedcardData.DataBean.RedBean> red = data.getRed();
        RedcardData.DataBean.RedBean redBean0 = red.get(0);
        yearMoney.setText("￥ " + redBean0.getMoney());
        yearRed.setText(redBean0.getPrice() + "");
        int status0 = redBean0.getStatus();
        switch (status0) {
            case 1:
                yearkttext.setClicked(false);
                break;
            default:
                yearkttext.setClicked(true);
                yearkttext.setText("已售完");
        }
        RedcardData.DataBean.RedBean redBean1 = red.get(1);
        yearHalfMoney.setText("￥ " + redBean1.getMoney());
        yearHalfRed.setText(redBean1.getPrice() + "");
        int status1 = redBean1.getStatus();
        switch (status1) {
            case 1:
                yearHalfKtText.setClicked(false);
                break;
            default:
                yearHalfKtText.setClicked(true);
                yearHalfKtText.setText("已售完");
                break;
        }
        RedcardData.DataBean.RedBean redBean2 = red.get(2);
        moonMoney.setText("￥ " + redBean2.getMoney());
        moonRed.setText(redBean2.getPrice() + "");
        int status2 = redBean2.getStatus();
        switch (status2) {
            case 1:
                yearMoonText.setClicked(false);
                break;
            default:
                yearMoonText.setClicked(true);
                yearMoonText.setText("已售完");
                break;
        }
        RedcardData.DataBean.RedBean redBean3 = red.get(3);
        circuitMoney.setText("￥ " + redBean3.getMoney());
        int status3 = redBean3.getStatus();
        switch (status3) {
            case 1:
                yearCircuitText.setClicked(false);
                break;
            default:
                yearCircuitText.setClicked(true);
                yearCircuitText.setText("已售完");
                break;
        }
    }

    @OnClick({R.id.year_kt_text, R.id.year_half_kt_text, R.id.year_moon_text, R.id.year_circuit_text})
    public void RedCard(View view) {
        TextView textView = null;
        int type = 0;
        switch (view.getId()) {
            case R.id.year_kt_text:
                textView = yearMoney;
                type = 1;
                break;
            case R.id.year_half_kt_text:
                textView = yearHalfMoney;
                type = 2;
                break;
            case R.id.year_moon_text:
                textView = moonMoney;
                type = 3;
                break;
            case R.id.year_circuit_text:
                textView = circuitMoney;
                type = 4;
                break;
        }
        isSkip((KtVipTextView) view, textView, type);
    }

    private void isSkip(KtVipTextView kttextView, TextView textView, int type) {
        if (kttextView.isClicked() == false) {
            Intent intent = new Intent(this, PurchaseRedActivity.class);
            intent.putExtra("time", textView.getText().toString());
            intent.putExtra("type", type);
            startActivity(intent);
        }
    }

    /**************************
     * onClicke
     ********************************/
    public void backtohome(View v) {
        setResult(IntentCode.ResultCode.BACKTOLIFE);
        finish();
    }

    private String url;

    public void share(View view) {
        shareDialog.Show(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void invite(View view) {
        Skip(Invite_FriendActivity.class);
    }

    public void sensitize(View view) {
        if (red_code_edit.getText().toString().equals("")) {
            ToastShow("激活码不能为空");
        } else {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("uid", "token", "tokentype", "code")
                    .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.ACTIVATECODE, jsonTool.getJson().toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {

                }
            });
        }
    }
}
