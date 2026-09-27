package com.ga.food.service;

import com.ga.food.model.Category;
import com.ga.food.model.Recipe;
import com.ga.food.exception.InformationNotFoundException;
import com.ga.food.repository.CategoryRepository;
import com.ga.food.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Long categoryId, Recipe recipe){
        System.out.println("Service calling createRecipe ==>");
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InformationNotFoundException(
                                "Category with id " + categoryId + " not found"
                        ));
        recipe.setCategory(category);
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipes() {
        System.out.println("Service calling getRecipes ==>");
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipe(Long id) {
        System.out.println("Service calling getRecipe ==>");
        return recipeRepository.findById(id);
    }

    public List<Recipe> getRecipesByCategory(Long categoryId) {
        System.out.println("Service calling getRecipesByCategory ==>");
        return recipeRepository.findByCategoryId(categoryId);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        System.out.println("Service calling updateRecipe ==>");

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if (optionalRecipe.isPresent()) {

            Recipe recipe = optionalRecipe.get();

            recipe.setName(updatedRecipe.getName());
            recipe.setIngredients(updatedRecipe.getIngredients());
            recipe.setPortion(updatedRecipe.getPortion());
            recipe.setTime(updatedRecipe.getTime());
            recipe.setSteps(updatedRecipe.getSteps());
            recipe.setPublic(updatedRecipe.isPublic());

            return recipeRepository.save(recipe);
        }

        return null;
    }

    public void deleteRecipe(Long id) {
        System.out.println("Service calling deleteRecipe ==>");
        recipeRepository.deleteById(id);
    }

}
