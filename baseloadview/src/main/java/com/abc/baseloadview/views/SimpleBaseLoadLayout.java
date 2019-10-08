package com.abc.baseloadview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.abc.baseloadview.R;


/**
 * 可实现xml
 * 附带加载中、加载错误、加载完成、bar
 * 实现动态配置
 * @name lz
 * @time 2019/7/8 16:35
 */
public class SimpleBaseLoadLayout extends FrameLayout {

    private SimpleBaseLoadView mLoadView;

    public SimpleBaseLoadLayout(@NonNull Context context) {
        this(context,null);
    }

    public SimpleBaseLoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SimpleBaseLoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleBaseLoadLayout);
        try {
            int contentId = ta.getResourceId(R.styleable.SimpleBaseLoadLayout_contentView, -1);
            if (contentId > 0) {
                mLoadView= new SimpleBaseLoadView(getContext(),contentId);
                addView(mLoadView.getLayoutView());
            }
        }catch (Exception e){
            Log.e("LoadingLayoutView",e.getMessage());
        }finally {
            ta.recycle();
        }
    }

    public SimpleBaseLoadView getLoadView() {
        return mLoadView;
    }

    public boolean isShowBar(){
        if (mLoadView!=null){
            return mLoadView.isShowBar();
        }
        return false;
    }

    public void showError() {
        if (mLoadView!=null)
        mLoadView.show(mLoadView.getErrorViewId());
    }

    public void showBar(boolean isShow, boolean isFillStatus) {
        if (mLoadView!=null)
        mLoadView.showBar(isShow, isFillStatus);
    }


    public void showContent() {
        if (mLoadView!=null)
        mLoadView.show(mLoadView.getContentViewId());
    }

    public void showEmpty() {
        if (mLoadView!=null)
        mLoadView.show(mLoadView.getEmptyViewId());
    }

    public void showLoading() {
        if (mLoadView!=null)
        mLoadView.show(mLoadView.getLoadingViewId());
    }

}
