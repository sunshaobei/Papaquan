package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.lib_sunshaobei2017.utils.GsonUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.StaffBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.staff_manage.MakeOver;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Staff_A_DPopupwindow extends PopupWindowPotting {

    private TextView finish;
    private TextView deleteUser;
    private TextView changeUser;
    private SharedPreferencesPotting my_login;
    private OnMakeOverListener onMakeOverListener;

    public Staff_A_DPopupwindow(Activity activity) {
        super(activity);
        my_login = new SharedPreferencesPotting(activity, "my_login");
    }

    @Override
    protected int getLayout() {
        return R.layout.staff_add_delete;
    }

    public void setOnMakeOverListener(OnMakeOverListener onMakeOverListener) {
        this.onMakeOverListener = onMakeOverListener;
    }

    @Override
    protected void initUI() {
        finish = Bind(R.id.finish);
        deleteUser = Bind(R.id.deleteUser);
        changeUser = Bind(R.id.changeUser);
    }

    @Override
    protected void setListener() {
        finish.setOnClickListener(v -> Hide());
        deleteUser.setOnClickListener(v -> {
            Hide();
            deleteUser();
        });
        changeUser.setOnClickListener(v -> {
            //TODO 更改管理员
            if (onMakeOverListener != null) {
                Hide();
                onMakeOverListener.makeover();
            }
        });
    }

    private void deleteUser() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", ID)
                    .put("sid", SID)
                    .put("uid", my_login.getItemInt("uid"))
                    .put("token", my_login.getItemString("token"))
                    .put("tokentype", 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.DELSHOPDMIN, jsonObject.toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean.getCode().equals("0")) {
                        ToastShow("删除成功");
                        EventBus.getDefault().post(new String("refresh"));
                        Hide();
                    } else {
                        ToastShow(baseBean.getInfo());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int ID;
    private int SID;

    public void show(View v, int id, int sid) {
        Show(v);
        this.ID = id;
        this.SID = sid;
    }

    public interface OnMakeOverListener {
        void makeover();
    }

}
