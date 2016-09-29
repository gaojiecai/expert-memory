package cn.com.hiservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

        String time = System.currentTimeMillis() + "";
        String name = "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0" + time + "cqI4mAv2orQr3fJtTJgmaGbA3ifi8eR0";
        String sign = MD5Util.getMD5String(name);
        ReqModule.newRequest()
                .tag(this)
                .api(APIs.home.api)
                .args("appid", "hiandroid")
                .args("timestamp", time)
                .args("sign", sign)
                .args("action","appIndex")
                .args("uid","")
                .args("brand_id","")
                .args("latitude","31.207719")
                .args("longitude","121.58846")
                .args("series_id","")
                .args("style_id","")
                .args("version","3.3.1")
                .targetDataType(HomeData.class).listener(new IRequest.IListener<HomeData>() {

            @Override
            public void onReceive(HomeData obj) {
                if (obj!=null){
                    Toast.makeText(getActivity(),"onReceive:"+obj.getCommentList().getCommentList().get(0).getOpinion(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getActivity(),"onReceive:----------",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailed(String errorCode, String msg) {
                Toast.makeText(getActivity(),"onFailed",Toast.LENGTH_LONG).show();
            }
        }).send();
    }
}
