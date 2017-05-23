package xm.ppq.papaquan.View.Life.shop_lodge;

import android.content.Context;
import android.widget.Toast;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by EdgeDi on 16:19.
 */

public class LodgeInfo {

    private OnLodgeInfo onLodgeInfo;
    private SharedPreferencesPotting potting;
    private Toast toast;

    public LodgeInfo(Context onLodgeInfo) {
        this.onLodgeInfo = (OnLodgeInfo) onLodgeInfo;
        potting = new SharedPreferencesPotting(onLodgeInfo, "my_login");
        toast = Toast.makeText(onLodgeInfo, "", Toast.LENGTH_SHORT);
    }

    public void getInfo() {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("type", "typeid", "uid", "reason", "content", "token", "tokentype")
                .put_value(onLodgeInfo.getType(), onLodgeInfo.getTypeId(), potting.getItemInt("uid"), onLodgeInfo.getReason(), onLodgeInfo.getContent(), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.LIFEREPORT, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    switch (baseBean.getCode()) {
                        case "0":
                            onLodgeInfo.Success();
                            break;
                        case "1":
                            toast.setText("举报过了");
                            toast.show();
                            break;
                        case "2":
                            toast.setText("举报失败");
                            toast.show();
                            break;
                        case "3":
                            toast.setText("数据不能为空");
                            toast.show();
                            break;
                    }
                }
            }
        });
    }

    public interface OnLodgeInfo {

        String getType();

        String getTypeId();

        String getReason();

        String getContent();

        void Success();

    }
}