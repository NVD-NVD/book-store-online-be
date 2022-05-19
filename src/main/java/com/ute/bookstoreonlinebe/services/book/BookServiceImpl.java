package com.ute.bookstoreonlinebe.services.book;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.models.Book;
import com.ute.bookstoreonlinebe.models.Category;
import com.ute.bookstoreonlinebe.models.embadded.EmbeddedCategory;
import com.ute.bookstoreonlinebe.models.embadded.EmbeddedDescription;
import com.ute.bookstoreonlinebe.models.embadded.EmbeddedPublishers;
import com.ute.bookstoreonlinebe.repositories.BookRepository;
import com.ute.bookstoreonlinebe.repositories.CategoryRepository;
import com.ute.bookstoreonlinebe.utils.EnumLanguage;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${default.updatingInfoBook}")
    private String defaultInfo;

    @Override
    public Book getBook(String id) {
        return null;
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Page<Book> getBookPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return bookRepository.getBookPaging(search, pageable);
    }

    @Override
    public List<Book> getAllBookFromCategory(String name) {
        return null;
    }

    @Override
    public Book createNewBook(BookDto dto) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên sách không được bỏ trống");
        }
        Book book = new Book();

        book.setName(dto.getName());
        if (!ObjectUtils.isEmpty(dto.getAuthor())) {
            book.setAuthor(dto.getAuthor());
        } else {
            book.setAuthor(defaultInfo);
        }
        if (!ObjectUtils.isEmpty(dto.getDescription())) {
            book.setDescription(dto.getDescription());
        } else {
            book.setDescription(null);
        }
        if (!ObjectUtils.isEmpty(dto.getPrice())) {
            book.setPrice(dto.getPrice());
        }
//        else {
//            book.setPrice();
//        }
        if (!ObjectUtils.isEmpty(dto.getPublishers())) {
            book.setPublishers(dto.getPublishers());
        } else {
            book.setPublishers(Collections.singletonList(new EmbeddedPublishers(
                    defaultInfo, null
            )));
        }
        if (!ObjectUtils.isEmpty(dto.getImage_URLs())) {
            book.setImage_URLs(dto.getImage_URLs());
        } else {
            book.setImage_URLs(Collections.singletonList(defaultInfo));
        }
        if (!ObjectUtils.isEmpty(dto.getQuantity())) {
            book.setQuantity(dto.getQuantity());
        } else {
            book.setQuantity(0);
        }
        if (!ObjectUtils.isEmpty(dto.getFallIntoCategories())) {
            book.setFallIntoCategories(dto.getFallIntoCategories());
        } else {
            book.setFallIntoCategories(null);
        }
        bookRepository.save(book);

        return book;
    }

    @Override
    public Book updateBook(BookDto dto) {
        return null;
    }

    @Override
    public Book deleteBook(String id) {
        return null;
    }
}
