package com.ute.bookstoreonlinebe.services.category;

import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.models.Category;
import com.ute.bookstoreonlinebe.repositories.CategoryRepository;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Book> getBookPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return categoryRepository.getBookPaging(search, pageable);
    }

    @Override
    public Category createNewCategory(String name) {
        return categoryRepository.save(new Category(name));
    }

    @Override
    public Category createNewCategory(String name, String... id) {
        return null;
    }

    @Override
    public Category deleteCategory(String id) {
        return null;
    }

    @Override
    public Category addBookToCategory(String categoryId, String... bookID) {
        return null;
    }

    @Override
    public Category removeBookFromCategory(String categoryId, String... bookID) {
        return null;
    }
}
