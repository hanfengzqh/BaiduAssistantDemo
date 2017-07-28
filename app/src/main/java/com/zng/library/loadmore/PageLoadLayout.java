package com.zng.library.loadmore;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.slidedemo.R;



/**
 * 继承自SwipeRefreshLayout,从而实现滑动到底部时上拉加载更多的功能.
 */
public class PageLoadLayout extends SwipeRefreshLayout implements AbsListView.OnScrollListener{

    /**
     * 滑动到最下面时的上拉操作
     */
    private int mTouchSlop;
    /**
     * listview实例
     */
    private ListView mListView;

    /**
     * 上拉监听器, 到了最底部的上拉加载操作
     */
    private OnLoadListener mOnLoadListener;

    /**
     * ListView的加载中footer
     */
    private View mListViewFooter;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;
    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    /**
     * 是否完成分页加载
     */
    private boolean iscompleteLoading = false;

    public PageLoadLayout(Context context){
        this(context, null);
    }

   public PageLoadLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.core_listview_footer, null, false);
    }

	@Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed, left, top, right, bottom);
        // 初始化ListView对象
        if(mListView == null){
            getListView();
        }
    }

    /**
     * 获取ListView对象
     */
    private void getListView(){
        int childs = getChildCount();
        if(childs > 1){
            View childView = getChildAt(1);
            if(childView instanceof ListView){
                mListView = (ListView)childView;
//                mListView.addFooterView(mListViewFooter);
                // 设置滚动监听器给ListView, 使得滚动的情况下也可以自动加载
                mListView.setOnScrollListener(this);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        final int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                // 按下
                mYDown = (int)event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                // 移动
                mLastY = (int)event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                // 抬起
                if(canLoad()){
                    loadData();
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * 是否可以加载更多, 条件是到了最底部, listview不在加载中, 且为上拉操作.
     */
    private boolean canLoad(){
    	
        return isBottom() && !isLoading && isPullUp();
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isBottom(){
        if(mListView != null && mListView.getAdapter() != null){
            //最后一个可见的条目位置是否等于数据源的长度减一（即最大下标）
            return mListView.getLastVisiblePosition() == (mListView.getAdapter().getCount() - 1);
        }
        return false;
    }

    /**
     * 是否是上拉操作
     */
    private boolean isPullUp(){
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 如果到了最底部,而且是上拉操作.那么执行onLoad方法
     */
    private void loadData(){
        if(iscompleteLoading){   //完成加载，不需要再进行分页加载数据
            return;
        }
        if(mOnLoadListener != null){
            // 设置状态
            setLoading(true);
            //
            mOnLoadListener.onPageLoad();
        }
    }

    /**
     * 是否显示分页加载，true：显示，false：不显示
     */
    public void setLoading(boolean loading){
        isLoading = loading;
        if(isLoading){
            mListView.addFooterView(mListViewFooter);
        }else{
            mListView.removeFooterView(mListViewFooter);
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * 完成分页加载，没有更多数据了
     */
    public void completePageData(){
        setLoading(false);
        iscompleteLoading = true;
        mListViewFooter.setVisibility(View.GONE);
    }

    /**
     * @param loadListener
     */
    public void setOnLoadListener(OnLoadListener loadListener){
        mOnLoadListener = loadListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState){
    }

    @Override
    public void onScroll(
            AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount){
        // 滚动时到了最底部也可以加载更多
        if(canLoad()){
            loadData();
        }
    }

    /**
     * 加载更多的监听器,加载下一页 
     * @author mrsimple
     */
    public interface OnLoadListener{
        void onPageLoad();
    }
}