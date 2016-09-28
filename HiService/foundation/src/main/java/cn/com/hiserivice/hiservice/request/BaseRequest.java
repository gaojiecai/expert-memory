package cn.com.hiserivice.hiservice.request;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public class BaseRequest {

    private static String mBaseUrl = APIs.host_online;
    private static BaseRequest me;
    private static OkHttpClient okHttpClient = new OkHttpClient();

    private BaseRequest() {
    }

    ;

    public static BaseRequest getInstance() {
        if (null == me) {
            synchronized (BaseRequest.class) {
                if (me == null) {
                    me = new BaseRequest();
                }
            }
        }
        return me;
    }


    public <T> void requestObjectResult(Object tag, String api, Object param, Type targetType, IRequest.IListener<T> listenr) {
        if (targetType == null) {
            targetType = Object.class;
        }

        CallBackWrapper<T> callBackWrapper = new CallBackWrapper<>(targetType, listenr);
        Request.Builder builder = new Request.Builder();

        String url = generateQueryUrl(mBaseUrl, api, null);
        Map<String, Object> paramMap = getParmMap(param, false);

        if (paramMap == null) {
            Request request = builder.url(url).headers(getNuiHeaders()).build();
            send(request, null, callBackWrapper);
            return;
        }

        FormBody.Builder fb = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            fb.add(entry.getKey(), "" + entry.getValue());
        }

        Request request = builder.tag(tag).url(url).headers(getNuiHeaders()).post(fb.build()).build();
        send(request, paramMap, callBackWrapper);
    }

    public void send(Request request, Map<String, Object> paramMap, Callback listener) {
        okHttpClient.newCall(request).enqueue(listener);
    }

    /**
     * 拼接url
     */
    public static String generateQueryUrl(String host, String api, Object paramModel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(host);

        if (!TextUtils.isEmpty(api)) {
            stringBuilder.append(api);
        }

        if (paramModel == null) {
            return stringBuilder.toString();
        } else {
            stringBuilder.append("?");
        }

        Map<String, Object> map = null;
        if (paramModel instanceof Map) {
            map = (Map<String, Object>) paramModel;
        } else {
            map = getParmMap(paramModel, true);
        }

        for (Map.Entry<String, Object> en : map.entrySet()) {
            stringBuilder.append("&");
            stringBuilder.append(en.getKey());
            stringBuilder.append("=");
            stringBuilder.append(en.getValue());
        }

        return stringBuilder.toString();
    }

    /**
     * 拼接参数
     *
     * @param paramModel
     * @param encode     get需编码，post不需要
     * @return
     */
    static Map<String, Object> getParmMap(Object paramModel, boolean encode) {
        if (paramModel == null) {
            return null;
        }

        if (paramModel instanceof Map) {
            return (Map) paramModel;
        }

        Field[] fs = paramModel.getClass().getFields();
        Map<String, Object> params = new HashMap<>(fs.length + 1);
        String name = null, value = null;
        for (Field f : fs) {
            try {
                if ((name = f.getName()) != null) {
                    Type t = f.getType();
                    if (t.equals(int.class) || t.equals(long.class)) {
                        value = Long.toString(f.getInt(paramModel));
                    } else if (t.equals(boolean.class)) {
                        value = Boolean.toString(f.getBoolean(paramModel));
                    } else {
                        value = f.get(paramModel) == null ? "" : f.get(paramModel).toString();
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (name != null && !TextUtils.isEmpty(value)) {
                if (encode) {
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                params.put(name, value);
            }
        }

        return params;
    }

    public static Headers getNuiHeaders() {
        Headers.Builder builder = new Headers.Builder();
        return builder.build();
    }


}
