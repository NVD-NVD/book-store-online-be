package com.ute.bookstoreonlinebe.services.category;

import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.models.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Category getCategoryByName(String name);

    List<Category> getAllCategory();

    Page<Book> getBookPaging(String search, int page, int size, String sort, String column);

    Category createNewCategory(String name);

    Category deleteCategory(String id);

    Category createNewCategory(String name, String...id);

    Category addBookToCategory(String categoryId, String...bookID);

    Category removeBookFromCategory(String categoryId, String...bookID);

    Category enableCategory(String id);

    Category disableCategory(String id);
}
