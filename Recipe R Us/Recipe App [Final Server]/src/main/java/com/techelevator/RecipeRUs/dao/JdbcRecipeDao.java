package com.techelevator.RecipeRUs.dao;

import com.techelevator.RecipeRUs.model.Recipe;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;

public class JdbcRecipeDao implements RecipeDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Recipe getRecipeFromRecipeId(int recipeId) {
        Recipe recipe = new Recipe();
        String sql = "SELECT title, instruction, servings, ingredients FROM recipe WHERE recipe_id = ?\n";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, recipeId);
        while(result.next()) {
            recipe = mapRowToRecipe(result);
        }
        return recipe;
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        Recipe newRecipe = new Recipe();
        String sql = "INSERT INTO recipe (title, instruction, servings, ingredients) VAlUES (?,?,?,?) RETURNING recipe_id";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, Integer.class, recipe);
        while(result.next()) {
            newRecipe = mapRowToRecipe(result);
        }
        return newRecipe;
    }

    private Recipe mapRowToRecipe(SqlRowSet row) {
        Recipe recipe = new Recipe();
        recipe.setId(row.getInt("recipe_id"));
        recipe.setTitle(row.getString("title"));
        recipe.setInstruction(row.getString("instruction"));
        recipe.setIngredients(row.getString("ingredients"));
        recipe.setIngredients(row.getString("servings"));

        return recipe;
    }
}
