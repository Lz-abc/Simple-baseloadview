package com.abc.simple.ui.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abc.simple.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView 空数据视图
 *
 * @name lz
 * @time 2019/8/22 16:36
 */
public class RVListEmptyView extends LinearLayout {
    @BindView(R.id.tv_msg)
    TextView tvMsg;

    public RVListEmptyView(Context context) {
        this(context, null);
    }

    public RVListEmptyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RVListEmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.empty_view, this, true);
        ButterKnife.bind(this);
    }
}
