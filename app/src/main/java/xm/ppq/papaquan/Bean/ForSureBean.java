package xm.ppq.papaquan.Bean;

import com.tencent.mapsdk.raster.model.LatLng;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ForSureBean {

    private String address;

    private String area;

    private LatLng latLng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}