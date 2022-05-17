package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 04/20/2022
 * Time: 7:54 PM
 * Filename: UserRepository
 */
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'email' : ?0}")
    Optional<User> getUser(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByIdAndEnableTrue(String id);
}
