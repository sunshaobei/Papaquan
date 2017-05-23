package xm.ppq.papaquan.Bean;

import com.bigkoo.pickerview.model.IPickerViewData;

/**
 * Created by Administrator on 2017/4/15.
 */

public class TypeBean implements IPickerViewData {

    public TypeBean(String ss) {
        this.ss = ss;
    }

    String ss;

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    @Override
    public String getPickerViewText() {
        return ss;
    }
}
