package xm.ppq.papaquan.View.mineaddress;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Adapter.AddressAdapter;
import xm.ppq.papaquan.Bean.AddressBean;
import xm.ppq.papaquan.Presenter.mineaddress.Mutual_Mine_Address;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SlideListView;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.make_address.Make_AddressActivity;

/**
 * Created by 个人地址 on 2017/2/23.
 */

public class MineAddressActivity extends BaseActivity implements View.OnClickListener, Round_MineAddress {

    @BindView(R.id.address_list)
    SlideListView address_list;
    @BindView(R.id.add_address)
    LinearLayout add_address;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.bar)
    LinearLayout statusBar;

    private AddressAdapter adapter;
    private Mutual_Mine_Address mine_address;

    @Override
    protected int getLayout() {
        return R.layout.activity_mineaddress;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initStatusBar(statusBar);
        center_result.setText("我的地址");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mine_address = new Mutual_Mine_Address(this);
        mine_address.start();
    }

    @Override
    protected void setListener() {
        add_address.setOnClickListener(this);
        finish_result.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address:
                Skip(Make_AddressActivity.class);
                break;
            case R.id.finish_result:
                finish();
                break;
        }
    }


    @Override
    public void setListAddress(List<AddressBean.Data> address) {
        if (adapter == null) {
            adapter = new AddressAdapter(this, address, R.layout.address_list_item);
            address_list.setAdapter(adapter);
        } else {
            adapter.setList(address);
            adapter.notifyDataSetChanged();
        }
    }

}
