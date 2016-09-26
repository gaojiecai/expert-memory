package cn.com.hiservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hiserivice.hiservice.base.BaseFragment;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class HomeFragment extends BaseFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getCustomNav()!=null){
            getCustomNav().getmMiddleTitleView().setText(HomeFragment.class.getSimpleName());
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
