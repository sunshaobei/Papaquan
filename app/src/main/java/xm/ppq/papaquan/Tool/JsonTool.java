package xm.ppq.papaquan.Tool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by EdgeDi on 20:38.
 */

public class JsonTool {

    private ArrayList<Object> keys;
    private ArrayList<Object> values;
    private JSONObject jsonObject;

    public JsonTool() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
        jsonObject = new JSONObject();
    }

    public JsonTool put_key(Object... key) {
        for (int i = 0; i < key.length; i++) {
            keys.add(key[i]);
        }
        return this;
    }

    public JsonTool put_value(Object... value) {
        for (int i = 0; i < value.length; i++) {
            values.add(value[i]);
        }
        return this;
    }

    public JSONObject getJson() {
        for (int i = 0; i < keys.size(); i++) {
            try {
                jsonObject.put(keys.get(i).toString(), values.get(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}