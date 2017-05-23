package xm.ppq.papaquan.Presenter.panic_buying;

import com.tencent.mapsdk.raster.model.LatLng;

/**
 * Created by Administrator on 2017/4/19.
 */

public interface Panic_BuyingPresenter {

    void start();

    void HumanList();

    double Calculated_distance(LatLng start, LatLng end);

    void purchasing();

    void getPicNTextInfo(int pid);
}
