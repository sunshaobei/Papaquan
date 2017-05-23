package xm.ppq.papaquan.View.Life.mer_info;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.Bean.ForSureBean;
import xm.ppq.papaquan.Bean.TypeClassfiyBean;
import xm.ppq.papaquan.Bean.TypeThreeBean;
import xm.ppq.papaquan.Bean.life.MerInfoBean;
import xm.ppq.papaquan.Bean.life.OtherBean;
import xm.ppq.papaquan.Presenter.mer_info.Mutual_Mer_Info;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.BaseActivity;
import xm.ppq.papaquan.View.Life.forsuresellerlocation.ForSureSellerLocationActivity;
import xm.ppq.papaquan.View.Life.othersetting.OtherSettingActivity;
import xm.ppq.papaquan.View.Life.upbusinesslicence.UpBusinessLicenceActivity;
import xm.ppq.papaquan.View.Life.upsellerimage.UpSellerImageActivity;
import xm.ppq.papaquan.life.Tool.TypePopopWindow;

/**
 * Created by 商户信息 on 2017/4/14.
 */

public class MerInfoActivity extends BaseActivity implements Round_Mer_Info {

    @BindView(R.id.finish_result)
    TextView finish_result;
    @BindView(R.id.center_result)
    TextView center_result;
    @BindView(R.id.type_text)
    TextView type_text;
    @BindView(R.id.info_type)
    LinearLayout info_type;
    @BindView(R.id.type_classify)
    LinearLayout type_classify;
    @BindView(R.id.classify_text)
    TextView classify_text;
    @BindView(R.id.info_address)
    LinearLayout info_address;
    @BindView(R.id.info_photo)
    LinearLayout info_photo;
    @BindView(R.id.info_certificate)
    LinearLayout info_certificate;
    @BindView(R.id.info_patent)
    LinearLayout info_patent;
    @BindView(R.id.info_setting)
    LinearLayout info_setting;
    @BindView(R.id.shop_name)
    EditText shop_name;
    @BindView(R.id.contact_number)
    LinearLayout contact_number;
    @BindView(R.id.linkman)
    LinearLayout linkman;
    @BindView(R.id.news)
    TextView news;
    @BindView(R.id.edit_tel)
    EditText edit_tel;
    @BindView(R.id.address_info_text)
    TextView address_info_text;

    private TypePopopWindow typePopopWindow;
    private SharedPreferencesPotting potting;
    private Mutual_Mer_Info mutual_mer_info;

    private OptionsPickerView type;
    private String title;
    private String sid;

    @Override
    protected int getLayout() {
        return R.layout.activity_mer_info;
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this);
        title = getData("title");
        potting = new SharedPreferencesPotting(this, "my_login");
        mutual_mer_info = new Mutual_Mer_Info(this);
        mutual_mer_info.startclassify();
        finish_result.setText("");
        center_result.setText(title);
        if (title.equals("商户信息")) {
            sid = getData("sid");
            news.setVisibility(View.GONE);
            linkman.setVisibility(View.GONE);
            contact_number.setVisibility(View.GONE);
            mutual_mer_info.getShopInfo();
        } else {
            news.setVisibility(View.VISIBLE);
            linkman.setVisibility(View.VISIBLE);
            contact_number.setVisibility(View.VISIBLE);
        }
        typePopopWindow = new TypePopopWindow(this, type_text);
    }

    @Override
    protected void setListener() {
        finish_result.setOnClickListener(v -> finish());
        info_type.setOnClickListener(v -> typePopopWindow.Show(v));
        type_classify.setOnClickListener(v -> {
            if (children.size() > 0) {
                type.show();
            }
        });
        info_address.setOnClickListener(v -> Skip(ForSureSellerLocationActivity.class));
        info_photo.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpSellerImageActivity.class);
            intent.putExtra("type", "商户");
            if (one != null) {
                intent.putStringArrayListExtra("one", one);
            }
            startActivity(intent);
        });
        info_certificate.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpSellerImageActivity.class);
            intent.putExtra("type", "经营许可证");
            if (three != null) {
                intent.putStringArrayListExtra("three", three);
            }
            startActivity(intent);
        });
        info_patent.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpBusinessLicenceActivity.class);
            if (two != null) {
                intent.putStringArrayListExtra("two", two);
            }
            startActivity(intent);
        });
        info_setting.setOnClickListener(v -> {
            Intent intent = new Intent(this, OtherSettingActivity.class);
            if (otherBean != null) {
                intent.putExtra("data", otherBean);
            }
            startActivity(intent);
        });
    }

    @BindView(R.id.photo_text)
    TextView photo_text;
    @BindView(R.id.qita_steeing)
    TextView qita_steeing;

    private OtherBean otherBean;
    private String result;
    private String id = "";

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getSid() {
        return sid;
    }

    @Override
    public String getCityCode() {
        return potting.getItemString("citycode");
    }

    @Override
    public int getUid() {
        return potting.getItemInt("uid");
    }

    @Override
    public String getToken() {
        return potting.getItemString("token");
    }

    @Override
    public String getName() {
        String name = shop_name.getText().toString();
        if (name.equals("")) {
            ToastShow("商家名字不能为空!");
            return null;
        } else {
            return name;
        }
    }

    @Subscribe
    public void Setting(OtherBean otherBean) {
        this.otherBean = otherBean;
        if (otherBean != null) {
            result = "";
            if (otherBean.getBox() == 1) {
                result += "包厢/";
            }
            if (otherBean.getCar() == 1) {
                result += "车位/";
            }
            if (otherBean.getConditioner() == 1) {
                result += "空调/";
            }
            if (otherBean.getWifi() == 1) {
                result += "wifi/";
            }
            result += "等";
            qita_steeing.setText(result);
        }
    }

    @BindView(R.id.business_certificate)
    TextView business_certificate;
    @BindView(R.id.business_licence)
    TextView business_licence;

    private ArrayList<String> one;//商户
    private ArrayList<String> two;//营业执照
    private ArrayList<String> three;//经营许可证

    @Subscribe
    public void Icon(TypeThreeBean bean) {
        if (bean.getType() == 0) {//商户照片
            if (bean.getList().size() > 0) {
                one = bean.getList();
                photo_text.setText("已上传");
            }
        } else if (bean.getType() == 1) {//营业执照
            if (bean.getList().size() > 0) {
                business_licence.setText("已上传");
                two = bean.getList();
            }
        } else if (bean.getType() == 2) {//经营许可证
            if (bean.getList().size() > 0) {
                business_certificate.setText("已上传");
                three = bean.getList();
            }
        }
    }

    private ForSureBean forSureBean;

    @Subscribe
    public void Address(ForSureBean forSureBean) {
        this.forSureBean = forSureBean;
        address_info_text.setText(forSureBean.getAddress());
    }

    @Override
    public JSONArray getPhoto() {
        if (one != null) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < one.size(); i++) {
                jsonArray.put(one.get(i));
            }
            return jsonArray;
        }
        return null;
    }

    @Override
    public String getPhoto2() {
        if (two != null) return two.get(0);
        return null;
    }

    @Override
    public String getPhoto3() {
        if (three != null) return three.get(0);
        return null;
    }

    @Override
    public String getTel() {
        return edit_tel.getText().toString();
    }

    @Override
    public int getShopType() {
        if (type_text.getText().toString().equals("商业商户")) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getIndOne() {
        return ind_one;
    }

    @Override
    public int getIndTwo() {
        return ind_two;
    }

    @Override
    public OtherBean getOther() {
        return otherBean;
    }

    @Override
    public ForSureBean getForSure() {
        if (forSureBean != null) {
            return forSureBean;
        } else {
            return null;
        }
    }

    @BindView(R.id.photo_woman)
    EditText photo_woman;
    @BindView(R.id.photo_number)
    EditText photo_number;

    @Override
    public String getPhotoWoman() {
        return photo_woman.getText().toString();
    }

    @Override
    public String getPhtooNumber() {
        return photo_number.getText().toString();
    }

    private int ind_one;
    private int ind_two;

    private ArrayList<TypeClassfiyBean.DataBean> dataBeen;
    private ArrayList<ArrayList<TypeClassfiyBean.DataBean.ChildrenBean>> children = new ArrayList<>();

    @Override
    public void setTypeClassify(ArrayList<TypeClassfiyBean.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
        if (dataBeen.size() > 0) {
            for (int i = 0; i < dataBeen.size(); i++) {
                ArrayList<TypeClassfiyBean.DataBean.ChildrenBean> been = new ArrayList<>();
                been.addAll(dataBeen.get(i).getChildren());
                children.add(been);
            }
            type = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
                ind_one = dataBeen.get(options1).getId();
                ind_two = children.get(options1).get(options2).getId();
                classify_text.setText(dataBeen.get(options1).getName() + "/" + children.get(options1).get(options2).getName());
            }).setLayoutRes(R.layout.type_item, v -> {
                TextView fulfil_type = (TextView) v.findViewById(R.id.fulfil_type);
                TextView finish_type = (TextView) v.findViewById(R.id.finish_type);
                fulfil_type.setOnClickListener(v1 -> {
                    type.returnData();
                    type.dismiss();
                });
                finish_type.setOnClickListener(v2 -> type.dismiss());
            })
                    .isDialog(false)
                    .setOutSideCancelable(true)
                    .setContentTextSize(17)
                    .setBgColor(Color.parseColor("#f9f9f9"))
                    .build();
            type.setPicker(dataBeen, children);
        }
    }

    @Override
    public void setBean(MerInfoBean.DataBean dataBean) {
        if (otherBean == null) {
            otherBean = new OtherBean();
            otherBean.setBox(dataBean.getRoom());
            otherBean.setCar(dataBean.getCar());
            otherBean.setMoney(dataBean.getConsume());
            otherBean.setWifi(dataBean.getWifi());
            otherBean.setTime(dataBean.getBusiness_hours());
            otherBean.setConditioner(dataBean.getAir());
        }
        id = String.valueOf(dataBean.getId());
        if (dataBean.getShoptype() == 1) {
            type_text.setText("商业商户");
        } else {
            type_text.setText("企业/政府机构");
        }
        shop_name.setText(dataBean.getName());
        address_info_text.setText(dataBean.getAddress());
        edit_tel.setText(dataBean.getTel());
        if (one == null) {
            if (dataBean.getLogo().size() > 0) {
                one = (ArrayList<String>) dataBean.getLogo();
                photo_text.setText("已上传");
            } else {
                photo_text.setText("未上传");
            }
        }
        if (two == null) {
            if (dataBean.getBusiness_license() != null) {
                two = new ArrayList<>();
                two.add(dataBean.getBusiness_license());
                business_licence.setText("已上传");
            } else {
                business_licence.setText("未上传");
            }
        }
        if (three == null) {
            if (dataBean.getBusiness_certificate() != null) {
                three = new ArrayList<>();
                three.add(dataBean.getBusiness_certificate());
                business_certificate.setText("已上传");
            } else {
                business_certificate.setText("未上传");
            }
        }
        String result = "";
        if (dataBean.getRoom() == 1) {
            result += "包厢/";
        }
        if (dataBean.getCar() == 1) {
            result += "车位/";
        }
        if (dataBean.getAir() == 1) {
            result += "空调/";
        }
        if (dataBean.getWifi() == 1) {
            result += "wifi/";
        }
        result += "等";
        qita_steeing.setText(result);
        if (dataBeen != null) {
            for (int i = 0; i < dataBeen.size(); i++) {
                if (dataBean.getIndustry() == dataBeen.get(i).getId()) {
                    for (int i1 = 0; i1 < dataBeen.get(i).getChildren().size(); i1++) {
                        if (dataBean.getIndustry_two() == dataBeen.get(i).getChildren().get(i1).getId()) {
                            classify_text.setText(dataBeen.get(i).getName() + "/" + dataBeen.get(i).getChildren().get(i1).getName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void SuccesShow() {
        ToastShow("商家提交成功");
        finish();
    }

    @Override
    public void ToastShow(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ErrorShow() {
        ToastShow("商家提交失败，请检查信息是否完整");
    }

    public void network(View view) {
        mutual_mer_info.startApply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}