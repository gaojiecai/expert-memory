package cn.com.hiserivice.hiservice.request;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import cn.com.hiserivice.hiservice.request.parser.IParser;
import cn.com.hiserivice.hiservice.request.responseModel.Result;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public class CallBackWrapper<T> implements Callback {

    private IRequest.IListener<T> listener;
    private Type targetType;
    private IParser<T> parser = null;

    static Handler handler = new Handler(Looper.getMainLooper());

    public CallBackWrapper(Type type, IRequest.IListener<T> listener) {
        this.targetType = type;
        this.listener = listener;
    }

    public CallBackWrapper(Type type, IRequest.IListener<T> listener, IParser<T> parser) {
        this.targetType = type;
        this.listener = listener;
        this.parser = parser;
    }

    @Override
    final public void onFailure(Call call, IOException e) {
        Log.v("gjc", "onFailure");
    }

    @Override
    final public void onResponse(Call call, Response response) throws IOException {
        Log.v("gjc", "onResponse");
        final Call dumy = call;
        if (!response.isSuccessful()) {
            onFailure(call, new IOException("Unexpected code " + response));
        }
        String resStr = null;
        Result<T> res = null;
        if (parser != null) {
            T data = parser.parser(response.body().bytes());
            res = new Result<>();
            res.code = PubRtnCode.OK;
            res.$_result = data;
        } else {
            Gson gson = new Gson();

            Type type = new TypeToken<Result>() {
            }.getType();

            if (resStr != null) {
                res = gson.fromJson(resStr, type);
            } else {
                res = gson.fromJson(response.body().charStream(), type);
            }

            if (PubRtnCode.OK.equals(res.code)) {
                res.$_result = gson.fromJson(res.data, targetType);
            } else {

            }
        }

        sendResult(res);
    }

    private void sendResult(Result res) {
        if (listener != null) {
            Message msg = Message.obtain(handler, new MainThreadRunner<T>(res, listener));
            msg.sendToTarget();
        }
    }

    private static class MainThreadRunner<T> implements Runnable {

        Result<T> res;
        IRequest.IListener<T> listener;

        public MainThreadRunner(Result<T> res, IRequest.IListener<T> listener) {
            this.res = res;
            this.listener = listener;
        }

        @Override
        public void run() {
            if (res != null) {
                if (PubRtnCode.OK.equals(res.code)) {
                    listener.onReceive(res.$_result);
                } else {
                    listener.onFailed(res.code, res.message);
                }
                return;
            }
            listener.onFailed(PubRtnCode.NO_ACCESS_AUTHORITY, PubRtnCode.NO_ACCESS_AUTHORITY);
        }
    }

}
