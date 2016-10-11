package cn.com.hiserivice.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hiserivice.hiservice.base.BaseFragment;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class MineFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getCustomNav()!=null){
            getCustomNav().setVisibility(View.GONE);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
