package xm.ppq.papaquan.View.Life.goodscommet;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lib_sunshaobei2017.app.SunActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.life.JudgePanicBuyBean;
import xm.ppq.papaquan.Bean.life.JudgeRebateBean;
import xm.ppq.papaquan.Presenter.life.goodscomment.Mutual_GoodsComment;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseUrl;

public class GoodsCommentActivity extends SunActivity implements Round_GoodsComment {

    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;

    private GoodsCommentAdapter goodsCommentAdapter;
    private int pid;
    private SharedPreferencesPotting my_login;
    private Mutual_GoodsComment mutual_goodsComment;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_comment);
        ButterKnife.bind(this);
        my_login = new SharedPreferencesPotting(this, "my_login");
        initStatusBar(bar);
        initData();
        initListener();
    }

    private String type = "all";

    private void initData() {
        url = getIntent().getStringExtra("url");
        pid = getIntent().getIntExtra("pid", 0);
        mutual_goodsComment = new Mutual_GoodsComment(this);
        UrlSelect();
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radio1:
                    if (url.equals(BaseUrl.PANICBUYAPPRAISE)) {
                        type = "all";
                    } else {
                        type = "0";
                    }
                    break;
                case R.id.radio2:
                    if (url.equals(BaseUrl.PANICBUYAPPRAISE)) {
                        type = "pic";
                    } else {
                        type = "1";
                    }
                    break;
            }
            UrlSelect();
        });
    }

    private void UrlSelect() {
        if (url.equals(BaseUrl.PANICBUYAPPRAISE)) {
            mutual_goodsComment.getPanicBuy();
        } else if (url.equals(BaseUrl.REBATE_APPLIST)) {
            mutual_goodsComment.getRebate();
        } else if (url.equals(BaseUrl.APPLIST)) {
            mutual_goodsComment.getCoupon();
        }
    }

    /******************************
     * wonClick
     *******************************/
    public void finish(View v) {
        finish();
    }

    public void share(View v) {
        ShareDialog shareDialog = new ShareDialog(this, R.layout.deteil_share);
        shareDialog.Show(v);
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getUid() {
        return my_login.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return my_login.getItemString("token");
    }

    private int page = 0;

    @Override
    public void setPanicBuy(JudgePanicBuyBean.DataBean dataBean) {
        if (dataBean.getAllcount() > 99) dataBean.setAllcount(99);
        radio1.setText("全部（" + dataBean.getAllcount() + "）");
        if (dataBean.getPiccount() > 99) dataBean.setPiccount(99);
        radio2.setText("有图（" + dataBean.getPiccount() + "）");
        if (dataBean.getList() != null) {
            if (goodsCommentAdapter == null) {
                goodsCommentAdapter = new GoodsCommentAdapter<>(this, dataBean.getList(), R.layout.item_goodscommentlistview);
                goodsCommentAdapter.setType("抢购");
                listview.setAdapter(goodsCommentAdapter);
            } else {
                goodsCommentAdapter.setList(dataBean.getList());
                goodsCommentAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setRebate(JudgeRebateBean bean) {
        JudgeRebateBean.OtherBean other = bean.getOther();
        if (other.getAllnum() > 99) other.setAllnum(99);
        radio1.setText("全部（" + other.getAllnum() + "）");
        if (other.getHaspic() > 99) other.setHaspic(99);
        radio2.setText("有图（" + other.getHaspic() + "）");
        if (bean.getData() != null) {
            if (goodsCommentAdapter == null) {
                goodsCommentAdapter = new GoodsCommentAdapter<>(this, bean.getData(), R.layout.item_goodscommentlistview);
                goodsCommentAdapter.setType("折扣");
                listview.setAdapter(goodsCommentAdapter);
            } else {
                goodsCommentAdapter.setList(bean.getData());
                goodsCommentAdapter.notifyDataSetChanged();
            }
        }
    }
}