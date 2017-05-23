package xm.ppq.papaquan.Bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/20.
 */

public class TypeClassfiyBean {

    private String info;
    private int code;
    private Object other;
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

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements IPickerViewData, Serializable {

        private int id;
        private String name;
        private String logo;
        private int pid;
        private ArrayList<ChildrenBean> children;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public ArrayList<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<ChildrenBean> children) {
            this.children = children;
        }

        @Override
        public String getPickerViewText() {
            return getName();
        }

        public static class ChildrenBean implements IPickerViewData, Serializable {

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
}
