package xm.ppq.papaquan.View.Life.allsortofseller;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import xm.ppq.papaquan.Adapter.ConcreteAdapter;
import xm.ppq.papaquan.Adapter.base.ViewHolder;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.ImageLoading;
import xm.ppq.papaquan.Tool.JsonTool;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.classification.ClassificationActivity;

public class AllSortOfSellerActivity extends BaseActivity {

    @BindView(R.id.gridview)
    GridView gridview;

    private SharedPreferencesPotting potting;
    private ArrayList<TypeClassfiyBean.DataBean> dataBeen;

    @Override
    protected int getLayout() {
        return R.layout.activity_all_sort_of_seller;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        potting = new SharedPreferencesPotting(this, "my_login");
        JsonTool jsonTool = new JsonTool();
        jsonTool.put_key("citycode", "type")
                .put_value(potting.getItemString("citycode"), "2");
        OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.GETINDUSTRY, jsonTool.getJson().toString(), new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                if (s != null) {
                    TypeClassfiyBean typeClassfiyBean = (TypeClassfiyBean) JsonUtil.fromJson(s, TypeClassfiyBean.class);
                    if (typeClassfiyBean.getCode() == 0) {
                        dataBeen = typeClassfiyBean.getData();
                        gridview.setAdapter(new ConcreteAdapter<TypeClassfiyBean.DataBean>(getActivity(), typeClassfiyBean.getData(), R.layout.item_allsortofsellergridview) {
                            @Override
                            protected void convert(ViewHolder viewHolder, TypeClassfiyBean.DataBean item, int position) {
                                viewHolder.setText(item.getName(), R.id.textView);
                                ImageLoading.common(getActivity(), BaseUrl.BITMAP + item.getLogo(), viewHolder.getView(R.id.image), R.drawable.default_icon_zheng);
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void setListener() {
        gridview.setOnItemClickListener((parent, view, position, id) -> {
            if (dataBeen != null) {
                Intent intent = new Intent();
                intent.putExtra("bean", dataBeen.get(position));
                setResult(0x56, intent);
                finish();
            }
        });
    }

    /****************
     * onClick
     ***********************/
    public void finish(View v) {
        finish();
    }

}