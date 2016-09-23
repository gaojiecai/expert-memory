package cn.com.hiservice;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

import cn.com.hiserivice.hiservice.BaseActivity;
import cn.com.hiserivice.hiservice.navi.Modules;
import cn.com.hiserivice.hiservice.navi.Navi;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by gaojicai1 on 2016/9/19.
 */
public class LaunchActivity extends Activity implements EasyPermissions.PermissionCallbacks {

    private BaseActivity.PermissionReqCallBack callBack;

    private List<String> requestedPerms = new ArrayList<>();
    private boolean permInited = false;
    private final int RQP = 1;

    @Override
    protected void onResume() {
        super.onResume();
        if (!permInited) {
            initPermission();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        home();
    }

    /**
     * 跳到首页
     */
    private void home() {
        Navi.from(this).component(Modules.HOME.HomeActivity).go();
        finish();
    }

    /**
     * 初始化权限
     */
    private void initPermission() {
        List<String> perms = new ArrayList<>();
        perms.add(Manifest.permission.READ_PHONE_STATE);
        perms.add(Manifest.permission.ACCESS_FINE_LOCATION);
        perms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        perms.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        for (String p : perms) {
            //Using EasyPermissions#hasPermissions(...) to check if the app already has the required permissions.
            if (!EasyPermissions.hasPermissions(this, p)) {
                requestedPerms.add(p);
            }
        }

        if (requestedPerms.isEmpty()) {
            home();
            return;
        }

        String[] pa = new String[requestedPerms.size()];
        for (int i = 0; i < requestedPerms.size(); i++) {
            pa[i] = requestedPerms.get(i);
        }

        requestPermissions("need the following permissions to make the app work",
                pa);
    }

    private void requestPermissions(String desc, String... perms) {
        //Requesting permissions with EasyPermissions#requestPermissions.
        //This method will request the system permissions and show the rationale string provided if necessary.
        EasyPermissions.requestPermissions(this, desc, RQP, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    // Some permissions have been granted
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (perms.contains(requestedPerms)) {
            if (callBack != null) {
                callBack.onAllPermissionAllowed(perms);
            }
            home();
        } else {
            if (callBack != null) {
                callBack.onPartialPermissionAllowed(perms);
            }
        }
    }

    // Some permissions have been denied
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (callBack != null) {
            callBack.onPermissionDenied(perms);
        }

        if (!checkDeniedPermissionsNeverAskAgain("不授予所请求的权限会导致应用不能正常工作. 请打开设置修改应用权限配置.", "设置", "取消", perms)) {
            home();
        }
    }

    /**
     * 引导用户设置权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkDeniedPermissionsNeverAskAgain(String rationale, String positiveButton, String negativeButton, List<String> deniedPerms) {
        boolean shouldShowRationale;
        for (String perm : deniedPerms) {
            shouldShowRationale = shouldShowRequestPermissionRationale(perm);
            if (!shouldShowRationale) {
                final Activity activity = this;

                AlertDialog dialog = new AlertDialog.Builder(activity)
                        .setMessage(rationale)
                        .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                                intent.setData(uri);
                                activity.startActivity(intent);
                            }
                        })
                        .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                home();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        }
        return false;
    }

}
