# Simple-baseloadview
简单的基础加载框架

![image](https://raw.githubusercontent.com/Lz-abc/Simple-baseloadview/master/pic/ezgif-4-26728a0fcb4f.gif)


### 在自己的baseActivity中加入这段代码
 ``` 
 mLoadView = new SimpleBaseLoadView(this, setLayout());
 setContentView(mLoadView.getLayoutView());
 ```
 
 ###  使用
 ```
 showBar(boolean isShow, boolean isFillStatus)//设置是否显示bar 是否填充状态栏
 showError()//显示错误
 showContent()//显示正文
 showEmpty()//显示空
 showLoading()//显示加载中
 ```
 
 ### 如果需要有部分activity 不想使用可以重写方法
``` 
 mLoadView = new SimpleBaseLoadView(this, setLayout()){
            @Override
            public boolean isCloseLoadVew() {
                return true;
            }
        };
setContentView(mLoadView.getLayoutView());
```

### 想要自定义加载视图可以这么做
``` 
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
```
