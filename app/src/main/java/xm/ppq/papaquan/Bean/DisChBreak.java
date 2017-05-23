package xm.ppq.papaquan.Bean;

import org.json.JSONObject;

/**
 * Created by EdgeDi on 14:27.
 */

public class DisChBreak {

    private JSONObject jsonObject;

    public DisChBreak(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}