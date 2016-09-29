package cn.com.hiserivice.hiservice.request.responseModel;

import com.google.gson.JsonElement;

/**
 * Created by gaojicai1 on 2016/9/29.
 */
public class Result<T> {
    public String ext;
    public String message;
    public String tip;
    public String action;
    public String Code;
    public JsonElement Data;
    public T $_result;
}
