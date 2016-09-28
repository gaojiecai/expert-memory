package cn.com.hiserivice.hiservice.request;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public class CallBackWrapper<T> implements Callback {

    private IRequest.IListener<T> listener;
    private Type targetType;

    public CallBackWrapper(Type type,IRequest.IListener<T> listener){
        this.targetType = type;
        this.listener = listener;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
}
