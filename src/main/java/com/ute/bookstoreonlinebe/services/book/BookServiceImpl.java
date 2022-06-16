package com.ute.bookstoreonlinebe.services.book;

import com.ute.bookstoreonlinebe.dtos.book.BookDto;
import com.ute.bookstoreonlinebe.entities.Category;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCategory;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPrice;
import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.entities.Book;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPublishers;
import com.ute.bookstoreonlinebe.exceptions.NotFoundException;
import com.ute.bookstoreonlinebe.repositories.BookRepository;
import com.ute.bookstoreonlinebe.services.category.CategoryService;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryService categoryService;

    @Value("${default.updatingInfoBook}")
    private String defaultInfo;

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Không tìm thấy sách có %id", id))
        );
    }

    @Override
    public Book getBookByName(String name) {
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
    public Book convertDtoToBook(BookDto dto) {
        Book book = new Book();

        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên sách không được bỏ trống");
        }
        book.setName(dto.getName());
        if (!ObjectUtils.isEmpty(dto.getAuthor())) {
            book.setAuthor(dto.getAuthor());
        } else {
            book.setAuthor(defaultInfo);
        }
        if (!ObjectUtils.isEmpty(dto.getDescription())) {
            book.setDescription(dto.getDescription());
        }
        if (!ObjectUtils.isEmpty(dto.getPrice())) {
            book.setPrice(dto.getPrice());
        }
        if (!ObjectUtils.isEmpty(dto.getPublishers())) {
            book.setPublishers(dto.getPublishers());
        } else {
            book.setPublishers(new EmbeddedPublishers(
                    defaultInfo, new Date()
            ));
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
            List<EmbeddedCategory> embeddedCategories = new ArrayList<>();
            embeddedCategories.addAll(dto.getFallIntoCategories());
            book.setFallIntoCategories(embeddedCategories);
        } else {
            book.setFallIntoCategories(null);
        }
        return book;
    }

    @Override
    public Book createNewBook(BookDto dto, MultipartFile files) {
        Book book = convertDtoToBook(dto);
        if (files != null)
        {

        }
        bookRepository.save(book);
        List<EmbeddedCategory> embeddedCategoryList = book.getFallIntoCategories();
        embeddedCategoryList.forEach(
                (e) -> {
                    Category category = new Category();
                    if (e.getId().isEmpty()) {
                        if (categoryService.getCategoryByName(e.getName()) == null) {
                            category = categoryService.save(new Category(null,e.getName(),null,true));
                        }else {
                            category = categoryService.getCategoryByName(e.getName());
                        }
                        e.setId(category.getId());
                    }
                    category = categoryService.getCategoryById(e.getId());
                    List<Book> books = new ArrayList<>();
                    books.addAll(category.getBooksOfCategory());
                    books.add(book);
                    category.setBooksOfCategory(books);
                    categoryService.updateCategory(category);

                });
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(String id, BookDto dto, MultipartFile files) {
        Book book = convertDtoToBook(dto);
        book.setId(id);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book changeStatusBook(String id) {
        Book book = getBookById(id);
        book.setEnable(!book.isEnable());
        return bookRepository.save(book);
    }

    @Override
    public Book addCategoryToBook(String bookId, String cateId) {
        Book book = getBookById(bookId);
        for (EmbeddedCategory embeddedCategory : book.getFallIntoCategories()) {
            if (embeddedCategory.getId().equals(cateId)) {
                throw new InvalidException(String.format("Catagory có %id đã có trong book", cateId));
            }
        }
        Category category = categoryService.getCategoryById(cateId);

        List<EmbeddedCategory> embeddedCategories = new ArrayList<>();
        embeddedCategories.addAll(book.getFallIntoCategories());
        embeddedCategories.add(new EmbeddedCategory(category.getId(), category.getName()));
        book.setFallIntoCategories(embeddedCategories);
        return bookRepository.save(book);
    }

    @Override
    public Book removeCategoryFromBook(String bookId, String cateId) {
        Book book = getBookById(bookId);
        Iterator itr = book.getFallIntoCategories().iterator();
        while (itr.hasNext()) {
            EmbeddedCategory embeddedCategory = (EmbeddedCategory) itr.next();
            if (embeddedCategory.getId().equals(cateId))
                itr.remove();
        }
        return book;
    }


    @Override
    public List<Book> searchBook(String search) {
        return bookRepository.searchBook(search);
    }
}
