package com.ga.food.controller;

import com.ga.food.model.Recipe;
import com.ga.food.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;

    @PostMapping("categories/{categoryId}/recipes")
    public Recipe createRecipe(
            @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody Recipe recipeObject
    ){
        System.out.println("calling createRecipe from controller");
        return recipeService.createRecipe(categoryId,recipeObject);
    }

    // GET ALL RECIPES
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        System.out.println("Calling getRecipes from controller");
        return recipeService.getRecipes();
    }

    // GET RECIPE BY ID
    @GetMapping("/recipes/{id}")
    public Optional<Recipe> getRecipe(@PathVariable Long id) {
        System.out.println("Calling getRecipe from controller");
        return recipeService.getRecipe(id);
    }

    // GET RECIPES BY CATEGORY
    @GetMapping("/categories/{categoryId}/recipes")
    public List<Recipe> getRecipesByCategory(
            @PathVariable Long categoryId
    ) {
        System.out.println("Calling getRecipesByCategory from controller");
        return recipeService.getRecipesByCategory(categoryId);
    }

    // UPDATE RECIPE
    @PutMapping("/recipes/{id}")
    public Recipe updateRecipe(
            @PathVariable Long id,
            @RequestBody Recipe updatedRecipe
    ) {
        System.out.println("Calling updateRecipe from controller");
        return recipeService.updateRecipe(id, updatedRecipe);
    }

    // DELETE RECIPE
    @DeleteMapping("/recipes/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        System.out.println("Calling deleteRecipe from controller");
        recipeService.deleteRecipe(id);
    }
}
