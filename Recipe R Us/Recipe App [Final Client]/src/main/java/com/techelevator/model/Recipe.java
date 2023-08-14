package com.techelevator.model;

public class Recipe {

    private int id;
    private String title ;
    private double preparation_time;
    private String instruction;
    private int servings;
    private String ingredients;
    public Recipe(){}

    public Recipe(int id, String title, String instruction, int servings, String ingredients) {
        this.id = id;
        this.title = title;
        this.instruction = instruction;
        this.servings = servings;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
