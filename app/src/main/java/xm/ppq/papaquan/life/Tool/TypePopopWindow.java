package xm.ppq.papaquan.life.Tool;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import xm.ppq.papaquan.R;

/**
 * Created by Administrator on 2017/4/15.
 */

public class TypePopopWindow extends PopupWindowPotting {

    private TextView type_finish;
    private RelativeLayout one_lin;
    private RelativeLayout two_lin;
    private TextView biz_text;
    private ImageView biz_image;
    private TextView company_text;
    private ImageView company_image;

    private String select_result;
    private TextView textView;

    private OnItemListener onItemListener;

    public TypePopopWindow(Activity activity) {
        super(activity);
    }

    public TypePopopWindow(Activity activity, TextView textView) {
        super(activity);
        this.textView = textView;
    }

    public OnItemListener getOnItemListener() {
        return onItemListener;
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.type_popupwindow;
    }

    @Override
    protected void initUI() {
        type_finish = Bind(R.id.type_finish);
        one_lin = Bind(R.id.one_lin);
        two_lin = Bind(R.id.two_lin);
        biz_text = Bind(R.id.biz_text);
        biz_image = Bind(R.id.biz_image);
        company_text = Bind(R.id.company_text);
        company_image = Bind(R.id.company_image);
    }

    @Override
    public void Show(View view) {
        super.Show(view);
        initData();
    }

    private void initData() {
        int position = 8;
        select_result = textView.getText().toString();
        if (select_result != null) {
            switch (select_result) {
                case "商业商户":
                    position = 0;
                    break;
                case "企业/政府机构":
                    position = 1;
                    break;
                case "1年":
                    position = 0;
                    break;
                case "2年":
                    position = 1;
                    break;
            }
        }
        TextSelect(position, biz_text, company_text);
        ImageSelect(position, biz_image, company_image);
    }

    private String one = "商业商户";
    private String two = "企业/政府机构";

    public void setText(String one, String two, String one_m, String two_m) {
        this.one = one;
        this.two = two;
        money_one = one_m;
        money_two = two_m;
        biz_text.setText(one);
        company_text.setText(two);
    }

    private String money_one;
    private String money_two;

    @Override
    protected void setListener() {
        type_finish.setOnClickListener(v -> Hide());
        one_lin.setOnClickListener(v -> {
            TextSelect(0, biz_text, company_text);
            ImageSelect(0, biz_image, company_image);
            textView.setText(one);
            if (onItemListener != null) onItemListener.onItem(money_one);
            Hide();
        });
        two_lin.setOnClickListener(v -> {
            TextSelect(1, biz_text, company_text);
            ImageSelect(1, biz_image, company_image);
            textView.setText(two);
            if (onItemListener != null) onItemListener.onItem(money_two);
            Hide();
        });
    }

    private int on = Color.parseColor("#e60012");
    private int off = Color.parseColor("#333333");

    private void TextSelect(int position, TextView... textViews) {
        for (int i = 0; i < textViews.length; i++) {
            if (i == position) {
                textViews[i].setTextColor(on);
            } else {
                textViews[i].setTextColor(off);
            }
        }
    }

    private void ImageSelect(int position, ImageView... imageViews) {
        for (int i = 0; i < imageViews.length; i++) {
            if (position == i) {
                imageViews[i].setVisibility(View.VISIBLE);
            } else {
                imageViews[i].setVisibility(View.GONE);
            }
        }
    }

    public interface OnItemListener {
        void onItem(String result);
    }

}