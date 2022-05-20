package com.ute.bookstoreonlinebe.services.category;

import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.exceptions.NotFoundException;
import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.models.Category;
import com.ute.bookstoreonlinebe.repositories.CategoryRepository;
import com.ute.bookstoreonlinebe.services.book.BookService;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookService bookService;


    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

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
        Category category = getCategoryByName(name);
        if(!ObjectUtils.isEmpty(category)){
            throw new InvalidException(String.format("Category có name %s không tồn tại", name));
        }
        return categoryRepository.save(new Category(name));
    }

    @Override
    public Category deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category có id %s không tồn tại", id)));
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category createNewCategory(String name, String... bookId) {
        Category category = createNewCategory(name);
        if(!ObjectUtils.isEmpty(category)){
            throw new InvalidException(String.format("Category có name %s không tồn tại", name));
        }
        List<Book> books = new ArrayList<>();
        for(String id : bookId){
            Book book = bookService.getBook(id);
            if(!ObjectUtils.isEmpty(book)){
                books.add(book);
            }
        }
        category.setBooksOfCategory(books);
        categoryRepository.save(category);
        return category;
    }


    @Override
    public Category addBookToCategory(String categoryId, String... bookID) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(String.format("Category có id %s không tồn tại", categoryId)));
        List<Book> books = new ArrayList<>();
        for(String id : bookID){
            Book book = bookService.getBook(id);
            if(!ObjectUtils.isEmpty(book)){
                books.add(book);
            }
        }
        category.setBooksOfCategory(books);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category removeBookFromCategory(String categoryId, String... bookID) {
        return null;
    }

    @Override
    public Category enableCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category có id %s không tồn tại", id)));
        category.setEnable(true);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category disableCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category có id %s không tồn tại", id)));
        category.setEnable(false);
        categoryRepository.save(category);
        return category;
    }
}
