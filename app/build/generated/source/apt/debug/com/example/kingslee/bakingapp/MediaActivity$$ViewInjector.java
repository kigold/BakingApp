// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MediaActivity$$ViewInjector<T extends com.example.kingslee.bakingapp.MediaActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493012, "field 'mStepDesc'");
    target.mStepDesc = finder.castView(view, 2131493012, "field 'mStepDesc'");
    view = finder.findRequiredView(source, 2131493014, "field 'mNextBtn'");
    target.mNextBtn = finder.castView(view, 2131493014, "field 'mNextBtn'");
    view = finder.findRequiredView(source, 2131493013, "field 'mPrevBtn'");
    target.mPrevBtn = finder.castView(view, 2131493013, "field 'mPrevBtn'");
    view = finder.findRequiredView(source, 2131493011, "field 'mMedia'");
    target.mMedia = finder.castView(view, 2131493011, "field 'mMedia'");
  }

  @Override public void reset(T target) {
    target.mStepDesc = null;
    target.mNextBtn = null;
    target.mPrevBtn = null;
    target.mMedia = null;
  }
}
