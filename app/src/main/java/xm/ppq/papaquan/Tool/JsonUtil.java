/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xm.ppq.papaquan.Tool;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


/**
 * 描述：json处理类.
 *
 * @author gengqiquan
 * @version v1.0
 * @date：2016-11-1 09:41:49
 */
public class JsonUtil {

    /**
     * 描述：将对象转化为json.
     *
     * @return
     */
    public static String toJson(Object src) {
        String json = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            json = gson.toJson(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 描述：将对象转化为json.
     *
     * @return
     */
    public static String toJson(Map src) {
        String json = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            json = gson.toJson(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 描述：将列表转化为json.
     *
     * @param list
     * @return
     */
    public static String toJson(List<?> list) {
        String json = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            json = gson.toJson(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 描述：将json转化为列表.
     *
     * @param json
     * @param typeToken new TypeToken<ArrayList<T>>() {}.getType();
     * @return
     */
    public static List<?> fromJson(String json, TypeToken typeToken) {
        List<?> list = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            Type type = typeToken.getType();
            list = gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 描述：将json转化为对象.
     *
     * @param json
     * @param clazz
     * @return
     */
    public static Object fromJson(String json, Class clazz) {
        Object obj = null;
        try {
            GsonBuilder gsonb = new GsonBuilder();
            Gson gson = gsonb.create();
            obj = gson.fromJson(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 描述：将String转化为JSONObject.
     *
     * @param str
     * @return
     */
    public static JSONObject toJSONObject(String str) {
        JSONObject json = null;
        try {
            json = new JSONObject(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 描述：将String转化为JSONArray.
     *
     * @param str
     * @return
     */
    public static JSONArray toJSONArray(String str) {
        JSONArray json = null;
        try {
            json = new JSONArray(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 描述：根据key取值
     *
     * @param json
     * @return
     */
    public static Object getObject(String json, String key) {
        Object str = null;
        try {
            JSONObject obj = new JSONObject(json);
            str = obj.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str == null) {
            str = "";
        }
        return str;
    }

    /**
     * 描述：根据key值获取String.
     *
     * @return
     */
    public static String getString(String json, String key) {
        String value = null;
        try {
            JSONObject obj = new JSONObject(json);
            value = obj.getString(key);
        } catch (JSONException e) {
            value = "";
        }
        return value;
    }

    /**
     * 描述：根据key值获取Integer.
     *
     * @return
     */
    public static Integer getInt(String json, String key) {
        Integer value = -1;
        try {
            JSONObject obj = new JSONObject(json);
            value = obj.getInt(key);
        } catch (JSONException e) {
            value = -1;
        }
        return value;
    }

    /**
     * 描述：根据key值获取Double.
     *
     * @return
     */
    public static Double getDouble(String json, String key) {
        Double value = null;
        try {
            JSONObject obj = new JSONObject(json);
            value = obj.getDouble(key);
        } catch (JSONException e) {
            value = 0.0;
        }
        return value;
    }

    public static String Count_Second(int time) {
        String count = null;
        if (time < 60) {
            count = "1分钟之前";
        } else {//分钟
            int t1 = time / 60;
            if (t1 < 60) {
                count = t1 + "分钟之前";
            } else {//小时
                int t2 = t1 / 60;
                if (t2 < 24) {
                    count = t2 + "小时之前";
                } else {//天
                    int t3 = t1 / 30;
                    if (t3 < 30) {
                        count = t3 + "天之前";
                    } else {//月
                        int t4 = t3 / 12;
                        if (t4 < 12) {
                            count = t4 + "月之前";
                        } else {
                            int t5 = t4 / 12;
                            count = t5 + "年之前";
                        }
                    }
                }
            }
        }
        return count;
    }

}
