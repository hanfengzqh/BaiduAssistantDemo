package com.example.slidedemo.model;

import android.graphics.drawable.Drawable;

/**
 * 推荐界面10个分类图标infor
 * @author zqh
 *
 */
public class CategoryInfor extends BaseInfor {

	public int _id;
	public String category_name;
	public String category_image_path;
	public Drawable image;
	
	public Drawable getImage() {
		return image;
	}
	public void setImage(Drawable image) {
		this.image = image;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_image_path() {
		return category_image_path;
	}
	public void setCategory_image_path(String category_image_path) {
		this.category_image_path = category_image_path;
	}
	
}
