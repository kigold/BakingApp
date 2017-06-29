package com.example.kingslee.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kingslee.bakingapp.adapter.RecipeAdapter;
import com.example.kingslee.bakingapp.datatype.Recipe;
import com.example.kingslee.bakingapp.utilities.NetworkUtils;
import com.example.kingslee.bakingapp.utilities.RecipeParseUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MainActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler
                                    ,LoaderManager.LoaderCallbacks<ArrayList<Recipe>>{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    @InjectView(R.id.recipe_list)
    RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    @InjectView(R.id.tv_err_msg)
    TextView mErrorMessage;
    @InjectView(R.id.pb_progress_bar)
    ProgressBar mProgressBar;
    private ArrayList<Recipe> mRecipe;
    private final String RECIPE_DATA_FOR_INTENT = "RECIPE_DATA";
    private final String BUNDLE_RECYCLER_LAYOUT = "bundle_recycle_layout";
    private final String BUNDLE_RECYCLER_RECIPEDATA = "bundle_recipe_data";
    private final String BUNDLE_GRID_SIZE = "bundle_grid_size";
    private static final int RECIPE_LOADER_ID = 0;
    private final int SMALL_SCREEN_ITEMS_IN_GRIDVIEW = 1;
    private final int LARGE_SCREEN_ITEMS_IN_GRIDVIEW = 3;
    private int mGrid_size = SMALL_SCREEN_ITEMS_IN_GRIDVIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);



        //mRecyclerView = (RecyclerView) findViewById(R.id.recipe_list);
        assert mRecyclerView != null;
        //mErrorMessage = (TextView) findViewById(R.id.tv_err_msg);
        mRecipeAdapter = new RecipeAdapter(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, mGrid_size);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecipeAdapter);
        //mProgressBar = (ProgressBar) findViewById(R.id.pb_progress_bar);

        //View recyclerView = findViewById(R.id.item_list);
        //assert recyclerView != null;
        //setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.recipe_details_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
            mGrid_size = LARGE_SCREEN_ITEMS_IN_GRIDVIEW;
            resizeGrid(mGrid_size);
        }

        if (savedInstanceState != null) {
            Parcelable savedRecyclerViewState = savedInstanceState.getParcelable(
                    BUNDLE_RECYCLER_LAYOUT);
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerViewState);
            mRecipe = savedInstanceState.getParcelableArrayList(BUNDLE_RECYCLER_RECIPEDATA);
            mGrid_size = savedInstanceState.getInt(BUNDLE_GRID_SIZE, SMALL_SCREEN_ITEMS_IN_GRIDVIEW);
            loadRecipeData();
        }
        else
        {
            //load movies
            loadRecipeData();
        }

        int loaderId = RECIPE_LOADER_ID;
        Bundle bundleForLoader = new Bundle();
        LoaderManager.LoaderCallbacks<ArrayList<Recipe>> callback = MainActivity.this;

        getSupportLoaderManager().initLoader(loaderId, bundleForLoader, callback);

    }

    @Override
    public Loader<ArrayList<Recipe>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<ArrayList<Recipe>>(this) {
            ArrayList<Recipe> mRecipeData = null;

            @Override
            protected void onStartLoading() {
                if (mRecipeData != null){
                    deliverResult(mRecipeData);
                }else{
                    mProgressBar.setVisibility(View.VISIBLE);
                    forceLoad();
                }
            }

            @Override
            public ArrayList<Recipe> loadInBackground() {
                URL recipeRequestUrl = NetworkUtils.buildUrl();
                try {
                    String jsonRecipeResponse = NetworkUtils.getResponseFromHttpUrl(recipeRequestUrl);
                    ArrayList<Recipe> recipe = RecipeParseUtils
                            .getRecipeFromHttpRequest(MainActivity.this, jsonRecipeResponse);
                    return recipe;
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(ArrayList<Recipe> data) {
                mRecipe = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Recipe>> loader, ArrayList<Recipe> data) {
        mProgressBar.setVisibility((View.INVISIBLE));
        if (data == null){
            showErro();
        }else {
            mRecipeAdapter.setData(mRecipe);
            showRecipeDataView();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Recipe>> loader) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mRecyclerView.getLayoutManager().onSaveInstanceState());
        outState.putParcelableArrayList(BUNDLE_RECYCLER_RECIPEDATA, mRecipe);
        outState.putInt(BUNDLE_GRID_SIZE, mGrid_size);
    }

    private void resizeGrid(int size) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, size);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    private void loadRecipeData(){
        showRecipeDataView();
        Bundle bundle = new Bundle();
        //fresh load
        if (mRecipe == null){
            getSupportLoaderManager().initLoader(RECIPE_LOADER_ID, bundle, this).forceLoad();
        }
        else{
            //from savedStateInstance
            getSupportLoaderManager().restartLoader(RECIPE_LOADER_ID, bundle, this).forceLoad();
        }
    }

    @Override
    public void onClick(Recipe recipe) {
        Context context = this;
        Intent i = new Intent();
        i.setClass(this, ItemDetailActivity.class);
        //i.putExtra(MOVIE_DATA_FOR_INTENT, movie.getAsJsonData());
        i.putExtra(RECIPE_DATA_FOR_INTENT, recipe);
        startActivity(i);
        Toast.makeText(context, recipe.getName(), Toast.LENGTH_SHORT)
                .show();
    }

    private void showRecipeDataView(){
        // hide error message view
        mErrorMessage.setVisibility(View.INVISIBLE);
        // show movie grid list
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showErro(){
        // show error message view
        mErrorMessage.setVisibility(View.VISIBLE);
        // hide movie grid list
        mRecyclerView.setVisibility(View.INVISIBLE);
    }
     /*
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        ItemDetailFragment fragment = new ItemDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ItemDetailActivity.class);
                        intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }*/

}

