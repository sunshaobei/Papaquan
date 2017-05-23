package myview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.CityData;
import data.GetJsonDataUtil;
import data.JsonBean;

/**
 * Created by sunshaobei on 2017/5/13.
 */

public class CityPickView {
    private Context context;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;


    public CityPickView(final Context context) {
        this.context = context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initMap();
            }
        }).start();
    }


    private HashMap<String,CityData.DataBean> initMap() {
        String json = new GetJsonDataUtil().getJson(context, "city.js");
        Gson gson = new Gson();
        CityData cityData = gson.fromJson(json.toString(), CityData.class);
        List<CityData.DataBean> data = cityData.getData();
        initmap(data);
        return map;
    }

    HashMap<String,CityData.DataBean> map = new HashMap<>();
    private void initmap( List<CityData.DataBean> data ) {
        for (int i = 0; i < data.size(); i++) {
            CityData.DataBean dataBean = data.get(i);
            map.put(dataBean.getFullname(),dataBean);
            if (dataBean.getChildren()!=null&&dataBean.getChildren().size()>0){
                initmap(dataBean.getChildren());
            }
        }
    }

    private void init() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }


    public void Show() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String s1 = options1Items.get(options1).getPickerViewText();
                String s2 = options2Items.get(options1).get(options2);
                String s3 = options3Items.get(options1).get(options2).get(options3);
                //TODO
                if (CityPickView.this.onSureListener!=null)
                {
                    if (s1.equals(s2))
                    {
                        onSureListener.onSure(map.get(s1),map.get(s3));
                    }else {
                        if (map.get(s3)!=null)
                        onSureListener.onSure(map.get(s1),map.get(s2),map.get(s3));
                        else onSureListener.onSure(map.get(s1),map.get(s2));
                    }
                }
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    public interface OnSureListener{
        void onSure(CityData.DataBean province,CityData.DataBean city);
        void onSure(CityData.DataBean province,CityData.DataBean city,CityData.DataBean distric);
    }
    private OnSureListener onSureListener;
    public void setOnSureListener(OnSureListener o)
    {
        this.onSureListener = o;
    }

}
