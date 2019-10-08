package com.abc.baseloadview.views;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

import com.abc.baseloadview.R;

/**
 * @name lz
 * @time 2019/9/29 16:40
 */
public  class SimpleBaseLoadView extends IBaseLoadView {
    /**
     * 构造方法
     *
     * @param context
     * @param layoutId 正文layoutId
     */
    public SimpleBaseLoadView(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    @LayoutRes
    public int getLoadLayoutId() {
        return R.layout.layout;
    }

    @Override
    @IdRes
    public int getErrorViewId() {
        return R.id.errorView;
    }

    @Override
    @IdRes
    public int getLoadingViewId() {
        return R.id.loadingView;
    }

    @Override
    @IdRes
    public int getEmptyViewId() {
        return R.id.emptyView;
    }

    @Override
    @IdRes
    public int getBarViewId() {
        return R.id.barView;
    }

    @Override
    @IdRes
    public int getContentViewId() {
        return R.id.contentView;
    }

    @Override
    public boolean isCloseLoadVew() {
        return false;
    }
}
