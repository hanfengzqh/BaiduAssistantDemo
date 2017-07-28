package com.example.slidedemo.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.example.slidedemo.R;
import com.example.slidedemo.R.drawable;
import com.example.slidedemo.R.id;
import com.example.slidedemo.R.layout;
import com.example.slidedemo.base.BaseCustomAdapter;
import com.example.slidedemo.model.CategoryInfor;

public class GridViewCategoryAdapter extends BaseCustomAdapter<CategoryInfor> {

	public GridViewCategoryAdapter(List<CategoryInfor> data, Context mContext) {
		super(data, mContext);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		CategoryInfor infor = data.get(position);
		if(convertView == null){
			convertView = View.inflate(mContext, R.layout.category_grid_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_categgory_title.setText(infor.getCategory_name());
		//如果创建下载时没有传入image参数, 则获取到的image为null
        if (TextUtils.isEmpty(infor.getCategory_image_path())) {
            Glide.with(mContext).load(R.drawable.app_icon_default).into(holder.iv_category);
        } else {
        	Glide.with(mContext).load(infor.getCategory_image_path()).into(holder.iv_category);
        }
//		Glide.with(mContext).load("").
//		placeholder(R.drawable.recommend_category_abulk_electricity_unpressed).
//		error(R.drawable.app_icon_default).dontAnimate().thumbnail(0.1f).into(holder.iv_category);
		
		return convertView;
	}
	
	static class ViewHolder {
		@Bind(R.id.iv_category)
		ImageView iv_category;
		@Bind(R.id.tv_category_title)
		TextView tv_categgory_title;
		
		public ViewHolder(View convertView) {
			ButterKnife.bind(this, convertView);
		}
	}

}
