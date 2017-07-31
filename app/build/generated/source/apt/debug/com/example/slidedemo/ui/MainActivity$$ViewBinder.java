// Generated code from Butter Knife. Do not modify!
package com.example.slidedemo.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.example.slidedemo.ui.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427396, "field 'mContainer'");
    target.mContainer = finder.castView(view, 2131427396, "field 'mContainer'");
    view = finder.findRequiredView(source, 2131427398, "field 'mToolbarView'");
    target.mToolbarView = finder.castView(view, 2131427398, "field 'mToolbarView'");
    view = finder.findRequiredView(source, 2131427414, "field 'toolbar'");
    target.toolbar = view;
    view = finder.findRequiredView(source, 2131427399, "field 'view_toolbar'");
    target.view_toolbar = finder.castView(view, 2131427399, "field 'view_toolbar'");
    view = finder.findRequiredView(source, 2131427401, "field 'pageLoadLayout'");
    target.pageLoadLayout = finder.castView(view, 2131427401, "field 'pageLoadLayout'");
    view = finder.findRequiredView(source, 2131427402, "field 'mListView'");
    target.mListView = finder.castView(view, 2131427402, "field 'mListView'");
    view = finder.findRequiredView(source, 2131427400, "field 'rl_search'");
    target.rl_search = finder.castView(view, 2131427400, "field 'rl_search'");
    view = finder.findRequiredView(source, 2131427428, "field 'home_title_search'");
    target.home_title_search = finder.castView(view, 2131427428, "field 'home_title_search'");
    view = finder.findRequiredView(source, 2131427415, "field 'title_message'");
    target.title_message = finder.castView(view, 2131427415, "field 'title_message'");
    view = finder.findRequiredView(source, 2131427416, "field 'title_download_manage'");
    target.title_download_manage = finder.castView(view, 2131427416, "field 'title_download_manage'");
    view = finder.findRequiredView(source, 2131427403, "field 'mAnimationView'");
    target.mAnimationView = finder.castView(view, 2131427403, "field 'mAnimationView'");
  }

  @Override public void unbind(T target) {
    target.mContainer = null;
    target.mToolbarView = null;
    target.toolbar = null;
    target.view_toolbar = null;
    target.pageLoadLayout = null;
    target.mListView = null;
    target.rl_search = null;
    target.home_title_search = null;
    target.title_message = null;
    target.title_download_manage = null;
    target.mAnimationView = null;
  }
}
