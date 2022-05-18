package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByName(String categoryName);
    List<Category> findAll();
}