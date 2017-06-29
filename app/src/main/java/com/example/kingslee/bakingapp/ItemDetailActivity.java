package com.example.kingslee.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

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


        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(RECIPE_DATA_FOR_INTENT)) {
                mRecipe = intentOrigin.getParcelableExtra(RECIPE_DATA_FOR_INTENT);
                //this.setTitle(mRecipe.getName());
                this.setTitle(foodImageUrl);
                /*
                mMeasure.setText(mRecipe.getIngredients().get(0).getMeasure());
                mQuantity.setText(mRecipe.getIngredients().get(0).getQuantity());
                mIngredient.setText(mRecipe.getIngredients().get(0).getIngredient());*/
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
    public void onClick(Step step) {

    }
}
