package xm.ppq.papaquan.Tool.TextView;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xm.ppq.papaquan.Tool.typewriting.SpanStringUtils;

/**
 * Created by Administrator on 2017/4/8.
 */

public class SpanTextView extends TextView {

    private SpannableString spannableString;
    private Context context;
    private final String AiteRule = "@[^,，：:\\s@]+";
    private String MatchRule;
    private String str = "";
    private MovementMethod mMovement;

    public SpanTextView(Context context) {
        super(context);
        this.context = context;
    }

    public SpanTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SpanTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public boolean hasFocusable() {
        return false;
    }

    /**
     * @param text
     * @return
     */
    public void setText(String text) {
        if (text != null) {
            if (getMatchRule() == null) {
            /* 默认一个@的匹配规则 */
                setMatchRule(AiteRule);
            }
            super.setText(setHighlight(text, getMatchRule()));
        }
    }

    int size;
    public CharSequence DisplaceTitle(String result) {
        if (result != null) {
            if (result.startsWith("#")) {
                try {
                    int start = result.indexOf("#");
                    int end = result.indexOf("#", 1) + 1;
                    String toptitle = result.substring(start, end);
                    result = result.substring(result.indexOf("#", 1) + 1);
                    this.spannableString.setSpan(new ClickSpan(context, toptitle), start, end, Spanned.SPAN_MARK_POINT);
                    return toptitle + result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return result;
            }
        }
        return result;
    }

    /**
     * '@XXX
     *
     * @param text 文字
     * @param rule 匹配规则
     * @return
     */
    private SpannableString setHighlight(String text, String rule) {
        spannableString = new SpannableString(text);
        CharSequence res = DisplaceTitle(text);
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(res);
        int length = res.length();
        int end = 0;
        for (int i = 0; i < length; i++) {
            if (matcher.find()) {
                int start = matcher.start();
                end = matcher.end();
                str = res.toString().substring(start, end);
                spannableString.setSpan(new ClickSpan(context, str), start, end, Spanned.SPAN_MARK_POINT);
            }
        }
        spannableString = SpanStringUtils.getEmotionContent(getContext(), spannableString);
        return spannableString;
    }

    /**
     * 这个就是解决之道
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = false;
        mMovement = getMovementMethod();
        if (mMovement != null) {
            CharSequence text = getText();
            Spannable spannable = SpannableString.valueOf(text);
            handled |= mMovement.onTouchEvent(this, spannable, event);
        }
        CharSequence text = getText();
        Spannable spannable = SpannableString.valueOf(text);
        ClickSpan[] links = spannable.getSpans(getSelectionStart(),
                getSelectionEnd(), ClickSpan.class);
        if (links.length > 0) {
            handled = true;
        }
        if (handled) {
            return true;
        } else {
            final boolean superResult = super.onTouchEvent(event);
            return superResult;
        }
    }

    public String getMatchRule() {
        return MatchRule;
    }

    public void setMatchRule(String matchRule) {
        this.MatchRule = matchRule;
    }

}