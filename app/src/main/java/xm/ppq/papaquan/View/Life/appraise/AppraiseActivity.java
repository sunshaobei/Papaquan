package xm.ppq.papaquan.View.Life.appraise;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.DynamciGridAdapter;
import xm.ppq.papaquan.Bean.life.TitleBean;
import xm.ppq.papaquan.Bean.life.TitleZBean;
import xm.ppq.papaquan.Presenter.life.appraise.PofAppraise;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ALi_ossInterface;
import xm.ppq.papaquan.Tool.ALioss;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.selectpic_activity.SelectPictureActivity;

/**
 * 评价
 */
public class AppraiseActivity extends BaseActivity implements DataUtils, ALi_ossInterface {

    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.addpic)
    RelativeLayout addpic;
    private ArrayList<String> list = new ArrayList<>();
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.flowlayout)
    TagFlowLayout flowlayout;

    private int pid;
    private ArrayList<String> piclist = new ArrayList<>();
    private ArrayList<String> selectedPicture = new ArrayList<>();
    private DynamciGridAdapter gridAdapter;
    private PofAppraise pofAppraise;
    private ALioss aLioss;
    private String type;
    private int sid;

    @Override
    protected int getLayout() {
        return R.layout.activity_appraise;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(bar);
        sid = getIntent().getIntExtra("sid", 0);
        type = getIntent().getStringExtra("type");
        pid = getIntent().getIntExtra("pid", -1);
        pofAppraise = new PofAppraise(this);
        if (type.equals("抢购")) {
            pofAppraise.getTitle(type, BaseUrl.REBLTE);
        } else if (type.equals("精选")) {
            pofAppraise.getTitle(type, BaseUrl.APPRAISE);
        } else if (type.equals("折扣")) {
            big_title.setText(getData("name"));
            small_title.setText(getData("title"));
        }
        aLioss = new ALioss(this);
        list.clear();
        list.add("非常不错！推荐");
        list.add("服务很好，给予好评！");
        list.add("好评！");
        list.add("体验不错，下次还来");
        list.add("环境很好，服务到位，我推荐！");
        flowlayout.setBackground(new ColorDrawable(Color.TRANSPARENT));
        flowlayout.setAdapter(new TagAdapter<String>(list) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView textView = new TextView(getActivity());
                textView.setTextSize(10);
                textView.setPadding(20, 20, 20, 20);
                textView.setTextColor(Color.BLACK);
                textView.setText(list.get(position));
                textView.setBackground(getResources().getDrawable(R.drawable.bg_flowlayout2));
                return textView;
            }
        });
        gridAdapter = new DynamciGridAdapter(piclist, this, gridview, addpic);
        gridview.setAdapter(gridAdapter);
    }

    @Override
    protected void setListener() {
        addpic.setOnClickListener(v -> {
            Intent intent = new Intent(this, SelectPictureActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("allSelectedPicture", piclist);
            intent.putExtras(bundle);
            if (piclist.size() < 9) {
                startActivityForResult(intent, IntentCode.RequestCode.TOPIC);
            }
        });
        flowlayout.setOnTagClickListener((view, position, parent) -> {
            edit.append(list.get(position) + " ");
            edit.setSelection(edit.getText().toString().length());
            return true;
        });
    }

    /************************************
     * 点击事件
     **********************************/
    public void finish(View v) {
        finish();
    }

    public void backtohome(View v) {
        setResult(IntentCode.ResultCode.BACKTOLIFE);
        finish();
    }

    public void appraise(View v) {
        if (piclist.size() > 0) {
            new Thread(() -> {
                for (int i = 0; i < piclist.size(); i++) {
                    String s = OwnUtil.compressImage(piclist.get(i), piclist.get(i) + "compress", 30);
                    aLioss.init(s, "pid" + i + ".jpg", 1);
                }
            }).start();
        } else {
            if (type.equals("抢购")) {
                pofAppraise.appraise(pid, edit.getText().toString(), upimagelist);
            } else if (type.equals("精选")) {
                pofAppraise.Coupon(pid, edit.getText().toString(), upimagelist);
            } else if (type.equals("折扣")) {
                pofAppraise.Discount(pid, edit.getText().toString(), upimagelist);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IntentCode.RequestCode.TOPIC && resultCode == RESULT_OK) {
            selectedPicture = (ArrayList) data.getSerializableExtra(SelectPictureActivity.INTENT_SELECTED_PICTURE);
            if (selectedPicture.size() != 0) {
                gridview.setVisibility(View.VISIBLE);
                addpic.setVisibility(View.GONE);
                for (String str : selectedPicture) {
                    if (!piclist.contains(str)) {
                        piclist.add(str);
                    }
                }
                gridAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getSid() {
        return sid;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void appraiseSuccess(String info) {
        ToastShow(info);
        setResult(IntentCode.ResultCode.BACKTOAPPRAISE);
        finish();
    }

    @Override
    public void appraiseError(String error) {
        ToastShow(error);
    }

    @BindView(R.id.big_title)
    TextView big_title;
    @BindView(R.id.small_title)
    TextView small_title;

    @Override
    public void setTitle_J(TitleBean.DataBean dataBean) {
        big_title.setText(dataBean.getTitle());
        small_title.setText(dataBean.getShopname());
    }

    @Override
    public void setTitle_Z(TitleZBean.DataBean dataBean) {
        big_title.setText(dataBean.getTitle());
        small_title.setText(dataBean.getShopname());
    }

    private ArrayList<String> upimagelist = new ArrayList<>();

    @Override
    public void upImageSuccess(String url) {
        //TODO 图片上传成功后，做评论操作
        upimagelist.add(url);
        if (upimagelist.size() == piclist.size()) {
            if (type.equals("抢购")) {
                pofAppraise.appraise(pid, edit.getText().toString(), upimagelist);
            } else if (type.equals("精选")) {
                pofAppraise.Coupon(pid, edit.getText().toString(), upimagelist);
            } else if (type.equals("折扣")) {
                pofAppraise.Discount(pid, edit.getText().toString(), upimagelist);
            }
            upimagelist.clear();
        }
    }

    @Override
    public void upImageError(String s) {

    }

    @Override
    public void upVideoSuccess(String s) {

    }

    @Override
    public void upProgress(int progress) {

    }

    @Override
    public void setVideoPic(String url) {

    }
}
