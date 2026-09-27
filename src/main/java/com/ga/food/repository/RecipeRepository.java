package com.ga.food.repository;

import com.ga.food.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByCategoryId(Long recipeId);

    Recipe findByNameAndUserIdAndIdIsNot(String recipeName, Long userId, Long recipeId);

    Recipe findByNameAndUserId(String recipeName, Long userId);
}