package xm.ppq.papaquan.Bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/20.
 */

public class SureBean {

    private String info;
    private int code;
    private String other;
    private ArrayList<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IPickerViewData {

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getPickerViewText() {
            return getName();
        }
    }
}
