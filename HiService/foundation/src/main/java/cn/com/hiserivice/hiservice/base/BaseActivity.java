package cn.com.hiserivice.hiservice.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import cn.com.hiserivice.hiservice.R;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by gaojicai1 on 2016/9/25.
 */
public class BaseActivity extends Activity implements EasyPermissions.PermissionCallbacks {

    private FrameLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.navi_toast);
        initBaseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public final void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,mContainer);
    }

    @Override
    public final void setContentView(View view) {
        mContainer.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContainer.addView(view,params);
    }

    private void initBaseView(){

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
