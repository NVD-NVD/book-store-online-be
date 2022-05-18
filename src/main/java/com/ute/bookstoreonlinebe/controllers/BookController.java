package com.ute.bookstoreonlinebe.controllers;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.services.book.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Create a new book")
    @PostMapping("/create")
    public ResponseEntity<Book> createNewCategory(@RequestBody BookDto dto){
        return new ResponseEntity<>(bookService.createNewBook(dto), HttpStatus.OK);
    }
}
