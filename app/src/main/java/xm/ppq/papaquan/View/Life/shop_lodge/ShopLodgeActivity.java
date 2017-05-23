package xm.ppq.papaquan.View.Life.shop_lodge;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.ShopLodgeBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 15:15.
 */

public class ShopLodgeActivity extends BaseActivity implements LodgeInfo.OnLodgeInfo {

    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.shop_url)
    ImageView shop_url;
    @BindView(R.id.shop_title)
    TextView shop_title;
    @BindView(R.id.shop_content)
    TextView shop_content;
    @BindView(R.id.shop_tel)
    TextView shop_tel;
    @BindView(R.id.other_edit)
    EditText other_edit;
    @BindView(R.id.content_edit)
    EditText content_edit;
    @BindView(R.id.shop_group)
    RadioGroup shop_group;

    private String typeid;
    private String type;
    private ShopLodgeBean.DataBean dataBean;
    private LodgeInfo lodgeInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_lodge;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        center_result.setText("投诉");
        finish_result.setText("");
        type = getData("type");
        typeid = getData("typeid");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("type", "typeid")
                .put_value(type == null ? "" : type, typeid == null ? "" : typeid);
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETREPORT, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (dataBean != null) {
                    ImageLoading.common(getActivity(), BaseUrl.BITMAP + dataBean.getImg(), shop_url, R.mipmap.food);
                    shop_title.setText(dataBean.getShopname());
                    shop_content.setText(dataBean.getTitle());
                    shop_tel.setText("客户服务电话：" + dataBean.getKfphone());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    ShopLodgeBean shopLodgeBean = (ShopLodgeBean) JsonUtil.fromJson(s, ShopLodgeBean.class);
                    if (shopLodgeBean.getCode() == 0) {
                        dataBean = shopLodgeBean.getData();
                    }
                }
            }
        });
        lodgeInfo = new LodgeInfo(this);
    }

    private String reason = "";

    @Override
    protected void setListener() {
        other_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (string_type.equals("more")) {
                    reason = s.toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        finish_result.setOnClickListener(v -> finish());
        shop_group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.one_radio:
                    string_type = "";
                    reason = ((RadioButton) group.getChildAt(0)).getText().toString();
                    break;
                case R.id.two_radio:
                    string_type = "";
                    reason = ((RadioButton) group.getChildAt(2)).getText().toString();
                    break;
                case R.id.money_radio:
                    string_type = "";
                    reason = ((RadioButton) group.getChildAt(4)).getText().toString();
                    break;
                case R.id.more_radio:
                    string_type = "more";
                    break;
            }
        });
    }

    private String string_type = "";

    public void tijiao(View view) {
        if (!reason.equals("")) {
            lodgeInfo.getInfo();
        } else {
            ToastShow("原因不能为空");
        }
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getTypeId() {
        return typeid;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public String getContent() {
        return content_edit.getText().toString();
    }

    @Override
    public void Success() {
        ToastShow("投诉成功");
        finish();
    }
}
