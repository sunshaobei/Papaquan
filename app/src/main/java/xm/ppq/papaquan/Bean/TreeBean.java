package xm.ppq.papaquan.Bean;

/**
 * Created by Administrator on 2017/4/14.
 */

public class TreeBean {

    private String result;
    private boolean isStuats;

    public TreeBean(String result, boolean isStuats) {
        this.result = result;
        this.isStuats = isStuats;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isStuats() {
        return isStuats;
    }

    public void setStuats(boolean stuats) {
        isStuats = stuats;
    }
}