package com.techelevator;

import com.techelevator.model.AuthenticatedUser;
import com.techelevator.model.UserCredentials;
import com.techelevator.services.AuthenticationService;
import com.techelevator.services.ConsoleService;
import com.techelevator.services.RecipeService;
// import org.springframework.jdbc.core.JdbcTemplate;

public class RecipeApp {

    private static final String API_BASE_URL = "http://localhost:8080/";

    public static final int REGISTER_USER = 1;
    public static final int LOGIN_USER = 2;
    public static final int EXIT_APP = 0;
    public static final int VIEW_RECIPE = 1;
    public static final int SEARCH_RECIPE = 3;
    public static final int ADD_RECIPE = 2;
    public static final int DELETE_RECIPE = 4;

    private ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private static final RecipeService recipeService = new RecipeService();

    private AuthenticatedUser currentUser;





    public static void main(String[] args) {
        RecipeApp app = new RecipeApp();
        app.run();
    }


    public void run() {
        consoleService.RecipeWelcome();
        mainMenu();
        //loginMenu();
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP ) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == REGISTER_USER) {
                accountRegistration();
            } else if (menuSelection == LOGIN_USER) {
                accountLogin();
            } else if (menuSelection != EXIT_APP) {
                consoleService.printMessage("Invalid Selection");
                consoleService.pause();
            }

        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != EXIT_APP) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please Choose an Option: ");
            if(menuSelection == 1) {
                recipeService.getRecipe(consoleService.getKeyWordFromUser());
                // have them have the ability to choose and add the recipe that they liked from the list

            } else if(menuSelection == 2) {
                //view current recipes

            } else if (menuSelection == 3){

                //add recipes

            } else if (menuSelection == 4) {
                consoleService.printErrorMessage("Invalid Selection");
                consoleService.pause();
                // exit out of loop
            }

        }
    }



    // Eventually add Authentication Service to the application and the login menu will go here


        private void accountRegistration() {
            consoleService.printMessage("Please register a new user account");
            UserCredentials credentials = consoleService.promptForUserLogin();
            if (authenticationService.register(credentials)) {
                consoleService.printMessage("Registration Successful. Please login below.");
            } else{
                consoleService.printErrorMessage("Registration Failure, Please try again.");
            }
        }

        private void accountLogin() {
            UserCredentials credentials = consoleService.promptForUserLogin();
            currentUser = authenticationService.login(credentials);
            if (currentUser == null) {
                consoleService.printErrorMessage("Login Failure Please try Again.");
            }
        }

    // Main menu view here


    // showRecipe() here
    // addRecipe() here
    //deleteRecipe() here
    // Search Up recipes
    private void searchRecipe() {




    }
    



}
