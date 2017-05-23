package xm.ppq.papaquan.View.news_warn;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.DeployBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.ShSwitchView;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by 消息提醒  on 2017/4/9.
 */

public class NewsWarnActivity extends BaseActivity implements ShSwitchView.OnSwitchStateChangeListener {

    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.aite_mine)
    ShSwitchView aite_mine;
    @BindView(R.id.discuss)
    ShSwitchView discuss;
    @BindView(R.id.reply)
    ShSwitchView reply;
    @BindView(R.id.all_move)
    ShSwitchView all_move;

    private SharedPreferencesPotting potting;

    @Override
    protected int getLayout() {
        return R.layout.activity_newswarn;
    }

    /**
     * 0为关   1为开
     */

    private DeployBean.DataBean dataBean;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("消息提醒");
        all_move.setOn(true);
        potting = new SharedPreferencesPotting(this, "my_login");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1);
        OkPotting.getInstance(BaseUrl.PAPA_URL).AskOne(BaseUrl.GETMSGSET, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                if (dataBean != null) {
                    Setting(aite_mine, String.valueOf(dataBean.getAt()));
                    Setting(discuss, String.valueOf(dataBean.getComment()));
                    Setting(reply, String.valueOf(dataBean.getReply()));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    DeployBean deployBean = (DeployBean) JsonUtil.fromJson(s, DeployBean.class);
                    if (deployBean.getCode() == 0) {
                        dataBean = deployBean.getData();
                    }
                }
            }
        });
    }

    private void Setting(ShSwitchView view, String status) {
        if (status.equals("0")) {
            view.setOn(false);
        } else {
            view.setOn(true);
        }
    }

    private void ChangeStatus(ShSwitchView view, String type) {
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("uid", "token", "tokentype", "type")
                .put_value(potting.getItemInt("uid"), potting.getItemString("token"), 1, type);
        OkPotting.getInstance(BaseUrl.PAPA_URL).Ask(BaseUrl.SETMSG, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean != null) {
                    if (baseBean.getCode().equals("0")) {
                        ToastShow("提醒修改成功");
                    } else {
                        ToastShow(baseBean.getInfo());
                        if (view.isOn()) view.setOn(false);
                        else view.setOn(true);
                    }
                }
            }
        });
    }

    @Override
    protected void setListener() {
        aite_mine.setOnSwitchStateChangeListener(this);
        discuss.setOnSwitchStateChangeListener(this);
        reply.setOnSwitchStateChangeListener(this);
        finish_result.setOnClickListener(v -> finish());
    }

    @Override
    public void onSwitchStateChange(boolean isOn, View view) {
        String type = null;
        switch (view.getId()) {
            case R.id.aite_mine:
                type = "at";
                break;
            case R.id.discuss:
                type = "comment";
                break;
            case R.id.reply:
                type = "reply";
                break;
        }
        ChangeStatus((ShSwitchView) view, type);
    }
}