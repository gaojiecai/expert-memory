package cn.com.hiserivice.hiservice;

import android.app.Activity;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by gaojicai1 on 2016/9/22.
 */
public class BaseActivity extends Activity implements EasyPermissions.PermissionCallbacks {


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    //to be extended by subclass to receive notifications of permission granted or not
    public static abstract class PermissionReqCallBack {
        public void onPartialPermissionAllowed(List<String> perms) {

        }

        public abstract void onAllPermissionAllowed(List<String> perms);

        public abstract void onPermissionDenied(List<String> perms);
    }
}
