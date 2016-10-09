package cn.com.hiservice.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.com.hiservice.R;
import cn.com.hiservice.vo.HomeData;

/**
 * Created by gaojicai1 on 2016/10/8.
 */
public class CircleViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<HomeData.BannerListBean> listBeen;
    private List<LinearLayout> linearLayouts = new ArrayList<>();

    public CircleViewPagerAdapter(Context context, List<HomeData.BannerListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(linearLayouts.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.image, null);


        SimpleDraweeView imageView = (SimpleDraweeView) view.findViewById(R.id.product_image_view);
        imageView.setImageURI(listBeen.get(position).getImg());
        linearLayouts.add(view);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
