package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONObject;

import xm.ppq.papaquan.Tool.shownews.DiscussUtil;
import xm.ppq.papaquan.Tool.typewriting.SpanStringUtils;

/**
 * Created by Administrator on 2017/3/22.
 */

public class Stringutil {

    public static CharSequence DisplaceTitle(String result, Context context) {
        if (result != null) {
            if (result.startsWith("#")) {
                try {
                    String toptitle = result.substring(result.indexOf("#") + 1, result.indexOf("#", 1) + 1);
                    result = result.substring(result.indexOf("#", 1) + 1);
                    return DiscussUtil.getAllResult("#5b6a91", "#" + toptitle, SpanStringUtils.getEmotionContent(context, result));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return SpanStringUtils.getEmotionContent(context, result);
            }
        }
        return result;
    }

    public static CharSequence DisplaceAddress(String result) {
        if (result == null) return "";
        else {
            result = result.replaceAll("null", "");
            return result;
        }
    }

    public static String ThreeString(String result, String replace) {
        return (result != null) ? result : replace;
    }
}