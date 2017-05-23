package xm.ppq.papaquan.Bean.life;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20.
 */

public class OtherBean implements Serializable {

    private String time;
    private String money;
    private int conditioner;
    private int car;
    private int wifi;
    private int box;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getConditioner() {
        return conditioner;
    }

    public void setConditioner(int conditioner) {
        this.conditioner = conditioner;
    }

    public int getCar() {
        return car;
    }

    public void setCar(int car) {
        this.car = car;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }
}