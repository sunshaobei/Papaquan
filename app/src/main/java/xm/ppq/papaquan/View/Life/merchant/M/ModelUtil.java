package xm.ppq.papaquan.View.Life.merchant.M;

import android.util.Log;

import com.example.lib_sunshaobei2017.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.MerchantData;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.merchant.Round_Merchant;

/**
 * Created by sunshaobei on 2017/4/18.
 */

public class ModelUtil implements Model {

    private Round_Merchant round_merchant;

    public ModelUtil(Round_Merchant round_merchant) {
        this.round_merchant = round_merchant;
    }

    @Override
    public void getData(String sid) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", round_merchant.getUid())
                    .put("sid", sid)
                    .put("token", round_merchant.getToken())
                    .put("tokentype", "1");
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.SHOPMANAGE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (!o.isEmpty()) {
                        MerchantData merchantData = GsonUtils.parseJSON(o, MerchantData.class);
                        Log.e("info", merchantData.getInfo());
                        if (merchantData.getCode() == 0) {
                            MerchantData.DataBean data = merchantData.getData();
                            round_merchant.setData(data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
