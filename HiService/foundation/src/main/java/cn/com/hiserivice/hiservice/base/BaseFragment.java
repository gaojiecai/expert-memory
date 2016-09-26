package cn.com.hiserivice.hiservice.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hiserivice.hiservice.view.CustomNavView;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public CustomNavView getCustomNav(){
        Activity activity = getActivity();
        if (activity instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity)activity;
            return baseActivity.getCustomNavView();
        }
        return null;
    }
}
