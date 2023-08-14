package com.techelevator.services;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.techelevator.model.Recipe;


public class RecipeService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_BASE_URL = "https://recipe-by-api-ninjas.p.rapidapi.com/v1/recipe?";


    public Recipe getRecipe(String value) {
        String url = API_BASE_URL + "query=" + value;
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<Void>(httpHeaders);

        ResponseEntity<Recipe> response = restTemplate.exchange(url, HttpMethod.GET, request, Recipe.class);
        Recipe recipeReturnedFromApi = response.getBody();
        return recipeReturnedFromApi;
    }



}
