package cn.com.hiserivice.hiservice.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import cn.com.hiserivice.hiservice.R;
import cn.com.hiserivice.hiservice.view.CustomNavView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by gaojicai1 on 2016/9/25.
 */
public class BaseActivity extends Activity implements EasyPermissions.PermissionCallbacks {

    private FrameLayout mContainer;
    private CustomNavView customNavView;

    private int statusBarHeight = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity);
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
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        mContainer.addView(view,params);
    }

    private void initBaseView() {
        mContainer = (FrameLayout) findViewById(R.id.base_content);
        customNavView = (CustomNavView) findViewById(R.id.navigation_bar);
        if ((!isTaskRoot()) && customNavView != null) {
            customNavView.getmLeftItemImgView().setImageResource(R.mipmap.back);
            customNavView.getmLeftItem().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        customNavBarSize();
    }

    public CustomNavView getCustomNavView(){
        return customNavView;
    }

    /**
     * 设置状态栏
     */
    public void customNavBarSize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = getStatusBarHeight();
            customNavView.getLayoutParams().height += statusBarHeight;
            customNavView.setPadding(
                    customNavView.getPaddingLeft(), customNavView.getPaddingTop() + statusBarHeight, customNavView.getPaddingRight(), customNavView.getPaddingBottom()
            );

            if (Build.MANUFACTURER.contains("Xiaomi")) {
                setMiuiStatusBarDarkMode(this, true);
            } else if (Build.MANUFACTURER.contains("Meizu")) {
                setMeizuStatusBarDarkIcon(this, true);
            }
        }
    }

    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight() {
        int result = statusBarHeight;
        if (result > 0) {
            return result;
        }
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelOffset(resourceId);
            statusBarHeight = result;
        }
        return result;
    }

    /**
     * 设置miui
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();

        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置meizu
     */
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");

                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    // to be extended by subclass to receive notifications of permissions granted or not
    public static abstract class PermissionReqCallBack {
        public void onPartialPermissionAllowed(List<String> perms) {

        }

        public abstract void onAllPermissionsAllowed(List<String> perms);

        public abstract void onPermissionDenied(List<String> perms);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
