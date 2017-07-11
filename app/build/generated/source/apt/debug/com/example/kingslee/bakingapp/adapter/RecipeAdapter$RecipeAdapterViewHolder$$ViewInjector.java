// Generated code from Butter Knife. Do not modify!
package com.example.kingslee.bakingapp.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class RecipeAdapter$RecipeAdapterViewHolder$$ViewInjector<T extends com.example.kingslee.bakingapp.adapter.RecipeAdapter.RecipeAdapterViewHolder> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493049, "field 'mRecipeThumbnail'");
    target.mRecipeThumbnail = finder.castView(view, 2131493049, "field 'mRecipeThumbnail'");
    view = finder.findRequiredView(source, 2131493050, "field 'mRecipeName'");
    target.mRecipeName = finder.castView(view, 2131493050, "field 'mRecipeName'");
  }

  @Override public void reset(T target) {
    target.mRecipeThumbnail = null;
    target.mRecipeName = null;
  }
}
