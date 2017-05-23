package xm.ppq.papaquan.Presenter.mine_money;

import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.AiteBean;
import xm.ppq.papaquan.Bean.MoneyBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.news_mine_money.Round_At;

/**
 * Created by sunshaobei on 2017/3/20.
 */

public class Mutual_At implements At_presenter {

    private Round_At round_at;
    private List<AiteBean.Data> datas;
    private List<MoneyBean.Data> money;

    public Mutual_At(Round_At round_at) {
        this.round_at = round_at;
    }

    @Override
    public void getData(int i, String url, JSONObject jsonObject) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(url, jsonObject.toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (i == 0) {
                    if (money != null) round_at.setMoney(money);
                } else {
                    if (datas != null) round_at.setAtData(datas);
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    if (JsonUtil.getString(s, "code").equals("0")) {
                        if (i == 0) {
                            MoneyBean moneyBean = (MoneyBean) JsonUtil.fromJson(s, MoneyBean.class);
                            money = moneyBean.data;
                        } else {
                            AiteBean aiteBean = (AiteBean) JsonUtil.fromJson(s, AiteBean.class);
                            datas = aiteBean.data;
                        }
                    }
                }
            }
        });
    }
}