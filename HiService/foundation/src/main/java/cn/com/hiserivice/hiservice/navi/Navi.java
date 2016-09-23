package cn.com.hiserivice.hiservice.navi;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import cn.com.hiserivice.hiservice.R;
import cn.com.hiserivice.hiservice.base.BaseApplication;

/**
 * Created by gaojicai1 on 2016/9/23.
 */
public class Navi implements INavigation {

    private Context from;
    private String component;
    private Bundle args = new Bundle();
    private int flags = 0;
    private int req_code;

    @Override
    public void go(String Component, Bundle args) {
        this.component = component;
        this.args = args;
        go();
    }

    @Override
    public void goForResult(String Component, Bundle args, int req_code) {
        this.component = component;
        this.args = args;
        this.req_code = req_code;
        go();
    }

    public static Navi from(Context from) {
        Navi navi = new Navi();
        navi.from = from;
        return navi;
    }

    public static Navi from(Fragment from) {
        return from(from.getActivity());
    }

    public Navi component(String component) {
        this.component = component;
        return this;
    }

    public Navi addArg(String key, String value) {
        this.args.putString(key, value);
        return this;
    }

    public Navi addArg(String key, long value) {
        this.args.putLong(key, value);
        return this;
    }

    public Navi addArg(String key, boolean value) {
        this.args.putBoolean(key, value);
        return this;
    }

    public Navi addArg(String key, Serializable value) {
        this.args.putSerializable(key, value);
        return this;
    }

    public Navi addArg(String key, Parcelable value) {
        this.args.putParcelable(key, value);
        return this;
    }

    public Navi addArg(Bundle bundle) {
        this.args.putAll(bundle);
        return this;
    }

    public Navi addFlags(int... flags) {
        if (flags != null) {
            for (int flag : flags) {
                this.flags |= flag;
            }
        }
        return this;
    }

    public void go() {
        Intent intent = Intent();
        if (intentOpenable(intent)) {
            from.startActivity(intent);
        }
    }

    public void goForResult(int reqCode) {
        Intent intent = Intent();
        if (intentOpenable(intent)){
            ((Activity)from).startActivityForResult(intent,reqCode);
        }
    }

    private Intent Intent() {
        ComponentName comp = new ComponentName(from,component);
        Intent intent = new Intent();
        intent.setComponent(comp);

        if (args.size()>0){
            intent.putExtras(args);
        }

        if (!(from instanceof Activity)){
            flags |= Intent.FLAG_ACTIVITY_NEW_TASK;
        }
        intent.addFlags(flags);

        return intent;
    }

    private boolean intentOpenable(Intent intent) {
        if (getPackageManager().resolveActivity(intent, 0) == null) {
            Toast toast = new Toast(from);
            View v = LayoutInflater.from(from).inflate(R.layout.navi_toast, null);
            ((TextView) v.findViewById(R.id.component_name)).setText(component);
            toast.setView(v);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
        return true;
    }

    private static PackageManager packageManager;

    private static PackageManager getPackageManager() {
        if (packageManager == null) {
            synchronized (Navi.class) {
                if (packageManager == null) {
                    packageManager = BaseApplication.me.getPackageManager();
                }
            }
        }
        return packageManager;
    }
}