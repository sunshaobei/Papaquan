package xm.ppq.papaquan.View.Life.sellerregist;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lib_sunshaobei2017.app.SunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;

public class SellerRegistActivity extends SunActivity {

    @BindView(R.id.bar)
    LinearLayout bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_regist);
        ButterKnife.bind(this);
        initStatusBar(bar);
    }






    /*****************w点击事件*********************/
    public void finish(View v)
    {
        finish();
    }
    public void share(View v)
    {
        //TODO
    }

    //注册成功
    public void registcomplete(View v)
    {
        setResult(IntentCode.ResultCode.BACKTOSELLERLOGIN);
        finish();
    }
}
