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
import cn.com.hiserivice.hiservice.security.MD5Util;
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
        String name = "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0" + System.currentTimeMillis() + "" + "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0";
        String sign = MD5Util.getMD5String(name);
        ReqModule.newRequest()
                .tag(this)
                .api(APIs.home.api)
                .args("appid", "hiandroid")
                .args("timestamp", System.currentTimeMillis() + "")
                .args("sign", sign)
                .args("action","appIndex")
                .args("uid","")
                .args("brand_id","")
                .args("latitude","31.207719")
                .args("longitude","121.58846")
                .args("series_id","")
                .args("style_id","")
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
