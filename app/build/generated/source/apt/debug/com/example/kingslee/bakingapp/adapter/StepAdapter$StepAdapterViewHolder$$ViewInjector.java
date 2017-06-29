// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class StepAdapter$StepAdapterViewHolder$$ViewInjector<T extends com.example.kingslee.bakingapp.adapter.StepAdapter.StepAdapterViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427461, "field 'mStepDesc'");
    target.mStepDesc = finder.castView(view, 2131427461, "field 'mStepDesc'");
    view = finder.findRequiredView(source, 2131427460, "field 'mStepThumbnail'");
    target.mStepThumbnail = finder.castView(view, 2131427460, "field 'mStepThumbnail'");
  }

  @Override public void reset(T target) {
    target.mStepDesc = null;
    target.mStepThumbnail = null;
  }
}
