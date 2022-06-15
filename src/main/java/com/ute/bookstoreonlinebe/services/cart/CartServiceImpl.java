package com.ute.bookstoreonlinebe.services.cart;

import com.ute.bookstoreonlinebe.dtos.card.CartDto;
import com.ute.bookstoreonlinebe.entities.Book;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCardListBook;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCart;
import com.ute.bookstoreonlinebe.services.book.BookService;
import com.ute.bookstoreonlinebe.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Override
    public User addBookToCart(String userID, Principal principal, String bookID, long quantity) {
        User user = userService.checkUserWithIDAndPrincipal(userID, principal);
        Book book = bookService.getBookById(bookID);
        EmbeddedCart embeddedCart = user.getCarts();

        List<EmbeddedCardListBook> embeddedCardListBook = embeddedCart.getListBookInCart();
        embeddedCardListBook.forEach((e) -> {
            if (e.getBook().getId().equals(bookID)){
                throw new IllegalArgumentException("Book đã có trong cart.");
            }
        });
        float total = (quantity * book.getPrice().getPrice()) - (book.getPrice().getPrice() / 100 * book.getDiscount());
        embeddedCardListBook.add(
                new EmbeddedCardListBook(book, quantity, total ));
        embeddedCart.setListBookInCart(embeddedCardListBook);
        user.setCarts(embeddedCart);

        return userService.save(user);
    }

    @Override
    public User addBookToCart(CartDto dto) {
        return null;
    }

    @Override
    public User removeBookFromCard(String userID, Principal principal, String bookID) {
        User user = userService.checkUserWithIDAndPrincipal(userID, principal);
        EmbeddedCart embeddedCart = user.getCarts();

        List<EmbeddedCardListBook> embeddedCardListBook = embeddedCart.getListBookInCart();
        Iterator<EmbeddedCardListBook> it = embeddedCardListBook.iterator();
        while (it.hasNext()){
            EmbeddedCardListBook eBook = it.next();
            it.remove();
        }
        embeddedCart.setListBookInCart(embeddedCardListBook);
        user.setCarts(embeddedCart);

        return userService.save(user);
    }

    @Override
    public User updateQuantityBookInCard(String userID, Principal principal, String bookID, long quantity) {
        User user = userService.checkUserWithIDAndPrincipal(userID, principal);
        Book book = bookService.getBookById(bookID);
        EmbeddedCart embeddedCart = user.getCarts();
        List<EmbeddedCardListBook> embeddedCardListBook = embeddedCart.getListBookInCart();
        embeddedCardListBook.forEach((e) -> {
            if (e.getBook().getId().equals(bookID)){
                e.setQuantity(quantity);
                float total = (quantity * book.getPrice().getPrice()) - (book.getPrice().getPrice() / 100 * book.getDiscount());
                embeddedCardListBook.add(
                        new EmbeddedCardListBook(book, quantity, total ));

            }
        });
        embeddedCart.setListBookInCart(embeddedCardListBook);
        user.setCarts(embeddedCart);

        return userService.save(user);
    }
}
