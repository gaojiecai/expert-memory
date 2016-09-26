package cn.com.hiserivice.hiservice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.hiserivice.hiservice.R;

/**
 * Created by gaojicai1 on 2016/9/25.
 */
public class CustomNavView extends LinearLayout {

    private LinearLayout mLeftItem;
    private TextView mLeftItemTitleView;
    private ImageView mLeftItemImgView;

    private FrameLayout mRightItem;
    private ImageView mRightItemImgView;
    private TextView mRightViewTitleView;

    private FrameLayout mMiddleConainer;
    private TextView mMiddleTitleView;
    private boolean midAlways = true;


    public CustomNavView(Context context) {
        this(context, null);
    }

    public CustomNavView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * default layout to match parent's size
     */
    public CustomNavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_nav_view, this, true);

        mLeftItem = (LinearLayout) findViewById(R.id.left_item);
        mLeftItemTitleView = (TextView) findViewById(R.id.left_item_title);
        mLeftItemImgView = (ImageView) findViewById(R.id.left_item_img);

        mRightItem = (FrameLayout) findViewById(R.id.right_item);
        mRightItemImgView = (ImageView) findViewById(R.id.right_item_img);
        mRightViewTitleView = (TextView) findViewById(R.id.right_item_title);

        mMiddleConainer = (FrameLayout) findViewById(R.id.middle_container);
        mMiddleTitleView = (TextView) findViewById(R.id.title_textView);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mLeftItem.addOnLayoutChangeListener(layoutChangeListener);
        mRightItem.addOnLayoutChangeListener(layoutChangeListener);
    }

    public LinearLayout getmLeftItem() {
        return mLeftItem;
    }

    public TextView getmLeftItemTitleView() {
        return mLeftItemTitleView;
    }

    public ImageView getmLeftItemImgView() {
        return mLeftItemImgView;
    }

    public FrameLayout getmRightItem() {
        return mRightItem;
    }

    public ImageView getmRightItemImgView() {
        return mRightItemImgView;
    }

    public TextView getmRightViewTitleView() {
        return mRightViewTitleView;
    }

    public FrameLayout getmMiddleConainer() {
        return mMiddleConainer;
    }

    public TextView getmMiddleTitleView() {
        return mMiddleTitleView;
    }

    OnLayoutChangeListener layoutChangeListener = new OnLayoutChangeListener() {
        @Override
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMidAlways(boolean midAlways) {
        this.midAlways = midAlways;
    }

    public CustomNavView reset() {
        this.setVisibility(VISIBLE);

        mLeftItemTitleView.setVisibility(GONE);
        mLeftItemTitleView.setText(null);
        mLeftItemImgView.setVisibility(GONE);
        mLeftItemImgView.setImageDrawable(null);

        mLeftItem.setOnClickListener(null);
        mLeftItem.removeAllViews();
        mLeftItem.setMinimumWidth(0);
        mLeftItem.addView(mLeftItemTitleView);
        mLeftItem.addView(mLeftItemImgView);

        mRightViewTitleView.setVisibility(GONE);
        mRightViewTitleView.setText(null);
        mRightItemImgView.setVisibility(GONE);
        mRightItemImgView.setImageDrawable(null);

        mRightItem.setOnClickListener(null);
        mRightItem.removeAllViews();
        mRightItem.setMinimumWidth(0);
        mRightItem.addView(mRightItemImgView);
        mRightItem.addView(mRightViewTitleView);

        mMiddleTitleView.setVisibility(GONE);
        mMiddleTitleView.setText(null);
        mMiddleConainer.removeAllViews();
        midAlways = true;
        mMiddleConainer.addView(mMiddleTitleView);
        return this;
    }

    public interface ICustomNavBar {
        void customNacBar(CustomNavView navView);
    }
}
