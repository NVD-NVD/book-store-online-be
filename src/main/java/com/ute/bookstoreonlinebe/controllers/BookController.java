package com.ute.bookstoreonlinebe.controllers;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.services.book.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @ApiOperation(value = "Create a new book")
    @PostMapping("/create")
    public ResponseEntity<Book> createNewBook(@RequestBody BookDto dto){
        return new ResponseEntity<>(bookService.createNewBook(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a book")
    @PostMapping("/delete/{id}")
    public ResponseEntity<Book> createNewBook(@PathVariable String id){
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all book")
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook( ){
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @ApiOperation(value = "getBookPaging")
    @PostMapping("/getBookPaging")
    public ResponseEntity<Page<Book>> getBookPaging(
        @RequestParam(name = "search", required = false, defaultValue = "") String search,
        @RequestParam(name = "page", required = false, defaultValue = "${paging.default.page}") int page,
        @RequestParam(name = "size", required = false, defaultValue = "${paging.default.size}") int size,
        @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
        @RequestParam(name = "column", required = false, defaultValue = "email") String column) {
            return new ResponseEntity<>(bookService.getBookPaging(search, page, size, sort, column), HttpStatus.OK);
    }
}
