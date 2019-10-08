package com.abc.simple.ui.main;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abc.baseloadview.views.SimpleBaseLoadLayout;
import com.abc.simple.R;
import com.abc.simple.base.java.BaseLoadActivity;
import com.abc.simple.ui.adapter.MainAdapter;
import com.abc.simple.ui.views.RVListEmptyView;
import com.abc.simple.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseLoadActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.rvList)
    RecyclerView rvList;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.loadingLayout)
    SimpleBaseLoadLayout loadingLayout;

    List<String> mList = new ArrayList<>();

    MainAdapter mMainAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }


    @Override
    public boolean isCloseLoadVew() {
        return true;
    }

    @Override
    public void init() {
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(mMainAdapter = new MainAdapter(mList));
        mMainAdapter.setEmptyView(new RVListEmptyView(this));
        mMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "onClick item " + position, Toast.LENGTH_SHORT).show();
            }
        });
        setList();
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                setList();
                srl.setRefreshing(false);
            }
        });
        mMainAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setList();
                        mMainAdapter.loadMoreComplete();
                    }
                }, 3000);
            }
        }, rvList);

        loadingLayout.showBar(false, false);
        loadingLayout.showContent();
        initListener();
    }

    private void initListener(){
        loadingLayout.getLoadView().getView(loadingLayout.getLoadView().getEmptyViewId()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了 EmptyView");
            }
        });
        loadingLayout.getLoadView().getView(loadingLayout.getLoadView().getErrorViewId()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("点击了 ErrorView");
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLeftPicClick() {
        super.onLeftPicClick();
        drawer.openDrawer(GravityCompat.START);
    }

    Handler mHandler = new Handler();

    private void setList() {
        int length = mList.size();
        for (int i=length; i < length + 50; i++) {
            mList.add("item" + i);
        }
        mMainAdapter.setNewData(mList);
        mMainAdapter.loadMoreComplete();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.showBar:
                loadingLayout.showBar(!loadingLayout.isShowBar(), true);
                break;
            case R.id.showLoading:
                loadingLayout.showLoading();
                break;
            case R.id.showContent:
                loadingLayout.showContent();
                break;
            case R.id.showError:
                loadingLayout.showError();
                break;
            case R.id.showEmpty:
                loadingLayout.showEmpty();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
