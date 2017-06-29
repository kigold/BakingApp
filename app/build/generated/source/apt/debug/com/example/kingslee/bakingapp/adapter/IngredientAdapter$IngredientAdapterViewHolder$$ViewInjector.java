// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class IngredientAdapter$IngredientAdapterViewHolder$$ViewInjector<T extends com.example.kingslee.bakingapp.adapter.IngredientAdapter.IngredientAdapterViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427432, "field 'mIngredient'");
    target.mIngredient = finder.castView(view, 2131427432, "field 'mIngredient'");
    view = finder.findRequiredView(source, 2131427434, "field 'mMeasure'");
    target.mMeasure = finder.castView(view, 2131427434, "field 'mMeasure'");
    view = finder.findRequiredView(source, 2131427433, "field 'mQuantity'");
    target.mQuantity = finder.castView(view, 2131427433, "field 'mQuantity'");
  }

  @Override public void reset(T target) {
    target.mIngredient = null;
    target.mMeasure = null;
    target.mQuantity = null;
  }
}
