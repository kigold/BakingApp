package com.example.kingslee.bakingapp.utilities;

import android.content.Context;

import com.example.kingslee.bakingapp.datatype.Ingredient;
import com.example.kingslee.bakingapp.datatype.Recipe;
import com.example.kingslee.bakingapp.datatype.RecipeList;
import com.example.kingslee.bakingapp.datatype.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KingsLee on 6/24/2017.
 */

public class RecipeParseUtils {
    public static ArrayList<Recipe> getRecipeFromHttpRequest(Context context, String raw_recipe)
            throws JSONException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        JSONArray recipeJson = new JSONArray(raw_recipe);

        if (recipeJson.length() > 0) {
            JSONArray recipeArray = recipeJson;
            for(int i=0; i < recipeArray.length(); i++) {
                JSONObject recipeObj = recipeArray.getJSONObject(i);
                Recipe recipe = new Recipe();
                recipe.setId(recipeObj.getInt("id"));
                recipe.setName(recipeObj.getString("name"));
                recipe.setImage(recipeObj.getString("image"));
                recipe.setServings(recipeObj.getString("servings"));

                //get ingredients
                ArrayList<Ingredient> temp_Ingredients= new ArrayList<>();
                JSONArray ingredientsJson = recipeObj.getJSONArray("ingredients");
                for(int j=0; j < ingredientsJson.length(); j++){
                    Ingredient temp_Ingredient = new Ingredient();
                    JSONObject temp = ingredientsJson.getJSONObject(j);
                    temp_Ingredient.setQuantity(temp.getString("quantity"));
                    temp_Ingredient.setMeasure(temp.getString("measure"));
                    temp_Ingredient.setIngredient(temp.getString("ingredient"));
                    temp_Ingredients.add(temp_Ingredient);
                }
                recipe.setIngredients(temp_Ingredients);
                //get steps
                ArrayList<Step> temp_Steps= new ArrayList<>();
                JSONArray stepsJson = recipeObj.getJSONArray("steps");
                for(int j=0; j < stepsJson.length(); j++){
                    Step temp_Step = new Step();
                    JSONObject temp = stepsJson.getJSONObject(j);
                    temp_Step.setId(temp.getInt("id"));
                    temp_Step.setShortDesc(temp.getString("shortDescription"));
                    temp_Step.setDesc(temp.getString("description"));
                    temp_Step.setVideoUrl(temp.getString("videoURL"));
                    temp_Step.setThumbnail(temp.getString("thumbnailURL"));
                    temp_Steps.add(temp_Step);
                }
                recipe.setSteps(temp_Steps);

                recipes.add(recipe);
            }
        }
        return recipes;
    }
    public static ArrayList<RecipeList> getRecipeListFromHttpRequest(Context context, String raw_recipe)
            throws JSONException {
        ArrayList<RecipeList> recipes = new ArrayList<>();
        JSONArray recipeJson = new JSONArray(raw_recipe);

        if (recipeJson.length() > 0) {
            JSONArray recipeArray = recipeJson;
            for (int i = 0; i < recipeArray.length(); i++) {
                JSONObject recipeObj = recipeArray.getJSONObject(i);
                RecipeList recipe = new RecipeList();
                recipe.setId(recipeObj.getInt("id"));
                recipe.setName(recipeObj.getString("name"));
                recipe.setImage(recipeObj.getString("image"));
                recipes.add(recipe);
            }
        }
        return recipes;
    }
}
