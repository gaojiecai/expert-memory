package cn.com.hiserivice.hiservice.request;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public interface IRequest {
    interface IListener<T> {
        void onReceive(T obj);

        void onFailed(String errorCode, String msg);
    }
}
