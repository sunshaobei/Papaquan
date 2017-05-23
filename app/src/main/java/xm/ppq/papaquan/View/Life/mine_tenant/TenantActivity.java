package xm.ppq.papaquan.View.Life.mine_tenant;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xm.ppq.papaquan.Adapter.TenantAdapter;
import xm.ppq.papaquan.Bean.life.TenantData;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ShareDialog;
import xm.ppq.papaquan.Tool.intent_code.IntentCode;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.mer_info.MerInfoActivity;
import xm.ppq.papaquan.View.Life.merchant_homepage.Merchant_HomepageActivity;
import xm.ppq.papaquan.View.Life.mine_tenant.M.ModelUtil;

/**
 * Created by 我的商户 on 2017/4/12.
 */

public class TenantActivity extends BaseActivity implements Round_Tenant {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.right_image)
    ImageView right_image;
    @BindView(R.id.tenant_list)
    ListView tenant_list;
    @BindView(R.id.all_tenant)
    LinearLayout all_tenant;

    private List<TenantData.DataBean> list = new ArrayList<>();
    private ShareDialog dialog;
    private TenantAdapter adapter;
    private ModelUtil model;

    @Override
    protected int getLayout() {
        return R.layout.activity_mine_tenant;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        finish_result.setText("");
        center_result.setText("我的商户");
        right_image.setImageResource(R.drawable.share);
        dialog = new ShareDialog(this, R.layout.deteil_share);
        adapter = new TenantAdapter(this, list, R.layout.manage_item_tenant);
        tenant_list.setAdapter(adapter);
        View inflate = LayoutInflater.from(this).inflate(R.layout.manage_foot_list, null);
        inflate.setOnClickListener(v -> Skip(MerInfoActivity.class, "title", "完善商户信息"));
        tenant_list.addFooterView(inflate);
        model = new ModelUtil(this);
        model.getData();
    }

    @Override
    protected void setListener() {
        tenant_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position<list.size()-1)
                {
                    Intent intent = new Intent(TenantActivity.this, Merchant_HomepageActivity.class);
                    intent.putExtra("sid",list.get(position).getId()+"");
                    startActivity(intent);
                }
            }
        });
        finish_result.setOnClickListener(v -> finish());
        right_image.setOnClickListener(v -> dialog.Show(v));
    }

    @Override
    public void setList(List<TenantData.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == IntentCode.ResultCode.BACKTOLIFE) {
            setResult(IntentCode.ResultCode.BACKTOLIFE);
            finish();
        }
    }
}
