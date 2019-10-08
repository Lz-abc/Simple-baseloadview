package com.abc.simple.base.java;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.baseloadview.views.SimpleBaseLoadView;
import com.abc.simple.R;
import com.abc.simple.base.joggle.IBaseActivity;
import com.gyf.immersionbar.ImmersionBar;


/**
 * @name lz
 * @time 2019/9/29 17:48
 */
public abstract class BaseLoadActivity extends Activity implements IBaseActivity, View.OnClickListener{

    private SimpleBaseLoadView mLoadView;

    public boolean isCloseLoadVew(){
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        mLoadView = new SimpleBaseLoadView(this, setLayout()){
            @Override
            public boolean isCloseLoadVew() {
                return BaseLoadActivity.this.isCloseLoadVew();
            }
        };
        setContentView(mLoadView.getLayoutView());
        showBar(true,true);
        init();
        if ( mLoadView.getView(R.id.ivPic)!=null) {
            mLoadView.getView(R.id.ivPic).setOnClickListener(this);
        }
    }
    public void setIvPicImg(int imgId) {
        ImageView ivPic = (ImageView) mLoadView.getView(R.id.ivPic);
        if (ivPic == null)
            return;
        if (imgId <= 0)
            return;
        ivPic.setImageResource(imgId);
    }

    public void setTitle(String title) {
        TextView tvTitle = (TextView) mLoadView.getView(R.id.tvTitle);
        if (tvTitle == null)
            return;
        if (TextUtils.isEmpty(title))
            return;
        tvTitle.setText(title);
    }

    public TextView getTvRight() {
        return (TextView) mLoadView.getView(R.id.tvRight);
    }

    public void setTvRight(String msg, View.OnClickListener listener) {
        if (!TextUtils.isEmpty(msg)) {
            TextView tvRight = getTvRight();
            if (tvRight == null) {
                return;
            }
            tvRight.setText(msg);
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setOnClickListener(listener);
        }
    }


    public void showError() {
        mLoadView.showError();
    }

    public void showBar(boolean isShow, boolean isFillStatus) {
        mLoadView.showBar(isShow, isFillStatus);
    }

    public void showContent() {
        mLoadView.showContent();
    }

    public void showEmpty() {
        mLoadView.showEmpty();
    }

    public void showLoading() {
        mLoadView.showLoading();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivPic:
                onLeftPicClick();
                break;
        }
    }

    public void onLeftPicClick(){

    }

}
