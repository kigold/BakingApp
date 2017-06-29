package com.example.kingslee.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingslee.bakingapp.adapter.RecipeAdapter;
import com.example.kingslee.bakingapp.datatype.Recipe;
import com.example.kingslee.bakingapp.datatype.Step;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MediaActivity extends AppCompatActivity {

    /*@InjectView(R.id.tv_err_msg)
    TextView mErrorMessage;
    @InjectView(R.id.pb_progress_bar)
    ProgressBar mProgressBar;*/
    @InjectView(R.id.tv_step_desc)
    TextView mStepDesc;
    @InjectView(R.id.bt_next)
    Button mNextBtn;
    @InjectView(R.id.fl_media)
    FrameLayout mMedia;
    private final String RECIPE_DATA_FOR_INTENT = "RECIPE_DATA";
    private final String STEP_DATA_FOR_INTENT = "STEP_DATA";
    private final String BUNDLE_RECYCLER_LAYOUT = "RECYCLE_LAYOUT_BUNDLE";
    private final String BUNDLE_RECIPE = "RECIPE_BUNDLE";
    private final String BUNDLE_STEP = "STEP_BUNDLE";
    Step mStep;
    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.inject(this);

        Intent intentOrigin = getIntent();

        if (intentOrigin != null) {
            if (intentOrigin.hasExtra(RECIPE_DATA_FOR_INTENT)) {
                mRecipe = intentOrigin.getParcelableExtra(RECIPE_DATA_FOR_INTENT);
            }
            if (intentOrigin.hasExtra(STEP_DATA_FOR_INTENT)) {
                mStep = intentOrigin.getParcelableExtra(STEP_DATA_FOR_INTENT);
            }

            this.setTitle(mRecipe.getName());
            mStepDesc.setText(mStep.getDesc());
            mNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent();
                    i.setClass(MediaActivity.this, MediaActivity.class);
                    i.putExtra(RECIPE_DATA_FOR_INTENT, mRecipe);
                    Step NextStep = null;
                    if (mStep.getId() + 1 < mRecipe.getSteps().size()){
                        NextStep = mRecipe.getSteps().get(mStep.getId() + 1);
                        Toast.makeText(getApplicationContext(), NextStep.getShortDesc(), Toast.LENGTH_SHORT)
                                .show();
                        i.putExtra(STEP_DATA_FOR_INTENT, NextStep);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "This is the Last Step", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_RECIPE, mRecipe);
        outState.putParcelable(BUNDLE_STEP, mStep);
    }

}
