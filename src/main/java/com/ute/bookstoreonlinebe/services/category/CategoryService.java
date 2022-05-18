package com.ute.bookstoreonlinebe.services.category;

import com.ute.bookstoreonlinebe.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    Category createNewCategory(String name);

    Category createNewCategory(String name, String...id);

    Category deleteCategory(String id);

    Category addBookToCategory(String categoryId, String...bookID);

    Category removeBookFromCategory(String categoryId, String...bookID);

}
