package xm.ppq.papaquan.View.city_search;

import com.tencent.map.geolocation.TencentLocation;

import java.util.List;

import xm.ppq.papaquan.Bean.CityBean;
import xm.ppq.papaquan.Bean.CityBean2;
import xm.ppq.papaquan.Bean.CityInfo;

/**
 * Created by Administrator on 2017/3/16.
 */

public interface Round_CitySearch {
    void setList(List list, int type);
    void setonLocationError(String s);
    void onLocationSuccess(String s,String citycode);
    void setnowLocationList(CityInfo cityInfo);
    void setnowLocationList( List<CityBean2.DataBean> data);
    void setExit(CityInfo cityInfo);
}
