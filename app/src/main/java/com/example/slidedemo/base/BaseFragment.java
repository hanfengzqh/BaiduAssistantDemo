package com.example.slidedemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements OnClickListener {

	protected View mContentView;
	public Context mContext;
	public Activity mActivity;
	protected Messenger mMessenger;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/** 设置竖屏加载 */
		getActivity().setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (mContentView == null) {
			mContext = getActivity();
			mActivity = getActivity();
			mContentView = inflater.inflate(getContentView(), container, false);
			ButterKnife.bind(this, mContentView); // 注入view和事件
			initContentView(mContentView);
		}

		// 判断Fragment对应的Activity是否存在这个视图
		ViewGroup parent = (ViewGroup) mContentView.getParent();
		if (parent != null) {
			// 如果存在,那么我就干掉,重写添加,这样的方式我们就可以缓存视图
			parent.removeView(mContentView);
		}
		return mContentView;
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		onClickEvent(v);
	}

	/** 返回布局文件ID */
	public abstract int getContentView();

	/** 初始化相关控件 */
	public abstract void initContentView(View viewContent);

	/** 点击事件的实现 */
	protected abstract void onClickEvent(View paramView);

	/**
	 * 设置监听器注册广播等
	 */
	protected abstract void setListener();

	public void initData() {
	}

	public void callAfterReady(final int id, final Object... args) {
		afterServiceReady("", id, args);
	}

	public void onMessageReceive(int msgType, Bundle data) {
	}

	protected void afterServiceReady(String mgr, int id, Object... args) {
	}
	
	
}
