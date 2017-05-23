package xm.ppq.papaquan.View.Life;

import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 18:14.
 */

public class Canvass_Business extends BaseActivity {

    @BindView(R.id.area)
    EditText area;
    @BindView(R.id.linkman)
    EditText linkman;
    @BindView(R.id.phone)
    EditText phone;

    @Override
    protected int getLayout() {
        return R.layout.canvass_business;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected void setListener() {

    }

    public void tijiao(View view) {
        if (area.getText().toString().equals("")) {
            ToastShow("区域不能为空");
        } else {
            if (linkman.getText().toString().equals("")) {
                ToastShow("代理人不能为空");
            } else {
                if (phone.getText().toString().equals("")) {
                    ToastShow("手机号码不能为空");
                } else {
                    JsonTool jsonTool = new JsonTool();
                    jsonTool.put_key("tel", "name", "area")
                            .put_value(phone.getText().toString(), linkman.getText().toString(), area.getText().toString());
                    OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.JOINUS, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
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
                                    ToastShow("申请成功，请静待佳音");
                                    finish();
                                } else {
                                    ToastShow(baseBean.getInfo());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}