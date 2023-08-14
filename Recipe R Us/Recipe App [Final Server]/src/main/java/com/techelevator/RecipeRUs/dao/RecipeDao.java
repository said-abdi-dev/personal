package com.techelevator.RecipeRUs.dao;

import com.techelevator.RecipeRUs.model.Recipe;

public interface RecipeDao {

    Recipe getRecipeFromRecipeId(int recipeId);

    Recipe createRecipe(Recipe recipe);


}
