package com.ute.bookstoreonlinebe.services.cart;

import com.ute.bookstoreonlinebe.dtos.card.CartDto;
import com.ute.bookstoreonlinebe.entities.User;

import java.security.Principal;

public interface CartService {
    User addBookToCart(String userID, Principal principal, String bookID, long quantity);

    User addBookToCart(CartDto dto);

    User removeBookFromCard(String userID, Principal principal, String bookID);

    User updateQuantityBookInCard(String userID, Principal principal, String bookID, long quantity);
}
