package xm.ppq.papaquan.View.Life.mine_tenant.M;

import android.content.Context;
import android.util.Log;

import com.example.lib_sunshaobei2017.utils.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.life.TenantData;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.mine_tenant.Round_Tenant;

/**
 * Created by sunshaobei on 2017/4/18.
 */

public class ModelUtil implements Model {

    private Round_Tenant round_tenant;
    private SharedPreferencesPotting my_login;

    public ModelUtil(Context context) {
        this.round_tenant = (Round_Tenant) context;
        my_login = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    public void getData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.MYSHOP, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (!o.isEmpty()) {
                        TenantData tenantData = GsonUtils.parseJSON(o, TenantData.class);
                        if (tenantData.getCode() == 0) {
                            List<TenantData.DataBean> data = tenantData.getData();
                            round_tenant.setList(data);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
