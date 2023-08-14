package com.techelevator.services;

import com.techelevator.model.Recipe;
import com.techelevator.model.UserCredentials;

import java.util.Map;
import java.util.Scanner;

public class ConsoleService {

    //Scanner

    private static final Scanner in = new Scanner(System.in);

    public void RecipeWelcome() {
        System.out.println("**********************");
        System.out.println("**********************");
        System.out.println("**** Welcome to ****");
        System.out.println("*** Recipes 'R Us ***");
        System.out.println("***********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }



    public UserCredentials promptForUserLogin() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View Recipes");
        System.out.println("2: Add Recipe");
        System.out.println("3: Delete Recipe");
        System.out.println("0: Exit");
        System.out.println();
    }



    public void printMessage(String message) {
        System.out.println(message);
    }
    public void pause() {
        System.out.println("\nPress Enter to Continue...");
        in.nextLine();
    }


    public String getKeyWordFromUser() {
        System.out.println();
        System.out.println("Enter Key word for recipe Generator >>>> ");
        return in.nextLine();
    }

    public String getRecipeFromUser() {
        System.out.println();
        System.out.println("Enter recipe name >>>");
        return "Recipe name ";
    }
    public void RecipeNotFound() {
        System.out.println("Recipe not found, Please try again");
    }

    public String getChoicesFromRecipeMenu() {
        System.out.println("S)how Recipes");
        System.out.println("Q)uit");
        return in.nextLine();
    }


    public void displayRecipes( Map<String, Recipe> inventory ) {

        for (Map.Entry<String, Recipe> entry : inventory.entrySet() ) {
            System.out.printf("%1s : %1s : %1.2f %n", entry.getKey(), entry.getValue().getId(), entry.getValue().getTitle(),
                    entry.getValue().getIngredients(), entry.getValue().getInstruction(),
                    entry.getValue().getServings());
        }

        System.out.println();
    }

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.println(prompt);
        try {
            menuSelection = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }

        return menuSelection;
    }

    public String promptForString(String prompt ){
        System.out.println(prompt);
        return in.nextLine();
    }

//    public int printRecipesFromApis(Recipe[] recipeFromApi) {
//        System.out.println("-------------------------------------");
//        System.out.println("Recipe");
//        System.out.printf("%-8s %-10s %-10s %-10s %-10s \n", "ID", "Title", "Ingredients", "Instructions", "Servings");
//        System.out.println("-------------------------------------");
//
//        for (Recipe recipe : recipeFromApi) {
//            System.out.printf("%-8s %-10s %-10s %-10s %-10s \n", recipe.getId(), recipe.getTitle(), recipe.getIngredients(),
//                    recipe.getInstruction(), recipe.getServings());
//        }
//
//        System.out.println("---------------");
//        while(true) {
//            String recipe = promptForString("Please enter key word for recipe");
//            for (Recipe recipes : recipeFromApi) {
//                if() {
//                    return
//                }
//            }
//
//        }
//
//
//    }

    public void printRecipeDetails(String title, String ingredients, String instructions, int servings) {
        System.out.println("-------------------------------------");
        System.out.println("Recipe Details");
        System.out.println("-------------------------------------");
        System.out.println("Title: " + title);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Instructions: " + instructions);
        System.out.println("Servings: " + servings);
    }





}

