package xm.ppq.papaquan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.AddressBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.Stringutil;
import xm.ppq.papaquan.View.make_address.Make_AddressActivity;

/**
 * Created by Administrator on 2017/2/23.
 */

public class AddressAdapter extends ConcreteAdapter<AddressBean.Data> {

    public AddressAdapter(Context context, List<AddressBean.Data> list, int itemLayout) {
        super(context, list, itemLayout);
    }

    @Override
    protected void convert(ViewHolder viewHolder, final AddressBean.Data item, int position) {
        viewHolder.setText(item.username, R.id.address_name)
                .setText(Stringutil.DisplaceAddress(item.province)+" "+ Stringutil.DisplaceAddress(item.city)+" "+ Stringutil.DisplaceAddress(item.area)+" "+ Stringutil.DisplaceAddress(item.street), R.id.address_double)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Make_AddressActivity.class);
                        intent.putExtra("action", Integer.valueOf(item.id));
                        intent.putExtra("province", Stringutil.DisplaceAddress(item.province));
                        intent.putExtra("city",Stringutil.DisplaceAddress(item.city));
                        intent.putExtra("area",Stringutil.DisplaceAddress(item.area));
                        intent.putExtra("street",Stringutil.DisplaceAddress(item.street));
                        intent.putExtra("username",Stringutil.DisplaceAddress(item.username));
                        intent.putExtra("deliverytel",Stringutil.DisplaceAddress(item.deliverytel));
                        intent.putExtra("zipcode",Stringutil.DisplaceAddress(item.zipcode));
                        intent.putExtra("isdefault",Stringutil.DisplaceAddress(item.isdefault));
                        getContext().startActivity(intent);
                    }
                }, R.id.address_make);
    }
}