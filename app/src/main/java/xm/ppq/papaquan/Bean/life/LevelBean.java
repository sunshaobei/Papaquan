package xm.ppq.papaquan.Bean.life;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 14:03.
 */

public class LevelBean {

    private String result;
    private boolean check;
    private ArrayList<LevelBean> child;
    private String id;

    public LevelBean() {
    }

    public LevelBean(String result, boolean check, String id) {
        this.result = result;
        this.check = check;
        this.id = id;
    }

    public ArrayList<LevelBean> getChild() {
        return child;
    }

    public LevelBean setChild(ArrayList<LevelBean> child) {
        this.child = child;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}