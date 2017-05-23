package xm.ppq.papaquan.Tool;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 城市编码 on 2017/3/3.
 */

public class CityNumberUtil {

    public static String ResolveData(Context context, String textcode) {
        try {
            StringBuffer buffer = new StringBuffer();
            InputStream in = context.getAssets().open("city.js");
            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = in.read(bytes)) != -1) {
                buffer.append(new String(bytes, 0, len, textcode));
            }
            in.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}