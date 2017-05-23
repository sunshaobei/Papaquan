package xm.ppq.papaquan.Tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/3/8.
 */

public class SharedPreferencesPotting {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPreferencesPotting(Context context, String name) {
        this.context = context;
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public SharedPreferencesPotting setItemString(String key, String value) {
        editor.putString(key, value);
        return this;
    }

    public SharedPreferencesPotting setItemBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        return this;
    }

    public boolean getItemBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public SharedPreferencesPotting setItemInt(String key, int value) {
        editor.putInt(key, value);
        return this;
    }

    public String getItemString(String key) {
        return preferences.getString(key, "");
    }

    public int getItemInt(String key) {
        return preferences.getInt(key, 0);
    }

    public void getClear() {
        editor.clear();
        editor.commit();
    }

    public SharedPreferencesPotting build() {
        editor.commit();
        return this;
    }

    public boolean isLogin() {
        int uid = preferences.getInt("uid", 0);
        if (uid != 0)
            return true;
        else return false;
    }


}