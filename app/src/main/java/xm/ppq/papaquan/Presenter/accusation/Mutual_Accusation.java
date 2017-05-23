package xm.ppq.papaquan.Presenter.accusation;

import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.accusation.Round_Accusation;

/**
 * Created by sunshaobei on 2017/3/16.
 */

public class Mutual_Accusation implements AccusationPresenter {
    private Round_Accusation round_accusation;

    public Mutual_Accusation(Round_Accusation round_accusation) {
        this.round_accusation = round_accusation;
    }

    @Override
    public void sendAccusation(JSONObject jsonObject, String url) {
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(url, jsonObject.toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean o) {
                switch (o.getCode()) {
                    case "1":
                        //数据不能 为空
                        round_accusation.adviceSuccess("内容不能为空");
                        break;
                    case "2":
                        //已经举报过了
                        round_accusation.adviceSuccess("已经举报过了");
                        break;
                    case "3":
                        //举报失败
                        round_accusation.adviceSuccess("举报失败");
                        break;
                    case "0":
                        //举报成功
                        round_accusation.adviceSuccess("举报成功");
                        break;
                }
            }
        });
    }
}
