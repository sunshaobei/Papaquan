package xm.ppq.papaquan.View.accusation;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Presenter.accusation.Mutual_Accusation;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.Tool.shownews.DiscussUtil;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.MyApplication;

/**
 * Created by Administrator on 2017/3/9.
 */

public class AccusationActivity extends BaseActivity implements Round_Accusation, CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.report_topic)
    TextView report_topic;
    @BindView(R.id.up_accu)
    Button up_accu;
    @BindView(R.id.accu_edit)
    EditText accu_edit;
    @BindView(R.id.one)
    CheckBox one;
    @BindView(R.id.two)
    CheckBox two;
    @BindView(R.id.three)
    CheckBox three;
    @BindView(R.id.four)
    CheckBox four;
    @BindView(R.id.five)
    CheckBox five;
    @BindView(R.id.six)
    CheckBox six;
    @BindView(R.id.onetext)
    TextView onetext;
    @BindView(R.id.twotext)
    TextView twotext;
    @BindView(R.id.threetext)
    TextView threetext;
    @BindView(R.id.fourtext)
    TextView fourtext;
    @BindView(R.id.fivetext)
    TextView fivetext;
    @BindView(R.id.sixtext)
    TextView sixtext;

    private String sb = "";
    private Mutual_Accusation mutual_accusation;
    private SharedPreferencesPotting potting;
    private String uid;
    private String tid;
    private String commid;

    @Override
    protected int getLayout() {
        return R.layout.activity_accusation;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        String name = getData("name");
        uid = getData("uid");
        tid = getData("tid");
        commid = getData("commid");
        if (name != null) {
            report_topic.setText(DiscussUtil.getResult("举报", "#477db8", "@" + name, "的动态"));
        } else {
            report_topic.setText(DiscussUtil.getResult("举报", "#477db8", "***", "的动态"));
        }
        mutual_accusation = new Mutual_Accusation(this);
    }

    public void finish(View v) {
        finish();
    }

    @Override
    protected void setListener() {
        one.setOnCheckedChangeListener(this);
        two.setOnCheckedChangeListener(this);
        three.setOnCheckedChangeListener(this);
        four.setOnCheckedChangeListener(this);
        five.setOnCheckedChangeListener(this);
        six.setOnCheckedChangeListener(this);
        up_accu.setOnClickListener(v -> {
            if (!uid.equals(String.valueOf(potting.getItemInt("uid")))) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("uid", potting.getItemInt("uid"));
                    jsonObject.put("rid", uid);
                    jsonObject.put("tid", tid);
                    jsonObject.put("commentid", "");
                    jsonObject.put("report_type", sb);
                    jsonObject.put("remark", accu_edit.getText().toString());
                    jsonObject.put("citycode", potting.getItemString("citycode"));
                    if (commid != null) jsonObject.put("commentid", commid);
                    mutual_accusation.sendAccusation(jsonObject, BaseUrl.REPORT);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                ToastShow("不能举报自己");
            }
        });
    }

    @Override
    public void adviceSuccess(String s) {
        ToastShow(s);
        if (s.equals("举报成功")) {
            finish();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.one:
                if (isChecked) {
                    sb = sb + "垃圾营销、";
                } else {
                    sb = sb.replace("垃圾营销、", "");
                }
                break;
            case R.id.two:
                if (isChecked) {
                    sb = sb + "不实信息、";
                } else {
                    sb = sb.replace("不实信息、", "");
                }
                break;
            case R.id.three:
                if (isChecked) {
                    sb = sb + "有害信息、";
                } else {
                    sb = sb.replace("有害信息、", "");
                }
                break;
            case R.id.four:
                if (isChecked) {
                    sb = sb + "违法信息、";
                } else {
                    sb = sb.replace("违法信息、", "");
                }
                break;
            case R.id.five:
                if (isChecked) {
                    sb = sb + "淫秽色情、";
                } else {
                    sb = sb.replace("淫秽色情、", "");
                }
                break;
            case R.id.six:
                if (isChecked) {
                    sb = sb + "人身攻击、";
                } else {
                    sb = sb.replace("人身攻击、", "");
                }
                break;
        }
    }
}
