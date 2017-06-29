// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ItemDetailActivity$$ViewInjector<T extends com.example.kingslee.bakingapp.ItemDetailActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427425, "field 'mIngredientList'");
    target.mIngredientList = finder.castView(view, 2131427425, "field 'mIngredientList'");
    view = finder.findRequiredView(source, 2131427427, "field 'mStepList'");
    target.mStepList = finder.castView(view, 2131427427, "field 'mStepList'");
    view = finder.findRequiredView(source, 2131427423, "field 'mBanner'");
    target.mBanner = finder.castView(view, 2131427423, "field 'mBanner'");
  }

  @Override public void reset(T target) {
    target.mIngredientList = null;
    target.mStepList = null;
    target.mBanner = null;
  }
}
