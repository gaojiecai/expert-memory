package cn.com.hiserivice.hiservice.request;

/**
 * Created by gaojicai1 on 2016/9/27.
 */
public class BaseRequest {

    private static BaseRequest me;

    private BaseRequest(){};

    public static BaseRequest getInstance(){
        if (null == me){
            synchronized (BaseRequest.class){
                if (me == null){
                    me = new BaseRequest();
                }
            }
        }
        return me;
    }

}
