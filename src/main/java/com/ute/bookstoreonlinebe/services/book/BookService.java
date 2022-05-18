package com.ute.bookstoreonlinebe.services.book;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.models.Book;

import java.util.List;

public interface BookService {
    Book getBook(String id);

    List<Book> getAllBook();

    List<Book> getAllBookFromCategory(String name);

    Book createNewBook(BookDto dto);

    Book updateBook(BookDto dto);

    Book deleteBook(String id);


}
