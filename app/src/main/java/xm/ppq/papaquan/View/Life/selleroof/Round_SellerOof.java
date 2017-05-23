package xm.ppq.papaquan.View.Life.selleroof;

import java.util.ArrayList;

import xm.ppq.papaquan.Bean.NewSellerOofBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.life.SeventhBean;
import xm.ppq.papaquan.Bean.life.TopSelleOofBean;

/**
 * Created by EdgeDi on 14:14.
 */

public interface Round_SellerOof {

    int getPage();

    String getCityCode();

    void setTop(ArrayList<TopSelleOofBean.DataBean> dataBean);

    void setNesEnter(ArrayList<NewSellerOofBean.DataBean> dataBeen);

    void setClassify(ArrayList<TypeClassfiyBean.DataBean> dataBeen);

    void setSeventh(ArrayList<SeventhBean.DataBean> dataBeen);
}