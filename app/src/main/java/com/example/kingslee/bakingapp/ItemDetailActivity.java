package com.example.kingslee.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.kingslee.bakingapp.adapter.IngredientAdapter;
import com.example.kingslee.bakingapp.datatype.Recipe;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemDetailActivity extends AppCompatActivity {

    IngredientAdapter mIngredientAdapter;
    @InjectView(R.id.rv_ingredient_list)
    RecyclerView mIngredientList;
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

        //mRecipeName = (TextView) findViewById(R.id.tv_recipe_name);


        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(RECIPE_DATA_FOR_INTENT)) {
                mRecipe = intentOrigin.getParcelableExtra(RECIPE_DATA_FOR_INTENT);
                this.setTitle(mRecipe.getName());
                /*
                mMeasure.setText(mRecipe.getIngredients().get(0).getMeasure());
                mQuantity.setText(mRecipe.getIngredients().get(0).getQuantity());
                mIngredient.setText(mRecipe.getIngredients().get(0).getIngredient());*/
                int recyclerViewOrientation = LinearLayoutManager.VERTICAL;
                boolean shouldReverseLayout = false;
                LinearLayoutManager layoutManagerIngredient
                        = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
                mIngredientAdapter = new IngredientAdapter();
                mIngredientList.setAdapter(mIngredientAdapter);
                mIngredientList.setLayoutManager(layoutManagerIngredient);
                mIngredientList.setHasFixedSize(true);
                mIngredientAdapter.setData(mRecipe.getIngredients());

                /*LinearLayoutManager layoutManagerTrailer
                        = new LinearLayoutManager(this, recyclerViewOrientation, shouldReverseLayout);
                mTrailerAdapter = new TrailerAdapter(this);
                mTrailerList.setLayoutManager(layoutManagerTrailer);
                mTrailerList.setHasFixedSize(true);*/

            }
        }

    }
}
