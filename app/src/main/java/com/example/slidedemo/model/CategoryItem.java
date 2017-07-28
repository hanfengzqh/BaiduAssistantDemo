package com.example.slidedemo.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryItem extends BaseInfor {
	private int endpage;
	private int allcount = 0;
	private List<AppItem> datalist = new ArrayList<AppItem>();

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	public int getEndpage() {
		return this.endpage;
	}

	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}

	public int getAllcount() {
		return this.allcount;
	}

	public void setDatalist(List<AppItem> datalist) {
		this.datalist = datalist;
	}

	public List<AppItem> getDatalist() {
		return this.datalist;
	}
}
