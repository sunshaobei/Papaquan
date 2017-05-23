package xm.ppq.papaquan.Presenter.mine_integral;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import myview.mybanner.Mybanner;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.IntergalHeadBean;
import xm.ppq.papaquan.Bean.life.IntergalListBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.CreateView;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.balance_detail.ExchangeHistoryActivity;
import xm.ppq.papaquan.View.mine_integral.Round_Integral;
import xm.ppq.papaquan.life.Tool.LocalImageHolderView;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Mutual_Integral implements IntegralPresenter {

    private Round_Integral round_integral;

    public Mutual_Integral(Round_Integral round_integral) {
        this.round_integral = round_integral;
    }

    @Override
    public void getHeadData(Context context) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_integral.getCityCode())
                    .put("uid", round_integral.getUid())
                    .put("token", round_integral.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.AD, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        IntergalHeadBean bean = (IntergalHeadBean) JsonUtil.fromJson(s, IntergalHeadBean.class);
                        round_integral.setHeadData(bean);
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void GetList() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("citycode", round_integral.getCityCode())
                    .put("page", 0)
                    .put("length", 10)
                    .put("uid", round_integral.getUid())
                    .put("token", round_integral.getToken())
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GOLDLIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        IntergalListBean bean = (IntergalListBean) JsonUtil.fromJson(s, IntergalListBean.class);
                        if (bean.getCode() == 0) {
                            round_integral.setList(bean);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}