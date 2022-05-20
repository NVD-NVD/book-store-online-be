package com.ute.bookstoreonlinebe.services.book;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book getBookById(String id);

    Book getBookByName(String name);

    List<Book> getAllBook();

    Page<Book> getBookPaging(String search, int page, int size, String sort, String column);

    List<Book> getAllBookFromCategory(String name);

    Book createNewBook(BookDto dto);

    Book updateBook(BookDto dto);

    Book deleteBook(String id);

    Book addBookToCategory(String bookId, String cateId);


}
