package com.example.lib_sunshaobei2017.utils;

import org.json.JSONObject;

/**
 * Created by sunshaobei on 2017/5/13.
 */

public class JSONBuilder {
    public static String build(String jsontoString,String...values){
        for (char c = 65; c < 65+values.length; c++) {
            jsontoString = jsontoString.replace(c+"",values[c- 65]);
        }
        return jsontoString;
    }
}
