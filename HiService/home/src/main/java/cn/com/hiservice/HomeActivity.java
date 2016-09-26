package cn.com.hiservice;

import android.os.Bundle;

import cn.com.hiserivice.hiservice.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private String[] tab = {"首页", "卡券", "发现", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
