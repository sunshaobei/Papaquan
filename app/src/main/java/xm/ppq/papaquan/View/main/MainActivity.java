package xm.ppq.papaquan.View.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_sunshaobei2017.app.SunActivity;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tauth.Tencent;
import com.umeng.analytics.MobclickAgent;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.HomePagerAdapter;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.CityInfo;
import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.PicBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.DropBean;
import xm.ppq.papaquan.Bean.life.LevelBean;
import xm.ppq.papaquan.Bean.life.LifeHomeData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.Util;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.discountNchoose.Round_DiscountChoose;
import xm.ppq.papaquan.View.Life.lifehome.LifeFragLayout;
import xm.ppq.papaquan.View.city_search.CitySearchActivity;
import xm.ppq.papaquan.View.login.LoginActivity;
import xm.ppq.papaquan.View.main.frame.MineFragLayout;
import xm.ppq.papaquan.View.main.frame.NewsFragLayout;
import xm.ppq.papaquan.View.main.frame.PapaFragLayout;
import xm.ppq.papaquan.View.published_dynamic.Dynamic;
import xm.ppq.papaquan.life.tenxun.BaseUiListener;

/**
 * Created by sunshaobei on 2017/4/18.
 */

public class MainActivity extends SunActivity implements Round_home, ALi_ossInterface, TencentLocationListener, Round_DiscountChoose {

    @BindView(android.R.id.tabhost)
    FragmentTabHost mtabHost;
    @BindView(R.id.dynamic)
    ImageView dynamic;
    @BindView(R.id.refreshclick)
    View refreshclick;

    private SharedPreferencesPotting my_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemain);
        ButterKnife.bind(this);
        my_login = new SharedPreferencesPotting(this, "my_login");
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(this, R.raw.ting, 1);
        initTabHost();
        aLioss = new ALioss(this);
        aLioss.setAinterface(this);
        setListener();
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            };
            if (checkSelfPermission(permissions[0]) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 0);
            } else {
                if (my_login.getItemString("citycode").equals("") || my_login.getItemString("citycode") == null) {
                    Intent intent = new Intent(MainActivity.this, CitySearchActivity.class);
                    intent.putExtra("action", 2);
                    startActivityForResult(intent, IntentCode.RequestCode.HOMETOCITYSEARCH);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void setListener() {
        if (mtabHost != null) {
            mtabHost.setOnTabChangedListener(tabId -> {
                if (soundPool != null) {
                    soundPool.play(1, 1, 1, 0, 0, 1);
                }
                if (tabId.equals("消息")) {
                    if (drop != null) {
                        if (drop.getVisibility() == View.VISIBLE)
                            runOnUiThread(() -> drop.setVisibility(View.GONE));
                    }
                }
                if (tabId.equals("动态")) {
                    refreshclick.setVisibility(View.VISIBLE);
                } else {
                    refreshclick.setVisibility(View.GONE);
                }

            });
        }
        dynamic.setOnClickListener(v -> {
            if (my_login.getItemString("token").isEmpty()) {
                ShowToast("请先登陆");
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                Intent intent = new Intent(MainActivity.this, Dynamic.class);
                startActivityForResult(intent, IntentCode.RequestCode.HOMETODYNAMIC);
            }
        });
        refreshclick.setOnClickListener(v -> {
            soundPool.play(1, 1, 1, 0, 0, 1);
            EventBus.getDefault().post("backtotoprefresh");
        });
    }

    private void initTabHost() {
        mtabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        addTabs(R.drawable.tab_icon_papa, "动态", PapaFragLayout.class);
        addTabs(R.drawable.tab_icon_life, "生活", LifeFragLayout.class);
        addTabs(R.drawable.tab_icon_life, "发动态", null);
        addTabs(R.drawable.tab_icon_news, "消息", NewsFragLayout.class);
        addTabs(R.drawable.tab_icon_mine, "我", MineFragLayout.class);
    }

    @Subscribe
    public void drop(LifeHomeData.Other other) {
        if (drop != null) {
            if (other.getMessage() > 0) {
                drop.setVisibility(View.VISIBLE);
            } else {
                drop.setVisibility(View.GONE);
            }
        }
    }

    private View drop;

    private SoundPool soundPool;

    private void addTabs(int images, String str, Class FramentClass) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_tabs_xx, null);
        if (images == R.drawable.tab_icon_news) {
            drop = view.findViewById(R.id.drop);
        }
        ImageView image = (ImageView) view.findViewById(R.id.tab_image);
        image.setImageResource(images);
        TextView tv = (TextView) view.findViewById(R.id.tab_text);
        tv.setText(str);
        if (str.equals("发动态")) {
            view.setVisibility(View.INVISIBLE);
        }
        mtabHost.addTab(mtabHost.newTabSpec(str).setIndicator(view), FramentClass, null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (JCVideoPlayer.backPress()) {
                    JCVideoPlayer.releaseAllVideos();
                    return true;
                } else if (isdismisscontrol) {
                    EventBus.getDefault().post("dismiss");
                    return true;
                } else {
                    Intent home = new Intent(Intent.ACTION_MAIN);
                    home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    home.addCategory(Intent.CATEGORY_HOME);
                    startActivity(home);
//                    if (isExit == false) {
//                        isExit = true;
//                        ShowToast("再次点击退出程序");
//                        timer = new Timer();
//                        timer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                isExit = false;
//                            }
//                        }, 2000);
//                    } else {
//                        finish();
//                    }
                    break;
                }
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private boolean isdismisscontrol = false;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String s) {
        switch (s) {
            case "dismissfalse":
                isdismisscontrol = true;
                break;
            case "dismisstrue":
                isdismisscontrol = false;
                break;
        }
    }

    @Subscribe
    public void QiangGou(String qiang) {
        if (qiang.equals("抢购")) {
            mtabHost.setCurrentTab(4);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0008) {
            String path;
            if (data != null) {
                Uri uri = data.getData();
                path = Util.getPath(MainActivity.this, uri);
                String s = OwnUtil.compressImage(path, path + "compress", 30);
                aLioss.init(s, ".jpg", 4);
            }
        }
        switch (resultCode) {
            case IntentCode.ResultCode.CITYBACKTOHOME:
                EventBus.getDefault().post("刷新");
                break;
            case IntentCode.ResultCode.DYNAMICTOHOME:
                mtabHost.setCurrentTab(0);
                EventBus.getDefault().post("moveTofirst");
                break;
            case IntentCode.ResultCode.BACKTOMINE:
                mtabHost.setCurrentTab(4);
                break;
        }
        Tencent.onActivityResultData(requestCode, resultCode, data, new BaseUiListener());
    }

    @Override
    public void setFrameLayout(HomePagerAdapter adapter) {

    }

    @Override
    public void setCurrentItem(ViewPager viewPager, int position) {

    }

    @Override
    public void initChar() {

    }

    private ALioss aLioss;

    @Override
    public void upImageSuccess(String url) {
        EventBus.getDefault().post(new PicBean(url));
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

    public void setCurrentItem(int position) {
        mtabHost.setCurrentTab(position);
        if (position == 0) {
            EventBus.getDefault().post("moveTofirst");
        }
    }

    public void ShowToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (permissions[0].equals(Manifest.permission.ACCESS_COARSE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initTencenLocationListener();
            } else {
                String citycode = my_login.getItemString("citycode");
                if (citycode.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, CitySearchActivity.class);
                    intent.putExtra("action", 2);
                    startActivityForResult(intent, IntentCode.RequestCode.HOMETOCITYSEARCH);
                }
                return;
            }
        }
    }

    private TencentLocationManager instance;

    private void initTencenLocationListener() {
        instance = TencentLocationManager.getInstance(this);
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(180000).setAllowCache(true).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
        instance.requestLocationUpdates(request, this);
    }

    private LatLng latLng;

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (i == TencentLocation.ERROR_OK) {

            my_login.setItemString("lat", String.valueOf(tencentLocation.getLatitude()))
                    .setItemString("lng", String.valueOf(tencentLocation.getLongitude()))
                    .build();
            boolean itemBoolean = new SharedPreferencesPotting(MainActivity.this, "login").getItemBoolean("one");
            if (itemBoolean) {

                OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getcityinfo").AskOne("", "{\"citycode\": \"" + tencentLocation.getCityCode() + "\"}", new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String o) {
                        if (JsonUtil.getString(o, "code").equals("0")) {
                            CityInfo cityinfo = (CityInfo) JsonUtil.fromJson(o, CityInfo.class);
                            int pointcode = cityinfo.getData().getPointcode();
                            if (pointcode == 0) {
                                my_login.setItemString("citycode", cityinfo.getData().getCode() + "")
                                        .setItemString("cityname", cityinfo.getData().getFullname())
                                        .build();
                            } else {
                                OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getcityinfo").AskOne("", "{\"citycode\": \"" + pointcode + "\"}", new Subscriber<String>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(String o) {
                                        if (JsonUtil.getString(o, "code").equals("0")) {
                                            CityInfo cityinfo = (CityInfo) JsonUtil.fromJson(o, CityInfo.class);
                                            my_login.setItemString("citycode", cityinfo.getData().getCode() + "")
                                                    .setItemString("cityname", cityinfo.getData().getFullname())
                                                    .build();
                                        }
                                    }
                                });
                            }
                        }
                    }
                });
                EventBus.getDefault().post("refreshcity");
                EventBus.getDefault().post("刷新");
            }
            latLng = new LatLng(tencentLocation.getLatitude(), tencentLocation.getLongitude());
            instance.removeUpdates(this);
        }
    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {
        switch (i) {
            case STATUS_DISABLED:
                break;
            case STATUS_DENIED://没有权限
                break;
            case STATUS_LOCATION_SWITCH_OFF:
                break;
            case STATUS_GPS_UNAVAILABLE:
                break;
        }
    }

    @Override
    public String getCityCode() {
        return null;
    }

    @Override
    public void setText(int position, String result, String id) {

    }

    @Override
    public TagFlowLayout getFlowLayout() {
        return null;
    }

    @Override
    public LatLng getLatLng() {
        return latLng;
    }

    @Override
    public void setListes
            (ArrayList<LevelBean> l1, ArrayList<LevelBean> l2, ArrayList<LevelBean> l3, ArrayList<LevelBean> l4) {

    }

    @Override
    public void setDiscountBean(ArrayList<DiscountBean.DataBean> dataBeen, LatLng start) {

    }

    @Override
    public void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start) {

    }
}