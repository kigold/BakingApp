// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.example.kingslee.bakingapp.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493006, "field 'mRecyclerView'");
    target.mRecyclerView = finder.castView(view, 2131493006, "field 'mRecyclerView'");
    view = finder.findRequiredView(source, 2131493008, "field 'mErrorMessage'");
    target.mErrorMessage = finder.castView(view, 2131493008, "field 'mErrorMessage'");
    view = finder.findRequiredView(source, 2131493007, "field 'mProgressBar'");
    target.mProgressBar = finder.castView(view, 2131493007, "field 'mProgressBar'");
  }

  @Override public void reset(T target) {
    target.mRecyclerView = null;
    target.mErrorMessage = null;
    target.mProgressBar = null;
  }
}
