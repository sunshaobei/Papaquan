package xm.ppq.papaquan.View.Life.classification;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.life.ClassificationBean;
import xm.ppq.papaquan.Bean.life.LinkHeadAdBean;

/**
 * Created by EdgeDi on 9:31.
 */

public interface Round_Classification {

    String getLat();

    String getLng();

    String getCityCode();

    String getType();

    String getIndustry();

    String getIndustry_Two();

    int getPage();

    void setBean(ArrayList<ClassificationBean.DataBean> dataBeen);

    void setHead(ArrayList<LinkHeadAdBean.DataBean> dataBeen);
}