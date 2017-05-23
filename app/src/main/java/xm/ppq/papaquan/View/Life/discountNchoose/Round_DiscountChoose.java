package xm.ppq.papaquan.View.Life.discountNchoose;

import com.tencent.mapsdk.raster.model.LatLng;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.DiscountBean;
import xm.ppq.papaquan.Bean.life.ChooseBean;
import xm.ppq.papaquan.Bean.life.LevelBean;

/**
 * Created by EdgeDi on 19:33.
 */

public interface Round_DiscountChoose {

    String getCityCode();

    void setText(int position, String result, String id);

    TagFlowLayout getFlowLayout();

    LatLng getLatLng();

    void setListes(ArrayList<LevelBean> l1, ArrayList<LevelBean> l2, ArrayList<LevelBean> l3, ArrayList<LevelBean> l4);

    void setDiscountBean(ArrayList<DiscountBean.DataBean> dataBeen, LatLng start);

    void setChooseBean(ArrayList<ChooseBean.DataBean> dataBeen, LatLng start);
}