package com.example.kingslee.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kingslee.bakingapp.R;
import com.example.kingslee.bakingapp.datatype.Ingredient;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KingsLee on 6/29/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder>{

    private ArrayList<Ingredient> mIngredients;

    public class IngredientAdapterViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_ingredient)
        public TextView mIngredient;
        @InjectView(R.id.tv_measure)
        public TextView mMeasure;
        @InjectView(R.id.tv_quantity)
        public TextView mQuantity;

        public IngredientAdapterViewHolder(View view){
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    @Override
    public IngredientAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttactToParentImmediately = false;

        View view = inflater.inflate(R.layout.ingredient_item, parent, shouldAttactToParentImmediately);
        return new IngredientAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapterViewHolder holder, int position) {
        Ingredient daIngredient = mIngredients.get(position);
        holder.mIngredient.setText(daIngredient.getIngredient());
        holder.mMeasure.setText(daIngredient.getMeasure());
        holder.mQuantity.setText(daIngredient.getQuantity());
    }

    @Override
    public int getItemCount() {
        if (null != mIngredients) {
            return mIngredients.size();
        }
        return 0;
    }

    public void setData(ArrayList<Ingredient> IngredientData){
        mIngredients = IngredientData;
        notifyDataSetChanged();
    }
}