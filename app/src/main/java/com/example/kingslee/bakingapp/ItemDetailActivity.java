package com.example.kingslee.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingslee.bakingapp.adapter.IngredientAdapter;
import com.example.kingslee.bakingapp.adapter.StepAdapter;
import com.example.kingslee.bakingapp.datatype.Recipe;
import com.example.kingslee.bakingapp.datatype.Step;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemDetailActivity extends AppCompatActivity implements StepAdapter.StepAdapterOnClickHandler {

    IngredientAdapter mIngredientAdapter;
    StepAdapter mStepAdapter;
    @InjectView(R.id.rv_ingredient_list)
    RecyclerView mIngredientList;
    @InjectView(R.id.rv_step_list)
    RecyclerView mStepList;
    @InjectView(R.id.iv_banner)
    public ImageView mBanner;
    private String foodImageUrl = "https://pixabay.com/en/food-grilled-chicken-spicy-1631727";
    /*
    @InjectView(R.id.tv_quantity)
    TextView mQuantity;
    @InjectView(R.id.tv_ingredient)
    TextView mIngredient;
    @InjectView(R.id.tv_measure)
    TextView mMeasure;*/
    Recipe mRecipe;
    private final String RECIPE_DATA_FOR_INTENT = "RECIPE_DATA";
    private final String STEP_DATA_FOR_INTENT = "STEP_DATA";
    private final String BUNDLE_RECYCLE_INGREDIENT = "BUNDLE_RECYCLE_INGREDIENT";
    private final String BUNDLE_RECYCLE_STEP = "BUNDLE_RECYCLE_STEP";
    private final String BUNDLE_RECIPE = "BUNDLE_RECIPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.inject(this);

        Picasso.with(getApplicationContext())
                .load(foodImageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mBanner);

        //Load UI from savedInstance
        if (savedInstanceState != null) {
            Parcelable savedRecycleViewIngredient = savedInstanceState.getParcelable(
                    BUNDLE_RECYCLE_INGREDIENT);
            Parcelable savedRecycleViewStep = savedInstanceState.getParcelable(
                    BUNDLE_RECYCLE_STEP);
            mIngredientList.getLayoutManager().onRestoreInstanceState(savedRecycleViewIngredient);
            mStepList.getLayoutManager().onRestoreInstanceState(savedRecycleViewStep);
            mRecipe= savedInstanceState.getParcelable(BUNDLE_RECIPE);
            this.setTitle(mRecipe.getName());

            //reInit Recycle Views
            int recyclerViewOrientation = LinearLayoutManager.VERTICAL;
            boolean shouldReverseLayout = false;
            LinearLayoutManager layoutManagerIngredient
                    = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
            mIngredientList.setLayoutManager(layoutManagerIngredient);
            mIngredientList.setHasFixedSize(true);
            mIngredientAdapter = new IngredientAdapter();
            mIngredientAdapter.setData(mRecipe.getIngredients());
            mIngredientList.setAdapter(mIngredientAdapter);

            LinearLayoutManager layoutManagerStep
                    = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
            mStepList.setLayoutManager(layoutManagerStep);
            mStepList.setHasFixedSize(true);
            mStepAdapter = new StepAdapter(this);
            mStepAdapter.setData(mRecipe.getSteps());
            mStepList.setAdapter(mStepAdapter);
        }
        else{
            //load data from json

        }

        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(RECIPE_DATA_FOR_INTENT)) {
                mRecipe = intentOrigin.getParcelableExtra(RECIPE_DATA_FOR_INTENT);
                this.setTitle(mRecipe.getName());

                int recyclerViewOrientation = LinearLayoutManager.VERTICAL;
                boolean shouldReverseLayout = false;
                LinearLayoutManager layoutManagerIngredient
                        = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
                mIngredientList.setLayoutManager(layoutManagerIngredient);
                mIngredientList.setHasFixedSize(true);
                mIngredientAdapter = new IngredientAdapter();
                mIngredientAdapter.setData(mRecipe.getIngredients());
                mIngredientList.setAdapter(mIngredientAdapter);

                LinearLayoutManager layoutManagerStep
                        = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
                mStepList.setLayoutManager(layoutManagerStep);
                mStepList.setHasFixedSize(true);
                mStepAdapter = new StepAdapter(this);
                mStepAdapter.setData(mRecipe.getSteps());
                mStepList.setAdapter(mStepAdapter);

            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_RECYCLE_INGREDIENT, mIngredientList.getLayoutManager().onSaveInstanceState());
        outState.putParcelable(BUNDLE_RECYCLE_STEP, mStepList.getLayoutManager().onSaveInstanceState());
        outState.putParcelable(BUNDLE_RECIPE, mRecipe);
    }

    @Override
    public void onClick(Step step) {
        Context context = this;
        Intent i = new Intent();
        i.setClass(this, MediaActivity.class);
        i.putExtra(RECIPE_DATA_FOR_INTENT, mRecipe);
        i.putExtra(STEP_DATA_FOR_INTENT, step);
        startActivity(i);
        Toast.makeText(context, step.getShortDesc(), Toast.LENGTH_SHORT)
                .show();
    }
}
