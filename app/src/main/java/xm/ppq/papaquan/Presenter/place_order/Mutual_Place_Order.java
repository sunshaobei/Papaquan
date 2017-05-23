package xm.ppq.papaquan.Presenter.place_order;

import java.text.DecimalFormat;

import xm.ppq.papaquan.View.place_order.Round_Place_Order;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Mutual_Place_Order implements Place_OrderPresenter {

    private Round_Place_Order round_place_order;

    public Mutual_Place_Order(Round_Place_Order round_place_order) {
        this.round_place_order = round_place_order;
    }

    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void Count(String type) {
        int number;
        switch (type) {
            case "+":
                number = round_place_order.getEdit();
                number++;
                round_place_order.setEdit(String.valueOf(number), df.format(round_place_order.getA_Money() * number) + "元");
                break;
            case "-":
                number = round_place_order.getEdit();
                if (number <= 1) {
                    number = 1;
                } else {
                    number--;
                }
                round_place_order.setEdit(String.valueOf(number), df.format(round_place_order.getA_Money() * number) + "元");
                break;
        }
    }
}