package com.example.slidedemo.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.zng.library.Constants;

public abstract class HomeBaseWidget<T> {

	private String TAG = "HomeBaseWidget";
	// 必须为Activity,弹dialog要用
	protected Activity mContext;

	public void setActivity(Activity activity) {
		this.mContext = activity;
	}

	public abstract View initContentView(T t);

	public void setOnTouchListener(View.OnTouchListener t) {
	};

	public void setOnItemClickListener(AdapterView.OnItemClickListener i) {
	};

	public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener i) {
	};

	public boolean onTouch(View v, MotionEvent event) {
		return false;
	};

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	};

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
	};

	public void onContentChange(T t) {
	};

	public void refreshItem(String packageName) {
	};

	public void refreshSelf() {
	};

	public void onDestroy() {
	};

	public void onPause() {
	};

	public void onResume() {
	};

}
