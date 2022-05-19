package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findBooksByNameIsLike(String name);

    List<Book> findAllByName();

    @Query
    List<Book> getAllBookByPublishersName(String name);

    @Query(value = "{'publishers.name' : { $regex: ?0, $options: 'i' } }"
            , sort = "{'enable' : -1, 'name' : 1}")
    Page<Book> getBookPaging(String search, Pageable pageable);

}
