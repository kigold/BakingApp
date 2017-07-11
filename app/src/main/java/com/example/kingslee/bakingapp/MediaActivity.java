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

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
//import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MediaActivity extends AppCompatActivity {

    /*@InjectView(R.id.tv_err_msg)
    TextView mErrorMessage;
    @InjectView(R.id.pb_progress_bar)
    ProgressBar mProgressBar;*/
    @InjectView(R.id.tv_step_desc)
    TextView mStepDesc;
    @InjectView(R.id.bt_next)
    Button mNextBtn;
    @InjectView(R.id.bt_prev)
    Button mPrevBtn;
    @InjectView(R.id.player_view)
    FrameLayout mMedia;
    private final String RECIPE_DATA_FOR_INTENT = "RECIPE_DATA";
    private final String STEP_DATA_FOR_INTENT = "STEP_DATA";
    private final String BUNDLE_RECYCLER_LAYOUT = "RECYCLE_LAYOUT_BUNDLE";
    private final String BUNDLE_RECIPE = "RECIPE_BUNDLE";
    private final String BUNDLE_STEP = "STEP_BUNDLE";
    private int stepIndex = -1;
    private boolean enablePrev = false;
    private boolean enableNext = true;
    Step mStep;
    Recipe mRecipe;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private ExoPlayer.EventListener exoPlayerEventListener;

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
            stepIndex = mStep.getId();
            toggleButton();
            mNextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Step NextStep = null;
                    if (stepIndex == -1) {stepIndex = mStep.getId(); }
                    if ((stepIndex + 1) < mRecipe.getSteps().size()){
                        //increament step
                        stepIndex =  stepIndex + 1;
                        //set control and togglebutton
                        toggleButton();
                        NextStep = mRecipe.getSteps().get(stepIndex);
                        Toast.makeText(getApplicationContext(), NextStep.getShortDesc(), Toast.LENGTH_SHORT)
                                .show();
                        //change the text view to show the next item
                        mStepDesc.setText(NextStep.getDesc());
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "This is the Last Step", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
            mPrevBtn.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Step PrevStep = null;
                    if (stepIndex == -1) {stepIndex = mStep.getId(); }
                    if ((stepIndex - 1) >= 0){
                        //decreases step
                        stepIndex =  stepIndex - 1;
                        //set control and toggle button
                        toggleButton();
                        PrevStep = mRecipe.getSteps().get(stepIndex);
                        Toast.makeText(getApplicationContext(), PrevStep.getShortDesc(), Toast.LENGTH_SHORT)
                                .show();
                        //change the text view to show the next item
                        mStepDesc.setText(PrevStep.getDesc());
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "This is the First Step", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }));

        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_RECIPE, mRecipe);
        outState.putParcelable(BUNDLE_STEP, mStep);
    }

    private void toggleButton(){
        //set control
        if((stepIndex - 1) >= 0){ enablePrev = true;}else{enablePrev = false;}
        if ((stepIndex + 1) < mRecipe.getSteps().size()){ enableNext = true;}else{enableNext = false;}
        //hide or show button
        mNextBtn.setEnabled(enableNext);
        mPrevBtn.setEnabled(enablePrev);
    }
}
