package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query(value = "{}")
    Page<Order> getOrderPaging(String search, Pageable pageable);

    @Query(value = "{'user': ?0}")
    Optional<List<Order>> getOrderByUserId(String id);

    @Query(value = "{$week: ?0}")
    List<Order> getOrderByOrderDate(Calendar search);

    @Query(value = "{$and: [{'user': ?0}, {'status' : ?1}, {'shipping': ?2}, {'delivered' : ?3}]}")
    List<Order> getListOrderByUserIdWithIf(String id, boolean status, boolean shipping, boolean delivered);

    @Query(value = "{$and: [{'user': ?0}, {'status' : ?1}]}")
    List<Order> getListOrderByUserIdWithStatusFalse(String id, boolean status);
}
