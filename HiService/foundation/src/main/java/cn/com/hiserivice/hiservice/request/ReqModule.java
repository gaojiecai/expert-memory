package cn.com.hiserivice.hiservice.request;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public class ReqModule {

    private static WeakHashMap<Object, Object> tags = new WeakHashMap<>();
    WeakReference<Object> reqTag;
    private String api;
    private Type targetDataType;
    private Map<String, Object> argsMap;
    private Object argsObj;
    private IRequest.IListener listener;

    public static ReqModule newRequest() {
        return new ReqModule();
    }

    public ReqModule tag(Object object) {
        if (object == null) {
            return this;
        }
        this.reqTag = new WeakReference<>(object);
        tags.put(object, this.reqTag);
        return this;
    }

    public ReqModule api(String api) {
        this.api = api;
        return this;
    }

    public ReqModule targetDataType(Type type) {
        targetDataType = type;
        return this;
    }

    public ReqModule args(String key, Object value) {
        if (argsMap == null) {
            argsMap = new HashMap<>();
        }
        argsMap.put(key, value);
        return this;
    }

    public ReqModule args(Map<String, Object> args) {
        if (argsMap == null) {
            argsMap = new HashMap<>();
        }
        args.putAll(args);
        return this;
    }

    public ReqModule args(Object obj) {
        if (obj == null) {
            return this;
        }
        if (obj instanceof Map){
            args((Map)obj);
            return this;
        }
        argsObj = obj;
        return this;
    }

    public <T> ReqModule listener(IRequest.IListener<T> listener) {
        this.listener = listener;
        return this;
    }

    public void send() {
        Map<String, Object> merged = new HashMap<>();
        if (argsObj!=null){
            Map arg1 = BaseRequest.getParmMap(argsObj,false);
            merged.putAll(arg1);
        }
        if (argsMap !=null){
            merged.putAll(argsMap);
        }

        BaseRequest.getInstance().requestObjectResult(reqTag,api,merged,targetDataType,listener);
    }
}
