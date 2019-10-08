package com.abc.simple.base.java;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abc.baseloadview.views.SimpleBaseLoadView;
import com.abc.simple.base.joggle.IBaseActivity;


/**
 * @name lz
 * @time 2019/9/2 15:18
 */
public abstract class BaseLoadFragment extends Fragment implements IBaseActivity {

    private SimpleBaseLoadView mBaseLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseLayout = new SimpleBaseLoadView(getContext(),setLayout());
        init();
        return mBaseLayout.getLayoutView();
    }

    public void showError(){
        mBaseLayout.show(mBaseLayout.getErrorViewId());
    }

    public void showBar(){
        mBaseLayout.show(mBaseLayout.getBarViewId());
    }

    public void showContent(){
        mBaseLayout.show(mBaseLayout.getContentViewId());
    }

    public void showEmpty(){
        mBaseLayout.show(mBaseLayout.getEmptyViewId());
    }

    public void showLoading(){
        mBaseLayout.show(mBaseLayout.getLoadingViewId());
    }
}
