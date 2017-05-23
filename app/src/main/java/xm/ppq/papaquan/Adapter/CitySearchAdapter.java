package xm.ppq.papaquan.Adapter;


import android.content.Context;

import java.util.List;

import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.CityBean;
import xm.ppq.papaquan.Bean.CityBean2;
import xm.ppq.papaquan.Bean.CityInfo;
import xm.ppq.papaquan.Bean.CitySearchData;
import xm.ppq.papaquan.R;

/**
 * Created by sunshaobei on 2017/3/18.
 */

public class CitySearchAdapter<T> extends ConcreteAdapter<T>{
    public CitySearchAdapter(Context context, List<T> list, int itemLayout) {
        super(context, list, itemLayout);

    }

    int type;
    public void setType(int type){
        this.type =type;
    }
    @Override
    protected void convert(ViewHolder viewHolder, T data, int position) {
        switch (type){
            case 1:
                CityBean.DataBean item= (CityBean.DataBean) data;
                viewHolder.setText(item.getName(),R.id.tv_cityitem);
                break;
            case 2:
                CityBean2.DataBean item2 = (CityBean2.DataBean) data;
                viewHolder.setText(item2.getFullname(),R.id.tv_cityitem);
                break;
            case 3:
                CityInfo.DataBean.OtherCityBean item3 = (CityInfo.DataBean.OtherCityBean) data;
                viewHolder.setText(item3.getFullname(),R.id.tv_cityitem);
                break;
            case 4:
                CitySearchData.DataBean item4 = (CitySearchData.DataBean)data;
                viewHolder.setText(item4.getFullname(),R.id.tv_cityitem);
                break;
        }
    }
}
