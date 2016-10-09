package cn.com.hiservice.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.hiserivice.hiservice.base.BaseFragment;
import cn.com.hiserivice.hiservice.request.APIs;
import cn.com.hiserivice.hiservice.request.IRequest;
import cn.com.hiserivice.hiservice.request.ReqModule;
import cn.com.hiserivice.hiservice.security.MD5Util;
import cn.com.hiservice.R;
import cn.com.hiservice.view.CircleViewPager;
import cn.com.hiservice.vo.HomeData;

/**
 * Created by gaojicai1 on 2016/9/26.
 */
public class HomeFragment extends BaseFragment {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private HomeAdapter adapter;
    private CircleViewPager circleViewPager;

    private List<String> mDatas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getCustomNav() != null) {
            getCustomNav().getmMiddleTitleView().setText("嗨修养车");
        }
        getData();
        initData();

        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        circleViewPager = (CircleViewPager)mRootView.findViewById(R.id.home_viewpager);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.home_recycler_view);
        //设置布局管理器
        //mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter = new HomeAdapter());



        return mRootView;
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A';i<'z';i++){
            mDatas.add(" "+(char)i);
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                this.tv = (TextView)view.findViewById(R.id.textView);
            }
        }
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
                .args("action", "appIndex")
                .args("uid", "")
                .args("brand_id", "")
                .args("latitude", "31.207719")
                .args("longitude", "121.58846")
                .args("series_id", "")
                .args("style_id", "")
                .args("version", "3.3.1")
                .targetDataType(HomeData.class).listener(new IRequest.IListener<HomeData>() {

            @Override
            public void onReceive(HomeData obj) {
                if (obj != null) {
                    Toast.makeText(getActivity(), "onReceive:" + obj.getCommentList().getCommentList().get(0).getOpinion(), Toast.LENGTH_LONG).show();
                    circleViewPager.setData(obj.getBannerList());
                } else {
                    Toast.makeText(getActivity(), "onReceive:----------", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailed(String errorCode, String msg) {
                Toast.makeText(getActivity(), "onFailed", Toast.LENGTH_LONG).show();
            }
        }).send();
    }
}
