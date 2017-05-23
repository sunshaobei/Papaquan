package xm.ppq.papaquan.Model.retrieve;

import org.json.JSONObject;

/**
 * Created by sunshaobei on 2017/3/7.
 */

public interface Retrieve_Model {
    void Request_data_getcode(JSONObject jsonObject, String url);
    void Request_data_setcode(JSONObject jsonObject,String url);
}
