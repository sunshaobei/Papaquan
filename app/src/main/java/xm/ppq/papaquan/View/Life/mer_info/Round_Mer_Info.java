package xm.ppq.papaquan.View.Life.mer_info;

import org.json.JSONArray;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.ForSureBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.MerInfoBean;
import xm.ppq.papaquan.Bean.life.OtherBean;

/**
 * Created by Administrator on 2017/4/20.
 */

public interface Round_Mer_Info {

    String getId();

    String getSid();

    String getCityCode();

    int getUid();

    String getToken();

    String getName();

    JSONArray getPhoto();

    String getPhoto2();

    String getPhoto3();

    String getTel();

    int getShopType();

    int getIndOne();

    int getIndTwo();

    OtherBean getOther();

    ForSureBean getForSure();

    String getPhotoWoman();

    String getPhtooNumber();

    void setTypeClassify(ArrayList<TypeClassfiyBean.DataBean> dataBeen);

    void setBean(MerInfoBean.DataBean dataBean);

    void SuccesShow();

    void ErrorShow();

    void ToastShow(String result);
}