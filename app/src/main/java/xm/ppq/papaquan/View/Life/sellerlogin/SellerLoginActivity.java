package xm.ppq.papaquan.View.Life.sellerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lib_sunshaobei2017.app.SunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.Life.sellerregist.SellerRegistActivity;

public class SellerLoginActivity extends SunActivity {


    @BindView(R.id.bar)
    LinearLayout bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_login);
        ButterKnife.bind(this);
        initStatusBar(bar);
    }





    /**************************onClick*****************************/
    public void finish(View v)
    {
        finish();
    }
    public void share(View v)
    {
        //TODO
    }
    //确认登录
    public void loginsure(View v)
    {
        finish();
    }
    public void goregist(View v)
    {
        startActivityForResult(new Intent(this, SellerRegistActivity.class), IntentCode.RequestCode.TOSELLERREGIST);
    }


    //onactivityresult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TOSELLERREGIST&&resultCode == IntentCode.ResultCode.BACKTOSELLERLOGIN)
        {
            //TODO
        }
    }
}
