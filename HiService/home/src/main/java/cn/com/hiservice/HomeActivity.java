package cn.com.hiservice;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;

import cn.com.hiserivice.card.CardFragment;
import cn.com.hiserivice.find.FindFragment;
import cn.com.hiserivice.hiservice.base.BaseActivity;
import cn.com.hiserivice.hiservice.base.BaseFragment;
import cn.com.hiserivice.hiservice.navi.Modules;
import cn.com.hiserivice.hiservice.view.CustomNavView;
import cn.com.hiserivice.mine.MineFragment;
import cn.com.hiservice.fragment.HomeFragment;

public class HomeActivity extends BaseActivity {

    private CustomNavView customNavView;
    private FrameLayout mContentView;
    private TabWidget mTabWidget;

    private String[] tab = {"首页", "卡券", "发现", "我的"};
    private View[] tab_btn = new View[tab.length];
    private int[] tab_btn_bg = {R.drawable.home_tab, R.drawable.card_tab, R.drawable.find_tab, R.drawable.mine_tab};
    private Fragment[] fragments = new Fragment[4];
    private int currentFrqagment = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showTargetFragment(intent);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        customNavView = getCustomNavView();
        mContentView = (FrameLayout) findViewById(R.id.home_content);
        mTabWidget = (TabWidget) findViewById(R.id.tab_widget);

        for (int i = 0; i < tab_btn.length; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.tab_item, mTabWidget, false);
            TextView tab_name = (TextView) v.findViewById(R.id.tab_name);
            ImageView tab_img = (ImageView) v.findViewById(R.id.tab_img);

            tab_name.setText(tab[i]);
            tab_img.setImageResource(tab_btn_bg[i]);

            tab_btn[i] = v;
            mTabWidget.addView(v);

            tab_btn[i].setOnClickListener(tabListener);
        }

        mTabWidget.setCurrentTab(0);

        showTargetFragment(getIntent());
    }

    /**
     * 展示目标fragment
     */
    private void showTargetFragment(Intent intent) {
        Bundle args = intent.getExtras();
        int target_fragment = 0;
        if (args != null) {
            String target = args.getString(Modules.HOME.ARG_SUB_MODULE, Modules.HOME.SubModules.HOME_PAGE);
            switch (target) {
                case Modules.HOME.SubModules.HOME_PAGE:
                    target_fragment = 0;
                    break;
                case Modules.HOME.SubModules.CARD_PAGE:
                    target_fragment = 1;
                    break;
                case Modules.HOME.SubModules.FIND_PAGE:
                    target_fragment = 2;
                    break;
                case Modules.HOME.SubModules.MINE_PAGE:
                    target_fragment = 3;
                    break;
            }

            if (target_fragment > fragments.length) {
                target_fragment = 0;
            }
        }
        tab_btn[target_fragment].performClick();
    }

    private View.OnClickListener tabListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            for (int i = 0; i < tab_btn.length; i++) {
                if (v == tab_btn[i] && i != currentFrqagment) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    if (currentFrqagment >= 0 && fragments[currentFrqagment] != null) {
                        transaction.detach(fragments[currentFrqagment]);
                    }

                    if (fragments[i] == null) {
                        fragments[i] = generateFragment(i);
                        transaction.add(R.id.home_content, fragments[i]);
                    } else {
                        transaction.attach(fragments[i]);
                    }
                    transaction.commit();

                    currentFrqagment = i;
                    mTabWidget.setCurrentTab(i);
                }
            }
        }
    };

    private Fragment generateFragment(int i) {
        String pageName = null;
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new CardFragment();
            case 2:
                return new FindFragment();
            case 3:
                return new MineFragment();
        }
        BaseFragment baseFragment = new BaseFragment();
        return baseFragment;
    }
}
