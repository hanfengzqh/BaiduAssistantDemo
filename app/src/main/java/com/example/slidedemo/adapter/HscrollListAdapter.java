package com.example.slidedemo.adapter;

import java.util.List;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.example.slidedemo.R;
import com.example.slidedemo.R.id;
import com.example.slidedemo.R.layout;
import com.example.slidedemo.base.BaseCustomAdapter;
import com.example.slidedemo.model.CategoryInfor;
import com.example.slidedemo.ui.MainActivity;
import com.zng.library.MGridView;

public class HscrollListAdapter extends BaseCustomAdapter<CategoryInfor> {

	private DisplayMetrics dm;

	public HscrollListAdapter(List<CategoryInfor> data, Context mContext) {
		super(data, mContext);

	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.catagory_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		int NUM = 5;// 每行显示的个数
////		int hSpacing = 10;// 水平间距
		getScreenDen();
		int size = data.size();
//		// 列数
		int columns = (size % 2 == 0) ? size / 2 : size / 2 + 1;
		// 设置gridview的宽度
		LayoutParams params = new LayoutParams(columns * dm.widthPixels / NUM,
				LayoutParams.MATCH_PARENT);
		holder.gv_view.setLayoutParams(params);
//		 设置列宽
		holder.gv_view.setColumnWidth(dm.widthPixels / NUM);
////		holder.gv_view.setHorizontalSpacing(hSpacing);
		holder.gv_view.setStretchMode(GridView.NO_STRETCH);
		if (size <= 3) {
			holder.gv_view.setNumColumns(size);
		} else {
			holder.gv_view.setNumColumns(columns);
		}
		holder.gridViewAdapter = new GridViewCategoryAdapter(data, mContext);
		holder.gv_view.setAdapter(holder.gridViewAdapter);
		
		holder.gv_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(mContext, "position = "+position, 0).show();
			}
		});
//		
//		holder.gv_view.setOnTouchListener(new View.OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				return true;
//			}
//		});
		
		return convertView;
	}

	static class ViewHolder {

		@Bind(R.id.gv_view)
		MGridView gv_view;
		GridViewCategoryAdapter gridViewAdapter;

		public ViewHolder(View convertView) {
			ButterKnife.bind(this, convertView);
		}
	}

	private void getScreenDen() {
		dm = new DisplayMetrics();
		MainActivity mainActivity = (MainActivity) mContext;
		mainActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

}
