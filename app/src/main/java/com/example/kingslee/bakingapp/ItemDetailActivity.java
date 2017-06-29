package com.example.kingslee.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kingslee.bakingapp.datatype.Recipe;

public class ItemDetailActivity extends AppCompatActivity {

    TextView mRecipeName;
    Recipe mRecipe;
    private final String RECIPE_DATA_FOR_INTENT = "RECIPE_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        mRecipeName = (TextView) findViewById(R.id.tv_recipe_name);


        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(RECIPE_DATA_FOR_INTENT)) {
                mRecipe = intentOrigin.getParcelableExtra(RECIPE_DATA_FOR_INTENT);

                mRecipeName.setText(mRecipe.getName());

            }
        }

    }
}
