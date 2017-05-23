package xm.ppq.papaquan.Tool.shownews;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by 评论内容处理类 on 2017/2/28.
 */

public class DiscussUtil {

    public static Spanned getAllResult(String host) {
        return Html.fromHtml("<font color=\"#5b6a91\">" + host + "：" + "</font>");
    }

    public static Spanned getAllResult(String host, CharSequence content, int i) {
        return Html.fromHtml("<font color=\"#5b6a91\">" + host + "：" + "</font>" + content);
    }

    public static Spanned getResult(String report, String color, String name, String topic) {
        return Html.fromHtml(report + "<font color=" + color + ">" + name + "</font>" + topic);
    }

    public static Spanned getAllResult(String host, String visitor) {
        return Html.fromHtml("<font color=\"#5b6a91\">" + host + "</font>" + "回复"
                + "<font color=\"#5b6a91\">" + visitor + "：" + "</font>");
    }

    public static Spanned getAllResult(String host, String visitor, String content, int i) {
        return Html.fromHtml("<font color=\"#5b6a91\">" + host + "</font>" + "回复"
                + "<font color=\"#5b6a91\">" + visitor + "：" + "</font>" + content);
    }

    public static Spanned getAllResult(CharSequence host, CharSequence visitor, CharSequence content, int i) {
        return Html.fromHtml("<font color=\"#5b6a91\">" + host + "</font>" + "回复"
                + "<font color=\"#5b6a91\">" + visitor + "：" + "</font>" + content);
    }

    public static Spanned getAllResult(String colors, String name, CharSequence content) {
        return Html.fromHtml("<font color=" + colors + ">" + name + "</font>" + content);
    }

    public static Spanned getAllResult(String colors, String name, String content, String ta_name) {
        return Html.fromHtml("<font color=" + colors + ">" + ta_name + "</font>" + "回复" +
                "<font color=" + colors + ">" + name + ":" + "</font>"
                + content);
    }

}