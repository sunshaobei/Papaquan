package xm.ppq.papaquan.View.Life.tecenguide;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lib_sunshaobei2017.app.BaseActivity;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.net.URISyntaxException;

import butterknife.BindView;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.customview.LoadingView;

public class TenCentGuideActivity extends BaseActivity implements TencentLocationListener {


    @BindView(R.id.map)
    WebView webview;
    @BindView(R.id.loadingview)
    LoadingView loadingView;

    double lat;
    double lng;

    @Override
    public int oncreate() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_ten_cent_guide;
    }

    @Override
    public void initView() {
        initTencenLocationListener();
    }


    private TencentLocationManager instance;
    private TencentLocationRequest request;

    public void initTencenLocationListener() {
        instance = TencentLocationManager.getInstance(this);
        request = TencentLocationRequest.create();
        request.setInterval(1000).setAllowCache(true).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
        instance.requestLocationUpdates(request, this);
    }


    @Override
    public void initListener() {

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onresume() {

    }

    @Override
    public void onstop() {

    }

    @Override
    public void ondestroy() {

    }


    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        switch (i) {
            case TencentLocation.ERROR_OK:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double latitude = tencentLocation.getLatitude();
                        double longitude = tencentLocation.getLongitude();
                        Intent intent = getIntent();
                        String lats = intent.getStringExtra("lat");
                        String lngs = intent.getStringExtra("lng");
                        String address = intent.getStringExtra("address");

                        try {
                            lat = Double.valueOf(lats);
                            lng = Double.valueOf(lngs);
                        } catch (Exception e) {
                            Toast.makeText(TenCentGuideActivity.this, "商家地址有误，请电话联系商家询问地址", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        WebSettings webSettings = webview.getSettings();
                        //设置WebView属性，能够执行Javascript脚本
                        webSettings.setJavaScriptEnabled(true);
                        //设置可以访问文件
                        webSettings.setAllowFileAccess(true);
                        //设置支持缩放
                        webSettings.setBuiltInZoomControls(true);
//                        loading.setVisibility(View.GONE);
                        //加载需要显示的网页
                        String url = "http://apis.map.qq.com/tools/routeplan/" + "&spointx=" + longitude + "&spointy=" + latitude + "&eword=" + address + "&epointx=" + lng + "&epointy=" + lat + "&navimenu=百度" + "?referer=" + getResources().getString(R.string.app_name) + "&key=I2FBZ-P7LKJ-GP2FN-F5ZUI-SJGLZ-Y7BH7&backurl=http://qq.com/";
                        webview.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int newProgress) {
                                // TODO 自动生成的方法存根  
                                if (newProgress == 100) {
                                    loadingView.setVisibility(View.GONE);
                                } else {
                                }
                            }
                        });

                        webview.loadUrl(url);
                        //设置Web视图
                        webview.setWebViewClient(new webViewClient());
                    }
                });
                instance.removeUpdates(this);
                break;
            case TencentLocation.ERROR_NETWORK:
                break;
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl("http://"+url);
            if (url.startsWith("https://") || url.startsWith("http://")) {
                if (url.equals("http://qq.com/")) {
                    finish();
                } else {
                    view.loadUrl(url);
                }
            } else {
                Intent intent;
                try {
                    intent = Intent.parseUri(url, 0);
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return false;
                }
                if (intent.resolveActivity(getPackageManager()) != null) {
                    ComponentName name = intent.resolveActivity(getPackageManager());
                    startActivity(intent);
                } else {
                    try {
                        intent = Intent.parseUri("http://map.qq.com/mobile/downloadinstall.html", 0);
                        startActivity(intent);
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
    }
}
