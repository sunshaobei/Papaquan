package xm.ppq.papaquan.Presenter.citysearch;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.CityBean;
import xm.ppq.papaquan.Bean.CityBean2;
import xm.ppq.papaquan.Bean.CityInfo;
import xm.ppq.papaquan.Bean.CitySearchData;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.city_search.Round_CitySearch;

/**
 * Created by Administrator on 2017/3/16.
 */

public class Mutual_CitySearch implements CitySearchPresenter, TencentLocationListener {

    private Round_CitySearch round_citySearch;
    private Context context;
    private TencentLocationManager instance;
    private TencentLocationRequest request;
    private final SharedPreferencesPotting my_login;


    public Mutual_CitySearch(Context context) {
        this.context = context;
        this.round_citySearch = (Round_CitySearch) context;
        my_login = new SharedPreferencesPotting(context, "my_login");
        initTencenLocationListener();
    }



    @Override
    public void getCityData(int type,String s) {

        if (type == 1) {
            OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getprovince").AskOne("", "", new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        CityBean cityBean = (CityBean) JsonUtil.fromJson(o, CityBean.class);
                        List<CityBean.DataBean> data = cityBean.getData();
                        round_citySearch.setList(data, 1);
                    }
                }
            });

        } else if (type == 2||type == 7) {
            OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getprocity").AskOne("", "{\"citycode\": \"" + s + "\"}", new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        CityBean2 cityBean2 = (CityBean2) JsonUtil.fromJson(o, CityBean2.class);
                        List<CityBean2.DataBean> data = cityBean2.getData();
                        if (type == 2)
                        round_citySearch.setList(data, 2);
                        else round_citySearch.setnowLocationList(data);
                    }
                }
            });


        } else if (type == 4) {
            OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/searchcity").AskOne("", "{\"city\": \"" + s + "\"}", new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (JsonUtil.getString(o, "code").equals("0")) {
                        CitySearchData cityBean = (CitySearchData) JsonUtil.fromJson(o, CitySearchData.class);
                        List<CitySearchData.DataBean> data = cityBean.getData();
                        round_citySearch.setList(data, 4);
                    }
                }
            });

        } else {
            OkPotting.getInstance("http://app.papaquan.com/index.php/index/Citys/getcityinfo").AskOne("", "{\"citycode\": \"" + s + "\"}", new Subscriber<String>() {
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
                        switch (type) {
                            case 3:
                                round_citySearch.setnowLocationList(cityinfo);
                                break;
                            case 6:
                                round_citySearch.setExit(cityinfo);
                                break;
                        }
                    }
                }
            });

        }

    }




    /**
     * 腾讯地图
     */
    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        switch (i) {
            case TencentLocation.ERROR_OK:

                handler.obtainMessage(1,tencentLocation).sendToTarget();
                instance.removeUpdates(this);
                break;
            case TencentLocation.ERROR_NETWORK:
                break;
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

    public void initTencenLocationListener() {
        instance = TencentLocationManager.getInstance(context);
        request = TencentLocationRequest.create();
        request.setInterval(1000).setAllowCache(true).setRequestLevel(TencentLocationRequest.REQUEST_LEVEL_ADMIN_AREA);
        instance.requestLocationUpdates(request, this);
    }

    Handler handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    TencentLocation tencentLocation = (TencentLocation) msg.obj;
                    String district = tencentLocation.getDistrict();
                    String cityCode = tencentLocation.getCityCode();
                    district = district.replace("null","");
                    if (!district.equals(""))
                    round_citySearch.onLocationSuccess(district,cityCode);
                    else {
                        String city = tencentLocation.getCity();
                        round_citySearch.onLocationSuccess(city,cityCode);
                    }
                    break;
                case 2:
                    break;
            }
        }
    };

}
