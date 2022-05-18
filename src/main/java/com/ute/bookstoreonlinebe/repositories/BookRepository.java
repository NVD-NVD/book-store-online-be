package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
