package com.techelevator.dao;

import com.techelevator.RecipeRUs.dao.JdbcRecipeDao;
import com.techelevator.RecipeRUs.model.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JdbcRecipeDaoTest extends BaseDaoTests{
    private JdbcRecipeDao sut;
    Recipe testRecipe = new Recipe(1, "title", "Instructions", 4, "Ingredients");

    @Before
    public void setup(){
        sut = new JdbcRecipeDao(dataSource);
    }

    @Test
    public void getRecipeFromRecipeId_works(){
        Assert.assertEquals(1.0, sut.getRecipeFromRecipeId(1).getId(), .009);
    }

    @Test
    public void create_recipe_test() {
        Recipe recipe = sut.createRecipe(testRecipe);
        Assert.assertEquals("Flan",  recipe.getTitle());
    }

    @Test
    public void recipe_id_not_null() {
        // Recipe recipe = sut.createRecipe(testRecipe);
        Assert.assertEquals("", sut.createRecipe(null), 0.009);
    }

}
