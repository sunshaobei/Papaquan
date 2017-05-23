package xm.ppq.papaquan.Presenter.life.coupondetail;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import xm.ppq.papaquan.Bean.ProductBean;
import xm.ppq.papaquan.Bean.life.ProductDetail;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.Tool.JsonUtil;
import xm.ppq.papaquan.Tool.OkPotting;
import xm.ppq.papaquan.Tool.OwnUtil;
import xm.ppq.papaquan.View.BaseUrl;
import xm.ppq.papaquan.View.Life.productdetail.GetData;

/**
 * Created by sunshaobei on 2017/4/25.
 */

public class PofProductDetail implements Presenter {

    private GetData getData;

    public PofProductDetail(GetData getData) {
        this.getData = getData;
    }

    @Override
    public void getData(String cid) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cid", cid);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.COUPONDETAIL, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String o) {
                    if (o != null) {
                        ProductBean productBean = (ProductBean) JsonUtil.fromJson(o, ProductBean.class);
                        if (productBean.getCode() == 0) {
                            getData.setData(productBean);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ImageText(String cid) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cid", cid);
            OkPotting.getInstance(BaseUrl.LIFE_URL).AskOne(BaseUrl.IMAGETEXT, jsonObject.toString(), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(String s) {
                    if (s != null) {
                        if (JsonUtil.getInt(s, "code") == 0) {
                            String code = JsonUtil.getString(s, "code");
                            code = JsonUtil.getString(code, "picdetails");
                            getData.setImageText(code);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public RadioButton CreateButton(Context context, String result) {
        RadioButton radioButton = new RadioButton(context);
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 0, 0);
        radioButton.setLayoutParams(layoutParams);
        radioButton.setMaxLines(1);
        radioButton.setButtonDrawable(null);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setBackground(context.getResources().getDrawable(R.drawable.pdradiogrounp));
        radioButton.setTextColor(context.getResources().getColorStateList(R.color.pd_color));
        radioButton.setPadding(10, 10, 10, 10);
        radioButton.setText(result);
        return radioButton;
    }
}