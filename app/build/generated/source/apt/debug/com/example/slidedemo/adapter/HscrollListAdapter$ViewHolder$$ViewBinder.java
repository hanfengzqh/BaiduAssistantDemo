// Generated code from Butter Knife. Do not modify!
package com.example.slidedemo.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HscrollListAdapter$ViewHolder$$ViewBinder<T extends com.example.slidedemo.adapter.HscrollListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427391, "field 'gv_view'");
    target.gv_view = finder.castView(view, 2131427391, "field 'gv_view'");
  }

  @Override public void unbind(T target) {
    target.gv_view = null;
  }
}
