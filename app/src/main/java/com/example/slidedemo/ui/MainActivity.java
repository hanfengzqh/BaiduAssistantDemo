package com.example.slidedemo.ui;

import java.util.ArrayList;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.slidedemo.R;
import com.example.slidedemo.adapter.GridViewCategoryAdapter;
import com.example.slidedemo.adapter.RecoListViewAdapter;
import com.example.slidedemo.model.CategoryInfor;
import com.nineoldandroids.view.ViewHelper;
import com.zng.library.MGridView;
import com.zng.library.loadmore.CollectionUtil;
import com.zng.library.loadmore.EloadType;
import com.zng.library.loadmore.ObservableListView;
import com.zng.library.loadmore.PageLoadLayout;
import com.zng.library.loadmore.ScrollState;
import com.zng.library.loadmore.ScrollUtils;
import com.zng.library.loadmore.Scrollable;
import com.zng.library.widget.TouchInterceptionFrameLayout;

public class MainActivity extends FragmentActivity implements PageLoadLayout.OnLoadListener{

	@Bind(R.id.container)
	TouchInterceptionFrameLayout mContainer;
	@Bind(R.id.toolbar_translationY)
	TextView mToolbarView;
	@Bind(R.id.toolbar)
	View toolbar;
	@Bind(R.id.view_toolbar)
	RelativeLayout view_toolbar;
	@Bind(R.id.pagerload_id)
	PageLoadLayout pageLoadLayout;
	@Bind(R.id.listview_content)
	ObservableListView mListView;
	@Bind(R.id.rl_search)
	RelativeLayout rl_search;
	@Bind(R.id.home_title_search)
	EditText home_title_search;// 搜索编辑框
	@Bind(R.id.title_message)
	TextView title_message;// 信息管理
	@Bind(R.id.title_download_manage)
	TextView title_download_manage;// 下载管理
	
	private RecoListViewAdapter mAdapter;
	private ScrollState mLastScrollState;
	/**父布局是否在滑动,false--滑动;true--不滑动*/
	private boolean mScrolled;
	private int tabHeight;
	private int mSlop;
	private int tabHeight1;
	boolean isFirst = true;
	private DisplayMetrics dm;
	private GridViewCategoryAdapter gridViewAdapter;
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<CategoryInfor> categoryList = new ArrayList<CategoryInfor>();
	private Context mContext;
	private View heard;
	private MGridView gv_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 隐藏标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置屏幕常亮不休眠--不需要权限
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		setContentView(R.layout.fragment_recom_data_result);
		ButterKnife.bind(this);
		mContext = this;
		init();
	}
	
	private void init() {
		setValue();
		initView();
		onDrawView();
	}
	
	private void initHearder(){
		heard = View.inflate(this, R.layout.hearder_recom_listview, null);
		gv_view = (MGridView) heard.findViewById(R.id.gv_view);
//		heard.measure(0, 0);
//		int heardheight = heard.getMeasuredHeight();
//		heard.setPadding(0, heardheight-520, 0, 0);
		mListView.addHeaderView(heard);
	}
	
	
	private void initView() {
		//listView的相关设置
		mListView.setTouchInterceptionViewGroup(mContainer);
		pageLoadLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
		// 启用加载更多功能
		pageLoadLayout.setOnLoadListener(this);
		//禁止下拉刷新
		pageLoadLayout.setEnabled(false);
		home_title_search.setInputType(InputType.TYPE_NULL);
		
		initHearder();
		setGridView();
		gridViewAdapter = new GridViewCategoryAdapter(categoryList, mContext);
		gv_view.setAdapter(gridViewAdapter);
		gv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "position = "+categoryList.get(position).getCategory_name(),0).show();;
			}
		});
		
		if (isFirst && mContext != null) {
			isFirst = false;
			// 第一次加载
			loadData(EloadType.FIRST);
		}
	}
	
	private void onDrawView() {
		ViewHelper.setAlpha(toolbar, 0);
		//set listview move event start 
		tabHeight = mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_111);
		tabHeight1 = mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_60);
		int ListPaddingHeight = mContext.getResources().getDimensionPixelSize(R.dimen.view_margin_318);
		pageLoadLayout.setPadding(0,ListPaddingHeight, 0, 0);
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
					//系统中fragment的view的id对应着android.R.id.content的id,获取到的是fragment的view
					/*lp.height = (int) (-translationY + mContext.
							findViewById(android.R.id.content).getHeight());*/
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
		
		/**
		 * 加载网络数据
		 * @param eloadType
		 */
		private void loadData(final EloadType eloadType) {
			for (int j = 0; j < 10; j++) {
				list.add("");
			}
			
			switch (eloadType) {
			case FIRST:
				mAdapter = new RecoListViewAdapter(list,mContext);
				mListView.setAdapter(mAdapter);
				mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						
						Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
					
					}
				});
				break;
			case PAGE:
				if (CollectionUtil.listIsNull(list)) {
//					mainActivity.showSnackbarMessage(EStyle.INFO, "没有更多数据");
					// 结束分页加载功能
					pageLoadLayout.completePageData();
				} else {
					// 分页返回数据
					mAdapter.addAll(list);
					// 完成分页加载数据
					pageLoadLayout.setLoading(false);
				}
				break;
			default:
				break;
			}
		}

		@Override
		public void onPageLoad() {
			Log.d("zqh", "加载更多");
			loadData(EloadType.PAGE);
		}
		
		private void getScreenDen() {
			dm = new DisplayMetrics();
			MainActivity mainActivity = (MainActivity) mContext;
			mainActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		}
		

	private void setValue() {
		for (int i = 0; i < 10; i++) {
			CategoryInfor infor = new CategoryInfor();
			if (i == 0) {
				infor.set_id(0);
				infor.setCategory_name("外卖接单");
			} else if (i == 1) {
				infor.set_id(1);
				infor.setCategory_name("餐饮管理");
			} else if (i == 2) {
				infor.set_id(2);
				infor.setCategory_name("会员管理");
			} else if (i == 3) {
				infor.set_id(3);
				infor.setCategory_name("团购电商");
			} else if (i == 4) {
				infor.set_id(4);
				infor.setCategory_name("支付平台");
			} else if (i == 5) {
				infor.set_id(5);
				infor.setCategory_name("娱乐休闲");
			} else if (i == 6) {
				infor.set_id(6);
				infor.setCategory_name("排队领号");
			} else if (i == 7) {
				infor.set_id(7);
				infor.setCategory_name("生活服务");
			} else if (i == 8) {
				infor.set_id(8);
				infor.setCategory_name("零售服务");
			} else if (i == 9) {
				infor.set_id(9);
				infor.setCategory_name("使用工具");
			}
//			else if (i == 10) {
//				infor.set_id(10);
//				infor.setCategory_name("系统预装");
//			}
			categoryList.add(infor);
		}
	}
	
	/** GridView的屏幕显示设置 */
	private void setGridView() {
		int NUM = 5;// 每行显示的个数
		// // int hSpacing = 10;// 水平间距
		getScreenDen();
		int size = categoryList.size();
		// // 列数
		int columns = (size % 2 == 0) ? size / 2 : size / 2 + 1;
		// 设置gridview的宽度
		LayoutParams params = new LayoutParams(columns * dm.widthPixels / NUM,
				LayoutParams.MATCH_PARENT);
		gv_view.setLayoutParams(params);
		// 设置列宽
		gv_view.setColumnWidth(dm.widthPixels / NUM);
		// // holder.gv_view.setHorizontalSpacing(hSpacing);
		gv_view.setStretchMode(GridView.NO_STRETCH);
		if (size <= 3) {
			gv_view.setNumColumns(size);
		} else {
			gv_view.setNumColumns(columns);
		}
	}
}
