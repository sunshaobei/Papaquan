package xm.ppq.papaquan.View.Life.othersetting;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lib_sunshaobei2017.app.SunActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.life.OtherBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShSwitchView;

public class OtherSettingActivity extends SunActivity {

    private OtherBean otherBean;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.edit_money)
    EditText editMoney;
    @BindView(R.id.edit_time)
    EditText editTime;
    @BindView(R.id.sh_box)
    ShSwitchView shBox;
    @BindView(R.id.sh_car)
    ShSwitchView shCar;
    @BindView(R.id.sh_wifi)
    ShSwitchView shWifi;
    @BindView(R.id.sh_kt)
    ShSwitchView shKt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_setting);
        ButterKnife.bind(this);
        initStatusBar(bar);
        if (getIntent().getSerializableExtra("data") != null) {
            otherBean = (OtherBean) getIntent().getSerializableExtra("data");
            editMoney.setText(otherBean.getMoney());
            editTime.setText(otherBean.getTime());
            Status(shBox, otherBean.getBox());
            Status(shCar, otherBean.getCar());
            Status(shWifi, otherBean.getWifi());
            Status(shKt, otherBean.getConditioner());
        } else {
            otherBean = new OtherBean();
        }
    }

    private void Status(ShSwitchView view, int i) {
        if (i == 1) view.setOn(true);
        else view.setOn(false);
    }

    /***********
     * onClick
     *************************/
    public void finish(View v) {
        finish();
    }

    //提交
    public void commit(View v) {
        if (shBox.isOn() == false) otherBean.setBox(0);
        else otherBean.setBox(1);
        if (shKt.isOn() == false) otherBean.setConditioner(0);
        else otherBean.setConditioner(1);
        if (shWifi.isOn() == false) otherBean.setWifi(0);
        else otherBean.setWifi(1);
        if (shCar.isOn() == false) otherBean.setCar(0);
        else otherBean.setCar(1);
        otherBean.setTime(editTime.getText().toString());
        otherBean.setMoney(editMoney.getText().toString());
        EventBus.getDefault().post(otherBean);
        finish();
    }
}
