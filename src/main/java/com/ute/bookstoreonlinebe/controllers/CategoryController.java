package com.ute.bookstoreonlinebe.controllers;

import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.models.Category;
import com.ute.bookstoreonlinebe.services.category.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Get all category")
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Category Paging")
    @PostMapping("/getCateGoryPaging")
    public ResponseEntity<Page<Book>> getCategoryPaging(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "${paging.default.page}") int page,
            @RequestParam(name = "size", required = false, defaultValue = "${paging.default.size}") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "email") String column) {
        return new ResponseEntity<>(categoryService.getBookPaging(search, page, size, sort, column), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new category (name)")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{name}")
    public ResponseEntity<Category> createNewCategory(@PathVariable String name){
        return new ResponseEntity<>(categoryService.createNewCategory(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable String id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Enable a category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/enable/{id}")
    public ResponseEntity<Category> enableCategory(@PathVariable String id){
        return new ResponseEntity<>(categoryService.enableCategory(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Disable a category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/disable/{id}")
    public ResponseEntity<Category> disableCategory(@PathVariable String id){
        return new ResponseEntity<>(categoryService.disableCategory(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Add book to category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBook/{cateId}/{bookId}")
    public ResponseEntity<Category> addBookToCategory(@PathVariable String cateId
            , @PathVariable String bookId){
        return new ResponseEntity<>(categoryService.createNewCategory(cateId), HttpStatus.OK);
    }

    @ApiOperation(value = "Remove book from category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/removeBook/{cateId}/{BookId}")
    public ResponseEntity<Category> removeBookFromCategory(@PathVariable String cateId, @PathVariable String...bookId){
        return new ResponseEntity<>(categoryService.removeBookFromCategory(cateId, bookId), HttpStatus.OK);
    }

}