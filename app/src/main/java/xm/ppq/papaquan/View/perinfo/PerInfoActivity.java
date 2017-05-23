package xm.ppq.papaquan.View.perinfo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.utils.JSONBuilder;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Presenter.perinfo.PerinfoPresenter;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.MakeDialog;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.SexSelect;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.Util;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.bindphone.BindPhoneActivity;
import xm.ppq.papaquan.View.city_search.CitySearchActivity;
import xm.ppq.papaquan.View.kidney_sign.KidneySignActivity;
import xm.ppq.papaquan.View.mineaddress.MineAddressActivity;
import xm.ppq.papaquan.View.ranksuggest.RankSuggestActivity;
import xm.ppq.papaquan.wxapi.WXEntryActivity;

/**
 * Created by Administrator on 2017/2/21.
 */

public class PerInfoActivity extends BaseActivity implements View.OnClickListener
        , MakeDialog.onFinishOnClickListener
        , MakeDialog.onConfimOnClickListener
        , Round_Perinfo
        , SexSelect.OnSelectListener
        , ALi_ossInterface {

    @BindView(R.id.make_nick_name)
    FrameLayout make_nick_name;
    @BindView(R.id.mine_address)
    FrameLayout mine_address;
    @BindView(R.id.result_nick)
    TextView result_nick;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.kid_sign)
    FrameLayout kid_sign;
    @BindView(R.id.sex_frame)
    FrameLayout sex_frame;
    @BindView(R.id.bar)
    LinearLayout statusBar;
    @BindView(R.id.bind_number)
    FrameLayout bind_number;
    @BindView(R.id.sex_info)
    TextView sex_info;
    @BindView(R.id.signature_info)
    TextView signature_info;
    @BindView(R.id.wx_info)
    TextView wx_info;
    @BindView(R.id.phone_info)
    TextView phone_info;
    @BindView(R.id.head_info)
    ImageView head_info;
    @BindView(R.id.head_icon_frame)
    FrameLayout head_icon_frame;
    @BindView(R.id.address_per_info)
    TextView address_per_info;
    @BindView(R.id.id_code)
    TextView id_code;
    @BindView(R.id.mine_level)
    TextView mine_level;
    @BindView(R.id.bind_wx)
    FrameLayout bind_wx;

    private MakeDialog dialog;
    private SexSelect sexSelect;
    private PerinfoPresenter perinfoPresenter;
    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_perinfo;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        dialog = new MakeDialog(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        sexSelect = new SexSelect(this, R.layout.sex_select);
        aLioss = new ALioss(this);
        aLioss.setAinterface(this);
        perinfoPresenter = new PerinfoPresenter(this);
        perinfoPresenter.AllInfo();
    }

    @Override
    protected void setListener() {
        make_nick_name.setOnClickListener(this);
        dialog.setOnConfimOnClickListener(this);
        dialog.setOnFinishOnClickListener(this);
        mine_address.setOnClickListener(this);
        finish_result.setOnClickListener(this);
        kid_sign.setOnClickListener(this);
        sex_frame.setOnClickListener(this);
        bind_number.setOnClickListener(this);
        sexSelect.setSelectListener(this);
        head_icon_frame.setOnClickListener(this);
        bind_wx.setOnClickListener(v -> {
            if (wx_info.getText().toString().equals("未绑定")) {
                IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.APP_ID, true);
                mApi.registerApp(WXEntryActivity.APP_ID);
                if (mApi != null && mApi.isWXAppInstalled()) {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test_neng";
                    mApi.sendReq(req);
                } else {
                    ToastShow("用户未安装微信");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make_nick_name:
                dialog.show();
                break;
            case R.id.mine_address:
                Skip(MineAddressActivity.class);
                break;
            case R.id.finish_result:
                finish();
                break;
            case R.id.kid_sign:
                Intent intent1 = new Intent(PerInfoActivity.this, KidneySignActivity.class);
                intent1.putExtra("signname", signature_info.getText().toString());
                startActivityForResult(intent1, 20);
                break;
            case R.id.sex_frame:
                sexSelect.Show(v);
                break;
            case R.id.bind_number:
                if (phone_info.getText().toString().isEmpty()) {
                    Skip(BindPhoneActivity.class);
                }
                break;
            case R.id.head_icon_frame:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
                break;
        }
    }

    private ALioss aLioss;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path;
        if (requestCode == 2) {
            if (data != null) {
                Uri uri = data.getData();
                path = Util.getPath(this, uri);
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    head_info.setImageBitmap(bitmap);
                    String s = OwnUtil.compressImage(path, path + "compress", 30);
                    aLioss.init(s, ".jpg", 2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (resultCode == 20) {
            perinfoPresenter.AllInfo();
        }
        if (resultCode == IntentCode.ResultCode.BACKTOMAKEADDRESS) {
            String code1 = data.getStringExtra("code1");
            String code2 = data.getStringExtra("code2");
            String code3 = data.getStringExtra("code3");
            if (!code2.equals(code1) && !code3.equals(code1) && !code2.equals(code3)) {
                if (code2.equals(""))
                    ChangeYouPosition(code1, code3, code2);
                else ChangeYouPosition(code1, code2, code3);
            } else if (code2.equals(code3)) {
                ChangeYouPosition(code1, code2, "");
            } else if (code1.equals(code3)) {
                ChangeYouPosition(code1, "", "");
            }
        }
    }

    @Override
    public void onconfim(View v) {
        String result = dialog.getNickName();
        if (result != null && !result.equals("")) {
            result_nick.setText(result);
            perinfoPresenter.ReviseN_S("nickname", result);
            dialog.Cancel();
        } else {
            ToastShow("昵称不能为空");
        }
    }

    @Override
    public void onfinish(View v) {
        dialog.Cancel();
    }

    @Override
    public void setNickname(String nickname) {
        result_nick.setText(nickname);
    }

    @Override
    public void setSex(String sex) {
        sex_info.setText(sex);
    }

    @Override
    public void setSignture(String signture) {
        signature_info.setText(signture);
    }

    @Override
    public void setPhone(String phone) {
        phone_info.setText(phone);
        potting.setItemString("tel", phone).build();
    }

    @Override
    public void setWX(String wx) {
        wx_info.setText(wx);
    }

    @Override
    public void setHeadUrl(String url) {
        ImageLoading.common(this, url, head_info, R.drawable.default_icon_zheng);
    }

    @Override
    public void setAddress(String address) {
        address_per_info.setText(address);
    }

    @Override
    public void setToast(String result) {
        ToastShow(result);
    }

    @Override
    public void setLevel(String level) {
        mine_level.setText(level);
    }

    @Override
    public void setUid(String uid) {
        id_code.setText(uid);
    }

    @Override
    public String getUid() {
        return potting.getItemInt("uid") + "";
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    /**
     * 1男2女
     *
     * @param v
     */
    @Override
    public void select(View v) {
        String sex = "1";
        switch (v.getId()) {
            case R.id.woman_select:
                sex = "2";
                sex_info.setText("女");
                break;
            case R.id.man_select:
                sex = "1";
                sex_info.setText("男");
                break;
        }
        perinfoPresenter.ReviseN_S("sex", sex);
        sexSelect.Hide();
    }

    @Override
    public void upImageSuccess(String url) {
        new Thread(() -> {
            ImageLoading.common(this, url, head_info, R.drawable.default_icon_zheng);
        });
        perinfoPresenter.ReviseN_S("headurl", url);
    }


    @Override
    public void upImageError(String s) {

    }

    @Override
    public void upVideoSuccess(String s) {

    }

    @Override
    public void upProgress(int progress) {

    }

    @Override
    public void setVideoPic(String url) {

    }

    public void make_address(View view) {
        Intent intent = new Intent(this, CitySearchActivity.class);
        intent.putExtra("action", 1);
        startActivityForResult(intent, IntentCode.RequestCode.TOCITYSEARCH);
    }

    //修改地区
    public void ChangeYouPosition(String province, String city, String distric) {
        String build = JSONBuilder.build("{\"province\": \"A\",\"city\": \"B\",\"area\": \"C\",\"uid\": \"D\",\"token\": \"E\",\"tokentype\": \"F\"}",
                province, city, distric, potting.getItemInt("uid") + "", potting.getItemString("token"), "1");
        String trim = build.trim();
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.EDITAREA, build, new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {
                if (o != null) {
                    String code = JsonUtil.getString(o, "code");
                    if (code.equals("0")) {
                        ToastShow("修改地区成功");
                        perinfoPresenter.AllInfo();
                    }
                }

            }
        });
    }

    public void level(View view) {
        startActivity(new Intent(this, RankSuggestActivity.class));
    }

}
