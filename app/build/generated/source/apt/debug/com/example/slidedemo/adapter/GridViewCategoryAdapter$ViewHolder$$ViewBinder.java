// Generated code from Butter Knife. Do not modify!
package com.example.slidedemo.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GridViewCategoryAdapter$ViewHolder$$ViewBinder<T extends com.example.slidedemo.adapter.GridViewCategoryAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427392, "field 'iv_category'");
    target.iv_category = finder.castView(view, 2131427392, "field 'iv_category'");
    view = finder.findRequiredView(source, 2131427393, "field 'tv_categgory_title'");
    target.tv_categgory_title = finder.castView(view, 2131427393, "field 'tv_categgory_title'");
  }

  @Override public void unbind(T target) {
    target.iv_category = null;
    target.tv_categgory_title = null;
  }
}
