package com.ga.food.repository;

import com.ga.food.Model.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName (String categoryName);
    Category findByNameAndDescription (String categoryName, String categoryDescription);

}
