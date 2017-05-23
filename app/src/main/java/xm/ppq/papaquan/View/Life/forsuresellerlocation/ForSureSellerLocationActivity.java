package xm.ppq.papaquan.View.Life.forsuresellerlocation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.lib_sunshaobei2017.app.SunActivity;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.ForSureBean;
import xm.ppq.papaquan.Bean.SureBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.RequestpermissionUtil;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.maputils.MapUtils;
import xm.ppq.papaquan.View.BaseUrl;

public class ForSureSellerLocationActivity extends SunActivity implements TencentLocationListener {

    private double latitude = 39;
    private double longitude = 116;
    private TencentMap tencentMap;
    private MapUtils mapUtils;
    private LatLng latLng;
    private String str = "北京";

    private OptionsPickerView type;
    private SharedPreferencesPotting potting;
    private ArrayList<SureBean.DataBean> dataBeen;
    private String area = "";

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.trading_area)
    TextView tradingArea;
    @BindView(R.id.citytitel)
    TextView citytitel;
    @BindView(R.id.setlocation)
    TextView setlocation;
    @BindView(R.id.map)
    MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_sure_seller_location);
        ButterKnife.bind(this);
        initStatusBar(bar);
        RequestpermissionUtil.requestGPSPermissions(this, null);
        mapUtils = new MapUtils(this, this, edit);
        mapUtils.initTencenLocationListener();
        initmap();
        initView();
        initListener();
        potting = new SharedPreferencesPotting(this, "my_login");
        latLng = new LatLng(latitude, longitude);
        tencentMap.setCenter(latLng);
        mapUtils.setMark(tencentMap, latLng, null);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", potting.getItemString("citycode"));
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.AREA, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (dataBeen != null) {
                        type = new OptionsPickerView.Builder(ForSureSellerLocationActivity.this, (options1, options2, options3, v) -> {
                            area = String.valueOf(dataBeen.get(options1).getId());
                            tradingArea.setText(dataBeen.get(options1).getName());
                        }).setLayoutRes(R.layout.type_item, v -> {
                            TextView fulfil_type = (TextView) v.findViewById(R.id.fulfil_type);
                            TextView finish_type = (TextView) v.findViewById(R.id.finish_type);
                            fulfil_type.setOnClickListener(v1 -> {
                                type.returnData();
                                type.dismiss();
                            });
                            finish_type.setOnClickListener(v2 -> type.dismiss());
                        })
                                .isDialog(false)
                                .setOutSideCancelable(true)
                                .setContentTextSize(17)
                                .setBgColor(Color.parseColor("#f9f9f9"))
                                .build();
                        type.setPicker(dataBeen);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        SureBean sureBean = (SureBean) JsonUtil.fromJson(s, SureBean.class);
                        if (sureBean.getCode() == 0) {
                            dataBeen = sureBean.getData();
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        String[] split = citytitel.getText().toString().trim().split(" ");
        int length = split.length;
        if (length > 2)
            str = split[1];
        else str = split[0];
        setlocation.setOnClickListener(v -> {
            mapUtils.getLocationBysearchString(str, citytitel.getText().toString().trim().replaceAll(" ", "") + edit.getText().toString().trim(), tencentMap);
        });
    }

    private void initView() {

    }

    private void initmap() {
        //获取TencentMap实例
        tencentMap = map.getMap();
        //设置卫星底图
        // tencentMap.setSatelliteEnabled(true);
        //设置实时路况开启
        tencentMap.setTrafficEnabled(true);
        //设置地图中心点
        tencentMap.setCenter(new LatLng(latitude, longitude));
        //设置缩放级别
        tencentMap.setZoom(18);
    }


    @Override
    protected void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        map.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        map.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        map.onStop();
        super.onStop();
    }

    /*************************
     * onClick
     ***************************/
    public void finish(View v) {
        finish();
    }

    public void area(View v) {
        if (type != null) {
            type.show();
        } else {
            Toast.makeText(this, "获取信息中", Toast.LENGTH_SHORT).show();
        }
    }

    public void affirm_address(View view) {
//        edit
        if (tradingArea.getText().equals("请选择")) {
            Toast.makeText(this, "请选择商圈", Toast.LENGTH_SHORT).show();
        } else {
            ForSureBean forSureBean = new ForSureBean();
            forSureBean.setAddress(edit.getText().toString());
            forSureBean.setArea(area);
            forSureBean.setLatLng(mapUtils.getLocation());
            EventBus.getDefault().post(forSureBean);
            finish();
        }
    }

    //TODO  腾讯地图监听
    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (i == TencentLocation.ERROR_OK) {
            latitude = tencentLocation.getLatitude();
            longitude = tencentLocation.getLongitude();
            Toast.makeText(ForSureSellerLocationActivity.this, "定位成功", Toast.LENGTH_SHORT).show();
            citytitel.setText(tencentLocation.getProvince() + " " + tencentLocation.getCity() + " " + tencentLocation.getDistrict());
            latLng = new LatLng(latitude, longitude);
            tencentMap.setCenter(latLng);
            mapUtils.setMark(tencentMap, latLng, tencentLocation);
            mapUtils.getInstance().removeUpdates(this);
        } else {
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

}
