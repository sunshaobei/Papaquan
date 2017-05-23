package xm.ppq.papaquan.Presenter.life.DiscountChoose;

import org.json.JSONObject;

import xm.ppq.papaquan.Tool.JsonTool;

/**
 * Created by EdgeDi on 19:37.
 */

public interface DiscountChoosePresenter {

    void StartInitTitle();

    void StartWares(String url, int page);

    void ResrttingItem4();

    void NotLifeGetInfo(String url, JSONObject jsonObject, int page);
}