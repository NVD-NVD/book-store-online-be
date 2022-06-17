package com.ute.bookstoreonlinebe.controllers;

import com.ute.bookstoreonlinebe.dtos.book.BookInCart;
import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.services.book.BookService;
import com.ute.bookstoreonlinebe.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController("/rest/cart")
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "User thêm book vào giỏ hàng")
    @PreAuthorize("hasRole('MEMBER')")
    @PutMapping("/{id}")
    public ResponseEntity<User> addBookToCart(
            @PathVariable(value = "id", required = true) String id, Principal principal,
            @RequestBody BookInCart book) {
        return new ResponseEntity<>(userService.addBookToCart(id, principal, book), HttpStatus.OK);
    }

    @ApiOperation(value = "User xóa book vào giỏ hàng")
    @PreAuthorize("hasRole('MEMBER')")
    @DeleteMapping("/{userID}/{bookID}")
    public ResponseEntity<User> deleteBookInCart(
            @PathVariable(value = "userID", required = true) String userID, Principal principal,
            @PathVariable(value = "bookID", required = true) String bookID) {
        return new ResponseEntity<>(userService.deleteBookInCart(userID, principal, bookID), HttpStatus.OK);
    }
}
