package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByName(String categoryName);
}