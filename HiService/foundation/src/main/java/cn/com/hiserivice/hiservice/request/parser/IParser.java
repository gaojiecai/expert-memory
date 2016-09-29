package cn.com.hiserivice.hiservice.request.parser;

/**
 * Created by gaojicai1 on 2016/9/29.
 */
public interface IParser<T> {

    public T parser(byte[] data);
}
