package xm.ppq.papaquan.life.Tool;

import android.content.Context;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rx.Subscriber;
import xm.ppq.papaquan.Adapter.JudgeOfAdapter;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.Bean.life.JudgeOfBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.BaseDialog;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/4/15.
 */

public class Reply_Judge_Dialog extends BaseDialog {

    private EditText edit_result;
    private TextView queding, quxiao;
    private String id;
    private String sid;
    private SharedPreferencesPotting potting;
    private JudgeOfBean.DataBean dataBean;

    public Reply_Judge_Dialog(Context context, int themeResId) {
        super(context, R.style.dialog);
    }

    @Override
    protected int getLayout() {
        return R.layout.reply_judge_dialog;
    }

    @Override
    protected void initUI() {
        potting = new SharedPreferencesPotting(getContext(), "my_login");
        edit_result = bind(R.id.edit_result);
        queding = bind(R.id.queding);
        quxiao = bind(R.id.quxiao);
        quxiao.setOnClickListener(v -> hide());
        queding.setOnClickListener(view -> {
            JsonTool jsonTool = new JsonTool();
            jsonTool.put_key("id", "sid", "uid", "content", "token", "tokentype")
                    .put_value(id, sid, potting.getItemInt("uid"), edit_result.getText().toString(), potting.getItemString("token"), 1);
            OkPotting.getInstance(BaseUrl.LIFE_URL).Ask(BaseUrl.SHOPREPLY, jsonTool.getJson().toString(), new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    if (baseBean.getCode().equals("0")) {
                        Toast.makeText(getContext(), "回复成功", Toast.LENGTH_SHORT).show();
                        if (dataBean != null) {
                            dataBean.setBusiness_reply(baseBean.getData());
                            adapter.notifyDataSetChanged();
                        }
                        hide();
                    } else {
                        Toast.makeText(getContext(), baseBean.getInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    private JudgeOfAdapter adapter;

    public void setDataBean(JudgeOfBean.DataBean dataBean, JudgeOfAdapter adapter) {
        this.dataBean = dataBean;
        this.adapter = adapter;
    }

    public Reply_Judge_Dialog setIDAll(String id, String sid) {
        this.id = id;
        this.sid = sid;
        return this;
    }

}
