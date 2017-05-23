package xm.ppq.papaquan.Presenter.makeaddress;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.make_address.Round_MakeAddress;

/**
 * Created by sunshaobei on 2017/3/21.
 */

public class Mutual_MakeAddress implements MakeAddressPresenter {

    private Round_MakeAddress round_makeAddress;
    private SharedPreferencesPotting potting;

    public Mutual_MakeAddress(Context context) {
        this.round_makeAddress = (Round_MakeAddress) context;
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    public void addNewaddress(int type, int action) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject
                    .put("uid", potting.getItemInt("uid"))
                    .put("province", round_makeAddress.getProvince())
                    .put("city", round_makeAddress.getCity())
                    .put("area", round_makeAddress.getArea())
                    .put("street", round_makeAddress.getStreet())
                    .put("username", round_makeAddress.getName())
                    .put("deliverytel", round_makeAddress.getPhone())
                    .put("zipcode", round_makeAddress.getPostcode())
                    .put("isdefault", 0)
                    .put("token", potting.getItemString("token"))
                    .put("tokentype", 1);
            if (type == 2) {
                jsonObject.put("addid", action);
            }
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.ADDTAKE, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(String s) {
                    String code = JsonUtil.getString(s, "code");
                    if (code.equals("0")) {
                        if (type == 2) round_makeAddress.setSuccess("修改成功");
                        else round_makeAddress.setSuccess("添加成功");
                    } else {
                        if (type == 2) round_makeAddress.setError("修改失败");
                        else round_makeAddress.setError("添加失败");
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
