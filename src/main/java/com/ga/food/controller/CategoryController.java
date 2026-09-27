package com.ga.food.controller;

import com.ga.food.model.Category;
import com.ga.food.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class CategoryController {
    //CRUD

    @Autowired
    private CategoryService categoryService;

    /*@Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }*/

    //C - Create - HTTP POST - to create a record (category)
    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject){
        System.out.println("calling create category");
        return categoryService.createCategory(categoryObject);
    }

    //R - Read - HTTP GET - to read all records/record by id
    @GetMapping("/categories")
    public List<Category> getCategories(){
        System.out.println("Calling getCategories");
        return categoryService.getCategories();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> getCategory(@PathVariable long id){
        System.out.println("Calling getCategories");
        return categoryService.getCategory(id);
    }

    //U - HTTP PUT - to update a record
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable long id, @RequestBody Category updatedCategory) {
        System.out.println("Calling updateCategory");
        return categoryService.updateCategory(id, updatedCategory);
    }


    //D - HTTP DELETE - to delete a record
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable long id) {
        System.out.println("Calling deleteCategory");
        categoryService.deleteCategory(id);
    }

    // UPLOAD CATEGORY IMAGE
    @PostMapping("/categories/{id}/image")
    public Category uploadCategoryImage(
            @PathVariable long id,
            @RequestParam("image") MultipartFile image) {

        System.out.println("Calling uploadCategoryImage");

        return categoryService.uploadCategoryImage(id, image);
    }

    // UPLOAD CATEGORY IMAGE
    @PostMapping("/categories/bytes/{id}/image")
    public Category uploadCategoryImageAsBytes(
            @PathVariable long id,
            @RequestParam("image") MultipartFile image) {

        return categoryService.uploadCategoryImageAsBytes(id, image);
    }
}
