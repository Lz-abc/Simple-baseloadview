package com.abc.simple.ui.adapter;


import android.support.annotation.Nullable;

import com.abc.simple.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * @name lz
 * @time 2019/6/26 11:57
 */
public class MainAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainAdapter(@Nullable List<String> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text, item);
    }

}
