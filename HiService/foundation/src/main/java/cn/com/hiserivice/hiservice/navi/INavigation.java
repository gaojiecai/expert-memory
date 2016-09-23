package cn.com.hiserivice.hiservice.navi;

import android.os.Bundle;

/**
 * Created by gaojicai1 on 2016/9/23.
 */
public interface INavigation {
    void go(String Component, Bundle args);

    void goForResult(String Component, Bundle args, int req_code);
}
