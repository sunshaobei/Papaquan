package xm.ppq.papaquan.Presenter.mineaddress;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.AddressBean;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;
import xm.ppq.papaquan.View.mineaddress.Round_MineAddress;

/**
 * Created by Administrator on 2017/2/23.
 */

public class Mutual_Mine_Address implements MineAddressPresenter {

    private Round_MineAddress mineAddress;
    private SharedPreferencesPotting potting;
    private int uid;
    private List<AddressBean.Data> address = new ArrayList<>();

    public Mutual_Mine_Address(Activity activity) {
        mineAddress = (Round_MineAddress) activity;
        potting = new SharedPreferencesPotting(activity, "my_login");
        uid = potting.getItemInt("uid");
    }

    @Override
    public void start() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", uid);
            jsonObject.put("page", "0");
            jsonObject.put("length", "10");
            jsonObject.put("tokentype",1);
            jsonObject.put("token", potting.getItemString("token"));
            OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.TAKELIST, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {
                    if (address.size() > 0) {
                        mineAddress.setListAddress(address);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getString(s, "code").equals("0")) {
                            AddressBean bean = (AddressBean) JsonUtil.fromJson(s, AddressBean.class);
                            address = bean.data;
                        }
                    }
                }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}