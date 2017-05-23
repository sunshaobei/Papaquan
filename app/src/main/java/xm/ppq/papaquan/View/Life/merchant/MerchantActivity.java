package xm.ppq.papaquan.View.Life.merchant;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Bean.life.MerchantData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.account_setting.AccountSettingActivity;
import xm.ppq.papaquan.View.Life.agio_manage.AgioManageActivity;
import xm.ppq.papaquan.View.Life.cancel_pass_word.CancelPassWordActivity;
import xm.ppq.papaquan.View.Life.judge_of.JudgeOfActivity;
import xm.ppq.papaquan.View.Life.mer_info.MerInfoActivity;
import xm.ppq.papaquan.View.Life.merchant.M.ModelUtil;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.quick_audit.Quick_AuditActivity;
import xm.ppq.papaquan.View.Life.scare_manage.Scare_ManageActivity;
import xm.ppq.papaquan.View.Life.staff_manage.StaffManageActivity;
import xm.ppq.papaquan.View.Life.tenant_abstract.Tenant_AbstractActivity;
import xm.ppq.papaquan.View.Life.verification.VerificationActivity;
import xm.ppq.papaquan.View.Life.vipmanage.VipManageActivity;
import xm.ppq.papaquan.View.richedit.RichEditActivity;

/**
 * Created by 商户管理 on 2017/4/12.
 */

public class MerchantActivity extends BaseActivity implements Round_Merchant {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.right_image)
    ImageView right_image;
    @BindView(R.id.tenant_home_text)
    TextView tenant_home_text;
    @BindView(R.id.verification_text)
    TextView verification_text;
    @BindView(R.id.lin_jianjie)
    LinearLayout lin_jianjie;
    @BindView(R.id.quick_lin)
    LinearLayout quick_lin;
    @BindView(R.id.auditing_content)
    TextView auditing_content;
    @BindView(R.id.auditing_key)
    TextView auditing_key;
    @BindView(R.id.masterview1)
    View masterview1;
    @BindView(R.id.masterview2)
    View masterview2;
    @BindView(R.id.masterview3)
    View masterview3;
    @BindView(R.id.tel)
    TextView tel;

    private SharedPreferencesPotting potting;
    private String sid;
    private String type;

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        potting = new SharedPreferencesPotting(this, "my_login");
        right_image.setImageResource(R.mipmap.home);
        sid = String.valueOf(getIntent().getIntExtra("sid", 0));
        type = getData("type");
        if (type.equals("已审核")) {
            auditing_content.setText("您的店铺已通过审核，可以正常显示");
            auditing_key.setText("续费");
            tenant_home_text.setOnClickListener(v -> Skip(Merchant_HomepageActivity.class, "sid", sid));
        } else {
            auditing_content.setText("当前店铺尚未审核，无法正常显示");
            auditing_key.setText("快速审核");
            tenant_home_text.setOnClickListener(v -> ToastShow("您的店铺尚未审核"));
        }
        new ModelUtil(this).getData(sid);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        verification_text.setOnClickListener(v -> Skip(VerificationActivity.class, "sid", sid));
        lin_jianjie.setOnClickListener(v -> Skip(RichEditActivity.class, "sid", sid));
    }

    @OnClick({R.id.quick_lin, R.id.judge_manage, R.id.staff_manage_text, R.id.vip_manage_all, R.id.scare_text, R.id.agio_text, R.id.choice_text, R.id.pass_word_lin, R.id.account_lin, R.id.mer_info_lin, R.id.right_image})
    public void List(View view) {
        switch (view.getId()) {
            case R.id.vip_manage_all:
                Skip(VipManageActivity.class, "sid", sid);
                break;
            case R.id.scare_text:
                Skip(Scare_ManageActivity.class, "type", "抢购管理", "sid", sid);
                break;
            case R.id.agio_text:
                Skip(AgioManageActivity.class, "sid", sid);
                break;
            case R.id.choice_text:
                Skip(Scare_ManageActivity.class, "type", "精选商品", "sid", sid);
                break;
            case R.id.pass_word_lin:
                Skip(CancelPassWordActivity.class, "sid", sid);
                break;
            case R.id.account_lin:
                Skip(AccountSettingActivity.class);
                break;
            case R.id.mer_info_lin:
                Skip(MerInfoActivity.class, "title", "商户信息", "sid", sid);
                break;
            case R.id.staff_manage_text:
                Skip(StaffManageActivity.class, "sid", sid);
                break;
            case R.id.judge_manage:
                Skip(JudgeOfActivity.class, "sid", sid);
                break;
            case R.id.quick_lin:
                Skip(Quick_AuditActivity.class, "sid", sid, "citycode", citycode);
                break;
            case R.id.right_image:
                setResult(IntentCode.ResultCode.BACKTOLIFE);
                finish();
                break;
        }
    }

    private String citycode;

    @Override
    public void setData(MerchantData.DataBean data) {
        tel.setText("商务服务电话：" + data.getTel());
        int master = data.getMaster();
        if (master == 1) {
            masterview1.setVisibility(View.VISIBLE);
            masterview3.setVisibility(View.GONE);
            masterview2.setVisibility(View.VISIBLE);
        } else {
            masterview1.setVisibility(View.GONE);
            masterview2.setVisibility(View.GONE);
            masterview3.setVisibility(View.VISIBLE);
        }
        center_result.setText(data.getName());
        citycode = data.getCitycode();
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

}