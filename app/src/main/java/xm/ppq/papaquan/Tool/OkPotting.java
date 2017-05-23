package xm.ppq.papaquan.Tool;


import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xm.ppq.papaquan.Bean.BaseBean;
import xm.ppq.papaquan.View.BaseUrl;

/**
 * Created by Administrator on 2017/2/22.
 */

public class OkPotting {

    private Request request;
    private OkHttpClient client;
    private Call call;
    private static String base_url;
    private static OkPotting okPotting;
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkPotting(String base_url) {
        this.base_url = base_url;
        client = new OkHttpClient();
        client.newBuilder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public void OnCancel(){
        call.cancel();
    }

    public static synchronized OkPotting getInstance(String base_url) {
        if (okPotting == null) {
            okPotting = new OkPotting("");
        }
        OkPotting.base_url = base_url;
        return okPotting;
    }

    private Request get(String url) {
        request = new Request.Builder()
                .url(base_url + url)
                .get()
                .build();
        return request;
    }

    private Request ComGet(String url) {
        request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return request;
    }

    public void ComAsk(String url, Callback callback) {
        call = client.newCall(ComGet(url));
        call.enqueue(callback);
    }

    private Request post(String url, FormBody formBody) {
        request = new Request.Builder()
                .url(base_url + url)
                .post(formBody)
                .build();
        return request;
    }

    private Request json(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(base_url + url)
                .post(body)
                .build();
        return request;
    }

    public void Ask(String url, Subscriber subscriber) {
        call = client.newCall(get(url));
        Asynchronous(call, subscriber);
    }

    public void Ask(String url, String json, Subscriber subscriber) {
        call = client.newCall(json(url, json));
        Asynchronous(call, subscriber);
    }

    public void AskOne(String url, String json, Subscriber subscriber) {
        call = client.newCall(json(url, json));
        AsynchronousOne(call, subscriber);
    }

    public void AskOne(String url, Subscriber subscriber) {
        call = client.newCall(get(url));
        AsynchronousOne(call, subscriber);
    }

    public void Ask(String url, FormBody formBody, Subscriber subscriber) {
        call = client.newCall(post(url, formBody));
        Asynchronous(call, subscriber);
    }

    private void Asynchronous(Call call, Subscriber subscriber) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                subscriber.onNext(e.toString());
                subscriber.onCompleted();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                }).map(new Func1<String, BaseBean>() {
                    @Override
                    public BaseBean call(String s) {
                        if (s != null) {
                            Gson gson = new Gson();
                            BaseBean baseBean = gson.fromJson(s, BaseBean.class);
                            return baseBean;
                        }
                        return null;
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    private void AsynchronousOne(Call call, Subscriber subscriber) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                }).map(s -> s)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });

    }
}