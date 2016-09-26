package cn.com.hiserivice.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hiserivice.hiservice.base.BaseFragment;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class FindFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getCustomNav()!=null){
            getCustomNav().getmMiddleTitleView().setText(FindFragment.class.getSimpleName());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
