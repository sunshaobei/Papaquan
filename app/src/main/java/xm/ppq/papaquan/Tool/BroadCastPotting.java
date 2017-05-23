package xm.ppq.papaquan.Tool;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.io.Serializable;

/**
 * Created by 广播封装 on 2017/3/20.
 */

public class BroadCastPotting<T> {

    private Activity activity;
    private ReceiveBroadCast broadCast;
    private String CastName;
    private BroadCastMessage message;

    public BroadCastPotting(Activity activity, String castName) {
        this.activity = activity;
        this.CastName = castName;
    }

    public BroadCastMessage getMessage() {
        return message;
    }

    public void setMessage(BroadCastMessage message) {
        this.message = message;
    }

    public void RegisteLinster(String key) {
        this.key = key;
        broadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CastName);
        activity.registerReceiver(broadCast, filter);
    }

    private class ReceiveBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (message != null) {
                message.Message(intent.getSerializableExtra(key));
            }
        }
    }

    private String key;

    public void SendBroadCastListener(String key, T result) {
        Intent intent = new Intent();
        intent.putExtra(key, (Serializable) result);
        intent.setAction(CastName);
        activity.sendBroadcast(intent);
    }

    public void UnBroadCast() {
        if (broadCast != null) {
            activity.unregisterReceiver(broadCast);
            broadCast = null;
        }
    }

    public interface BroadCastMessage<T> {
        void Message(T message);
    }

}