package cn.com.hiservice.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.util.List;

import cn.com.hiservice.R;
import cn.com.hiservice.vo.HomeData;

/**
 * Created by gaojicai1 on 2016/10/8.
 */
public class CircleViewPager extends FrameLayout {

    private List<HomeData.BannerListBean> listBeen;

    private Context context;
    private ViewPager viewPager;

    public CircleViewPager(Context context) {
        this(context, null);
    }

    public CircleViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;

    }

    public void setData(List<HomeData.BannerListBean> bannerListBeen) {
        this.listBeen = bannerListBeen;
        initUI(context);
    }

    private void initUI(Context context) {
        if (listBeen == null || listBeen.size() == 0) {
            return;
        }

        LayoutInflater.from(context).inflate(R.layout.circle_view_pager_layout, this, true);

        /*for (int i = 0; i < listBeen.size(); i++) {
            SimpleDraweeView view =  new SimpleDraweeView(context);
            //view.setTag(listBeen[i]);
            imageViewList.add(view);
        }*/

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CircleViewPagerAdapter(context,listBeen));
    }


    class GetListTask extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            return null;
        }
    }

}
