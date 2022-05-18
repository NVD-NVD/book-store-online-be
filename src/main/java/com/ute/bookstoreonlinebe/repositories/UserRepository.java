package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'email' : ?0}")
    Optional<User> getUser(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndEnableTrue(String id);
}
