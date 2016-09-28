package cn.com.hiservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hiserivice.hiservice.base.BaseFragment;
import cn.com.hiserivice.hiservice.request.APIs;
import cn.com.hiserivice.hiservice.request.IRequest;
import cn.com.hiserivice.hiservice.request.ReqModule;
import cn.com.hiservice.vo.HomeData;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class HomeFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getCustomNav() != null) {
            getCustomNav().getmMiddleTitleView().setText("嗨修养车");
        }

        getData();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void getData() {
        ReqModule.newRequest()
                .tag(this)
                .api(APIs.home.api)
                .args("","")
                .targetDataType(HomeData.class).listener(new IRequest.IListener<HomeData>() {

            @Override
            public void onReceive(HomeData obj) {

            }

            @Override
            public void onFailed(String errorCode, String msg) {

            }
        }).send();
    }
}
