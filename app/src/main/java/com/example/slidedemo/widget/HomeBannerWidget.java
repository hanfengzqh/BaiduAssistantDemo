package com.example.slidedemo.widget;

import java.util.ArrayList;

import android.animation.ValueAnimator;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.slidedemo.R;
import com.nineoldandroids.view.ViewHelper;
import com.zng.library.loadmore.ObservableListView;
import com.zng.library.loadmore.ScrollState;
import com.zng.library.loadmore.ScrollUtils;
import com.zng.library.loadmore.Scrollable;
import com.zng.library.widget.TouchInterceptionFrameLayout;

public class HomeBannerWidget extends HomeBaseWidget<ArrayList<String>> {
	
	@Bind(R.id.container)
	TouchInterceptionFrameLayout mContainer;
	@Bind(R.id.toolbar_translationY)
	TextView mToolbarView;
	@Bind(R.id.toolbar)
	View toolbar;
	@Bind(R.id.listview_content)
	ObservableListView mListView;
	@Bind(R.id.view_toolbar)
	RelativeLayout view_toolbar;
	@Bind(R.id.rl_search)
	RelativeLayout rl_search;
	@Bind(R.id.home_title_search)
	EditText home_title_search;// 搜索编辑框
	@Bind(R.id.title_message)
	TextView title_message;// 信息管理
	@Bind(R.id.title_download_manage)
	TextView title_download_manage;// 下载管理
	
	private static Object objClock  = new Object();
	private static HomeBannerWidget mInstance;
	private ScrollState mLastScrollState;
	private View bannerView;
	private int tabHeight;
	private int tabHeight1;
	private int mSlop;
	/**父布局是否在滑动,false--滑动;true--不滑动*/
	private boolean mScrolled;
	
	public static HomeBannerWidget getInstance(){
		synchronized (objClock) {
			if(mInstance == null){
				mInstance = new HomeBannerWidget();
			}
			return mInstance;
		}
	}
	
	@Override
	public View initContentView(ArrayList<String> t) {
		bannerView = View.inflate(mContext, R.layout.fragment_recom_data_result, null);
		ButterKnife.bind(this, bannerView);
		onDrawView();
		
		return bannerView;
	}
	
	private void onDrawView() {
		ViewHelper.setAlpha(toolbar, 0);
		//set listview move event start 
		tabHeight = mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_111);
		tabHeight1 = mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_60);
		//set listview move event end 
		ViewConfiguration vc = ViewConfiguration.get(mContext);
		mSlop = vc.getScaledTouchSlop();
		mContainer.setScrollInterceptionListener(mInterceptionListener);
	}
	
	//判断父容器是否拦截点击事件
		private TouchInterceptionFrameLayout.TouchInterceptionListener mInterceptionListener = new TouchInterceptionFrameLayout.TouchInterceptionListener() {
			
			/*向上滑动diffY<0;向下滑动diffY>0//若是该布局应该拦截触摸事件则返回true
			diffY:Y轴方向上两个坐标点的差值。向上滑动时，两点之间的差值为<0;向下滑动时，两点间的差值为>0*/
			@Override
			public boolean shouldInterceptTouchEvent(MotionEvent ev,
					boolean moving, float diffX, float diffY) {
				
				if (!mScrolled && mSlop < Math.abs(diffX)
						&& Math.abs(diffY) < Math.abs(diffX)) {
					return false;
				}
	
				Scrollable scrollable = getCurrentScrollable();
				if (scrollable == null) {
					mScrolled = false;
					return false;
				}
	
				int toolbarHeight = mToolbarView.getHeight();//222
				//获取Y轴偏移量 向下移动为负值<0;向下移动为正值>0
				int translationY = (int) getContainerTranslationY();
				
				boolean scrollingDown = 0 < diffY;//向下滑动
				boolean scrollingUp = diffY < 0;//向上滑动
				if (scrollingDown) {//向下滑动
					if (mListView.getFirstVisiblePosition() == 0) {
						if (scrollable.getCurrentScrollY() == 0 && translationY < 0) {
							mScrolled = true;
							mLastScrollState = ScrollState.UP;
							return true;
						}
					}else{
						mScrolled = false;
						return false;
					}
				} else if (scrollingUp) {//向上滑动
	
					if (-toolbarHeight < translationY) {
						mScrolled = true;
						mLastScrollState = ScrollState.DOWN;
						return true;
					}
				}
				mScrolled = false;
				return false;
			}
	
			//向下滑动事件被拦截则调用此方法
			@Override
			public void onDownMotionEvent(MotionEvent ev) {
			}
			
			// 触摸事件被拦截时调用此方法
			@Override
			public void onMoveMotionEvent(MotionEvent ev, float diffX, float diffY) {
				//获取mContainer父控件移动的距离
			float translationY = ScrollUtils.getFloat(getContainerTranslationY() + diffY,
					-mToolbarView.getHeight()+tabHeight1, 0);
				ViewHelper.setTranslationY(mContainer, translationY);//设置view相对原始位置移动的距离
				ViewHelper.setTranslationY(view_toolbar, -translationY);
				if (translationY < tabHeight1) {
					android.view.ViewGroup.LayoutParams lp = mContainer.getLayoutParams();
					lp.height = (int) (-translationY + mContainer.getHeight());
					mContainer.requestLayout();
					fixView(translationY);
				} 
			}
	
			@Override
			public void onUpOrCancelMotionEvent(MotionEvent ev) {
				mScrolled = false;
				adjustToolbar(mLastScrollState);
			}
		};
	
		/**获取实现了Scrollable的listView，进而获取listView上下滑动的距离*/
		private Scrollable getCurrentScrollable() {
			
			return (Scrollable)mListView;
		}
		
		/**根据滑动距离调整搜索框的横向宽度 */
		private void fixView(float translationY) {
			RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) rl_search.getLayoutParams();
			int left = dipToPx(10);
			int right = dipToPx(10);
			float percent = 0;
	
			if (translationY < 0) {
				percent =  -translationY/ tabHeight/2;
			}
	
			ViewHelper.setAlpha(toolbar, percent);
	
			int abSize = getTranslationY();
			percent = -translationY / abSize;
			left += percent * dipToPx(30);
			right += percent * dipToPx(30);
	
			params.setMargins(left, params.topMargin, right, params.bottomMargin);
			rl_search.setLayoutParams(params);
		}
		
		
		public int dipToPx(int dipValue) {
			return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
					dipValue, mContext.getResources().getDisplayMetrics());
		}
		
		/**toolbar透明度调整*/
		private void adjustToolbar(ScrollState scrollState) {
			
			int toolbarHeight = mToolbarView.getHeight();
			final Scrollable scrollable = getCurrentScrollable();
			if (scrollable == null) {
				return;
			}
			int scrollY = scrollable.getCurrentScrollY();
			
			if (scrollState == ScrollState.DOWN) {
				showToolbar();
			} else if (scrollState == ScrollState.UP) {
	
				if (toolbarHeight <= scrollY) {
					hideToolbar();
				} else {
					showToolbar();
				}
			} else if (!toolbarIsShown() && !toolbarIsHidden()) {
				showToolbar();
			}
		}
		
		/**显示toolbar*/
		private void showToolbar() {
	
			if (!toolbarIsShown() && !toolbarIsHidden())
				animateToolbar(0);
		}
	
		/**隐藏toolbar*/
		private void hideToolbar() {
			animateToolbar(-mToolbarView.getHeight());
		}
	
		/**在toY与getContainerTranslationY()之间取值来调整父控件、toolbar、搜索框的形状变化*/
		private void animateToolbar(final float toY) {
			float layoutTranslationY = getContainerTranslationY();
			if (layoutTranslationY != toY) {
				ValueAnimator animator = ValueAnimator.ofFloat(getContainerTranslationY(), toY).setDuration(200);
				animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float translationY = (Float) animation.getAnimatedValue();
						ViewHelper.setTranslationY(mContainer,translationY);
						ViewHelper.setTranslationY(view_toolbar, -translationY);
						fixView(translationY);
					}
				});
				animator.start();
			}
		}
		
		/**toolbar显示的条件*/
		private boolean toolbarIsShown() {
			return getContainerTranslationY() == tabHeight1;
		}
	
		/**toolbar隐藏的条件*/
		private boolean toolbarIsHidden() {
			return getContainerTranslationY() == -mToolbarView.getHeight();
		}
		
		/**整个可滑动调节距离为*/
		private int getTranslationY(){
			return mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_200);
		}
		
		/**获取父控件view相对起始位置的滑动距离=0向下滑动;<0向上滑动*/
		private float getContainerTranslationY(){
			float y = ViewHelper.getTranslationY(mContainer);//获取view相对于起始位置的移动距离
			
			return (y < 0)? (y - tabHeight1) : (y + tabHeight1);
		}
	
}
