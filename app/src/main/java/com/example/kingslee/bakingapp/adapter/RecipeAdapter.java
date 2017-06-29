package com.example.kingslee.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kingslee.bakingapp.R;
import com.example.kingslee.bakingapp.datatype.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KingsLee on 6/23/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{

    private ArrayList<Recipe> mRecipe;
    private final RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler{
        void onClick(Recipe recipe);
    }

    public RecipeAdapter(RecipeAdapterOnClickHandler clickHander){
        mClickHandler = clickHander;
    }

    public class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.iv_recipe_thumbnail)
        public ImageView mRecipeThumbnail;
        @InjectView(R.id.tv_recipe_name)
        public TextView mRecipeName;

        public RecipeAdapterViewHolder(View view){
            super(view);
            ButterKnife.inject(this, view);
            //mRecipeThumbnail = (ImageView) view.findViewById(R.id.iv_recipe_thumbnail);
            //mRecipeName = (TextView) view.findViewById(R.id.tv_recipe_name);
            //mRecipeIngredients = (TextView) view.findViewById(R.id.tv_recipe_ingredients);
            //mRecipeSteps = (TextView) view.findViewById(R.id.tv_recipe_steps);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Recipe recipe = mRecipe.get(getAdapterPosition());
            mClickHandler.onClick(recipe);
        }
    }

    @Override
    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttactToParentImmediately = false;

        View view = inflater.inflate(R.layout.recipe_list_item, parent, shouldAttactToParentImmediately);
        return new RecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
        Recipe daRecipe = mRecipe.get(position);
        Context context = holder.itemView.getContext();
        holder.mRecipeName.setText(daRecipe.getName());
        /*
        //Ingredient temp = new Ingredient();
        String temp = "";
        //implement list for ingridient instead of text view
        for ( Ingredient t: daRecipe.getIngredients()) {
            temp += "Quantity: " + t.getQuantity() +
                    " Measure: " + t.getMeasure() +
                    " Ingredient: " + t.getIngredient() + "/n";
        }
        holder.mRecipeIngredients.setText(temp);
        temp = "";
        //implement list for step instead of text view
        for ( Step t: daRecipe.getSteps()) {
            temp += "Id: " + t.getId()+
                    " Short Description: " + t.getShortDesc() +
                    " Description: " + t.getDesc() + "/n";
        }
        holder.mRecipeSteps.setText(temp);
        holder.mRecipeIngredients.setText("test");*/
        Picasso.with(context)
                .load(daRecipe.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.mRecipeThumbnail);
    }

    @Override
    public int getItemCount() {
        if (null != mRecipe) {
            return mRecipe.size();
        }
        return 0;
    }

    public void setData(ArrayList<Recipe> recipeData){
        mRecipe = recipeData;
        notifyDataSetChanged();
    }
}
