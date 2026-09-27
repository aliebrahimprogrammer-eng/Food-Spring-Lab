package com.ga.food.service;

import com.ga.food.Model.Category;
import com.ga.food.exception.InformationExistException;
import com.ga.food.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /*@Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }*/

    public Category createCategory(Category categoryObject){
        System.out.println("Service: calling create category");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category!=null){
            throw new InformationExistException("Category with name " + category.getName() + " already exists");
        }else{
            return categoryRepository.save(categoryObject);
        }
    }

    public List<Category> getCategories() {
        System.out.println("Service calling getCategories");
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(long id) {
        System.out.println("Service calling getCategory");
        return categoryRepository.findById(id);
    }


    public Category updateCategory(long id, Category updatedCategory) {
        System.out.println("Service calling updateCategory");

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());

            return categoryRepository.save(category);
        }

        return null;
    }

    public void deleteCategory(long id) {
        System.out.println("Service calling deleteCategory");

        categoryRepository.deleteById(id);
    }

    public Category uploadCategoryImage(long id, MultipartFile image) {

        System.out.println("Service: uploading category image");

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            return null;
        }

        Category category = optionalCategory.get();

        try {

            // Create uploads/categories directory
            Path uploadPath = Paths.get("uploads/categories");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get original file extension
            String originalFilename = image.getOriginalFilename();

            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(
                        originalFilename.lastIndexOf(".")
                );
            }

            // Generate unique filename
            String filename = UUID.randomUUID() + extension;

            // Save image
            Path filePath = uploadPath.resolve(filename);

            Files.copy(image.getInputStream(), filePath);

            // Save URL in database
            String imageUrl = "/uploads/categories/" + filename;

            category.setImageUrl(imageUrl);

            return categoryRepository.save(category);

        } catch (IOException e) {

            throw new RuntimeException("Could not save image", e);
        }
    }

    public Category uploadCategoryImageAsBytes(long id, MultipartFile image) {

        Optional<Category> optionalCategory =
                categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            return null;
        }

        Category category = optionalCategory.get();

        try {
            category.setImage(image.getBytes());

            return categoryRepository.save(category);

        } catch (IOException e) {
            throw new RuntimeException("Could not save image", e);
        }
    }
}
