package xm.ppq.papaquan.Presenter.life.restaurant;

import org.json.JSONObject;

/**
 * Created by EdgeDi on 10:51.
 */

public interface RestaurantPresenter {

    void StartInfo();

    void GetOrder();

    void PlaceOrder(JSONObject jsonObject);
}