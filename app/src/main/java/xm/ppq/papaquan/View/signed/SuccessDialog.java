package xm.ppq.papaquan.View.signed;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import xm.ppq.papaquan.R;
import xm.ppq.papaquan.View.mine_integral.Mine_IntegralActivity;
import xm.ppq.papaquan.View.redcard.RedCardActivity;

import static xm.ppq.papaquan.View.signed.GiftAdapter.Prizetype;

/**
 * Created by sunshaobei on 2017/4/10.
 */

public class SuccessDialog extends DialogFragment {

    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.showrule)
    View showrule;
    @BindView(R.id.tv_rule)
    TextView tv_rule;
    @BindView(R.id.iknow)
    View iknow;

    //签到时显示内容
    @BindView(R.id.text)
    View text;
    //签到得的金币数量
    @BindView(R.id.count)
    TextView count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(STYLE_NO_TITLE);
        View view = inflater.inflate(R.layout.item_succesdialog, null);
        ButterKnife.bind(this, view);
        close.setOnClickListener(v -> {
            dismiss();
        });
        image.setOnClickListener(v -> {
            onSureClickListener.onSureClick();
        });
        iknow.setOnClickListener(v -> {
            dismiss();
        });
        String tag = getTag();
        //签到
        switch (tag) {
            case "signed":
                image.setImageResource(R.mipmap.qdsuccess);
                image.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                close.setVisibility(View.VISIBLE);
                showrule.setVisibility(View.GONE);
                tv_rule.setVisibility(View.GONE);
                iknow.setVisibility(View.GONE);
                break;
            //领取奖励
            case "receive":
                image.setImageResource(R.mipmap.lqsuccess);
                image.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                close.setVisibility(View.VISIBLE);
                showrule.setVisibility(View.GONE);
                tv_rule.setVisibility(View.GONE);
                iknow.setVisibility(View.GONE);
                image.setOnClickListener(v -> {
                    if (Prizetype == 1) {
                        SuccessDialog.this.startActivity(new Intent(getActivity(), Mine_IntegralActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), RedCardActivity.class));
                    }
                    dismiss();
                });
                break;
            case "rule":
                image.setVisibility(View.GONE);
                close.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
                showrule.setVisibility(View.VISIBLE);
                tv_rule.setVisibility(View.VISIBLE);
                iknow.setVisibility(View.VISIBLE);
                tv_rule.setText("1.用户每天可签到一次，每次获得5金币奖励；\n" +
                        "\n" +
                        "2.连续签到为连续天数累加，中途断隔1天将会重置连续签到天数；\n" +
                        "\n" +
                        "3.连续签到及累计签到奖励，达到指定签到天数后即可领取相应的奖励，每项奖励不可重复领取；\n" +
                        "\n" +
                        "4.金币奖励直接返还到用户个人中心，红卡奖励将直接给与开通红卡资格，有效期领取即生效；\n" +
                        "\n" +
                        "5.以上活动内容，在法律允许范围内，最终解释权归啪啪圈所有，如有疑问，请联系客服进行咨询。");
                break;
        }

        return view;
    }

    private OnSureClickListener onSureClickListener;

    public interface OnSureClickListener {
        void onSureClick();
    }

    public void setOnSureClickListener(OnSureClickListener o) {
        this.onSureClickListener = o;
    }
}
