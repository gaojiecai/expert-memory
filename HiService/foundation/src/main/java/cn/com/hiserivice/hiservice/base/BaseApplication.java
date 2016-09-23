package cn.com.hiserivice.hiservice.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by gaojicai1 on 2016/9/23.
 */
public class BaseApplication extends Application {
    public static BaseApplication me = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        me = this;
    }
}
