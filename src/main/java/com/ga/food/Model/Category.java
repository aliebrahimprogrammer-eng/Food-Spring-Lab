package com.ga.food.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//@AllArgsConstructor
//@ToString
//@NoArgsConstructor
//@Setter
//@Getter
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Column
    private String imageUrl;
    //@Lob
    @Column
    private byte[] image;

    // Runs automatically BEFORE a new category is saved
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Runs automatically before an existing category is updated
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    private List<Recipe> recipeList;





}
