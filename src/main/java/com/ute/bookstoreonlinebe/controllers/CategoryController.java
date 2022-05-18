package com.ute.bookstoreonlinebe.controllers;

import com.ute.bookstoreonlinebe.models.Category;
import com.ute.bookstoreonlinebe.services.category.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(name = "/all")
    public ResponseEntity<List<Category>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{name}")
    public ResponseEntity<Category> createNewCategory(@PathVariable String name){
        return new ResponseEntity<>(categoryService.createNewCategory(name), HttpStatus.OK);
    }

    @ApiOperation(value = "Add book to category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/{cateId}/{bookId}")
    public ResponseEntity<Category> addBookToCategory(@PathVariable String cateId){
        return new ResponseEntity<>(categoryService.createNewCategory(cateId), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Category> deleteBookFromCategory(@PathVariable String name){
        return new ResponseEntity<>(categoryService.createNewCategory(name), HttpStatus.OK);
    }
}
