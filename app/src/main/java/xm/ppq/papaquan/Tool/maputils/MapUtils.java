package xm.ppq.papaquan.Tool.maputils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.lbssearch.TencentSearch;
import com.tencent.lbssearch.httpresponse.BaseObject;
import com.tencent.lbssearch.httpresponse.HttpResponseListener;
import com.tencent.lbssearch.object.Location;
import com.tencent.lbssearch.object.param.Address2GeoParam;
import com.tencent.lbssearch.object.param.Geo2AddressParam;
import com.tencent.lbssearch.object.result.Address2GeoResultObject;
import com.tencent.lbssearch.object.result.Geo2AddressResultObject;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.TencentMap;


/**
 * Created by sunshaobei on 2017/4/15.
 */

public class MapUtils {

    private TencentLocationListener tencenLocationListener;

    private Context context;
    private final TencentSearch tencentSearch;
    private Marker marker;
//    private EditText editText;

    public MapUtils(Context context, TencentLocationListener tencenLocationListener, EditText editText) {
        this.context = context;
        this.tencenLocationListener = tencenLocationListener;
        tencentSearch = new TencentSearch(context);
//        this.editText = editText;
    }

    private TencentLocationManager instance;

    public TencentLocationManager getInstance() {
        return instance;
    }

    public void initTencenLocationListener() {
        instance = TencentLocationManager.getInstance(context);
        TencentLocationRequest request = TencentLocationRequest.create();
        request.setInterval(180000).setAllowCache(true).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
        instance.requestLocationUpdates(request, tencenLocationListener);
    }


    public void setMark(TencentMap tencentMap, LatLng latLng, TencentLocation location) {
        tencentMap.setOnMapCameraChangeListener(new TencentMap.OnMapCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                LatLng target = cameraPosition.getTarget();
                marker.setPosition(target);
                searchLocationbylatln((float) target.getLatitude(), (float) target.getLongitude());
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                LatLng target = cameraPosition.getTarget();
                marker.setPosition(target);
                searchLocationbylatln((float) target.getLatitude(), (float) target.getLongitude());
            }
        });
        marker = tencentMap.addMarker(new MarkerOptions()
                .position(latLng)
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory
                        .defaultMarker())
                .draggable(true));
        if (location != null)
            marker.setTitle(getLocationInfo(location));
        else marker.setTitle("北京市海淀区彩和坊路海淀西大街74号");
        marker.showInfoWindow();// 设置默认显示一个infowinfow

        //marker点击事件
        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                // TODO Auto-generated method stub
                if (marker.isInfoWindowShown())
                    marker.hideInfoWindow();
                else marker.showInfoWindow();
                return true;
            }
        });
        //infowindow点击事件
        tencentMap.setOnInfoWindowClickListener(new TencentMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
            }
        });
        //marker拖拽事件
        tencentMap.setOnMarkerDraggedListener(new TencentMap.OnMarkerDraggedListener() {

            //拖拽开始时调用
            @Override
            public void onMarkerDragStart(Marker arg0) {
                // TODO Auto-generated method stub

            }

            //拖拽结束后调用
            @Override
            public void onMarkerDragEnd(Marker arg0) {
                // TODO Auto-generated method stub
                tencentMap.setCenter(arg0.getPosition());
            }

            //拖拽时调用
            @Override
            public void onMarkerDrag(Marker arg0) {
                // TODO Auto-generated method stub
                LatLng position = arg0.getPosition();
                float latitude = (float) position.getLatitude();
                float longtitude = (float) position.getLongitude();
                searchLocationbylatln(latitude, longtitude);
            }
        });

    }

    private void searchLocationbylatln(float latitude, float longtitude) {
        Geo2AddressParam param = new Geo2AddressParam().location(new Location()
                .lat(latitude).lng(longtitude));
        //设置此参数可以返回坐标附近的poi，默认为false,不返回
        param.get_poi(true);
        tencentSearch.geo2address(param, new HttpResponseListener() {
            @Override
            public void onSuccess(int i, BaseObject object) {
                Geo2AddressResultObject oj = (Geo2AddressResultObject) object;
                String result = oj.result.address;
                result = result + oj.result.pois.get(0).title;
//                for (int j = 0; j < oj.result.pois.size(); j++) {
//                    Log.e("pios", oj.result.pois.get(j).title);
//                }
                marker.setTitle(result);
//                editText.setText(result);
//                editText.setSelection(result.length());

            }

            @Override
            public void onFailure(int i, String s, Throwable throwable) {

            }
        });
    }

    public String getLocationInfo(TencentLocation t) {
        String s = "";
        if (!t.getProvince().equals("Unknown"))
            s = s + t.getProvince();
        if (!t.getCity().equals("Unknown"))
            s = s + " " + t.getCity();
        if (!t.getDistrict().equals("Unknown"))
            s = s + " " + t.getDistrict();
        if (!t.getTown().equals("Unknown"))
            s = s + " " + t.getTown();
        if (!t.getVillage().equals("Unknown"))
            s = s + " " + t.getVillage();
        if (!t.getStreet().equals("Unknown"))
            s = s + " " + t.getStreet();
        if (!t.getStreet().equals("Unknown"))
            s = s + " " + t.getStreet();
        if (!t.getStreetNo().equals("Unknown"))
            s = s + " " + t.getStreetNo();
        if (!t.getPoiList().equals("Unknown"))
            s = s + " " + t.getPoiList();
        s = s.trim();
        s = s.replace("[]", "");
        return s;
    }

    public void getLocationBysearchString(String region, String s, TencentMap map) {
        Address2GeoParam address2GeoParam =
                new Address2GeoParam().address(s).region(region);
        tencentSearch.address2geo(address2GeoParam, new HttpResponseListener() {

            private LatLng latLng;

            @Override
            public void onSuccess(int arg0, BaseObject arg1) {
                // TODO Auto-generated method stub
                if (arg1 == null) {
                    return;
                }
                Address2GeoResultObject obj = (Address2GeoResultObject) arg1;
                Location location = obj.result.location;
                float lat = location.lat;
                float lng = location.lng;
                latLng = new LatLng(lat, lng);
                marker.setPosition(latLng);
                searchLocationbylatln(lat, lng);
                map.setCenter(latLng);
            }

            public void onFailure(int arg0, String arg1, Throwable arg2) {
                Toast.makeText(context, arg1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getMarkerAdress() {
        return marker.getTitle();
    }

    public LatLng getLocation() {
        return marker.getPosition();
    }
}
