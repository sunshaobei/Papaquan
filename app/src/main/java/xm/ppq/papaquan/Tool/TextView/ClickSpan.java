package xm.ppq.papaquan.Tool.TextView;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import xm.ppq.papaquan.Adapter.MoneyMineAdapter;
import xm.ppq.papaquan.Tool.SharedPreferencesPotting;
import xm.ppq.papaquan.View.tasheet.Ta_SheetActivity;
import xm.ppq.papaquan.View.topic_deail.Topic_DetailActivity;

/**
 * Created by Administrator on 2017/4/8.
 */

public class ClickSpan extends ClickableSpan {

    private Context context;
    private String memberName = "";
    private SharedPreferencesPotting potting;

    public ClickSpan(Context context, String memberName) {
        this.context = context;
        this.memberName = memberName;
        potting = new SharedPreferencesPotting(context, "my_login");
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (memberName.startsWith("#")) {
            intent = new Intent(context, Topic_DetailActivity.class);
            intent.putExtra("hotid", memberName);
        } else {
            String name = "@" + potting.getItemString("nickname");
            if (!name.equals(memberName)) {
                intent = new Intent(context, Ta_SheetActivity.class);
                intent.putExtra("Uuid", memberName);
            }
        }
        if (intent != null) {
            context.startActivity(intent);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        /** 给文字染色 **/
        ds.setARGB(255, 91, 106, 145);
        /** 去掉下划线 ， 默认自带下划线 **/
        ds.setUnderlineText(false);
    }
}