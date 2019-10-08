package com.abc.baseloadview.views;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.abc.baseloadview.R;
import com.abc.baseloadview.utils.ScreenUtils;

import java.lang.ref.WeakReference;

/**
 * @name lz
 * @time 2019/7/10 14:59
 */
public abstract class IBaseLoadView<T> {

    private View mLayoutView;
    private View mContentView;
    private View mBarView;
    private WeakReference<Context> mContext;
    private final SparseArray<View> mFirstViews;//一级视图列表
    private final SparseArray<View> mViews;//子集视图列表

    /**
     * 构造方法
     *
     * @param context
     * @param layoutId 正文layoutId
     */
    public IBaseLoadView(Context context, int layoutId) {
        mContext = new WeakReference<>(context);
        mViews = new SparseArray<>();
        mFirstViews = new SparseArray<>();
        mContentView = LayoutInflater.from(mContext.get()).inflate(layoutId, null);
        if (mLayoutView==null&&!isCloseLoadVew()) {
            mLayoutView = LayoutInflater.from(mContext.get()).inflate(getLoadLayoutId(), null);
            addViews(mContentView);
        }
    }


    /**
     * 默认将视图添加进views
     *
     * @param contentView 正文视图
     */
    private void addViews(View contentView) {
        convert(getEmptyViewId());
        convert(getLoadingViewId());
        convert(getErrorViewId());
        FrameLayout view = convert(getContentViewId());
        view.addView(contentView);
    }


    /**
     * 根据id查找view
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null&&mLayoutView!=null) {
            view = mLayoutView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 根据viewId 获取视图view
     * 获取一级视图
     * 不支持外部调用
     *
     * @param viewId
     * @param <T>
     * @return
     */
    private <T extends View> T convert(@IdRes int viewId) {
        View view = mFirstViews.get(viewId);
        if (view == null) {
            view = mLayoutView.findViewById(viewId);
            mFirstViews.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 实现bar 可以与其它视图并存 所以单独出来
     *
     * @param isShow       是否显示bar
     * @param isFillStatus 是否填充状态栏
     */
    public void showBar(boolean isShow, boolean isFillStatus) {
        if (mLayoutView == null) {
            return;
        }
        if (mBarView == null) {
            mBarView = mLayoutView.findViewById(R.id.barView);
        }
        if (isShow) {
            mBarView.setVisibility(View.VISIBLE);
        } else {
            mBarView.setVisibility(View.GONE);
        }
        if (isFillStatus) {
            setStatusBarHeight();
        }
    }

    public boolean isShowBar(){
        if (mBarView==null){
            return false;
        }
        return mBarView.getVisibility()==View.VISIBLE;
    }

    /**
     * 設置填充状态栏高度
     */
    private void setStatusBarHeight() {
        if (mBarView != null) {
            mBarView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ScreenUtils.getStatusBarHeight(mContext.get()) + ScreenUtils.getBarHeight(mContext.get())));
        }
    }

    /**
     * 显示指定视图
     *
     * @param showViewId 视图id
     */
    public void show(int showViewId) {
        if (showViewId == 0) {
            Log.e("IBaseLoadView","showView not null");
            return;
        }
        for (int i = 0; i < mFirstViews.size(); i++) {
            int id = mFirstViews.keyAt(i);
            if (id == showViewId) {
                getView(id).setVisibility(View.VISIBLE);
            } else {
                getView(id).setVisibility(View.GONE);
            }
        }
    }

    /**
     * 父视图  Activity设置setContentView（getLayoutView()）
     *
     * @return
     */
    public View getLayoutView() {
        if (!isCloseLoadVew()) {
            return mLayoutView;
        }
        return mContentView;
    }

    public abstract
    @LayoutRes
    int getLoadLayoutId();

    public abstract
    @IdRes
    int getErrorViewId();

    public abstract
    @IdRes
    int getLoadingViewId();

    public abstract
    @IdRes
    int getEmptyViewId();

    public abstract
    @IdRes
    int getBarViewId();

    public abstract
    @IdRes
    int getContentViewId();

    /**
     * 是否关闭Load视图 用于单独页面关闭Load视图
     * 默认开启
     * @return
     */
    public abstract boolean isCloseLoadVew();
}
